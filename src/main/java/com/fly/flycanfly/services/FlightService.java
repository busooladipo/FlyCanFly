package com.fly.flycanfly.services;

import com.fly.flycanfly.dto.SynthesisCompanyDto;
import com.fly.flycanfly.entities.FlightCriteria;
import com.fly.flycanfly.entities.FlightEntity;
import com.fly.flycanfly.entities.SynthesisCriteria;

import java.util.List;

public interface FlightService {
    FlightEntity addFlight(FlightEntity flight);

    List<FlightEntity> getAllFlights();

    List<FlightEntity> searchFlight(FlightCriteria flightCriteria);

    FlightEntity getFlights(Long idFlight);

    Long getNumberOfFlights(SynthesisCriteria synthesisCriteria);

    List<SynthesisCompanyDto> getNumberOfFlightsByCompany(SynthesisCriteria synthesisCriteria);
}
