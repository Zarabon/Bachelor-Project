package com.example.TykPlugin4Tyk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import org.springframework.web.bind.annotation.RestController;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.DataFetcher;

import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class Backend {

    public static void main(String[] args) {
        SpringApplication.run(Backend.class, args);
        System.out.println("GraphQL server running on http://localhost:8080/graphql");
    }

    private final List<Map<String, Object>> bewohnerData = List.of(
            Map.of(
                    "id", "B0001",
                    "rolle", "Bewohner",
                    "vorname", "Susanne",
                    "nachname", "Müller",
                    "geschlecht", "Weiblich",
                    "religion", "keine",
                    "telefonnummer", "+49 15201788987",
                    "AGBsUnterschrieben", true,
                    "versicherung", Map.of(
                            "name", "TKK",
                            "versicherungsnummer", "tkk0815SuMueller"
                    ),
                    "notfall", Map.of(
                            "blutgruppe", "0+",
                            "allergien", "Mais, Knoblauch",
                            "notfallkontakt", Map.of(
                                    "id", "NK0011",
                                    "rolle", "",
                                    "vorname", "Sören",
                                    "nachname", "Müller",
                                    "geschlecht", "Männlich",
                                    "religion", "",
                                    "telefonnummer", "+49 15201788987"
                            ),
                            "hausarzt", Map.of(
                                    "id", "A0002",
                                    "rolle", "Arzt",
                                    "vorname", "Moritz",
                                    "nachname", "Hempel",
                                    "geschlecht", "Männlich",
                                    "religion", "",
                                    "telefonnummer", "+49 0201112254"
                            )
                    )
            )
    );

    @Bean
    RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder
                .type("Query", builder -> builder
                        .dataFetcher("bewohner", getBewohnerFetcher())
                );
    }

    private DataFetcher<List<Map<String, Object>>> getBewohnerFetcher() {
        return dataFetchingEnvironment -> bewohnerData;
    }
}


