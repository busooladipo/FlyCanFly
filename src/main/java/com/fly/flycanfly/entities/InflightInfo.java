package com.fly.flycanfly.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class InflightInfo {

    private String title;
    private String description;

}
