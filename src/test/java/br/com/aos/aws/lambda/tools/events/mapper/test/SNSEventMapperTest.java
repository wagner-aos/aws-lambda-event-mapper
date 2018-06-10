package br.com.aos.aws.lambda.tools.events.mapper.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.amazonaws.services.lambda.runtime.events.SNSEvent.SNSRecord;

import br.com.aos.aws.lambda.tools.utils.SystemUtils;

/**
 * @author Wagner Alves
 *
 */
public class SNSEventMapperTest extends BaseTest{

    String event = SystemUtils.readFile("json-events/sns-event.json");
    
    @Test
    public void shouldTestSNSEvent() throws IOException {

        SNSEvent snsEvent = eventMapper.readValue(event, SNSEvent.class);
        
        List<SNSRecord> records = snsEvent.getRecords();
        SNSRecord snsRecord = records.get(0);
       

        assertThat(snsEvent).isNotNull();
        assertThat(snsEvent.getRecords()).isNotNull();
        assertThat(snsEvent.getRecords()).hasSize(1);
        assertThat(snsEvent.getRecords().get(0)).isNotNull();
        
        assertEquals(snsRecord.getEventVersion(),"1.0");
        assertEquals(snsRecord.getEventSubscriptionArn(),"arn:aws:sns:EXAMPLE");
        assertEquals(snsRecord.getEventSource(),"aws:sns");
        assertEquals(snsRecord.getSNS().getMessage(),"Hello Wagner!");                       
       
    }

}
