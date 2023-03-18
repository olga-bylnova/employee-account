package com.innowise.accounting.dto;

import com.innowise.accounting.entity.Role;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class EmployeeSaveDto {
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String email;
    private String password;
    private Role role;
}
