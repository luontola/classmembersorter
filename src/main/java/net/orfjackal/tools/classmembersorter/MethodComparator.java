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
        repository = new ClassLoaderRepository(MethodComparator.class.getClassLoader());
    }

    public int compare(java.lang.reflect.Method o1, java.lang.reflect.Method o2) {
        try {
            JavaClass javaClass = repository.loadClass(o1.getDeclaringClass());
            int line1 = firstLineNumber(o1, javaClass);
            int line2 = firstLineNumber(o2, javaClass);
            return line1 - line2;

        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static int firstLineNumber(java.lang.reflect.Method method, JavaClass javaClass) {
        Method methodInfo = javaClass.getMethod(method);
        LineNumberTable table = methodInfo.getLineNumberTable();
        return table.getSourceLine(0);
    }
}
