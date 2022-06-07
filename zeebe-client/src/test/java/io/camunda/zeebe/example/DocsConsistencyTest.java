/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH under
 * one or more contributor license agreements. See the NOTICE file distributed
 * with this work for additional information regarding copyright ownership.
 * Licensed under the Zeebe Community License 1.1. You may not use this file
 * except in compliance with the Zeebe Community License 1.1.
 */
package io.camunda.zeebe.example;

import static org.assertj.core.api.Assertions.assertThat;

import io.camunda.zeebe.example.cluster.TopologyViewer;
import io.camunda.zeebe.example.data.HandleVariablesAsPojo;
import io.camunda.zeebe.example.job.JobWorkerCreator;
import io.camunda.zeebe.example.process.NonBlockingProcessInstanceCreator;
import io.camunda.zeebe.example.process.ProcessDeployer;
import io.camunda.zeebe.example.process.ProcessInstanceCreator;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public final class DocsConsistencyTest {
  @Parameter(0)
  public Class<?> exampleClass;

  @Parameter(1)
  public String expectedClassName;

  @Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(
        new Object[][] {
          {TopologyViewer.class, "io.camunda.zeebe.example.cluster.TopologyViewer"},
          {JobWorkerCreator.class, "io.camunda.zeebe.example.job.JobWorkerCreator"},
          {
            NonBlockingProcessInstanceCreator.class,
            "io.camunda.zeebe.example.process.NonBlockingProcessInstanceCreator"
          },
          {ProcessDeployer.class, "io.camunda.zeebe.example.process.ProcessDeployer"},
          {ProcessInstanceCreator.class, "io.camunda.zeebe.example.process.ProcessInstanceCreator"},
          {HandleVariablesAsPojo.class, "io.camunda.zeebe.example.data.HandleVariablesAsPojo"},
        });
  }

  @Test
  public void todo() {
    assertThat(exampleClass.getName())
        .withFailMessage(
            "This class's source code is referenced from the java-client-example docs. "
                + "Make sure to adapt them as well.")
        .isEqualTo(expectedClassName);
  }
}
