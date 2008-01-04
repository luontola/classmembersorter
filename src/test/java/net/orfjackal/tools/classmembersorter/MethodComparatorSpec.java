package net.orfjackal.tools.classmembersorter;

import jdave.Specification;
import jdave.junit4.JDaveRunner;
import org.junit.runner.RunWith;

/**
 * @author Esko Luontola
 * @since 4.1.2008
 */
@RunWith(JDaveRunner.class)
public class MethodComparatorSpec extends Specification<MethodComparator> {

    public class WhenTwoMethodsAreCompared {

        private MethodComparator comparator;

        public MethodComparator create() {
            comparator = new MethodComparator(TestData.METHOD_CLASS);
            return comparator;
        }

        public void theFirstOneShouldBeLesser() {
            specify(comparator.compare(TestData.METHOD_ONE, TestData.METHOD_TWO) < 0);
        }

        public void theSecondOneShouldBeGreater() {
            specify(comparator.compare(TestData.METHOD_TWO, TestData.METHOD_ONE) > 0);
        }
    }

    public class WhenAMethodIsComparedWithItself {

        private MethodComparator comparator;

        public MethodComparator create() {
            comparator = new MethodComparator(TestData.METHOD_CLASS);
            return comparator;
        }

        public void itShouldBeEqualToItself() {
            specify(comparator.compare(TestData.METHOD_ONE, TestData.METHOD_ONE) == 0);
        }
    }
}
