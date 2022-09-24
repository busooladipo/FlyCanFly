package com.fly.flycanfly.repositories.impl;

import com.fly.flycanfly.dto.SynthesisCompanyDto;
import com.fly.flycanfly.entities.FlightCriteria;
import com.fly.flycanfly.entities.FlightEntity;
import com.fly.flycanfly.entities.SynthesisCriteria;
import com.fly.flycanfly.enums.CompanyName;
import com.fly.flycanfly.repositories.FlightRepoCustom;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class FlightRepoImpl implements FlightRepoCustom {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<FlightEntity> searchFlight(FlightCriteria flightCriteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FlightEntity> criteriaQuery = criteriaBuilder.createQuery(FlightEntity.class);
        Root<FlightEntity> flightEntityRoot = criteriaQuery.from(FlightEntity.class);
        List<Predicate> predicates = new ArrayList<>();

        if (flightCriteria.getFlightType() != null) {
            predicates.add(criteriaBuilder.equal(flightEntityRoot.get("flightType"), flightCriteria.getFlightType()));
        }
        if (flightCriteria.getTravelType() != null) {
            predicates.add(criteriaBuilder.equal(flightEntityRoot.get("travelType"), flightCriteria.getTravelType()));
        }
        if (flightCriteria.getCompanyName() != null) {
            predicates.add(criteriaBuilder.equal(flightEntityRoot.get("companyEntity").get("companyName"), flightCriteria.getCompanyName()));
        }
        if (flightCriteria.getDepartureLocation() != null) {
            predicates.add(criteriaBuilder.like(flightEntityRoot.get("departureLocation"), "%" + flightCriteria.getDepartureLocation() + "%"));
        }
        if (flightCriteria.getArrivalLocation() != null) {
            predicates.add(criteriaBuilder.like(flightEntityRoot.get("arrivalLocation"), "%" + flightCriteria.getArrivalLocation() + "%"));
        }
        if (flightCriteria.getAircraftType() != null) {
            predicates.add(criteriaBuilder.like(flightEntityRoot.get("aircraftType"), "%" + flightCriteria.getAircraftType() + "%"));
        }
        if (flightCriteria.getDepartureDateMax() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(flightEntityRoot.get("departureDate"), flightCriteria.getDepartureDateMax()));
        }
        if (flightCriteria.getDepartureDateMin() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(flightEntityRoot.get("departureDate"), flightCriteria.getDepartureDateMin()));
        }
        if (flightCriteria.getDepartureTimeMax() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(flightEntityRoot.get("departureTime"), flightCriteria.getDepartureTimeMax()));
        }
        if (flightCriteria.getDepartureTimeMin() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(flightEntityRoot.get("departureTime"), flightCriteria.getDepartureTimeMin()));
        }
        if (flightCriteria.getArrivalDateMax() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(flightEntityRoot.get("arrivalDate"), flightCriteria.getArrivalDateMax()));
        }
        if (flightCriteria.getArrivalDateMin() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(flightEntityRoot.get("arrivalDate"), flightCriteria.getArrivalDateMin()));
        }
        if (flightCriteria.getArrivalTimeMax() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(flightEntityRoot.get("arrivalTime"), flightCriteria.getArrivalTimeMax()));
        }
        if (flightCriteria.getArrivalTimeMin() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(flightEntityRoot.get("arrivalTime"), flightCriteria.getArrivalTimeMin()));
        }
        if (flightCriteria.getBackDateMax() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(flightEntityRoot.get("backDate"), flightCriteria.getBackDateMax()));
        }
        if (flightCriteria.getBackDateMin() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(flightEntityRoot.get("backDate"), flightCriteria.getBackDateMin()));
        }
        if (flightCriteria.getBackTimeMax() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(flightEntityRoot.get("backTime"), flightCriteria.getBackTimeMax()));
        }
        if (flightCriteria.getBackTimeMin() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(flightEntityRoot.get("backTime"), flightCriteria.getBackTimeMin()));
        }

        if (flightCriteria.getConnectionDurationMax() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(flightEntityRoot.get("connectionDuration"), flightCriteria.getConnectionDurationMax()));
        }
        if (flightCriteria.getConnectionDurationMin() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(flightEntityRoot.get("connectionDuration"), flightCriteria.getConnectionDurationMin()));
        }
        if (flightCriteria.getFlightDurationMax() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(flightEntityRoot.get("flightDuration"), flightCriteria.getFlightDurationMax()));
        }
        if (flightCriteria.getFlightDurationMin() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(flightEntityRoot.get("flightDuration"), flightCriteria.getFlightDurationMin()));
        }


        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Long getNumberOfFlights(SynthesisCriteria synthesisCriteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<FlightEntity> flightEntityRoot = criteriaQuery.from(FlightEntity.class);
        criteriaQuery.select(criteriaBuilder.count(flightEntityRoot));

        List<Predicate> predicates = new ArrayList<>();

        if (synthesisCriteria.getDepartureDateMax() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(flightEntityRoot.get("departureDate"), synthesisCriteria.getDepartureDateMax()));
        }
        if (synthesisCriteria.getDepartureDateMin() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(flightEntityRoot.get("departureDate"), synthesisCriteria.getDepartureDateMin()));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public List<SynthesisCompanyDto> getNumberOfFlightsByCompany(SynthesisCriteria synthesisCriteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SynthesisCompanyDto> criteriaQuery = criteriaBuilder.createQuery(SynthesisCompanyDto.class);
        Root<FlightEntity> flightEntityRoot = criteriaQuery.from(FlightEntity.class);
        Expression<CompanyName> groupByExp = flightEntityRoot.get("companyEntity").get("companyName").as(CompanyName.class);
        Expression<Long> countExp = criteriaBuilder.count(groupByExp);
        criteriaQuery.multiselect(groupByExp, countExp);
        criteriaQuery.groupBy(groupByExp);

        List<Predicate> predicates = new ArrayList<>();

        if (synthesisCriteria.getDepartureDateMax() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(flightEntityRoot.get("departureDate"), synthesisCriteria.getDepartureDateMax()));
        }
        if (synthesisCriteria.getDepartureDateMin() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(flightEntityRoot.get("departureDate"), synthesisCriteria.getDepartureDateMin()));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
