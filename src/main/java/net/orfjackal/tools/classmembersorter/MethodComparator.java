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

    private JavaClass clazz;

    public MethodComparator(java.lang.Class<?> declaringClass) {
        try {
            Repository repository = new ClassLoaderRepository(declaringClass.getClassLoader());
            clazz = repository.loadClass(declaringClass);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public int compare(java.lang.reflect.Method o1, java.lang.reflect.Method o2) {
        int line1 = firstLineNumber(o1, clazz);
        int line2 = firstLineNumber(o2, clazz);
        return line1 - line2;
    }

    private static int firstLineNumber(java.lang.reflect.Method method, JavaClass javaClass) {
        Method methodInfo = javaClass.getMethod(method);
        LineNumberTable table = methodInfo.getLineNumberTable();
        return table.getSourceLine(0);
    }
}
