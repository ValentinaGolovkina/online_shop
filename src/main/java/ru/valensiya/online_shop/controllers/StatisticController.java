package ru.valensiya.online_shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.valensiya.online_shop.AppLoggingAspect;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/statistic")
public class StatisticController {
    private final AppLoggingAspect aspect;


    @GetMapping
    public Map<String, Long> getStatistic() {
        return aspect.getStatistics();
    }
}
