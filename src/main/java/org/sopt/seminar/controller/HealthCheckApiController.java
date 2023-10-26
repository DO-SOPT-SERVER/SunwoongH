package org.sopt.seminar.controller;

import lombok.Getter;
import org.sopt.seminar.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@Controller
public class HealthCheckApiController {
    @ResponseBody
    @GetMapping("/v1")
    public Map<String, String> healthCheckV1() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        return response;
    }

    @GetMapping("/v2")
    public ResponseEntity<String> healthCheckV2() {
        return ResponseEntity.ok("OK");
    }

    @ResponseBody
    @GetMapping("/v3")
    public String healthCheckV3() {
        return "OK";
    }

    @GetMapping("/v4")
    public ResponseEntity<Map<String, String>> healthCheckV4() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/v5")
    public ResponseEntity<HealthCheckResponse> healthCheckV5() {
        return ResponseEntity.ok(new HealthCheckResponse());
    }

    @ResponseBody
    @GetMapping("/v6")
    public ApiResponse<?> healthCheckV6() {
        return ApiResponse.of(OK, true);
    }

    @Getter
    static class HealthCheckResponse {
        private static final String STATUS = "OK";
        private final String status;

        HealthCheckResponse() {
            this.status = STATUS;
        }
    }
}
