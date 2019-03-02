package chap01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FilteringApples {

    // 筛选绿色的苹果
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    // 筛选重的苹果
    public static List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }

    // 根据谓词筛选
    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    // 是否为绿色
    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    // 是否为大重量苹果
    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));
        // 筛选出绿色的苹果
        List<Apple> greenApples = filterApples(inventory, FilteringApples::isGreenApple);
        // 筛选出重量大的苹果
        List<Apple> heavyApples = filterApples(inventory, FilteringApples::isHeavyApple);
        // lambda表达式
        filterApples(inventory, (Apple a) -> "green".equals(a.getColor()));
        // lambda表达式
        filterApples(inventory, (Apple a) -> a.getWeight() > 150);

    }

    public static class Apple {
        private int weight = 0;
        private String color = "";

        public Apple(int weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }
}
