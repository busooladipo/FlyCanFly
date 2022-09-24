package com.fly.flycanfly.repositories;

import com.fly.flycanfly.entities.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepo extends FlightRepoCustom, JpaRepository<FlightEntity, Long> {
}
