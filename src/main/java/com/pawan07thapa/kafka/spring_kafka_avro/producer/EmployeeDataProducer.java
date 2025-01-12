package com.pawan07thapa.kafka.spring_kafka_avro.producer;

import com.pawan07thapa.kafka.spring_kafka_avro.model.Employee;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class EmployeeDataProducer {
  private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDataProducer.class);
  private final KafkaTemplate<String, Employee> template;

  private String topic;

  EmployeeDataProducer(
      KafkaTemplate<String, Employee> template, @Value("${topic.outbound}") String topic) {
    this.template = template;
    this.topic = topic;
  }

  public CompletableFuture<Boolean> send(Employee outboundEvent) {

    CompletableFuture<Boolean> future = new CompletableFuture<>();
    this.template
        .send(topic, outboundEvent)
        .whenComplete(
            (result, throwable) -> {
              if (throwable == null) {
                LOGGER.info(
                    "Outbound message send successfully to topic={}, outboundEvent={}",
                    this.topic,
                    result.getProducerRecord().value());
                future.complete(true);

              } else {
                LOGGER.error("Failed to send outbound message error={}", throwable.getMessage());
                future.completeExceptionally(throwable);
              }
            });
    return future;
  }
}
