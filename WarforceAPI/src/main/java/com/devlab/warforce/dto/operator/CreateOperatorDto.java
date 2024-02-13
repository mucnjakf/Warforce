package com.devlab.warforce.dto.operator;

import com.devlab.warforce.enumeration.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOperatorDto {

    private String firstName;

    private String lastName;

    private String dateOfBirth;

    private Gender gender;

    private Integer platoonId;
}
