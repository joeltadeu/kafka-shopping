package com.sk.monitoringservice;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class MonitoringServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(MonitoringServiceApplication.class, args);
  }
}
