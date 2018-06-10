/**
 * @author Wagner Alves
 *
 * @date 09-06-2018
 */
package br.com.aos.aws.lambda.tools.events.context.test;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import br.com.aos.aws.lambda.tools.events.context.MockContextBuilder;

/**
 * @author wagner-aos
 *
 */
public class MockContextTest {
    
    @Test
    public void shouldCreateContext() {

        int MOCK_EXECUTION_TIME_LIMIT = 60000;
    
        Context context = new MockContextBuilder("lambda-dynamodb")
                .withRemainingTimeInMillis(MOCK_EXECUTION_TIME_LIMIT)
                .withRemainingTimeCountdown(true)
                .createMockContext();
        
        assertThat(context).isNotNull();
        assertEquals(context.getFunctionName(),"lambda-dynamodb");
        
    }

}
