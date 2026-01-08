package com.desafio.itau.controller;

import com.desafio.itau.entity.Statistics;
import com.desafio.itau.service.StatisticService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/statistics")
@AllArgsConstructor
@Tag(name = "Statistics", description = "Endpoint for statistics")
public class StatisticsController {

    private final StatisticService statisticService;

    @Operation(summary = "Retrieve statistics",description = "Get statistics from transactions made in the last 10 minutes")
    @GetMapping
    public ResponseEntity<Statistics> Statistics() {
        return ResponseEntity.ok(statisticService.Statistics());
    }
}
