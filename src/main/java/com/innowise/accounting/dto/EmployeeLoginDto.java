package com.innowise.accounting.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class EmployeeLoginDto {
    private String email;
    private String password;
}
