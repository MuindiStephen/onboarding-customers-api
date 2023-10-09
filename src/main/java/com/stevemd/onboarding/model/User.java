
package com.stevemd.onboarding.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence",sequenceName = "user_sequence",allocationSize = 1)
    private Long id;

    @Column(name = "email",nullable = false)
    private String name;

    @Column(name = "name",nullable = false)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;
}
