package com.fly.flycanfly.repositories;

import com.fly.flycanfly.dto.SynthesisCompanyDto;
import com.fly.flycanfly.entities.FlightCriteria;
import com.fly.flycanfly.entities.FlightEntity;
import com.fly.flycanfly.entities.SynthesisCriteria;

import java.util.List;

public interface FlightRepoCustom {
    List<FlightEntity> searchFlight(FlightCriteria flightCriteria);

    Long getNumberOfFlights(SynthesisCriteria synthesisCriteria);

    List<SynthesisCompanyDto> getNumberOfFlightsByCompany(SynthesisCriteria synthesisCriteria);
}
