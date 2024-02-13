package com.devlab.warforce.controller;

import com.devlab.warforce.dto.platoon.CreatePlatoonDto;
import com.devlab.warforce.dto.platoon.PlatoonDto;
import com.devlab.warforce.dto.platoon.UpdatePlatoonDto;
import com.devlab.warforce.service.PlatoonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/platoons")
@RequiredArgsConstructor
public class PlatoonController {

    private final PlatoonService platoonService;
    private final JmsTemplate jmsTemplate;

    @GetMapping
    public ResponseEntity<List<PlatoonDto>> getPlatoons() {
        List<PlatoonDto> platoonDtos = platoonService.getPlatoons();

        jmsTemplate.convertAndSend("Getting all platoons - Platoons count: " + platoonDtos.size() + "\n\n");

        return ResponseEntity.ok(platoonDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlatoonDto> getPlatoon(@PathVariable Integer id) {
        PlatoonDto platoonDto = platoonService.getPlatoon(id);

        jmsTemplate.convertAndSend("Getting platoon: " + platoonDto + "\n\n");

        return ResponseEntity.ok(platoonDto);
    }

    @PostMapping
    public ResponseEntity<PlatoonDto> createPlatoon(@RequestBody CreatePlatoonDto createPlatoonDto) {
        PlatoonDto platoonDto = platoonService.createPlatoon(createPlatoonDto);

        jmsTemplate.convertAndSend("Created platoon: " + platoonDto + "\n\n");

        return ResponseEntity.created(URI.create("/api/platoons/" + platoonDto.getId())).body(platoonDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlatoonDto> updatePlatoon(@PathVariable Integer id, @RequestBody UpdatePlatoonDto updatePlatoonDto) {
        PlatoonDto platoonDto = platoonService.updatePlatoon(id, updatePlatoonDto);

        jmsTemplate.convertAndSend("Updated platoon: " + platoonDto + "\n\n");

        return ResponseEntity.ok(platoonDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlatoon(@PathVariable Integer id) {
        platoonService.deletePlatoon(id);

        jmsTemplate.convertAndSend("Deleted platoon with ID: " + id + "\n\n");

        return ResponseEntity.noContent().build();
    }
}
