package com.alaaturki.novadesk.entity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity {


    @Column(
            unique = true,
            nullable = false
    )
    private String name;


}