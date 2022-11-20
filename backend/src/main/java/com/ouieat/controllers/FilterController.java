package com.ouieat.controllers;

import com.ouieat.repository.FilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilterController {
    public final FilterRepository filterRepository;

    @Autowired
    public FilterController(FilterRepository filterRepository) {
        this.filterRepository = filterRepository;
    }

}
