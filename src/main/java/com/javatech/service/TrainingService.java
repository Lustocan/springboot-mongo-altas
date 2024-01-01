package com.javatech.service;

import com.javatech.model.Training;
import com.javatech.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TrainingService {

    @Autowired
    private TrainingRepository repository ;

    public List<Training> getTrainingByUserId(String username){
        if(username==null) return null ;
        ArrayList<Training> cur = (ArrayList) repository.findAll();
        List<Training> ret = new ArrayList<>();

        for(int i=0; i<cur.size(); i++){
            if(cur.get(i).getUsername().equals(username)){
                ret.add(cur.get(i));
            }
        }
        return ret ;
    }

    public Training createTr(Training training){
        training.setTrId(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(training);
    }
}
