package com.travelplanner.repositories;

import com.travelplanner.models.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
    Destination findByName(String name);  // Custom query to find destinations by name
}
