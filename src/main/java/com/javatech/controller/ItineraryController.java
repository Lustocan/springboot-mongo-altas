package com.javatech.controller;

import com.javatech.model.Itinerary;
import com.javatech.service.ItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ControllerAdvice
@RequestMapping("/itineraries")
public class ItineraryController {
    @Autowired
    private ItineraryService service ;
    @PostMapping()
    public ResponseEntity<Itinerary> createIt(@RequestBody Itinerary itinerary){
        Itinerary el = service.createIt(itinerary);

        if(el==null) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        return new ResponseEntity<>(el,HttpStatus.CREATED);
    }

    @GetMapping("/{province}")
    public ResponseEntity<List<Itinerary>> getItinerariesByProvince(@PathVariable String province){
        List<Itinerary> cur = service.getItinerariesByProvince(province);
        if(cur==null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
        return new ResponseEntity<>(cur, HttpStatus.OK);
    }

    @GetMapping("/{province}/{pathname}")
    public ResponseEntity<Itinerary> getItinerariesByPathName(@PathVariable String province,@PathVariable String pathname){
        Itinerary cur = service.getItinerariesByPathName(pathname);
        if(cur==null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
        return new ResponseEntity<>(cur, HttpStatus.OK);
    }
}
