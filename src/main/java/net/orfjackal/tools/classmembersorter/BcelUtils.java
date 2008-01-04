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

/**
 * @author Esko Luontola
 * @since 4.1.2008
 */
public final class BcelUtils {

    private BcelUtils() {
    }

    public static int firstLineNumber(Method method, int defaultValue) {
        LineNumberTable table = method.getLineNumberTable();
        return (table != null) ? table.getSourceLine(0) : defaultValue;
    }

    public static int firstLineNumber(JavaClass javaClass, int defaultValue) {
        int min = Integer.MAX_VALUE;
        for (Method method : javaClass.getMethods()) {
            int line = firstLineNumber(method, defaultValue);
            min = Math.min(min, line);
        }
        return min;
    }
}
