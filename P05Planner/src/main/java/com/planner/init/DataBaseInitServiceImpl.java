package com.planner.init;

import com.planner.service.PriorityService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class DataBaseInitServiceImpl implements DataBaseInitService {
    private final PriorityService priorityService;

    public DataBaseInitServiceImpl(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @PostConstruct
    public void init() {
        this.priorityService.dbInit();
    }
}