package com.finder.finder.controller;

import com.finder.finder.service.FinderService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FinderController {

    private final FinderService finderService;

    public FinderController(FinderService service) {
        this.finderService = service;
    }

    @PostMapping("/find")
    public Long findMinNNumber(
            @Parameter(description = "Аюсолютный путь к .xlsx файлу", required = true)
            @RequestParam String filePath,
            @Parameter(description = "Порядок минимального искомого числа (1 для наименьшего, 2 для второго по величине и т.д.)", required = true)
            @RequestParam int n) {
        return finderService.findNumber(filePath, n);
    }

}
