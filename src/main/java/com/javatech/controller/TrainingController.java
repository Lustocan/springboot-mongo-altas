package com.javatech.controller;

import com.javatech.model.Training;
import com.javatech.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ControllerAdvice
@RequestMapping("/training")
public class TrainingController {

    @Autowired
    private TrainingService service ;

    @PostMapping()
    public ResponseEntity<Training> createTr(@RequestBody Training training){
        return new ResponseEntity<>(service.createTr(training),HttpStatus.CREATED) ;
    }

    @GetMapping("/myList")
    public ResponseEntity<List<Training>> getByUsername(@RequestHeader String username){
        List<Training> cur = service.getTrainingByUserId(username);
        if(cur==null) return null ;
        return new ResponseEntity<>(cur, HttpStatus.OK);
    }

    @DeleteMapping("/{trId}")
    public ResponseEntity<String> deleteUser(@PathVariable String trId){
        return new ResponseEntity<>(service.deleteTr(trId), HttpStatus.OK);
    }
}
