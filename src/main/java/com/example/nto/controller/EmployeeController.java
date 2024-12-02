package com.example.nto.controller;

import com.example.nto.entity.Employee;
import com.example.nto.repository.CodeRepository;
import com.example.nto.repository.EmployeeRepository;
import com.example.nto.service.EmployeeService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;
    private final CodeRepository codeRepository;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeRepository employeeRepository, CodeRepository codeRepository) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
        this.codeRepository = codeRepository;
    }

    @Getter
    public static class UserData {
        @JsonProperty("value")
        private String value;
    }

    @GetMapping("/{login}/auth")
    public ResponseEntity<String> authEmployee(@PathVariable String login) {
        try {
            boolean loginExists = employeeService.checkLogin(login);

            if (loginExists) {
                return ResponseEntity.ok("данный логин существует");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("логина не существует или неверный");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("что-то пошло не так");
        }
    }

    @GetMapping("/{login}/info")
    public ResponseEntity<?> infoEmployee(@PathVariable String login) {
        try {
            Employee user = employeeRepository.getEmployeeByLogin(login);
            if (user != null) {
                Map<String, Object> response = new LinkedHashMap<>();
                response.put("id", user.getId());
                response.put("login", user.getLogin());
                response.put("name", user.getName());
                response.put("photo", user.getPhoto());
                response.put("position", user.getPosition());
                response.put("lastVisit", user.getLastVisit());

                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("логина не существует или неверный");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("что-то пошло не так");
        }
    }

    @PatchMapping("/{login}/open")
    public ResponseEntity<?> openEmployee(@PathVariable String login, @RequestBody UserData userData) {
        try {
            long code = Long.parseLong(userData.getValue());
            boolean loginExists = employeeService.checkLogin(login);

            if (loginExists) {
                boolean codeExists = codeRepository.checkCode(code);

                if (codeExists) {
                    return ResponseEntity.ok("дверь открылась");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("что-то пошло не так");
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("логина не существует или неверный");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("что-то пошло не так");
        }
    }
}
