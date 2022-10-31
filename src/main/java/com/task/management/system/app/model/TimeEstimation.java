package com.task.management.system.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeEstimation {

    private String timeSpent;
    private String estimate;
    private String timeRemaining;

}
