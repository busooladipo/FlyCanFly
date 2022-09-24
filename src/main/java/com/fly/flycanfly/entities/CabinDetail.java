package com.fly.flycanfly.entities;

import com.fly.flycanfly.enums.CabinClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CabinDetail {

    @Enumerated(value = EnumType.STRING)
    private CabinClass cabinClass;
    private String baggage;
    private String cancellation;
    private String rebooking;
    private String refund;
    private Double fare;

}
