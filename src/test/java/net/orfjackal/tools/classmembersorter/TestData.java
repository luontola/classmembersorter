package net.orfjackal.tools.classmembersorter;

import java.lang.reflect.Method;

/**
 * @author Esko Luontola
 * @since 4.1.2008
 */
public class TestData {

    public static final Class<?> CLASS_ONE = FirstClass.class;
    public static final Class<?> CLASS_TWO = SecondClass.class;
    public static final Class<?> METHOD_CLASS = MethodClass.class;
    public static final Method METHOD_ONE = getMethod(METHOD_CLASS, "methodOne");
    public static final Method METHOD_TWO = getMethod(METHOD_CLASS, "methodTwo");

    public static class FirstClass {
    }

    public static class SecondClass {
    }

    public static class MethodClass {

        public void methodOne() {
        }

        public void methodTwo() {
        }
    }

    private static Method getMethod(Class<?> clazz, String method) {
        try {
            return clazz.getMethod(method);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }
}
