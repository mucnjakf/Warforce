package com.devlab.warforce.service;

import com.devlab.warforce.dto.platoon.CreatePlatoonDto;
import com.devlab.warforce.dto.platoon.PlatoonDto;
import com.devlab.warforce.dto.platoon.PlatoonOperatorDto;
import com.devlab.warforce.dto.platoon.UpdatePlatoonDto;
import com.devlab.warforce.entity.Platoon;
import com.devlab.warforce.repository.PlatoonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class PlatoonService {

    private final PlatoonRepository platoonRepository;

    public List<PlatoonDto> getPlatoons() {
        Iterable<Platoon> platoons = platoonRepository.findAll();

        return StreamSupport.stream(platoons.spliterator(), false)
                .map(this::mapPlatoonToPlatoonDto)
                .toList();
    }

    public PlatoonDto getPlatoon(Integer id) {
        Platoon platoon = getPlatoonEntity(id);

        return mapPlatoonToPlatoonDto(platoon);
    }

    public PlatoonDto createPlatoon(CreatePlatoonDto createPlatoonDto) {
        Platoon platoon = Platoon
                .builder()
                .name(createPlatoonDto.getName())
                .commander(createPlatoonDto.getCommander())
                .location(createPlatoonDto.getLocation())
                .build();

        platoonRepository.save(platoon);

        return mapPlatoonToPlatoonDto(platoon);
    }

    public PlatoonDto updatePlatoon(Integer id, UpdatePlatoonDto updatePlatoonDto) {
        Platoon platoon = getPlatoonEntity(id);

        platoon.setName(updatePlatoonDto.getName());
        platoon.setCommander(updatePlatoonDto.getCommander());
        platoon.setLocation(updatePlatoonDto.getLocation());

        platoonRepository.save(platoon);

        return mapPlatoonToPlatoonDto(platoon);
    }

    public void deletePlatoon(Integer id) {
        Platoon platoon = getPlatoonEntity(id);

        platoonRepository.delete(platoon);
    }

    public Platoon getPlatoonEntity(Integer id) {
        return platoonRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Platoon not found"));
    }

    private PlatoonDto mapPlatoonToPlatoonDto(Platoon platoon) {
        List<PlatoonOperatorDto> operators = new ArrayList<>();

        if (platoon.getOperators() != null) {
            operators = platoon
                    .getOperators()
                    .stream()
                    .map(x -> PlatoonOperatorDto
                            .builder()
                            .id(x.getId())
                            .firstName(x.getFirstName())
                            .lastName(x.getLastName())
                            .dateOfBirth(x.getDateOfBirth())
                            .gender(x.getGender())
                            .build()
                    ).toList();
        }

        return PlatoonDto
                .builder()
                .id(platoon.getId())
                .name(platoon.getName())
                .commander(platoon.getCommander())
                .location(platoon.getLocation())
                .operators(operators)
                .build();
    }
}
