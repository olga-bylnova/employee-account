package com.innowise.accounting.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
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
public class EmployeeUpdateDto {
    @EqualsAndHashCode.Exclude
    private Long id;
    private String firstName;
    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate birthday;
    private String email;
    private String password;
    private Role role;
}
