
package com.stevemd.onboarding.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(
        name = "customers",
        uniqueConstraints = {

        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence",sequenceName = "user_sequence",allocationSize = 1)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;
}
