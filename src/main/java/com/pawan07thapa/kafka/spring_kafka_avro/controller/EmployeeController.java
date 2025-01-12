package com.pawan07thapa.kafka.spring_kafka_avro.controller;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.springframework.http.HttpStatus.REQUEST_TIMEOUT;

import com.pawan07thapa.kafka.spring_kafka_avro.model.Employee;
import com.pawan07thapa.kafka.spring_kafka_avro.producer.EmployeeDataProducer;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
  // TODO: 11/01/25  //Create a mapper, map the event before sending
  private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
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
  public CompletableFuture<ResponseEntity> sendMessage(@RequestBody Employee employee) {
    LOGGER.info("Data received, passing it to producer");
    var responseFuture = employeeDataProducer.send(employee).thenApply(response);
    return responseFuture.completeOnTimeout(
        ResponseEntity.status(REQUEST_TIMEOUT).body("Operation Timed Out"), 5, SECONDS);
  }
}
