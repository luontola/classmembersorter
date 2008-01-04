/*
 * Class Member Sorter
 * Copyright (c) 2008 Esko Luontola, www.orfjackal.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
public class MethodLineNumberComparatorSpec extends Specification<MethodLineNumberComparator> {

    private MethodLineNumberComparator comparator;

    public class WhenTwoMethodsAreCompared {

        public MethodLineNumberComparator create() {
            comparator = new MethodLineNumberComparator();
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

        public MethodLineNumberComparator create() {
            comparator = new MethodLineNumberComparator();
            return comparator;
        }

        public void itShouldBeEqualToItself() {
            specify(comparator.compare(METHOD_ONE, METHOD_ONE) == 0);
        }
    }

    public class WhenTheOtherMethodIsInAParentClass {

        public MethodLineNumberComparator create() {
            comparator = new MethodLineNumberComparator();
            return comparator;
        }

        public void theMethodInChildClassShouldBeLesser() {
            specify(comparator.compare(METHOD_ONE, METHOD_PARENT) < 0);
            specify(comparator.compare(METHOD_ONE, METHOD_SUPER_PARENT) < 0);
        }

        public void theMethodInParentClassShouldBeGreater() {
            specify(comparator.compare(METHOD_PARENT, METHOD_ONE) > 0);
            specify(comparator.compare(METHOD_SUPER_PARENT, METHOD_ONE) > 0);
        }
    }

    public class WhenMethodsAreInUnrelatedClasses {

        public MethodLineNumberComparator create() {
            comparator = new MethodLineNumberComparator();
            return comparator;
        }

        public void theClassesShouldBeInAlphabeticalOrder() {
            int alphabeticalOrder = CLASS_UNRELATED.getName().compareTo(CLASS_CHILD.getName());
            specify(comparator.compare(METHOD_UNRELATED, METHOD_ONE), should.equal(alphabeticalOrder));
        }
    }
}
