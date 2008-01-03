package net.orfjackal.tools.templateproject;

import jdave.Specification;
import jdave.junit4.JDaveRunner;
import org.junit.runner.RunWith;

/**
 * @author Esko Luontola
 * @since 4.1.2008
 */
@RunWith(JDaveRunner.class)
public class AppSpec extends Specification<App> {

    public class AnApp {

        public App create() {
            return new App();
        }

        public void shouldBeOk() {
            specify(context, should.be.ok());
        }
    }
}
