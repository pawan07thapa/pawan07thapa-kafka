package com.pawan07thapa.kafka.spring_kafka_avro.Controller;

import com.pawan07thapa.kafka.spring_kafka_avro.producer.EmployeeDataProducer;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

  private final EmployeeDataProducer employeeDataProducer;

  EmployeeController(EmployeeDataProducer employeeDataProducer) {
    this.employeeDataProducer = employeeDataProducer;
  }



}
