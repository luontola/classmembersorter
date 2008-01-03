package net.orfjackal.tools.classmembersorter;

import jdave.Specification;
import jdave.junit4.JDaveRunner;
import org.junit.runner.RunWith;

/**
 * @author Esko Luontola
 * @since 4.1.2008
 */
@RunWith(JDaveRunner.class)
public class InnerClassComparatorSpec extends Specification<InnerClassComparator> {

    private Class<?> classOne = FirstClass.class;
    private Class<?> classTwo = SecondClass.class;

    private static class FirstClass {
    }

    private static class SecondClass {
    }

    public class WhenTwoInnerClassesAreCompared {

        private InnerClassComparator comparator;

        public InnerClassComparator create() {
            comparator = new InnerClassComparator();
            return comparator;
        }

        public void theFirstOneShouldBeLesser() {
            specify(comparator.compare(classOne, classTwo) < 0);
        }

        public void theSecondOneShouldBeGreater() {
            specify(comparator.compare(classTwo, classOne) > 0);
        }
    }

    public class WhenAnInnerClassIsComparedWithItself {

        private InnerClassComparator comparator;

        public InnerClassComparator create() {
            comparator = new InnerClassComparator();
            return comparator;
        }

        public void itShouldBeEqualToItself() {
            specify(comparator.compare(classOne, classOne) == 0);
        }
    }
}