package com.devlab.warforce.entity;

import com.devlab.warforce.enumeration.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "operators")
public class Operator {

    @Id
    @GeneratedValue
    private Integer id;

    private String firstName;

    private String lastName;

    private LocalDateTime dateOfBirth;

    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "platoon_id", nullable = false)
    private Platoon platoon;
}
