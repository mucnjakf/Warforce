package com.devlab.warforce.dto.platoon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePlatoonDto {

    private String name;

    private String commander;

    private String location;
}
