package com.company.service;

import com.company.model.Activity;
import com.company.model.ActivityToDisplay;

import java.time.LocalDate;
import java.util.List;

public interface IActivityService {
    boolean addActivityToPage(Activity activity);

    List<ActivityToDisplay> getUserActivityPage(Integer userId , LocalDate date);

    ActivityToDisplay getUserActivityTotals(Integer userId, LocalDate date);

    boolean deleteActivityFromPage(int id);
}
