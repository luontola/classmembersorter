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

import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.LineNumberTable;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.util.ClassLoaderRepository;

import java.io.IOException;
import java.util.Arrays;

public class ReadLineNumbersFromClassFile {

    private static final boolean READ_PHYSICAL_FILE = false;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        JavaClass javaClass;

        if (READ_PHYSICAL_FILE) {
            ClassParser parser = new ClassParser("target/test-classes/ReadLineNumbersFromClassFile.class");
            javaClass = parser.parse();
        } else {
            ClassLoaderRepository repository = new ClassLoaderRepository(ReadLineNumbersFromClassFile.class.getClassLoader());
            javaClass = repository.loadClass(ReadLineNumbersFromClassFile.class.getName());
        }
        System.out.println("javaClass = " + javaClass);

        for (Method method : javaClass.getMethods()) {
            System.out.println("----------------");
            LineNumberTable table = method.getLineNumberTable();
            System.out.println("Method = " + method.getName());
            System.out.println("LineNumberTable = " + Arrays.toString(table.getLineNumberTable()));
            System.out.println("Code = " + method.getCode());
        }
    }

    public void empty() {
    }

    // Byte code instrumentation libraries:
    //
    // BCEL: http://jakarta.apache.org/bcel/
    // + gives direct access to line number table, as shown in code above
    // http://repo1.maven.org/maven2/org/apache/bcel/bcel/5.2/
    // bcel-5.2.jar   521K
    //
    // Javassist: http://www.csg.is.titech.ac.jp/~chiba/javassist/
    // + yes: javassist.bytecode.MethodInfo.getLineNumber
    // http://repo1.maven.org/maven2/jboss/javassist/3.6.ga/
    // javassist-3.6.ga.jar   530K
    //
    // ASM: http://asm.objectweb.org/
    // - does not give easy access to line numbers
    //
    // CGLIB: http://cglib.sourceforge.net/
    // - nope, only info on method signatures etc.
    //
    // More related programs: http://aopalliance.sourceforge.net/motivations.html
}
