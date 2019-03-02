package chap04;

import java.util.*;
import java.util.stream.Collectors;

public class StreamBasic {
    // 筛选出最小三个卡路里低于400的菜品
    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes) {
        List<Dish> lowCaloricDishes = new ArrayList<>();
        // 筛选出卡路里低于400的菜品
        for (Dish dish : dishes) {
            if (dish.getCalories() < 400) {
                lowCaloricDishes.add(dish);
            }
        }
        // 对菜品集合根据卡路里大小排序
        lowCaloricDishes.sort(Comparator.comparing(Dish::getCalories));
        // 选出前三个菜品
        List<String> lowCaloricDishesName = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (lowCaloricDishes.get(i) != null) {
                lowCaloricDishes.add(lowCaloricDishes.get(i));
            }
        }
        return lowCaloricDishesName;
    }

    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes) {
        return dishes.stream()
                .filter(dish -> dish.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .limit(3)
                .map(Dish::getName)
                .collect(Collectors.toList());
    }
}
