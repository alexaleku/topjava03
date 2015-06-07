package main.java.ru.javawebinar.topjava.util;

import main.java.ru.javawebinar.topjava.model.UserMeal;
import main.java.ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                //LocalDateTime.of(int year,Month month,int dayOfMonth,int hour,int minute)
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 444),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 656),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 125),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 63),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 123),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 222),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 654),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 444),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 656),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 125),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 63),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 123),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 222),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 654),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 444),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 656),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 125),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 63),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 123),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 222),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 654),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 444),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 656),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 125),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 63),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 123),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 222),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 654),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 444),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 656),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 125),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 63),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 123),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 222),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 654),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 444),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 656),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 125),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 63),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 123),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 222),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 654),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 444),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 656),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 125),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 63),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 123),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 222),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 654),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 444),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 656),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 125),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 63),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 123),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 222),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 654),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 444),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 656),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 125),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 63),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 123),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 222),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 654),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 444),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 656),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 125),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 63),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 123),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 222),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 654),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 656),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 125),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 63),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 123),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 222),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 654),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 444),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 656),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 125),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 63),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 123),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 222),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 654),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 656),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 125),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 63),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 123),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 222),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 654),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 444),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 656),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 125),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 63),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 123),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 222),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 654),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 656),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 125),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 63),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 123),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 222),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 654),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 444),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 656),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 125),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 63),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 123),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 222),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 654),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 656),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 125),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 63),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 123),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 222),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 654),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 444),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 656),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 125),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 63),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 123),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 222),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 654),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 656),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 125),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 63),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 123),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 222),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 654),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 444),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 656),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 125),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 63),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 123),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 222),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 654)

        );

        long t1 = System.nanoTime();
        getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 1000);
        long t2 = System.nanoTime();
        getFilteredMealsWithExceeded_Loops(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 1000);
        long t3 = System.nanoTime();

        long millis1 = TimeUnit.NANOSECONDS.toMillis(t2 - t1);
        long millis2 = TimeUnit.NANOSECONDS.toMillis(t3 - t2);

        System.out.println(String.format("sequential sort took: %d ms", millis1));
        System.out.println(String.format("sequential sort took: %d ms", millis2));

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
            if (TimeUtil.isBetween(userMealWithExceed.getDateTime().toLocalTime(), startTime, endTime)) {
                userMealsWithExceedFilteredList.add(userMealWithExceed);
            }
        }

        return userMealsWithExceedFilteredList;
    }

    public static List<UserMealWithExceed> getFilteredMealsWithExceeded_Loops(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesADay) {
        List<UserMealWithExceed> result = new ArrayList<>();

        for (UserMeal userMeal : mealList) {
            // filter all meals by specified time period
            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                int calories = 0;
                // loop through all meals to find the same day meals to calculate calories
                for (UserMeal userMealInner : mealList) {
                    if (userMealInner.getDateTime().toLocalDate().equals(userMeal.getDateTime().toLocalDate()))
                        calories += userMealInner.getCalories();
                }
                result.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), calories > caloriesADay));
            }
        }
        return result;
    }
}

