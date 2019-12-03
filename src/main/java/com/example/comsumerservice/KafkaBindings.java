package com.example.comsumerservice;

import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

/**
 * created by lovedeep in com.example.comsumerservice
 */

interface KafkaBindings {

//    		String PAGE_VIEW_OUT = "pveo";
    String PAGE_VIEW_IN = "pvei";

    String PAGE_COUNTS_OUT = "pco";
    String PAGE_COUNTS_IN = "pci";
    String PAGE_COUNTS_MV = "pcmview";

    @Input(PAGE_COUNTS_IN)
    KTable<String, Long> pageCountsIn();

    @Output(PAGE_COUNTS_OUT)
    KStream<String, Long> pageCountOut();

//		@Output(PAGE_VIEW_OUT)
//        MessageChannel pageViewEventsOut();

    @Input(PAGE_VIEW_IN)
    KStream<String, User> pageViewEventsIn();
}