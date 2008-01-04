package net.orfjackal.tools.classmembersorter;

import jdave.Specification;
import jdave.junit4.JDaveRunner;
import static net.orfjackal.tools.classmembersorter.TestData.*;
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
            comparator = new MethodComparator(CLASS_CHILD);
            return comparator;
        }

        public void theFirstOneShouldBeLesser() {
            specify(comparator.compare(METHOD_ONE, METHOD_TWO) < 0);
        }

        public void theSecondOneShouldBeGreater() {
            specify(comparator.compare(METHOD_TWO, METHOD_ONE) > 0);
        }
    }

    public class WhenAMethodIsComparedWithItself {

        private MethodComparator comparator;

        public MethodComparator create() {
            comparator = new MethodComparator(CLASS_CHILD);
            return comparator;
        }

        public void itShouldBeEqualToItself() {
            specify(comparator.compare(METHOD_ONE, METHOD_ONE) == 0);
        }
    }
}
