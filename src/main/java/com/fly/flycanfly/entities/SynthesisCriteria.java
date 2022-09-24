package com.fly.flycanfly.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SynthesisCriteria {
    private LocalDate departureDateMax;
    private LocalDate departureDateMin;
}
