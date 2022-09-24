package com.fly.flycanfly.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBookmark;
    @Column(name = "title")
    private String title;
    @Column(name = "adding_date")
    private LocalDate addingDate;
    @Column(name = "numbe_of_flights")
    private int numberOfFlights;
    @Embedded
    private FlightCriteria flightCriteria;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private UserAccountEntity userAccountEntity;

    public Bookmark() {
        this.addingDate = LocalDate.now();
    }
}
