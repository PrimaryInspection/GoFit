package com.company.service;

import com.company.model.Activity;
import com.company.model.ActivityToDisplay;

import java.time.LocalDate;
import java.util.List;

public interface IActivityService {

    boolean addActivityToDiary (Activity activity);

    List<ActivityToDisplay> getUserActivityDiary(Integer userId , LocalDate chosenDate);

    ActivityToDisplay getUserActivityDiaryTotals(Integer userId , LocalDate chosenDate);

    boolean deleteFromActivityDiary(int id);
}
