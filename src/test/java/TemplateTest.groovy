import org.junit.Test

import static org.junit.Assert.assertEquals

/**
 * Created by PMUZYKA on 2017-10-04.
 */

// TDD - interpolation example
class TemplateTest {

    @Test
    void shouldEvaluateTextWithExpressions(){
        Template template = new Template('My name is ${firstName} ${lastName}')
        String result = template.evaluate([firstName: 'Jan', lastName: 'Kowalski'])
        assertEquals('My name is Jan Kowalski',result)
    }

    @Test
    void shouldThrowException(){

    }
}
