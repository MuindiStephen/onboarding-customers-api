
package com.stevemd.onboarding.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @User is a POJO class [Data Model] to represent database programming.
 * It holds or represents persistent data or data that can be sustained even if
 * the process is changed or removed.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(
        name = "customers"
//        ,
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = "name"),
//                @UniqueConstraint(columnNames = "email")
//        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "customer_sequence")
    @SequenceGenerator(name = "customer_sequence",sequenceName = "customer_sequence",allocationSize = 1)
    private Long id;


    @NotBlank
    @Size(max = 20)
    @Getter
    @Setter
    @Column(name = "name",nullable = false)
    private String name;

    @Getter
    @Setter
    @NotBlank
    @Size(max = 30)
    @Column(name = "email",nullable = false)
    private String email;

    @Getter
    @Setter
    @NotBlank
    @Size(max = 150)
    @Column(name = "password",nullable = false)
    private String password;
}
