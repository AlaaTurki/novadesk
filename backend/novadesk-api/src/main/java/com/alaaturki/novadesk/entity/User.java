package com.alaaturki.novadesk.entity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {


    @Column(nullable = false)
    private String username;



    @Column(
            unique = true,
            nullable = false
    )
    private String email;



    @Column(nullable = false)
    private String password;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "role_id"
    )
    private Role role;


}