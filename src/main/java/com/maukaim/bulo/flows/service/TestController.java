package com.maukaim.bulo.flows.service;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/flows")
public class TestController {

    @GetMapping(value = "/salutations")
    public ResponseEntity<String> echoHello(@RequestParam String name) {
        return ResponseEntity.ok("Hello " + name);
    }
}
