package com.alaaturki.novadesk.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role extends BaseEntity {


    @Column(nullable = false, unique = true)
    private String name;


    @OneToMany(mappedBy = "role")
    private List<User> users;

}