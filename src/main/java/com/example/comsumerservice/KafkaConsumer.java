package com.example.comsumerservice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import static com.example.comsumerservice.KafkaBindings.*;
/**
 * created by lovedeep in com.example.comsumerservice
 */
@Component
@EnableBinding(KafkaBindings.class)
public class KafkaConsumer {

    @Component
    public static class PageEventSink {
        @StreamListener
        @SendTo(PAGE_COUNTS_OUT)
        public KStream<String, Long> process(@Input(PAGE_VIEW_IN) KStream<String, User> events) {
            return events
                    .filter((key, value) -> value.getName().startsWith("j"))
                    .map((key, value) -> new KeyValue<>(value.getName(), "0"))
                    .groupByKey()
                    .count(Materialized.as(PAGE_COUNTS_MV))
                    .toStream();
        }
    }

    @Component
    public static class PageCountSink {

        private final Log log = LogFactory.getLog(getClass());

        @StreamListener
        public void process(@Input(PAGE_COUNTS_IN) KTable<String, Long> counts) {
            counts
                    .toStream()
                    .foreach((key, value) -> log.info("Count :- "+key + '=' + value));
        }
    }





}
