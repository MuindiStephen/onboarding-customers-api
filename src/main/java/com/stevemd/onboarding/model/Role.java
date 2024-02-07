package com.stevemd.onboarding.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "roles")
public class Role {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, name = "role_name")
    private User_Role name;

    public Role() {

    }

    public Role(User_Role name) {
        this.name = name;
    }

    public void setName(User_Role name) {
        this.name = name;
    }
}
