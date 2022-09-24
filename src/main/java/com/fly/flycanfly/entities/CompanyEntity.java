package com.fly.flycanfly.entities;

import com.fly.flycanfly.enums.CompanyName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long companyId;

    @Column(name = "company_name")
    @Enumerated(value = EnumType.STRING)
    private CompanyName companyName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "companyEntity")
    private Set<FlightEntity> flightEntitySet;

    @ElementCollection
    @CollectionTable(name = "cabin_details", joinColumns = @JoinColumn(name = "company_id"))
    private Set<CabinDetail> cabinDetailSet;

    @ElementCollection
    @CollectionTable(name = "inflight_infos", joinColumns = @JoinColumn(name = "company_id"))
    private Set<InflightInfo> inflightInfoSet;


}

