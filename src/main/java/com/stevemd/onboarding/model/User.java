
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "customer_sequence")
    @SequenceGenerator(name = "customer_sequence",sequenceName = "customer_sequence",allocationSize = 1)
    private Long id;


    @Getter
    @Setter
    @Column(name = "name",nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(name = "email",nullable = false)
    private String email;

    @Getter
    @Setter
    @Column(name = "password",nullable = false)
    private String password;
}
