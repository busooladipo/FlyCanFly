package com.fly.flycanfly.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@Setter
@Getter
public class UserAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUserAccount;
    @Column
    private String userName;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String confirmPassword;
    @Column
    private boolean enabled;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccountEntity")
    private Set<Bookmark> bookmarks;


    public UserAccountEntity() {
        this.enabled = false;
    }
}
