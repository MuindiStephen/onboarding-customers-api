
package com.stevemd.onboarding.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * @User is a POJO class [Data Model] to represent database programming.
 * It holds or represents persistent data or data that can be sustained even if
 * the process is changed or removed.
 *
 * Object Relational Mapping.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "fieldagents")
public class UserFieldAgent {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;

    @Column(name = "name",nullable = false, unique = true)
    private String name;

    @Column(name = "email",nullable = false,unique = true)
    private String email;
    @Column(name = "password",nullable = false)
    private String password;



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @ElementCollection(targetClass = Role.class) // establish relationship btwn users and their roles
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", nullable = false)
    private Set<Role> roles;
}
