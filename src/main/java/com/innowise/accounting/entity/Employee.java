package com.innowise.accounting.entity;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Employee {
    @EqualsAndHashCode.Exclude
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String email;
    private String password;
    private Role role;
}
