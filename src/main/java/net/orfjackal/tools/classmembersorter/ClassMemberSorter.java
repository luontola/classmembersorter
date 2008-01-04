package net.orfjackal.tools.classmembersorter;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Esko Luontola
 * @since 4.1.2008
 */
public class ClassMemberSorter {

    public static Class<?>[] getDeclaredClasses(Class<?> declaringClass) {
        Class<?>[] classes = declaringClass.getDeclaredClasses();
        Arrays.sort(classes, new InnerClassComparator());
        return classes;
    }

    public static Method[] getDeclaredMethods(Class<?> declaringClass) {
        Method[] methods = declaringClass.getDeclaredMethods();
        Arrays.sort(methods, new MethodComparator());
        return methods;
    }

    public static Method[] getMethods(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        Arrays.sort(methods, new MethodComparator());
        return methods;
    }
}
