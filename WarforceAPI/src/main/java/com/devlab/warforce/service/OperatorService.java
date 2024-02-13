package com.devlab.warforce.service;

import com.devlab.warforce.dto.operator.CreateOperatorDto;
import com.devlab.warforce.dto.operator.OperatorDto;
import com.devlab.warforce.dto.operator.OperatorPlatoonDto;
import com.devlab.warforce.dto.operator.UpdateOperatorDto;
import com.devlab.warforce.entity.Operator;
import com.devlab.warforce.entity.Platoon;
import com.devlab.warforce.repository.OperatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class OperatorService {

    private final OperatorRepository operatorRepository;
    private final PlatoonService platoonService;

    public List<OperatorDto> getOperators() {
        Iterable<Operator> operators = operatorRepository.findAll();

        return StreamSupport.stream(operators.spliterator(), false)
                .map(this::mapOperatorToOperatorDto)
                .toList();
    }

    public OperatorDto getOperator(Integer id) {
        Operator operator = getOperatorEntity(id);

        return mapOperatorToOperatorDto(operator);
    }

    public OperatorDto createOperator(CreateOperatorDto createOperatorDto) {
        Platoon platoon = platoonService.getPlatoonEntity(createOperatorDto.getPlatoonId());

        LocalDateTime dob = LocalDate.parse(createOperatorDto.getDateOfBirth()).atStartOfDay();

        Operator operator = Operator
                .builder()
                .firstName(createOperatorDto.getFirstName())
                .lastName(createOperatorDto.getLastName())
                .dateOfBirth(dob)
                .gender(createOperatorDto.getGender())
                .platoon(platoon)
                .build();

        operatorRepository.save(operator);

        return mapOperatorToOperatorDto(operator);
    }

    public OperatorDto updateOperator(Integer id, UpdateOperatorDto updateOperatorDto) {
        Platoon platoon = platoonService.getPlatoonEntity(updateOperatorDto.getPlatoonId());
        LocalDateTime dob = LocalDate.parse(updateOperatorDto.getDateOfBirth()).atStartOfDay();

        Operator operator = getOperatorEntity(id);

        operator.setFirstName(updateOperatorDto.getFirstName());
        operator.setLastName(updateOperatorDto.getLastName());
        operator.setDateOfBirth(dob);
        operator.setGender(updateOperatorDto.getGender());
        operator.setPlatoon(platoon);

        operatorRepository.save(operator);

        return mapOperatorToOperatorDto(operator);
    }

    public void deleteOperator(Integer id) {
        Operator operator = getOperatorEntity(id);

        operatorRepository.delete(operator);
    }

    private Operator getOperatorEntity(Integer id) {
        return operatorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Operator not found"));
    }

    private OperatorDto mapOperatorToOperatorDto(Operator operator) {
        return OperatorDto
                .builder()
                .id(operator.getId())
                .firstName(operator.getFirstName())
                .lastName(operator.getLastName())
                .dateOfBirth(operator.getDateOfBirth())
                .gender(operator.getGender())
                .platoon(
                        OperatorPlatoonDto
                                .builder()
                                .id(operator.getPlatoon().getId())
                                .name(operator.getPlatoon().getName())
                                .commander(operator.getPlatoon().getCommander())
                                .location(operator.getPlatoon().getLocation())
                                .build()
                )
                .build();
    }
}
