package net.orfjackal.tools.classmembersorter;

import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.util.ClassLoaderRepository;
import org.apache.bcel.util.Repository;

import java.lang.reflect.Method;
import java.util.Comparator;

/**
 * Sorts methods according to the order in which they have been declared in the source code.
 *
 * @author Esko Luontola
 * @since 4.1.2008
 */
public class MethodLineNumberComparator implements Comparator<Method> {

    private Repository repository;

    public MethodLineNumberComparator() {
        repository = new ClassLoaderRepository(getClass().getClassLoader());
    }

    public int compare(Method o1, Method o2) {
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

    private int firstLineNumber(Method method) {
        return BcelUtils.firstLineNumber(toBcel(method), 0);
    }

    private org.apache.bcel.classfile.Method toBcel(Method method) {
        try {
            JavaClass javaClass = repository.loadClass(method.getDeclaringClass());
            return javaClass.getMethod(method);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Unable to load class of method: " + method, e);
        }
    }

    private static boolean sameClass(Method o1, Method o2) {
        return o1.getDeclaringClass().equals(o2.getDeclaringClass());
    }

    private static boolean parentClass(Method parent, Method child) {
        return parent.getDeclaringClass().isAssignableFrom(child.getDeclaringClass());
    }

    private static int alphabeticalOrder(Method o1, Method o2) {
        return o1.getDeclaringClass().getName().compareTo(o2.getDeclaringClass().getName());
    }
}
