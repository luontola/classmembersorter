package net.orfjackal.tools.classmembersorter;

import java.lang.reflect.Method;

/**
 * @author Esko Luontola
 * @since 4.1.2008
 */
public class TestData {

    public static final Class<?> CLASS_ONE = FirstClass.class;
    public static final Class<?> CLASS_TWO = SecondClass.class;
    public static final Class<?> CLASS_THREE = ChildClass.class;
    public static final Class<?> CLASS_FOUR = SuperParentClass.class;
    public static final Class<?> CLASS_FIVE = ParentClass.class;

    public static final Class<?> CLASS_CHILD = ChildClass.class;
    public static final Class<?> CLASS_UNRELATED = ChildClass.UnrelatedClass.class;
    public static final Method METHOD_ONE = getMethod(CLASS_CHILD, "methodOne");
    public static final Method METHOD_TWO = getMethod(CLASS_CHILD, "methodTwo");
    public static final Method METHOD_PARENT = getMethod(ParentClass.class, "methodParent");
    public static final Method METHOD_SUPER_PARENT = getMethod(SuperParentClass.class, "methodSuperParent");
    public static final Method METHOD_UNRELATED = getMethod(CLASS_UNRELATED, "unrelated");

    public class FirstClass {
    }

    public class SecondClass {
    }

    public class ChildClass extends ParentClass {
        public void methodOne() {
        }

        public void methodTwo() {
        }

        public class UnrelatedClass {
            public void unrelated() {
            }
        }
    }

    public class SuperParentClass {
        public void methodSuperParent() {
        }
    }

    public class ParentClass extends SuperParentClass {
        public void methodParent() {
        }
    }

    private static Method getMethod(Class<?> clazz, String method) {
        try {
            return clazz.getDeclaredMethod(method);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }
}