package net.orfjackal.tools.classmembersorter;

import java.lang.reflect.Method;

/**
 * @author Esko Luontola
 * @since 4.1.2008
 */
public class TestData {

    public static final Class<?> CLASS_ONE = FirstClass.class;
    public static final Class<?> CLASS_TWO = SecondClass.class;
    public static final Class<?> CLASS_THREE = MethodClass.class;
    public static final Class<?> CLASS_FOUR = SuperParentMethodClass.class;
    public static final Class<?> CLASS_FIVE = ParentMethodClass.class;

    public static final Class<?> CLASS_CHILD = MethodClass.class;
    public static final Method METHOD_ONE = getMethod(CLASS_THREE, "methodOne");
    public static final Method METHOD_TWO = getMethod(CLASS_THREE, "methodTwo");
    public static final Method METHOD_PARENT = getMethod(CLASS_THREE, "methodParent");
    public static final Method METHOD_SUPER_PARENT = getMethod(CLASS_THREE, "methodSuperParent");

    public class FirstClass {
    }

    public class SecondClass {
    }

    public class MethodClass extends ParentMethodClass {

        public void methodOne() {
        }

        public void methodTwo() {
        }
    }

    public class SuperParentMethodClass {

        public void methodSuperParent() {

        }
    }

    public class ParentMethodClass extends SuperParentMethodClass {

        public void methodParent() {

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
