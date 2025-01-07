package com.github.fernandoteixxeira.sqslistener.listener;

import com.github.fernandoteixxeira.sqslistener.entity.Person;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class PersonSqsListener {

    @SqsListener("${cloud.aws.sqs.endpoint}")
    public void receiveMessage(Person pessoa) {
        log.info("Mensagem recebida: {}", pessoa);
    }
}