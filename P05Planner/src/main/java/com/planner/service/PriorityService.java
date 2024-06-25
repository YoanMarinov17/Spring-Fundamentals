package com.planner.service;

import com.planner.model.entity.Priority;
import com.planner.model.enums.PriorityName;

public interface PriorityService {
    Priority findPriorityByName(PriorityName priorityName);

    void dbInit();

}
