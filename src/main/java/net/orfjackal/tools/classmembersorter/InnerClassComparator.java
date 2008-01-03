package net.orfjackal.tools.classmembersorter;

import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.util.ClassLoaderRepository;
import org.apache.bcel.util.Repository;

import java.util.Comparator;

/**
 * @author Esko Luontola
 * @since 4.1.2008
 */
public class InnerClassComparator implements Comparator<java.lang.Class<?>> {

    private Repository repository;

    public InnerClassComparator() {
        repository = new ClassLoaderRepository(MethodComparator.class.getClassLoader());
    }

    public int compare(java.lang.Class<?> o1, java.lang.Class<?> o2) {
        try {
            JavaClass c1 = repository.loadClass(o1);
            JavaClass c2 = repository.loadClass(o2);
            int line1 = firstLineNumber(c1);
            int line2 = firstLineNumber(c2);
            return line1 - line2;

        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static int firstLineNumber(JavaClass javaClass) {
        int min = Integer.MAX_VALUE;
        for (Method method : javaClass.getMethods()) {
            int line = method.getLineNumberTable().getSourceLine(0);
            min = Math.min(min, line);
        }
        return min;
    }
}
