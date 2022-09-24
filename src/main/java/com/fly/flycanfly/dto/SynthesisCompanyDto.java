package com.fly.flycanfly.dto;

import com.fly.flycanfly.enums.CompanyName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SynthesisCompanyDto {

    private CompanyName companyName;
    private Long numberOfFlights;
}
