package com.devlab.warforce.dto.platoon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlatoonDto {

    private Integer id;

    private String name;

    private String commander;

    private String location;

    private List<PlatoonOperatorDto> operators;
}
