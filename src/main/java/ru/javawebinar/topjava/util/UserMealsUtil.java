package main.java.ru.javawebinar.topjava.util;

import main.java.ru.javawebinar.topjava.model.UserMeal;
import main.java.ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                //LocalDateTime.of(int year,Month month,int dayOfMonth,int hour,int minute)
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 444),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 656),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 28, 10, 0), "Завтрак", 125),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 63),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 29, 13, 0), "Обед", 123),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 222),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 29, 10, 0), "Завтрак", 654),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 28, 13, 0), "Обед", 333),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 29, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 345),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 456),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 28, 20, 0), "Ужин", 500)
        );
        List<UserMealWithExceed> filteredMealsWithExceeded = getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 1000);
        System.out.println(filteredMealsWithExceeded);
    }

    public static List<UserMealWithExceed> getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        // create a map to collect meals for each day, day is a key, value is an array of meals
        Map<LocalDate, ArrayList<UserMeal>> mealsMapByDateMap = new HashMap<>();

        // go through all meals and fill the map
        for (UserMeal userMeal : mealList) {
            if (!mealsMapByDateMap.containsKey(userMeal.getDateTime().toLocalDate())) {
                mealsMapByDateMap.put(userMeal.getDateTime().toLocalDate(), new ArrayList<>());
            }
            mealsMapByDateMap.get(userMeal.getDateTime().toLocalDate()).add(userMeal);
        }

        // create UserMealWithExceed list
        List<UserMealWithExceed> userMealWithExceedsList = new ArrayList<>();

        int caloriesPerDayCalculated = 0;

        // iterate through the map defining correct values for the exceed variable for each meal and fill up the userMealWithExceedsList list
        for (ArrayList<UserMeal> mealsInADayList : mealsMapByDateMap.values()) {

            //iterate through an array that stores meals for a day
            for (UserMeal meal : mealsInADayList) {
                caloriesPerDayCalculated += meal.getCalories();
            }
            // calculate the exceed flag
            boolean exceed = caloriesPerDayCalculated > caloriesPerDay;

            // create UserMealWithExceed beans for each meal in the array
            for (UserMeal meal : mealsInADayList) {
                userMealWithExceedsList.add(new UserMealWithExceed(meal, exceed));
            }
            caloriesPerDayCalculated = 0;
        }

        // filter the UserMealWithExceed list by specified time period
        List<UserMealWithExceed> userMealsWithExceedFilteredList = new ArrayList<>();

        for (UserMealWithExceed userMealWithExceed : userMealWithExceedsList) {
            if(TimeUtil.isBetween(userMealWithExceed.getDateTime().toLocalTime(), startTime, endTime)) {
            userMealsWithExceedFilteredList.add(userMealWithExceed);
        }
        }

        return userMealsWithExceedFilteredList;
    }
}
