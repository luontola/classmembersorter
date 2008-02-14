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
import org.apache.bcel.classfile.LineNumberTable;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.util.ClassLoaderRepository;
import org.apache.bcel.util.Repository;

/**
 * @author Esko Luontola
 * @since 4.1.2008
 */
public final class BcelLineNumberStrategy implements LineNumberStrategy {

    private final Repository repository = new ClassLoaderRepository(getClass().getClassLoader());

    public int firstLineNumber(Class<?> clazz, int defaultValue) {
        int min = Integer.MAX_VALUE;
        for (Method method : toBcel(clazz).getMethods()) {
            int line = firstLineNumber(method, defaultValue);
            min = Math.min(min, line);
        }
        return min;
    }

    public int firstLineNumber(java.lang.reflect.Method method, int defaultValue) {
        Method m = toBcel(method);
        return firstLineNumber(m, defaultValue);
    }

    private static int firstLineNumber(Method method, int defaultValue) {
        LineNumberTable table = method.getLineNumberTable();
        return (table != null) ? table.getSourceLine(0) : defaultValue;
    }

    private Method toBcel(java.lang.reflect.Method method) {
        JavaClass javaClass = toBcel(method.getDeclaringClass());
        return javaClass.getMethod(method);
    }

    private JavaClass toBcel(Class<?> clazz) {
        try {
            return repository.loadClass(clazz);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Unable to load class: " + clazz, e);
        }
    }
}
