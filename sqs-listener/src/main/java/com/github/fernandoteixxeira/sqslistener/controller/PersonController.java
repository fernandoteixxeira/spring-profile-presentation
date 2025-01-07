package com.github.fernandoteixxeira.sqslistener.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.fernandoteixxeira.sqslistener.entity.Person;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/person")
public class PersonController {

    SqsClient sqsClient;
    @Value("${cloud.aws.sqs.endpoint}")
    private String queueUrl;

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody Person person) throws JsonProcessingException {
        String messageBody = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .writeValueAsString(person);

        SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(messageBody)
                .build();

        sqsClient.sendMessage(sendMessageRequest);
        return ResponseEntity.ok().build();
    }
}
