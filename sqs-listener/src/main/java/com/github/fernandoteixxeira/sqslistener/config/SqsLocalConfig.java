package com.github.fernandoteixxeira.sqslistener.config;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.endpoints.SqsEndpointParams;
import software.amazon.awssdk.services.sqs.endpoints.internal.DefaultSqsEndpointProvider;
import software.amazon.awssdk.services.sqs.model.CreateQueueRequest;
import software.amazon.awssdk.services.sqs.model.CreateQueueResponse;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Configuration
@Profile("local")
public class SqsLocalConfig {
    @Bean
    public StaticCredentialsProvider staticCredentialsProvider() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create("test", "test");
        return StaticCredentialsProvider.create(credentials);
    }

    @Bean
    public SqsClient sqsClient(
            @Value("${cloud.aws.endpoint}") URI endpoint,
            @Value("${cloud.aws.sqs.endpoint}") String sqsEndpoint,
            StaticCredentialsProvider staticCredentialsProvider
    ) {
        SqsClient client = SqsClient.builder()
                .endpointOverride(endpoint)
                .region(Region.US_EAST_1)
                .credentialsProvider(staticCredentialsProvider)
                .build();
        createQueue(client, sqsEndpoint);
        return client;
    }

    @Bean
    public SqsAsyncClient sqsAsyncClient(
            @Value("${cloud.aws.endpoint}") URI endpoint,
            @Value("${cloud.aws.sqs.endpoint}") String sqsEndpoint,
            StaticCredentialsProvider staticCredentialsProvider
    ) {
        SqsAsyncClient client = SqsAsyncClient.builder()
                .endpointOverride(endpoint) // Endpoint LocalStack
                .region(Region.US_EAST_1)
                .credentialsProvider(staticCredentialsProvider)
                .build();
        createQueue(client, sqsEndpoint);
        return client;
    }

    private void createQueue(SqsClient client, String sqsEndpoint) {
        CreateQueueRequest createQueueRequest = CreateQueueRequest.builder()
                .queueName("person")
                .build();
        CreateQueueResponse result = client.createQueue(createQueueRequest);
        log.info("Fila criada com URL: " + result.queueUrl());
    }


    @SneakyThrows
    private void createQueue(SqsAsyncClient client, String sqsEndpoint) {
        CreateQueueRequest createQueueRequest = CreateQueueRequest.builder()
                .queueName("person")
                .build();
        CompletableFuture<CreateQueueResponse> result = client.createQueue(createQueueRequest);
        log.info("Fila criada com URL: " + result.get().queueUrl());
    }
}