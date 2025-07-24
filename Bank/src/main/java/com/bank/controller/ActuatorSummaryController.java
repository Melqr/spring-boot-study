package com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.boot.actuate.metrics.MetricsEndpoint.MetricDescriptor;
import org.springframework.boot.actuate.metrics.MetricsEndpoint.MetricNamesDescriptor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;


// basic actuator 
// http://localhost:8080/actuator

@RestController
@RequestMapping("/api/actuator/v1")
public class ActuatorSummaryController {

	@Autowired
	private HealthEndpoint healthEndpoint;

	@Autowired
	private InfoEndpoint infoEndpoint;

	@Autowired
	private MetricsEndpoint metricsEndpoint;

	@GetMapping("/summary")
	public ResponseEntity<Map<String, Object>> getActuatorSummary() {
		Map<String, Object> result = new LinkedHashMap<>();

		result.put("health", healthEndpoint.health());
		result.put("info", infoEndpoint.info());

		MetricDescriptor memoryUsed = metricsEndpoint.metric("jvm.memory.used", null);
		MetricDescriptor httpRequests = metricsEndpoint.metric("http.server.requests", null);
		MetricDescriptor cpuUsage = metricsEndpoint.metric("system.cpu.usage", null);

		result.put("jvm.memory.used", memoryUsed);
		result.put("http.server.requests", httpRequests);
		result.put("system.cpu.usage", cpuUsage);
		result.put("version", "Jenkins");

		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/metricDetails")
	public ResponseEntity<Map<String, Object>> getActuatorMetric() {
		Map<String, Object> result = new LinkedHashMap<>();
		MetricNamesDescriptor md = metricsEndpoint.listNames();
		result.put("metric.list", md.getNames());

		return ResponseEntity.ok(result);
	}
}
