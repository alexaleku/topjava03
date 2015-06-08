package main.java.ru.javawebinar.topjava.util;

import main.java.ru.javawebinar.topjava.model.UserMeal;
import main.java.ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealListForPerformance = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            mealListForPerformance.add(new UserMeal(LocalDateTime.of(new Random().nextInt(2000) + 1, new Random().nextInt(11) + 1, new Random().nextInt(27) + 1, new Random().nextInt(24), new Random().nextInt(60)), "Обед", 444));
        }

        List<UserMeal> mealList = Arrays.asList(
                //LocalDateTime.of(int year,Month month,int dayOfMonth,int hour,int minute)
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 444),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 656),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 125),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 10, 0), "Завтрак", 63),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 13, 0), "Обед", 123),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, new Random().nextInt(28) + 1, 20, 0), "Ужин", 222)
        );
        long t0 = System.nanoTime();
        getFilteredMealsWithExceeded_Streams(mealListForPerformance, LocalTime.of(7, 0), LocalTime.of(12, 0), 1000);
        long t1 = System.nanoTime();
        getFilteredMealsWithExceeded(mealListForPerformance, LocalTime.of(7, 0), LocalTime.of(12, 0), 1000);
        long t2 = System.nanoTime();
        //getFilteredMealsWithExceeded_Loops(mealListForPerformance, LocalTime.of(7, 0), LocalTime.of(12, 0), 1000);
        //long t3 = System.nanoTime();


        long millis0 = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        long millis1 = TimeUnit.NANOSECONDS.toMillis(t2 - t1);
        //long millis2 = TimeUnit.NANOSECONDS.toMillis(t3 - t2);

        System.out.println(String.format("comment_here: %d ms", millis0));
        System.out.println(String.format("comment_here: %d ms", millis1));
      //  System.out.println(String.format("comment_here: %d ms", millis2));


    }

    public static List<UserMealWithExceed> getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, ArrayList<UserMeal>> mealsMapByDateMap = new HashMap<>();

        for (UserMeal userMeal : mealList) {
            LocalDate mealDay = userMeal.getDateTime().toLocalDate();
            if (!mealsMapByDateMap.containsKey(mealDay)) {
                mealsMapByDateMap.put(mealDay, new ArrayList<>());
            }
            mealsMapByDateMap.get(mealDay).add(userMeal);
        }

        List<UserMealWithExceed> userMealWithExceedsList = new ArrayList<>();

        for (ArrayList<UserMeal> mealsInADayList : mealsMapByDateMap.values()) {
            for (UserMeal meal : mealsInADayList) {
                if (TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                    int caloriesPerDayCalculated = 0;
                    for (UserMeal mealForCalories : mealsInADayList) {
                        caloriesPerDayCalculated += mealForCalories.getCalories();
                    }
                    userMealWithExceedsList.add(new UserMealWithExceed(meal, caloriesPerDayCalculated > caloriesPerDay));
                }
            }
        }

        return userMealWithExceedsList;
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

    public static List<UserMealWithExceed> getFilteredMealsWithExceeded_Loops_forEach(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesADay) {
        List<UserMealWithExceed> result = new ArrayList<>();

        mealList.forEach((userMeal) -> {
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
        });
        return result;
    }

    public static List<UserMealWithExceed> getFilteredMealsWithExceeded_Streams(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesADay) {
        Map<LocalDate, Integer> caloriesSumByDate = mealList.stream()
                .collect(Collectors.groupingBy(userMeal -> userMeal.getDateTime().toLocalDate(), Collectors.summingInt(UserMeal::getCalories)));
        return mealList.stream()
                .filter(um -> TimeUtil.isBetween(um.getDateTime().toLocalTime(), startTime, endTime))
                .map(um -> new UserMealWithExceed(um, caloriesSumByDate.get(um.getDateTime().toLocalDate()) < caloriesADay))
                .collect(Collectors.toList());
    }
}

