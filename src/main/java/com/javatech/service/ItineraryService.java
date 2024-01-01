package com.javatech.service;

import com.javatech.model.Itinerary;
import com.javatech.repository.ItineraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ItineraryService {
    @Autowired
    private ItineraryRepository repository ;

    public List<Itinerary> getItinerariesByProvince(String province){
        if(province==null) return null ;
        ArrayList<Itinerary> cur = (ArrayList)repository.findAll();
        List<Itinerary> ret = new ArrayList<>();

        for(int i=0; i<cur.size(); i++){
            if(cur.get(i).getProvince().equals(province)){
                ret.add(cur.get(i));
            }
        }
        return ret ;
    }

    public Itinerary getItinerariesByPathName(String pathname){
        if(pathname==null) return null ;
        List<Itinerary> cur = repository.findAll();
        ArrayList<Itinerary> arr = (ArrayList)cur;
        for(int i=0; i<arr.size(); i++){
            if(arr.get(i).getName().equals(pathname)){
                return arr.get(i);
            }
        }
        return null;
    }

    public Itinerary createIt(Itinerary itinerary){
        if(itinerary.getName()==null||itinerary.getStart()==null
                ||itinerary.getProvince()==null
                ||itinerary.getEnd()==null
                ||itinerary.getLength()==null
                ||itinerary.getLatitude().length==0
                ||itinerary.getLongitude().length==0
                ||itinerary.getPointNames().length==0){

            return null;
        }
        itinerary.setItId(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(itinerary);
    }
}
