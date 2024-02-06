package com.stevemd.onboarding.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Setter
    @Getter
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

    public User_Role getName() {
        return name;
    }

    public void setName(User_Role name) {
        this.name = name;
    }
}
