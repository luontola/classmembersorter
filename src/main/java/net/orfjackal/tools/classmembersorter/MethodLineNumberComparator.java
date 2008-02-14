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

import java.lang.reflect.Method;
import java.util.Comparator;

/**
 * Sorts methods according to the order in which they have been declared in the source code.
 *
 * @author Esko Luontola
 * @since 4.1.2008
 */
public class MethodLineNumberComparator implements Comparator<Method> {

    private final LineNumberStrategy strategy = new BcelLineNumberStrategy();

    public int compare(Method o1, Method o2) {
        if (sameClass(o1, o2)) {
            int line1 = firstLineNumber(o1);
            int line2 = firstLineNumber(o2);
            return line1 - line2;
        } else if (parentClass(o1, o2)) {
            return 1;
        } else if (parentClass(o2, o1)) {
            return -1;
        } else {
            return alphabeticalOrder(o1, o2);
        }
    }

    private int firstLineNumber(Method method) {
        return strategy.firstLineNumber(method, 0);
    }

    private static boolean sameClass(Method o1, Method o2) {
        return o1.getDeclaringClass().equals(o2.getDeclaringClass());
    }

    private static boolean parentClass(Method parent, Method child) {
        return parent.getDeclaringClass().isAssignableFrom(child.getDeclaringClass());
    }

    private static int alphabeticalOrder(Method o1, Method o2) {
        return o1.getDeclaringClass().getName().compareTo(o2.getDeclaringClass().getName());
    }
}
