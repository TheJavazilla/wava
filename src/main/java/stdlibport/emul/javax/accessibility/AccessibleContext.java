package javax.accessibility;

import wava.sun.awt.AWTAccessor;
import wava.sun.awt.AppContext;

import java.util.Locale;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeEvent;
import java.awt.IllegalComponentStateException;

/**
 * AccessibleContext represents the minimum information all accessible objects
 * return.  This information includes the accessible name, description, role,
 * and state of the object, as well as information about its parent and
 * children.  AccessibleContext also contains methods for
 * obtaining more specific accessibility information about a component.
 * If the component supports them, these methods will return an object that
 * implements one or more of the following interfaces:
 * <P><ul>
 * <li>{@link AccessibleAction} - the object can perform one or more actions.
 * This interface provides the standard mechanism for an assistive
 * technology to determine what those actions are and tell the object
 * to perform them.  Any object that can be manipulated should
 * support this interface.
 * <li>{@link AccessibleComponent} - the object has a graphical representation.
 * This interface provides the standard mechanism for an assistive
 * technology to determine and set the graphical representation of the
 * object.  Any object that is rendered on the screen should support
 * this interface.
 * <li>{@link  AccessibleSelection} - the object allows its children to be
 * selected.  This interface provides the standard mechanism for an
 * assistive technology to determine the currently selected children of the object
 * as well as modify its selection set.  Any object that has children
 * that can be selected should support this interface.
 * <li>{@link AccessibleText} - the object presents editable textual information
 * on the display.  This interface provides the standard mechanism for
 * an assistive technology to access that text via its content, attributes,
 * and spatial location.  Any object that contains editable text should
 * support this interface.
 * <li>{@link AccessibleValue} - the object supports a numerical value.  This
 * interface provides the standard mechanism for an assistive technology
 * to determine and set the current value of the object, as well as obtain its
 * minimum and maximum values.  Any object that supports a numerical value
 * should support this interface.</ul>
 *
 *
 * @beaninfo
 *   attribute: isContainer false
 * description: Minimal information that all accessible objects return
 *
 * @author      Peter Korn
 * @author      Hans Muller
 * @author      Willie Walker
 * @author      Lynn Monsanto
 */
public abstract class AccessibleContext {

    /**
     * The AppContext that should be used to dispatch events for this
     * AccessibleContext
     */
    private volatile AppContext targetAppContext;

    static {
        AWTAccessor.setAccessibleContextAccessor(new AWTAccessor.AccessibleContextAccessor() {
            @Override
            public void setAppContext(AccessibleContext accessibleContext, AppContext appContext) {
                accessibleContext.targetAppContext = appContext;
            }

            @Override
            public AppContext getAppContext(AccessibleContext accessibleContext) {
                return accessibleContext.targetAppContext;
            }
        });
    }

    public static final String ACCESSIBLE_NAME_PROPERTY = "AccessibleName";
    public static final String ACCESSIBLE_DESCRIPTION_PROPERTY = "AccessibleDescription";
    public static final String ACCESSIBLE_STATE_PROPERTY = "AccessibleState";
    public static final String ACCESSIBLE_VALUE_PROPERTY = "AccessibleValue";
    public static final String ACCESSIBLE_SELECTION_PROPERTY = "AccessibleSelection";
    public static final String ACCESSIBLE_CARET_PROPERTY = "AccessibleCaret";
    public static final String ACCESSIBLE_VISIBLE_DATA_PROPERTY = "AccessibleVisibleData";
    public static final String ACCESSIBLE_CHILD_PROPERTY = "AccessibleChild";
    public static final String ACCESSIBLE_ACTIVE_DESCENDANT_PROPERTY = "AccessibleActiveDescendant";
    public static final String ACCESSIBLE_TABLE_CAPTION_CHANGED = "accessibleTableCaptionChanged";
    public static final String ACCESSIBLE_TABLE_SUMMARY_CHANGED = "accessibleTableSummaryChanged";
    public static final String ACCESSIBLE_TABLE_MODEL_CHANGED = "accessibleTableModelChanged";
    public static final String ACCESSIBLE_TABLE_ROW_HEADER_CHANGED = "accessibleTableRowHeaderChanged";
    public static final String ACCESSIBLE_TABLE_ROW_DESCRIPTION_CHANGED = "accessibleTableRowDescriptionChanged";
    public static final String ACCESSIBLE_TABLE_COLUMN_HEADER_CHANGED = "accessibleTableColumnHeaderChanged";
    public static final String ACCESSIBLE_TABLE_COLUMN_DESCRIPTION_CHANGED = "accessibleTableColumnDescriptionChanged";
    public static final String ACCESSIBLE_ACTION_PROPERTY = "accessibleActionProperty";
    public static final String ACCESSIBLE_HYPERTEXT_OFFSET = "AccessibleHypertextOffset";
    public static final String ACCESSIBLE_TEXT_PROPERTY = "AccessibleText";
    public static final String ACCESSIBLE_INVALIDATE_CHILDREN = "accessibleInvalidateChildren";
    public static final String ACCESSIBLE_TEXT_ATTRIBUTES_CHANGED = "accessibleTextAttributesChanged";
    public static final String ACCESSIBLE_COMPONENT_BOUNDS_CHANGED = "accessibleComponentBoundsChanged";

    protected Accessible accessibleParent = null;

    protected String accessibleName = null;

    protected String accessibleDescription = null;

    private PropertyChangeSupport accessibleChangeSupport = null;

    private AccessibleRelationSet relationSet = new AccessibleRelationSet();

    private Object nativeAXResource;

    public String getAccessibleName() {
        return accessibleName;
    }

    public void setAccessibleName(String s) {
        String oldName = accessibleName;
        accessibleName = s;
        firePropertyChange(ACCESSIBLE_NAME_PROPERTY,oldName,accessibleName);
    }

    public String getAccessibleDescription() {
        return accessibleDescription;
    }

    public void setAccessibleDescription(String s) {
        String oldDescription = accessibleDescription;
        accessibleDescription = s;
        firePropertyChange(ACCESSIBLE_DESCRIPTION_PROPERTY, oldDescription,accessibleDescription);
    }

    public abstract AccessibleRole getAccessibleRole();

    public abstract AccessibleStateSet getAccessibleStateSet();

    public Accessible getAccessibleParent() {
        return accessibleParent;
    }

    public void setAccessibleParent(Accessible a) {
        accessibleParent = a;
    }

    public abstract int getAccessibleIndexInParent();

    public abstract int getAccessibleChildrenCount();

    public abstract Accessible getAccessibleChild(int i);

    public abstract Locale getLocale() throws IllegalComponentStateException;

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        if (accessibleChangeSupport == null)
            accessibleChangeSupport = new PropertyChangeSupport(this);
        accessibleChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        if (accessibleChangeSupport != null)
            accessibleChangeSupport.removePropertyChangeListener(listener);
    }

    public AccessibleAction getAccessibleAction() {
        return null;
    }

    public AccessibleComponent getAccessibleComponent() {
        return null;
    }

    public AccessibleSelection getAccessibleSelection() {
        return null;
    }

    public AccessibleText getAccessibleText() {
        return null;
    }

    public AccessibleEditableText getAccessibleEditableText() {
        return null;
    }

    public AccessibleValue getAccessibleValue() {
        return null;
    }

    public AccessibleIcon [] getAccessibleIcon() {
        return null;
    }

    public AccessibleRelationSet getAccessibleRelationSet() {
        return relationSet;
    }

    public AccessibleTable getAccessibleTable() {
        return null;
    }

    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        if (accessibleChangeSupport != null) {
            if (newValue instanceof PropertyChangeEvent) {
                PropertyChangeEvent pce = (PropertyChangeEvent)newValue;
                accessibleChangeSupport.firePropertyChange(pce);
            } else
                accessibleChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
        }
    }

}