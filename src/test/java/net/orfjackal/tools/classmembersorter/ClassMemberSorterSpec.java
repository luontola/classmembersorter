package net.orfjackal.tools.classmembersorter;

import jdave.Specification;
import jdave.junit4.JDaveRunner;
import static net.orfjackal.tools.classmembersorter.TestData.*;
import org.junit.runner.RunWith;

import java.lang.reflect.Method;

/**
 * @author Esko Luontola
 * @since 4.1.2008
 */
@RunWith(JDaveRunner.class)
public class ClassMemberSorterSpec extends Specification<ClassMemberSorter> {

    public class AClassMemberSorter {

        public ClassMemberSorter create() {
            return null;
        }

        public void shouldGetDeclaredClassesInOrder() {
            Class<?>[] classes = ClassMemberSorter.getDeclaredClasses(TestData.class);
            specify(classes, should.containInOrder(CLASS_ONE, CLASS_TWO, CLASS_THREE, CLASS_FOUR, CLASS_FIVE));
        }

        public void shouldGetDeclaredMethodsInOrder() {
            Method[] methods = ClassMemberSorter.getDeclaredMethods(CLASS_CHILD);
            specify(methods, should.containInOrder(METHOD_ONE, METHOD_TWO));
        }

//        public void shouldGetMethodsInOrderWithInheritedMethodsLast() {
//            Method[] methods = ClassMemberSorter.getMethods(CLASS_CHILD);
//            specify(methods, should.containInPartialOrder(METHOD_ONE, METHOD_TWO, METHOD_PARENT, METHOD_SUPER_PARENT));
//        }
    }
}
