| Date| Author | Description | Version |
| --- | --- | --- | --- |
| 07/06/2018 | Wagner Alves (aka Barão) | AWS Lambda Event Mapper | 1.0.0-SNAPSHOT | 

# AWS Lambda Event Mapper

## Description:
Library for testing AWS lambda events, you can create json lambda events in order to test java lambda handlers.

## AwsLambdaEventsObjectMapper

This is an extension of [ObjectMapper](https://fasterxml.github.io/jackson-databind/javadoc/2.5/com/fasterxml/jackson/databind/ObjectMapper.html)
which can serialize and deserialize Lambda events:

Currently it can parse json events to com.amazonaws.services.lambda.runtime.events:

 - `DynamodbEvent`
 - `S3Event`
 - `SNSEvent`

## How to test your lambda events (DynamoDB Stream Example):

1-Create a lambda json event:
> See an example at: /src/test/resources/json-events
```
{
  "Records": [
    {
      "eventID": "1",
      "eventVersion": "1.0",
      "dynamodb": {

  ...
      	
```

2-Create an instance of AwsLambdaEventsObjectMapper:
```
AwsLambdaEventsObjectMapper eventMapper = new AwsLambdaEventsObjectMapper();
```

3-Read the json event file with:
```
String event = SystemUtils.readFile("json-events/dynamo-event.json");
```

4-Get the events:
```
DynamodbEvent dbEvent = eventMapper.readValue(event, DynamodbEvent.class);

List<DynamodbStreamRecord> records = dbEvent.getRecords();
DynamodbStreamRecord dynamodbStreamRecord = records.get(0);

```

5-Assert the results:
```
assertThat(dbEvent).isNotNull();
assertThat(dbEvent.getRecords()).isNotNull();
assertThat(dbEvent.getRecords()).hasSize(1);
assertThat(dbEvent.getRecords().get(0)).isNotNull();

assertEquals(dynamodbStreamRecord.getEventID(),"1");
assertEquals(dynamodbStreamRecord.getEventVersion(),"1.0");
assertEquals(dynamodbStreamRecord.getEventName(),"INSERT");
assertEquals(dynamodbStreamRecord.getEventSource(),"aws:dynamodb");
assertEquals(dynamodbStreamRecord.getEventSourceARN(),"arn:aws:dynamodb:us-east-1:ACCOUNT_ID:table/TableName/stream/2018-06-01T17:38:53.651");

```


## Useful Links:

[AWS Lambda Events](https://docs.aws.amazon.com/lambda/latest/dg/eventsources.html)
[Wagner's GitHub](https://github.com/wagner-aos)

## TODO:

- `Amazon Kinesis Data Streams`


## I hope you enjoy it ;-)



