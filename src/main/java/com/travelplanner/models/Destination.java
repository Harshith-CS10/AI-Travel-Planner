package com.travelplanner.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "destinations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String description;

    @Column
    private String bestTimeToVisit;

    @Column
    private Double averageCost;

    @ManyToMany(mappedBy = "destinations")
    private List<Itinerary> itineraries;

    public void setDetails(String details) {
        this.description = details;
    }
}
