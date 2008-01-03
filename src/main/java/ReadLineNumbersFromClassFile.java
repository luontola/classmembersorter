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
            ClassParser parser = new ClassParser("target/classes/ReadLineNumbersFromClassFile.class");
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

    // TODO: try to use com.sun.org.apache.bcel.internal.classfile.ClassParser to get the line numbers of the code in Method classes
    // and if it works, fix the sorting of SpecRunner (classes and methods)
    //
    // BCEL: http://jakarta.apache.org/bcel/
    // + gives direct access to line number table, as shown in code above
    // http://repo1.maven.org/maven2/org/apache/bcel/bcel/5.2/
    // bcel-5.2.jar   521K
    //
    // ASM: http://asm.objectweb.org/
    // - does not give easy access to line numbers
    //
    // CGLIB: http://cglib.sourceforge.net/
    // - nope, only info on method signatures etc.
    //
    // Javassist: http://www.csg.is.titech.ac.jp/~chiba/javassist/
    // + yes: javassist.bytecode.MethodInfo.getLineNumber
    // http://repo1.maven.org/maven2/jboss/javassist/3.6.ga/
    // javassist-3.6.ga.jar   530K
    //
    // More realted programs: http://aopalliance.sourceforge.net/motivations.html
}
