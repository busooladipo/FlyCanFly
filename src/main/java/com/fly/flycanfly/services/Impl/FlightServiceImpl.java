package com.fly.flycanfly.services.Impl;

import com.fly.flycanfly.dto.SynthesisCompanyDto;
import com.fly.flycanfly.entities.FlightCriteria;
import com.fly.flycanfly.entities.FlightEntity;
import com.fly.flycanfly.entities.SynthesisCriteria;
import com.fly.flycanfly.repositories.FlightRepo;
import com.fly.flycanfly.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightRepo flightRepo;

    @Override
    public FlightEntity addFlight(FlightEntity flight) {
        return flightRepo.save(flight);
    }

    @Override
    public List<FlightEntity> getAllFlights() {
        return flightRepo.findAll();
    }

    @Override
    public List<FlightEntity> searchFlight(FlightCriteria flightCriteria) {
        return flightRepo.searchFlight(flightCriteria);
    }

    @Override
    public FlightEntity getFlights(Long idFlight) {
        return flightRepo.findById(idFlight).orElse(null);
    }

    @Override
    public Long getNumberOfFlights(SynthesisCriteria synthesisCriteria) {
        return flightRepo.getNumberOfFlights(synthesisCriteria);
    }

    @Override
    public List<SynthesisCompanyDto> getNumberOfFlightsByCompany(SynthesisCriteria synthesisCriteria) {
        return flightRepo.getNumberOfFlightsByCompany(synthesisCriteria);
    }
}
