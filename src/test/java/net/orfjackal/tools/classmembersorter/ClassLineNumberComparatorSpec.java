package net.orfjackal.tools.classmembersorter;

import jdave.Specification;
import jdave.junit4.JDaveRunner;
import org.junit.runner.RunWith;

/**
 * @author Esko Luontola
 * @since 4.1.2008
 */
@RunWith(JDaveRunner.class)
public class ClassLineNumberComparatorSpec extends Specification<ClassLineNumberComparator> {

    private ClassLineNumberComparator comparator;

    public class WhenTwoInnerClassesAreCompared {

        public ClassLineNumberComparator create() {
            comparator = new ClassLineNumberComparator();
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

        public ClassLineNumberComparator create() {
            comparator = new ClassLineNumberComparator();
            return comparator;
        }

        public void itShouldBeEqualToItself() {
            specify(comparator.compare(TestData.CLASS_ONE, TestData.CLASS_ONE) == 0);
        }
    }
}