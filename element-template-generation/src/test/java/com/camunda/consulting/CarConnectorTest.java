package com.camunda.consulting;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceResult;
import io.camunda.zeebe.spring.test.ZeebeSpringTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@ZeebeSpringTest
@SpringBootTest(properties = { "camunda.connector.webhook.enabled=false", "camunda.connector.polling.enabled=false" })
public class CarConnectorTest {
  @Autowired
  ZeebeClient zeebeClient;

  @BeforeEach
  void setup() {
    zeebeClient
        .newDeployResourceCommand()
        .addResourceFromClasspath("test.bpmn")
        .send()
        .join();
  }

  @Test
  void shouldRun() {
    ProcessInstanceResult result = zeebeClient
        .newCreateInstanceCommand()
        .bpmnProcessId("test")
        .latestVersion()
        .withResult()
        .send()
        .join();
    CarConnectorOutput output = result
        .getVariablesAsType(ResultType.class)
        .car();
    assertThat(output.make()).isEqualTo("Audi");
    assertThat(output.model()).isEqualTo("A6");
    assertThat(output.gearbox()).isEqualTo("Automatic");

  }
}
