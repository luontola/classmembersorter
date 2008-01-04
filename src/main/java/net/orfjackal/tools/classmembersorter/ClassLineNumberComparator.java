package net.orfjackal.tools.classmembersorter;

import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.util.ClassLoaderRepository;
import org.apache.bcel.util.Repository;

import java.util.Comparator;

/**
 * Sorts (inner) classes according to the order in which they have been declared in the source code.
 *
 * @author Esko Luontola
 * @since 4.1.2008
 */
public class ClassLineNumberComparator implements Comparator<Class<?>> {

    private Repository repository;

    public ClassLineNumberComparator() {
        repository = new ClassLoaderRepository(getClass().getClassLoader());
    }

    public int compare(Class<?> o1, Class<?> o2) {
        try {
            JavaClass c1 = repository.loadClass(o1);
            JavaClass c2 = repository.loadClass(o2);
            int line1 = Utils.firstLineNumber(c1, 0);
            int line2 = Utils.firstLineNumber(c2, 0);
            return line1 - line2;

        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
