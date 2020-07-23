package java.beans;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.lang.reflect.Method;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;

import wava.sun.reflect.misc.MethodUtil;
import wava.sun.reflect.misc.ReflectUtil;

public class EventHandler implements InvocationHandler {

    private Object target;
    private String action;
    private final String eventPropertyName;
    private final String listenerMethodName;
    private final AccessControlContext acc = AccessController.getContext();

    @ConstructorProperties({"target", "action", "eventPropertyName", "listenerMethodName"})
    public EventHandler(Object target, String action, String eventPropertyName, String listenerMethodName) {
        this.target = target;
        this.action = action;

        if (target == null) throw new NullPointerException("target must be non-null");
        if (action == null) throw new NullPointerException("action must be non-null");

        this.eventPropertyName = eventPropertyName;
        this.listenerMethodName = listenerMethodName;
    }

    /**
     * Returns the object to which this event handler will send a message.
     *
     * @return the target of this event handler
     * @see #EventHandler(Object, String, String, String)
     */
    public Object getTarget()  {
        return target;
    }

    /**
     * Returns the name of the target's writable property
     * that this event handler will set,
     * or the name of the method that this event handler
     * will invoke on the target.
     *
     * @return the action of this event handler
     * @see #EventHandler(Object, String, String, String)
     */
    public String getAction()  {
        return action;
    }

    /**
     * Returns the property of the event that should be
     * used in the action applied to the target.
     *
     * @return the property of the event
     *
     * @see #EventHandler(Object, String, String, String)
     */
    public String getEventPropertyName()  {
        return eventPropertyName;
    }

    /**
     * Returns the name of the method that will trigger the action.
     * A return value of <code>null</code> signifies that all methods in the
     * listener interface trigger the action.
     *
     * @return the name of the method that will trigger the action
     *
     * @see #EventHandler(Object, String, String, String)
     */
    public String getListenerMethodName()  {
        return listenerMethodName;
    }

    private Object applyGetters(Object target, String getters) {
        if (getters == null || getters.equals("")) {
            return target;
        }
        int firstDot = getters.indexOf('.');
        if (firstDot == -1) {
            firstDot = getters.length();
        }
        String first = getters.substring(0, firstDot);
        String rest = getters.substring(Math.min(firstDot + 1, getters.length()));

        try {
            Method getter = null;
            if (target != null) {
                getter = Statement.getMethod(target.getClass(),
                                      "get" + NameGenerator.capitalize(first),
                                      new Class<?>[]{});
                if (getter == null) {
                    getter = Statement.getMethod(target.getClass(),
                                   "is" + NameGenerator.capitalize(first),
                                   new Class<?>[]{});
                }
                if (getter == null) {
                    getter = Statement.getMethod(target.getClass(), first, new Class<?>[]{});
                }
            }
            if (getter == null) {
                throw new RuntimeException("No method called: " + first +
                                           " defined on " + target);
            }
            Object newTarget = MethodUtil.invoke(getter, target, new Object[]{});
            return applyGetters(newTarget, rest);
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to call method: " + first +
                                       " on " + target, e);
        }
    }

    /**
     * Extract the appropriate property value from the event and
     * pass it to the action associated with
     * this <code>EventHandler</code>.
     *
     * @param proxy the proxy object
     * @param method the method in the listener interface
     * @return the result of applying the action to the target
     *
     * @see EventHandler
     */
    public Object invoke(final Object proxy, final Method method, final Object[] arguments) {
        AccessControlContext acc = this.acc;
        if (acc == null)
            throw new SecurityException("AccessControlContext is not set");

        return AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                return invokeInternal(proxy, method, arguments);
            }
        }, acc);
    }

    private Object invokeInternal(Object proxy, Method method, Object[] arguments) {
        String methodName = method.getName();
        if (method.getDeclaringClass() == Object.class)  {
            // Handle the Object public methods.
            if (methodName.equals("hashCode"))  {
                return new Integer(System.identityHashCode(proxy));
            } else if (methodName.equals("equals")) {
                return (proxy == arguments[0] ? Boolean.TRUE : Boolean.FALSE);
            } else if (methodName.equals("toString")) {
                return proxy.getClass().getName() + '@' + Integer.toHexString(proxy.hashCode());
            }
        }

        if (listenerMethodName == null || listenerMethodName.equals(methodName)) {
            Class[] argTypes = null;
            Object[] newArgs = null;

            if (eventPropertyName == null) {     // Nullary method.
                newArgs = new Object[]{};
                argTypes = new Class<?>[]{};
            } else {
                Object input = applyGetters(arguments[0], getEventPropertyName());
                newArgs = new Object[]{input};
                argTypes = new Class<?>[]{input == null ? null :
                                       input.getClass()};
            }
            try {
                int lastDot = action.lastIndexOf('.');
                if (lastDot != -1) {
                    target = applyGetters(target, action.substring(0, lastDot));
                    action = action.substring(lastDot + 1);
                }
                Method targetMethod = Statement.getMethod(
                             target.getClass(), action, argTypes);
                if (targetMethod == null) {
                    targetMethod = Statement.getMethod(target.getClass(),
                             "set" + NameGenerator.capitalize(action), argTypes);
                }
                if (targetMethod == null) {
                    String argTypeString = (argTypes.length == 0)
                        ? " with no arguments"
                        : " with argument " + argTypes[0];
                    throw new RuntimeException(
                        "No method called " + action + " on " +
                        target.getClass() + argTypeString);
                }
                return MethodUtil.invoke(targetMethod, target, newArgs);
            }
            catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
            catch (InvocationTargetException ex) {
                Throwable th = ex.getTargetException();
                throw (th instanceof RuntimeException)
                        ? (RuntimeException) th
                        : new RuntimeException(th);
            }
        }
        return null;
    }

    /**
     * Creates an implementation of <code>listenerInterface</code> in which
     * <em>all</em> of the methods in the listener interface apply
     * the handler's <code>action</code> to the <code>target</code>. This
     * method is implemented by calling the other, more general,
     * implementation of the <code>create</code> method with both
     * the <code>eventPropertyName</code> and the <code>listenerMethodName</code>
     * taking the value <code>null</code>. Refer to
     * {@link java.beans.EventHandler#create(Class, Object, String, String)
     * the general version of create} for a complete description of
     * the <code>action</code> parameter.
     * <p>
     * To create an <code>ActionListener</code> that shows a
     * <code>JDialog</code> with <code>dialog.show()</code>,
     * one can write:
     *
     *<blockquote>
     *<pre>
     *EventHandler.create(ActionListener.class, dialog, "show")
     *</pre>
     *</blockquote>
     *
     * @param <T> the type to create
     * @param listenerInterface the listener interface to create a proxy for
     * @param target the object that will perform the action
     * @param action the name of a (possibly qualified) property or method on
     *        the target
     * @return an object that implements <code>listenerInterface</code>
     *
     * @throws NullPointerException if <code>listenerInterface</code> is null
     * @throws NullPointerException if <code>target</code> is null
     * @throws NullPointerException if <code>action</code> is null
     *
     * @see #create(Class, Object, String, String)
     */
    public static <T> T create(Class<T> listenerInterface,
                               Object target, String action)
    {
        return create(listenerInterface, target, action, null, null);
    }

    /**
    /**
     * Creates an implementation of <code>listenerInterface</code> in which
     * <em>all</em> of the methods pass the value of the event
     * expression, <code>eventPropertyName</code>, to the final method in the
     * statement, <code>action</code>, which is applied to the <code>target</code>.
     * This method is implemented by calling the
     * more general, implementation of the <code>create</code> method with
     * the <code>listenerMethodName</code> taking the value <code>null</code>.
     * Refer to
     * {@link java.beans.EventHandler#create(Class, Object, String, String)
     * the general version of create} for a complete description of
     * the <code>action</code> and <code>eventPropertyName</code> parameters.
     * <p>
     * To create an <code>ActionListener</code> that sets the
     * the text of a <code>JLabel</code> to the text value of
     * the <code>JTextField</code> source of the incoming event,
     * you can use the following code:
     *
     *<blockquote>
     *<pre>
     *EventHandler.create(ActionListener.class, label, "text", "source.text");
     *</pre>
     *</blockquote>
     *
     * This is equivalent to the following code:
     *<blockquote>
     *<pre>
//Equivalent code using an inner class instead of EventHandler.
     *new ActionListener() {
     *    public void actionPerformed(ActionEvent event) {
     *        label.setText(((JTextField)(event.getSource())).getText());
     *     }
     *};
     *</pre>
     *</blockquote>
     *
     * @param <T> the type to create
     * @param listenerInterface the listener interface to create a proxy for
     * @param target the object that will perform the action
     * @param action the name of a (possibly qualified) property or method on
     *        the target
     * @param eventPropertyName the (possibly qualified) name of a readable property of the incoming event
     *
     * @return an object that implements <code>listenerInterface</code>
     *
     * @throws NullPointerException if <code>listenerInterface</code> is null
     * @throws NullPointerException if <code>target</code> is null
     * @throws NullPointerException if <code>action</code> is null
     *
     * @see #create(Class, Object, String, String, String)
     */
    public static <T> T create(Class<T> listenerInterface,
                               Object target, String action,
                               String eventPropertyName)
    {
        return create(listenerInterface, target, action, eventPropertyName, null);
    }

    /**
     * Creates an implementation of <code>listenerInterface</code> in which
     * the method named <code>listenerMethodName</code>
     * passes the value of the event expression, <code>eventPropertyName</code>,
     * to the final method in the statement, <code>action</code>, which
     * is applied to the <code>target</code>. All of the other listener
     * methods do nothing.
     * <p>
     * The <code>eventPropertyName</code> string is used to extract a value
     * from the incoming event object that is passed to the target
     * method.  The common case is the target method takes no arguments, in
     * which case a value of null should be used for the
     * <code>eventPropertyName</code>.  Alternatively if you want
     * the incoming event object passed directly to the target method use
     * the empty string.
     * The format of the <code>eventPropertyName</code> string is a sequence of
     * methods or properties where each method or
     * property is applied to the value returned by the preceding method
     * starting from the incoming event object.
     * The syntax is: <code>propertyName{.propertyName}*</code>
     * where <code>propertyName</code> matches a method or
     * property.  For example, to extract the <code>point</code>
     * property from a <code>MouseEvent</code>, you could use either
     * <code>"point"</code> or <code>"getPoint"</code> as the
     * <code>eventPropertyName</code>.  To extract the "text" property from
     * a <code>MouseEvent</code> with a <code>JLabel</code> source use any
     * of the following as <code>eventPropertyName</code>:
     * <code>"source.text"</code>,
     * <code>"getSource.text"</code> <code>"getSource.getText"</code> or
     * <code>"source.getText"</code>.  If a method can not be found, or an
     * exception is generated as part of invoking a method a
     * <code>RuntimeException</code> will be thrown at dispatch time.  For
     * example, if the incoming event object is null, and
     * <code>eventPropertyName</code> is non-null and not empty, a
     * <code>RuntimeException</code> will be thrown.
     * <p>
     * The <code>action</code> argument is of the same format as the
     * <code>eventPropertyName</code> argument where the last property name
     * identifies either a method name or writable property.
     * <p>
     * If the <code>listenerMethodName</code> is <code>null</code>
     * <em>all</em> methods in the interface trigger the <code>action</code> to be
     * executed on the <code>target</code>.
     * <p>
     * For example, to create a <code>MouseListener</code> that sets the target
     * object's <code>origin</code> property to the incoming <code>MouseEvent</code>'s
     * location (that's the value of <code>mouseEvent.getPoint()</code>) each
     * time a mouse button is pressed, one would write:
     *<blockquote>
     *<pre>
     *EventHandler.create(MouseListener.class, target, "origin", "point", "mousePressed");
     *</pre>
     *</blockquote>
     *
     * This is comparable to writing a <code>MouseListener</code> in which all
     * of the methods except <code>mousePressed</code> are no-ops:
     *
     *<blockquote>
     *<pre>
//Equivalent code using an inner class instead of EventHandler.
     *new MouseAdapter() {
     *    public void mousePressed(MouseEvent e) {
     *        target.setOrigin(e.getPoint());
     *    }
     *};
     * </pre>
     *</blockquote>
     *
     * @param <T> the type to create
     * @param listenerInterface the listener interface to create a proxy for
     * @param target the object that will perform the action
     * @param action the name of a (possibly qualified) property or method on
     *        the target
     * @param eventPropertyName the (possibly qualified) name of a readable property of the incoming event
     * @param listenerMethodName the name of the method in the listener interface that should trigger the action
     *
     * @return an object that implements <code>listenerInterface</code>
     *
     * @throws NullPointerException if <code>listenerInterface</code> is null
     * @throws NullPointerException if <code>target</code> is null
     * @throws NullPointerException if <code>action</code> is null
     *
     * @see EventHandler
     */
    public static <T> T create(Class<T> listenerInterface,
                               Object target, String action,
                               String eventPropertyName,
                               String listenerMethodName)
    {
        // Create this first to verify target/action are non-null
        final EventHandler handler = new EventHandler(target, action,
                                                     eventPropertyName,
                                                     listenerMethodName);
        if (listenerInterface == null) {
            throw new NullPointerException(
                          "listenerInterface must be non-null");
        }
        final ClassLoader loader = getClassLoader(listenerInterface);
        final Class<?>[] interfaces = {listenerInterface};
        return AccessController.doPrivileged(new PrivilegedAction<T>() {
            @SuppressWarnings("unchecked")
            public T run() {
                return (T) Proxy.newProxyInstance(loader, interfaces, handler);
            }
        });
    }

    private static ClassLoader getClassLoader(Class<?> type) {
        ReflectUtil.checkPackageAccess(type);
        ClassLoader loader = type.getClassLoader();
        if (loader == null) {
            loader = Thread.currentThread().getContextClassLoader(); // avoid use of BCP
            if (loader == null) {
                loader = ClassLoader.getSystemClassLoader();
            }
        }
        return loader;
    }
}
