package com.pawan07thapa.kafka.spring_kafka_avro.producer;

import com.pawan07thapa.kafka.spring_kafka_avro.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDataProducer {
  private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDataProducer.class);
  private final KafkaTemplate<String, Employee> template;

  @Value("${outbound.topic}")
  private String topic;

  EmployeeDataProducer(KafkaTemplate<String, Employee> template, String topic) {
    this.template = template;
    this.topic = topic;
  }

  public void send(Employee outboundEvent) {

    var future = this.template.send(topic, outboundEvent);
    future.whenComplete(
        (result, throwable) -> {
          if (result != null) {
            LOGGER.info(
                "Outbound message send successfully to topic={}, outboundEvent={}",
                this.topic,
                result.getProducerRecord().value());
          } else {
            LOGGER.error("Failed to send outbound message error={}", throwable.getMessage());
          }
        });
  }
}
