package com.pawan07thapa.kafka.spring_kafka_avro.consumer;

import com.pawan07thapa.kafka.spring_kafka_avro.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDataConsumer {
  private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDataConsumer.class);

  @KafkaListener(topics = "${topic.inbound}", groupId = "${spring.kafka.consumer.group-id}")
  public void consumeMessage(Employee employee) {
    LOGGER.info("Event received data ={}", employee.toString());
    System.out.println("Event received " + employee);
  }
}
