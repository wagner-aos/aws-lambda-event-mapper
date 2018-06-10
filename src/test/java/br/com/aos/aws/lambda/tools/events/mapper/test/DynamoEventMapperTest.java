package br.com.aos.aws.lambda.tools.events.mapper.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent.DynamodbStreamRecord;

import br.com.aos.aws.lambda.tools.utils.SystemUtils;

/**
 * 
 * @author Wagner Alves
 *
 */
public class DynamoEventMapperTest extends BaseTest{

    String event = SystemUtils.readFile("json-events/dynamo-event.json");

    @Test
    public void shouldTestDynamoEvent() throws IOException {

        DynamodbEvent dbEvent = eventMapper.readValue(event, DynamodbEvent.class);

        List<DynamodbStreamRecord> records = dbEvent.getRecords();
        DynamodbStreamRecord dynamodbStreamRecord = records.get(0);

        assertThat(dbEvent).isNotNull();
        assertThat(dbEvent.getRecords()).isNotNull();
        assertThat(dbEvent.getRecords()).hasSize(1);
        assertThat(dbEvent.getRecords().get(0)).isNotNull();
        
        
        assertEquals(dynamodbStreamRecord.getEventID(),"1");
        assertEquals(dynamodbStreamRecord.getEventVersion(),"1.0");
        assertEquals(dynamodbStreamRecord.getEventName(),"INSERT");
        assertEquals(dynamodbStreamRecord.getEventSource(),"aws:dynamodb");
        assertEquals(dynamodbStreamRecord.getEventSourceARN(),"arn:aws:dynamodb:us-east-1:ACCOUNT_ID:table/TableName/stream/2018-06-01T17:38:53.651");

    }

}
