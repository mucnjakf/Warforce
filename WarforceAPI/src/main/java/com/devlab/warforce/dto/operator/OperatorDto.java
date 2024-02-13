package com.devlab.warforce.dto.operator;

import com.devlab.warforce.enumeration.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperatorDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private LocalDateTime dateOfBirth;

    private Gender gender;

    private OperatorPlatoonDto platoon;
}
