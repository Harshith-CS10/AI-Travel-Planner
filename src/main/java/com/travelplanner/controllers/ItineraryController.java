package com.travelplanner.controllers;

import com.travelplanner.models.Itinerary;
import com.travelplanner.services.ItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/itineraries")
public class ItineraryController {

    @Autowired
    private ItineraryService itineraryService;

    // Fetch all itineraries
    @GetMapping
    public List<Itinerary> getAllItineraries() {
        return itineraryService.getAllItineraries();
    }

    // Fetch a single itinerary by ID


    // Create a new itinerary
    @PostMapping
    public Itinerary createItinerary(@RequestBody Itinerary itinerary) {
        return itineraryService.createItinerary(itinerary);
    }

    // Update an existing itinerary
//    @PutMapping("/{id}")
//    public Itinerary updateItinerary(@PathVariable Long id, @RequestBody Itinerary updatedItinerary) {
//        return itineraryService.updateItinerary(id, updatedItinerary);
//    }

    // Delete an itinerary
    @DeleteMapping("/{id}")
    public String deleteItinerary(@PathVariable Long id) {
        return itineraryService.deleteItinerary(id);
    }
}
