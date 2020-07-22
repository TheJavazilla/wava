package com.sun.beans.finder;

public final class ClassFinder {

    public static Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            if (loader == null)
                loader = ClassLoader.getSystemClassLoader(); // can be null in IE (see 6204697)
            if (loader != null)
                return Class.forName(name, false, loader);

        } catch (ClassNotFoundException exception) {
            // use current class loader instead
        } catch (SecurityException exception) {
            // use current class loader instead
        }
        return Class.forName(name);
    }

    public static Class<?> findClass(String name, ClassLoader loader) throws ClassNotFoundException {
        if (loader != null) {
            try {
                return Class.forName(name, false, loader);
            } catch (ClassNotFoundException exception) {
                // use default class loader instead
            } catch (SecurityException exception) {
                // use default class loader instead
            }
        }
        return findClass(name);
    }

    public static Class<?> resolveClass(String name) throws ClassNotFoundException {
        return resolveClass(name, null);
    }

    public static Class<?> resolveClass(String name, ClassLoader loader) throws ClassNotFoundException {
        Class<?> type = PrimitiveTypeMap.getType(name);
        return (type == null) ? findClass(name, loader) : type;
    }

    /**
     * Disable instantiation.
     */
    private ClassFinder() {
    }

}