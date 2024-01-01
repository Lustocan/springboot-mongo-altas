package com.javatech.repository;

import com.javatech.model.Itinerary;
import com.javatech.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItineraryRepository extends MongoRepository<Itinerary, String>{}
