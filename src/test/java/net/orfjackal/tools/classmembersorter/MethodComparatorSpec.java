package net.orfjackal.tools.classmembersorter;

import jdave.Specification;
import jdave.junit4.JDaveRunner;
import org.junit.runner.RunWith;

import java.lang.reflect.Method;

/**
 * @author Esko Luontola
 * @since 4.1.2008
 */
@RunWith(JDaveRunner.class)
public class MethodComparatorSpec extends Specification<MethodComparator> {

    private Method methodOne;
    private Method methodTwo;

    private static class Foo {

        public void methodOne() {
        }

        public void methodTwo() {
        }
    }

    public MethodComparatorSpec() throws NoSuchMethodException {
        methodOne = Foo.class.getMethod("methodOne");
        methodTwo = Foo.class.getMethod("methodTwo");
    }

    public class WhenTwoMethodsAreCompared {

        private MethodComparator comparator;

        public MethodComparator create() {
            comparator = new MethodComparator(Foo.class);
            return comparator;
        }

        public void theFirstOneShouldBeLesser() {
            specify(comparator.compare(methodOne, methodTwo) < 0);
        }

        public void theSecondOneShouldBeGreater() {
            specify(comparator.compare(methodTwo, methodOne) > 0);
        }
    }

    public class WhenAMethodIsComparedWithItself {

        private MethodComparator comparator;

        public MethodComparator create() {
            comparator = new MethodComparator(Foo.class);
            return comparator;
        }

        public void itShouldBeEqualToItself() {
            specify(comparator.compare(methodOne, methodOne) == 0);
        }
    }
}
