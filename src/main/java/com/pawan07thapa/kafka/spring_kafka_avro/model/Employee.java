package com.pawan07thapa.kafka.spring_kafka_avro.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Employee {
  @NotNull private Long id;
  @NotBlank private String name;
  private String age;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Employee{");
    sb.append("id=").append(id);
    sb.append(", name='").append(name).append('\'');
    sb.append(", age='").append(age).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
