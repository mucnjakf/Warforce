package com.devlab.warforce.dto.operator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperatorPlatoonDto {

    private Integer id;

    private String name;

    private String commander;

    private String location;
}
