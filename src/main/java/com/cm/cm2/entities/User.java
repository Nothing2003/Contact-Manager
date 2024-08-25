package com.cm.cm2.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class User {

    @Id
    private String userId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    @Column(length = 10000)
    private String about;
    @Column(columnDefinition = "TEXT", length = 10000)
    private String profilePic;
    private boolean enable = false;
    private boolean emailVefied = false;
    private boolean phoneVerified = false;
    private Providers provider = Providers.SELF;
    private String providerUserId;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();

}
