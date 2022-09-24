package com.fly.flycanfly.dto;

import com.fly.flycanfly.entities.CabinDetail;
import com.fly.flycanfly.entities.InflightInfo;
import com.fly.flycanfly.enums.CompanyName;
import com.fly.flycanfly.enums.FlightType;
import com.fly.flycanfly.enums.TravelType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto {
    private Long idFlight;
    private CompanyName companyName;
    private FlightType flightType;
    private TravelType travelType;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private LocalDate backDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private LocalTime backTime;
    private String departureLocation;
    private String arrivalLocation;
    private LocalTime flightDuration;
    private LocalTime connectionDuration;
    private String aircraftType;
    private Set<CabinDetail> cabinDetailSet;
    private Set<InflightInfo> inflightInfoSet;
    private Set<String> comforts;
}
