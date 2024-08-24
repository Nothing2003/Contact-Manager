package com.cm.cm2.entities;

import jakarta.persistence.*;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table
public class SocialLink {

    @Id
    private int id;
    private String link;
    private String title;
    @ManyToOne

    private Contact contact;
}
