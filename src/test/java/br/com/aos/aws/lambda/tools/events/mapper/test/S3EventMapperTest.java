/**
 * @author Wagner Alves
 *
 * @date 09-06-2018
 */
package br.com.aos.aws.lambda.tools.events.mapper.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.event.S3EventNotification.S3EventNotificationRecord;

import br.com.aos.aws.lambda.tools.utils.SystemUtils;

/**
 * @author Wagner Alves
 *
 */
public class S3EventMapperTest extends BaseTest{

    String event = SystemUtils.readFile("json-events/s3-event.json");
    
    @Test
    public void shouldTestS3Event() throws IOException {

        S3Event s3Event = eventMapper.readValue(event, S3Event.class);
        
        List<S3EventNotificationRecord> records = s3Event.getRecords();
        S3EventNotificationRecord s3EventNotificationRecord = records.get(0);

        assertThat(s3Event).isNotNull();
        assertThat(s3Event.getRecords()).isNotNull();
        assertThat(s3Event.getRecords()).hasSize(1);
        assertThat(s3Event.getRecords().get(0)).isNotNull();
        
        assertEquals(s3EventNotificationRecord.getEventVersion(),"2.0");
        assertEquals(s3EventNotificationRecord.getEventName(),"ObjectCreated:Put");
        assertEquals(s3EventNotificationRecord.getEventSource(),"aws:s3");
       
        assertEquals(s3EventNotificationRecord.getS3().getObject().getKey(),"wagner-alves.jpg");
        assertEquals(s3EventNotificationRecord.getS3().getObject().geteTag(),"d41d8cd98f00b204e9800998ecf8427e");        
       
    }


}
