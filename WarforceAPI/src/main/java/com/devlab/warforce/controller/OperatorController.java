package com.devlab.warforce.controller;

import com.devlab.warforce.dto.operator.CreateOperatorDto;
import com.devlab.warforce.dto.operator.OperatorDto;
import com.devlab.warforce.dto.operator.UpdateOperatorDto;
import com.devlab.warforce.service.OperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/operators")
@RequiredArgsConstructor
public class OperatorController {

    private final OperatorService operatorService;
    private final JmsTemplate jmsTemplate;

    @GetMapping
    public ResponseEntity<List<OperatorDto>> getOperators() {
        List<OperatorDto> operatorDtos = operatorService.getOperators();

        jmsTemplate.convertAndSend("Getting all operators - Operators count: " + operatorDtos.size() + "\n\n");

        return ResponseEntity.ok(operatorDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperatorDto> getOperator(@PathVariable Integer id) {
        OperatorDto operatorDto = operatorService.getOperator(id);

        jmsTemplate.convertAndSend("Getting operator: " + operatorDto + "\n\n");

        return ResponseEntity.ok(operatorDto);
    }

    @PostMapping
    public ResponseEntity<OperatorDto> createOperator(@RequestBody CreateOperatorDto createOperatorDto) {
        OperatorDto operatorDto = operatorService.createOperator(createOperatorDto);

        jmsTemplate.convertAndSend("Created operator: " + operatorDto + "\n\n");

        return ResponseEntity.created(URI.create("/api/operators/" + operatorDto.getId())).body(operatorDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OperatorDto> updateOperator(@PathVariable Integer id, @RequestBody UpdateOperatorDto updateOperatorDto) {
        OperatorDto operatorDto = operatorService.updateOperator(id, updateOperatorDto);

        jmsTemplate.convertAndSend("Updated operator: " + operatorDto + "\n\n");

        return ResponseEntity.ok(operatorDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOperator(@PathVariable Integer id) {
        operatorService.deleteOperator(id);

        jmsTemplate.convertAndSend("Deleted operator with ID: " + id + "\n\n");

        return ResponseEntity.ok(id);
    }
}
