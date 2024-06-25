package com.planner.repo;

import com.planner.model.entity.Priority;
import com.planner.model.enums.PriorityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, String> {
    Priority findByPriorityName(PriorityName name);
}