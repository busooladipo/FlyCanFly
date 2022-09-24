package com.fly.flycanfly.dto;

import com.fly.flycanfly.entities.FlightCriteria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookmarkDto {
    private String title;
    private int numberOfFlights;
    private FlightCriteria flightCriteria;

}
