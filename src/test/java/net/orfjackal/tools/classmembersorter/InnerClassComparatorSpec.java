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

    public class WhenTwoInnerClassesAreCompared {

        private InnerClassComparator comparator;

        public InnerClassComparator create() {
            comparator = new InnerClassComparator();
            return comparator;
        }

        public void theFirstOneShouldBeLesser() {
            specify(comparator.compare(TestData.CLASS_ONE, TestData.CLASS_TWO) < 0);
        }

        public void theSecondOneShouldBeGreater() {
            specify(comparator.compare(TestData.CLASS_TWO, TestData.CLASS_ONE) > 0);
        }
    }

    public class WhenAnInnerClassIsComparedWithItself {

        private InnerClassComparator comparator;

        public InnerClassComparator create() {
            comparator = new InnerClassComparator();
            return comparator;
        }

        public void itShouldBeEqualToItself() {
            specify(comparator.compare(TestData.CLASS_ONE, TestData.CLASS_ONE) == 0);
        }
    }
}