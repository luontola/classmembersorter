package net.orfjackal.tools.classmembersorter;

import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.LineNumberTable;
import org.apache.bcel.classfile.Method;

/**
 * @author Esko Luontola
 * @since 4.1.2008
 */
public final class BcelUtils {

    private BcelUtils() {
    }

    public static int firstLineNumber(Method method, int defaultValue) {
        LineNumberTable table = method.getLineNumberTable();
        return (table != null) ? table.getSourceLine(0) : defaultValue;
    }

    public static int firstLineNumber(JavaClass javaClass, int defaultValue) {
        int min = Integer.MAX_VALUE;
        for (Method method : javaClass.getMethods()) {
            int line = firstLineNumber(method, defaultValue);
            min = Math.min(min, line);
        }
        return min;
    }
}
