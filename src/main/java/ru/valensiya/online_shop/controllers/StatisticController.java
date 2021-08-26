package ru.valensiya.online_shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/statistic")
public class StatisticController {


    @GetMapping
    public String getStatistic() {
        String statistic = "statistic";
        return statistic;
    }
}
