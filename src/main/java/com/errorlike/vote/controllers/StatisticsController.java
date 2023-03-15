package com.errorlike.vote.controllers;

import com.errorlike.vote.dtos.StatisticsForBarChartDTO;
import com.errorlike.vote.services.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping()
    public ResponseEntity<?> getAll(@RequestParam(required = true) Long formId) {
        List<StatisticsForBarChartDTO> data = statisticsService.getBarDataByFormId(formId);
        return ResponseEntity.ok(data);
    }

}
