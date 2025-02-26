package com.travelplanner.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.travelplanner.models.Destination;
import com.travelplanner.models.Itinerary;
import com.travelplanner.repositories.ItineraryRepository;
import com.travelplanner.repositories.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItineraryService {


    @Autowired
    private ItineraryRepository itineraryRepository;

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();



    public Itinerary generateAIItinerary(String destinationName, int days) {
        // Send request
//        String response = restTemplate.postForObject(fullApiUrl, entity, String.class);
//        System.out.println("AI Response: " + response); // Debug Log

        try {
            System.out.println("Requesting AI Itinerary for " + destinationName + " for " + days + " days."); // Debug Log

            // Construct prompt
            String prompt = "Generate a " + days + "-day travel itinerary for " + destinationName +
                    " with details about places to visit, activities, and estimated cost.";

            // Construct JSON request
            ObjectNode requestBody = objectMapper.createObjectNode();
            ArrayNode contents = objectMapper.createArrayNode();
            ObjectNode content = objectMapper.createObjectNode();
            ArrayNode parts = objectMapper.createArrayNode();
            ObjectNode textPart = objectMapper.createObjectNode();

            textPart.put("text", prompt);
            parts.add(textPart);
            content.set("parts", parts);
            contents.add(content);
            requestBody.set("contents", contents);

            // Set HTTP headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + geminiApiKey);

            HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(requestBody), headers);

            // API URL
            String fullApiUrl = GEMINI_API_URL + "?key=" + geminiApiKey;
            System.out.println("Calling API: " + fullApiUrl); // Debug Log

            // Send request
            String response = restTemplate.postForObject(fullApiUrl, entity, String.class);
            System.out.println("AI Response: " + response); // Debug Log

            // Extract AI Response
            String itineraryText = extractItineraryFromResponse(response);
            System.out.println("Extracted Itinerary Text: " + itineraryText); // Debug Log

            if (itineraryText == null || itineraryText.isEmpty()) {
                System.out.println("AI returned an empty response.");
                return null;
            }

            // Convert AI response into a List<Destination> and save them
            List<Destination> destinationList = parseAndSaveDestinations(itineraryText);

            // Save Itinerary to Database
            Itinerary itinerary = new Itinerary();
            itinerary.setTitle(destinationName + " Itinerary");
            itinerary.setDurationInDays(days);
            itinerary.setDestinations(destinationList);

            return itineraryRepository.save(itinerary);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String extractItineraryFromResponse(String response) {
        try {
            JsonNode jsonResponse = objectMapper.readTree(response);
            JsonNode candidates = jsonResponse.path("candidates");

            if (candidates.isArray() && !candidates.isEmpty()) {
                JsonNode content = candidates.get(0).path("content");
                JsonNode parts = content.path("parts");

                if (parts.isArray() && !parts.isEmpty()) {
                    return parts.get(0).path("text").asText();
                }
            }
            return "No itinerary data found.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error processing AI response.";
        }
    }

    private List<Destination> parseAndSaveDestinations(String itineraryText) {
        List<Destination> destinations = new ArrayList<>();
        String[] lines = itineraryText.split("\n");

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;

            // Extract destination name, remove numbering if present
            String destinationName = line.replaceAll("^\\d+\\.\\s*", "").trim();

            // Check if destination already exists
            Destination destination = destinationRepository.findByName(destinationName);

            if (destination == null) {
                destination = new Destination();
                destination.setName(destinationName);
                destination.setDetails("Auto-generated itinerary stop.");
                destination = destinationRepository.save(destination);
            }

            destinations.add(destination);
        }

        return destinations;
    }

    public List<Itinerary> getAllItineraries() {
        return itineraryRepository.findAll();
    }


    public Itinerary createItinerary(Itinerary itinerary) {
        return itineraryRepository.save(itinerary);
    }

//    public Itinerary updateItinerary(Long id, Itinerary updatedItinerary) {
//        return itineraryRepository.findById(id).map(existingItinerary -> {
//            existingItinerary.setDestination(updatedItinerary.getDestination());
//            existingItinerary.setDays(updatedItinerary.getDays());
//            return itineraryRepository.save(existingItinerary);
//        }).orElseThrow(() -> new RuntimeException("Itinerary not found with id " + id));
//    }

    public String deleteItinerary(Long id) {
        if (itineraryRepository.existsById(id)) {
            itineraryRepository.deleteById(id);
            return "Itinerary deleted successfully";
        } else {
            throw new RuntimeException("Itinerary not found with id " + id);
        }
    }
}

