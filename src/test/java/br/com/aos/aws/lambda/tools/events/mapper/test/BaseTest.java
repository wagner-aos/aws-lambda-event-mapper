/**
 * @author Wagner Alves
 *
 * @date 09-06-2018
 */
package br.com.aos.aws.lambda.tools.events.mapper.test;

import org.junit.Before;

import br.com.aos.aws.lambda.tools.events.mapper.AwsLambdaEventsObjectMapper;

/**
 * @author wagner-aos
 *
 */
public class BaseTest {
    
    protected AwsLambdaEventsObjectMapper eventMapper;

    @Before
    public void setUp() {
        eventMapper = new AwsLambdaEventsObjectMapper();
    }

}
