package com.codemayur.demospringboot3.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
@Slf4j
class UserController {

    @GetMapping("health")
    ResponseEntity<String> health() {
        log.info("UP");
        return ResponseEntity.ok("UP");
    }

}
