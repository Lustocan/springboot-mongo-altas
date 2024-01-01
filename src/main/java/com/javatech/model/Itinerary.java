package com.javatech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "itinerary")
@AllArgsConstructor
@NoArgsConstructor
public class Itinerary {
    @Id
    private String itId             ;
    private String name             ;
    private String province         ;
    private String length           ;
    private String start            ;
    private String end              ;
    private float latitude[]        ;
    private float longitude[]       ;
    private String pointNames[]     ;
}
