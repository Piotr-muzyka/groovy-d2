import org.junit.Test

import static org.junit.Assert.assertEquals

/**
 * Created by PMUZYKA on 2017-10-04.
 */

// TDD - interpolation example
class TemplateTest {

    private static final String EXPECTED_RESULT = 'My name is Jan Kowalski'

    private Template template = new Template('My name is ${firstName} ${lastName}')
    private Map<String, String> parameters = [firstName: 'Jan', lastName: 'Kowalski']

    // Template is safe (won't be modified by the tests since Junit is creating a separate object for each test)

    @Test
    void shouldEvaluateTextWithExpressions(){

        String result = template.evaluate([firstName: 'Jan', lastName: 'Kowalski'])
        assertEquals('My name is Jan Kowalski',result)
    }

    @Test (expected = IllegalArgumentException.class) // this test should succeed if we get a IllegalArgumentException
    void shouldThrowException(){
        new Template('My name is ${firstName} ${lastName}').evaluate([:])
    }

    @Test
    void shouldIgnoreExtraParameters(){
        String result = template.evaluate(parameters, [age: '12'])
        assertEquals(EXPECTED_RESULT, template.evaluate(parameters))
    }
}
