package dev.shermende.kafkadlqretry;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.UUID;
import java.util.stream.IntStream;

@Slf4j
@EnableKafka
@EnableCaching
@SpringBootApplication
@RequiredArgsConstructor
public class KafkaDlqRetryApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(KafkaDlqRetryApplication.class, args);
    }

    private final KafkaTemplate<Object, Object> template;

    @Override
    public void run(String... args) {
        IntStream.range(0, 300).forEach(i -> template.send(
            new ProducerRecord<>(
                "main",
                UUID.randomUUID().toString()
            )
        ));
    }

}