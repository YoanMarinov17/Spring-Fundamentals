package com.planner.model.entity;

import com.planner.model.enums.PriorityName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "priorities")
public class Priority extends BaseEntity{

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private PriorityName priorityName;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "priority")
    private List<Task> tasks;

    public Priority(PriorityName priorityName, String description) {
        this.priorityName = priorityName;
        this.description = description;
    }
    public Priority() {

    }
}
