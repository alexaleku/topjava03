package main.java.ru.javawebinar.topjava.util;

import main.java.ru.javawebinar.topjava.model.UserMeal;
import main.java.ru.javawebinar.topjava.model.UserMealWithExceed;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class UserMealsUtilTest {
    List<UserMeal> mealList;

    @Before
    public void setUp() throws Exception {
        mealList = Arrays.asList(
                //LocalDateTime.of(int year,Month month,int dayOfMonth,int hour,int minute)
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 28, 10, 0), "Завтрак", 120),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 28, 13, 0), "Обед", 300),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 28, 20, 0), "Ужин", 500),  // for 28th 920 calories
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 29, 10, 0), "Завтрак", 650),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 29, 13, 0), "Обед", 140),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 29, 20, 0), "Ужин", 500),  // for 29th 1290 calories
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 450),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 440),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 650),  // for 30th 1540 calories
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 60),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 220),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 340)  // for 31st 620 calories
        );
    }

    @Test
    public void testGetFilteredMealsWithExceeded_notEmpty() throws Exception {

        List<UserMealWithExceed> filteredMealsWithExceeded = UserMealsUtil.getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 1000);

        assertTrue(!filteredMealsWithExceeded.isEmpty());
    }

    @Test
    public void testGetFilteredMealsWithExceeded_haveMealsForRightTimePeriod() throws Exception {

        List<UserMealWithExceed> filteredMealsWithExceeded = UserMealsUtil.getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 1000);

        for (UserMealWithExceed meal : filteredMealsWithExceeded) {
            assertTrue(meal.getDateTime().toLocalTime().isAfter(LocalTime.of(7, 0)) && meal.getDateTime().toLocalTime().isBefore(LocalTime.of(12, 0)));
        }
    }

    @Test
    public void testGetFilteredMealsWithExceeded_haveRightExceedFlag() throws Exception {

        List<UserMealWithExceed> filteredMealsWithExceeded = UserMealsUtil.getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 1000);
        for (UserMealWithExceed meal : filteredMealsWithExceeded) {
          if (meal.getDateTime().toLocalDate() == LocalDate.of(2015, Month.MAY, 28)) {
              assertTrue(!meal.isExceed());
          } else if (meal.getDateTime().toLocalDate() == LocalDate.of(2015, Month.MAY, 29)) {
              assertTrue(meal.isExceed());
          } else if (meal.getDateTime().toLocalDate() == LocalDate.of(2015, Month.MAY, 30)) {
              assertTrue(meal.isExceed());
          } else if (meal.getDateTime().toLocalDate() == LocalDate.of(2015, Month.MAY, 31)) {
            assertTrue(!meal.isExceed());
        }
        }
    }

}