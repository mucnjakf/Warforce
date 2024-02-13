package com.devlab.warforce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "platoons")
public class Platoon {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String commander;

    private String location;

    @OneToMany(mappedBy = "platoon")
    private List<Operator> operators;

}
