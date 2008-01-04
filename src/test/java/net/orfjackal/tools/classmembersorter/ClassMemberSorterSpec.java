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

        public void shouldGetMethodsInOrderWithInheritedMethodsLast() {
            Method[] methods = ClassMemberSorter.getMethods(CLASS_CHILD);
            specify(methods, should.containInPartialOrder(METHOD_ONE, METHOD_TWO, METHOD_PARENT, METHOD_SUPER_PARENT));
        }
    }
}
