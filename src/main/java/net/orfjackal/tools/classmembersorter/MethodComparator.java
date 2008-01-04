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
public class MethodComparator implements Comparator<java.lang.reflect.Method> {

    private Repository repository;

    public MethodComparator() {
        repository = new ClassLoaderRepository(getClass().getClassLoader());
    }

    public int compare(java.lang.reflect.Method o1, java.lang.reflect.Method o2) {
        int line1 = firstLineNumber(o1);
        int line2 = firstLineNumber(o2);
        return line1 - line2;
    }

    private int firstLineNumber(java.lang.reflect.Method method) {
        try {
            JavaClass javaClass = repository.loadClass(method.getDeclaringClass());
            Method methodInfo = javaClass.getMethod(method);
            LineNumberTable table = methodInfo.getLineNumberTable();
            return table.getSourceLine(0);

        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Unable to load class of method: " + method, e);
        }
    }
}
