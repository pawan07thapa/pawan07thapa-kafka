package com.pawan07thapa.kafka.spring_kafka_avro.controller;

import com.pawan07thapa.kafka.spring_kafka_avro.model.Employee;
import com.pawan07thapa.kafka.spring_kafka_avro.producer.EmployeeDataProducer;
import java.util.function.Function;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
  // TODO: 11/01/25  //Create a mapper, map the event before sending
  private final EmployeeDataProducer employeeDataProducer;

  EmployeeController(EmployeeDataProducer employeeDataProducer) {
    this.employeeDataProducer = employeeDataProducer;
  }

  Function<Boolean, ResponseEntity> response =
      success -> {
        if (success) {
          return ResponseEntity.ok().body("Message sent successfully");
        } else {
          return ResponseEntity.badRequest().body("Please check input data");
        }
      };

  @PostMapping(value = "/")
  public ResponseEntity sendMessage(@RequestBody Employee employee) {
    boolean success = employeeDataProducer.send(employee);
    return response.apply(success);
  }
}
