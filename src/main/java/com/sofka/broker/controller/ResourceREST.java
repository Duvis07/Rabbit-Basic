package com.sofka.broker.controller;


import com.sofka.broker.dto.RequestHelloDTO;
import com.sofka.broker.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/broker")
public class ResourceREST {
    @Autowired
    RabbitMQSender rabbitMQSender;

    @PostMapping("/hello")
    public Mono<String> sendHello(@RequestBody RequestHelloDTO requestHelloDTO){
        return rabbitMQSender.send(requestHelloDTO)
                .then(Mono.just(String.format("Hello world %s", requestHelloDTO.getName())));
    }
}
