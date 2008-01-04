package net.orfjackal.tools.classmembersorter;

import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.LineNumberTable;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.util.ClassLoaderRepository;
import org.apache.bcel.util.Repository;

import java.util.Comparator;

/**
 * @author Esko Luontola
 * @since 4.1.2008
 */
public class MethodLineNumberComparator implements Comparator<java.lang.reflect.Method> {

    private Repository repository;

    public MethodLineNumberComparator() {
        repository = new ClassLoaderRepository(getClass().getClassLoader());
    }

    public int compare(java.lang.reflect.Method o1, java.lang.reflect.Method o2) {
        if (sameClass(o1, o2)) {
            int line1 = firstLineNumber(o1);
            int line2 = firstLineNumber(o2);
            return line1 - line2;
        } else if (parentClass(o1, o2)) {
            return 1;
        } else if (parentClass(o2, o1)) {
            return -1;
        } else {
            return alphabeticalOrder(o1, o2);
        }
    }

    private int firstLineNumber(java.lang.reflect.Method method) {
        try {
            JavaClass javaClass = repository.loadClass(method.getDeclaringClass());
            Method methodInfo = javaClass.getMethod(method);
            LineNumberTable table = methodInfo.getLineNumberTable();
            return (table != null) ? table.getSourceLine(0) : 0;

        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Unable to load class of method: " + method, e);
        }
    }

    private static boolean sameClass(java.lang.reflect.Method o1, java.lang.reflect.Method o2) {
        return o1.getDeclaringClass().equals(o2.getDeclaringClass());
    }

    private static boolean parentClass(java.lang.reflect.Method parent, java.lang.reflect.Method child) {
        return parent.getDeclaringClass().isAssignableFrom(child.getDeclaringClass());
    }

    private static int alphabeticalOrder(java.lang.reflect.Method o1, java.lang.reflect.Method o2) {
        return o1.getDeclaringClass().getName().compareTo(o2.getDeclaringClass().getName());
    }
}
