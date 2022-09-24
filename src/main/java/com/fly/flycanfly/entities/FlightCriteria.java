package com.fly.flycanfly.entities;

import com.fly.flycanfly.enums.CompanyName;
import com.fly.flycanfly.enums.FlightType;
import com.fly.flycanfly.enums.TravelType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class FlightCriteria {

    private CompanyName companyName;
    private FlightType flightType;
    private TravelType travelType;
    private double fareMin;
    private double fareMax;
    private LocalDate departureDateMin;
    private LocalDate departureDateMax;
    private LocalDate arrivalDateMin;
    private LocalDate arrivalDateMax;
    private LocalDate backDateMin;
    private LocalDate backDateMax;
    private LocalTime departureTimeMin;
    private LocalTime departureTimeMax;
    private LocalTime arrivalTimeMin;
    private LocalTime arrivalTimeMax;
    private LocalTime backTimeMin;
    private LocalTime backTimeMax;
    private String departureLocation;
    private String arrivalLocation;
    private LocalTime flightDurationMin;
    private LocalTime flightDurationMax;
    private LocalTime connectionDurationMin;
    private LocalTime connectionDurationMax;
    private String aircraftType;

}
