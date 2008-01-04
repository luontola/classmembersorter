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

import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.util.ClassLoaderRepository;
import org.apache.bcel.util.Repository;

import java.util.Comparator;

/**
 * Sorts (inner) classes according to the order in which they have been declared in the source code.
 *
 * @author Esko Luontola
 * @since 4.1.2008
 */
public class ClassLineNumberComparator implements Comparator<Class<?>> {

    private Repository repository;

    public ClassLineNumberComparator() {
        repository = new ClassLoaderRepository(getClass().getClassLoader());
    }

    public int compare(Class<?> o1, Class<?> o2) {
        try {
            JavaClass c1 = repository.loadClass(o1);
            JavaClass c2 = repository.loadClass(o2);
            int line1 = BcelUtils.firstLineNumber(c1, 0);
            int line2 = BcelUtils.firstLineNumber(c2, 0);
            return line1 - line2;

        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
