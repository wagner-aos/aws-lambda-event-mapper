package br.com.aos.aws.lambda.tools.events.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import br.com.aos.aws.lambda.tools.events.module.LambdaEventsModule;

/**
 * ObjectMapper which correctly handles events in com.amazonaws.services.lambda.runtime.events
 * 
 * @author Wagner Alves
 */
public class AwsLambdaEventsObjectMapper extends ObjectMapper {

    private static final long serialVersionUID = 1L;

    public AwsLambdaEventsObjectMapper() {
        super();

        // Don't serialize things that are null
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        this.registerModule(new JodaModule());
        this.registerModule(new LambdaEventsModule());
    }
}
