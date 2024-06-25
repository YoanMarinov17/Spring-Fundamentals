package com.planner.service.impl;

import com.planner.model.entity.Priority;
import com.planner.model.enums.PriorityName;
import com.planner.repo.PriorityRepository;
import com.planner.service.PriorityService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PriorityServiceImpl implements PriorityService {
    private final PriorityRepository priorityRepository;

    public PriorityServiceImpl(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }


    @Override
    public void dbInit() {
        if (priorityRepository.count() == 0) {
            List<Priority> prioritiesDataList = Arrays.asList(
                    new Priority(PriorityName.URGENT, "An urgent problem that blocks the system use until the issue is resolved."),
                    new Priority(PriorityName.IMPORTANT, "A core functionality that your product is explicitly supposed to perform is compromised."),
                    new Priority(PriorityName.LOW, "Should be fixed if time permits but can be postponed."));
            this.priorityRepository.saveAllAndFlush(prioritiesDataList);
        }
    }


    @Override
    public Priority findPriorityByName(PriorityName priorityName) {
        return this.priorityRepository.findByPriorityName(priorityName);
    }


}
