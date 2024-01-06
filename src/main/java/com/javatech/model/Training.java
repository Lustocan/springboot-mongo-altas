package com.javatech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "training")
@AllArgsConstructor
@NoArgsConstructor
public class Training {
    @Id
    private String id         ;
    private String username       ;
    private String time      ;
    private String kilometers   ;
    private String kilocalories ;
    private String steps        ;
    private String date           ;
}
