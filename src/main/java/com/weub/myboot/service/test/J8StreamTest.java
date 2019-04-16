package com.weub.myboot.service.test;

import com.weub.myboot.model.test.Dish;
import com.weub.myboot.model.test.Trader;
import com.weub.myboot.model.test.Transaction;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class J8StreamTest {

    public static void main(String[] args) {
        //threeHighCaloriesDishName();
        //threeHighCaloriesDishNameDetail();
        //twoMeatDish();
        //calDishNameLength();
        //uniqueCharacter();
        //anyMatch();
        //find();
        //count();

        //5.5 exam
        //tradSortByValue();
        //uniqueCity();
        //cambridgeTraderSortedByName();
        //traderNameStrSortedByCharacter();
        //milanTraderExist();
        //cambridgeTradeValue();
        //maxTradeValue();
        //minTradeValue();

        //combine(new int[]{1,2,3}, new int[]{3,4});
        //System.out.println(IntStream.rangeClosed(1, 100).filter(i -> i % 3 == 0).count());
        //pythagoreanTriples();
        //f();

        //int sum = dishList().stream().mapToInt(Dish::getCalories).sum();
        groupingByCaloricLevel();
    }

    public static List<Dish> dishList() {
        return Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
    }

    public static void threeHighCaloriesDishName() {
        List<String> menuNameList = dishList().stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(toList());
        System.out.println(menuNameList);
    }

    public static void threeHighCaloriesDishNameDetail() {
        dishList().stream()
                .filter(d -> {
                    System.out.println("filter: " + d.getName());
                    return d.getCalories() > 300;
                })
                .map(d -> {
                    System.out.println("map: " + d.getName());
                    return d.getName();
                })
                .limit(3)
                .collect(toList());
    }

    public static void twoMeatDish() {
        dishList().stream()
                .filter(d -> Dish.Type.MEAT == d.getType())
                .limit(2)
                .forEach(System.out::println);
    }

    public static void calDishNameLength() {
        dishList().stream()
                .map(Dish::getName)
                .map(String::length)
                .forEach(System.out::println);
    }

    public static void uniqueCharacter() {
        List<String> wordList = dishList().stream()
                .map(d -> d.getName().split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(wordList);
    }

    public static List<int[]> combine(int[] A, int[] B) {

        List<int[]> result = Arrays.stream(A).boxed().flatMap(a ->
                Arrays.stream(B)
                        .filter(b -> (a + b) % 3 == 0)
                        .boxed()
                        .map(b -> new int[]{a, b})
        ).collect(toList());
        System.out.println(result);
        return result;

        /*return Arrays.stream(A)
                .map(a -> Arrays.stream(B)
                        .filter(b -> (a + b) % 3 == 0)
                        .map(b -> new int[]{a, b})
                )
                .collect(toList());*/
    }

    public static void anyMatch() {
        boolean vegetarianExist = dishList().stream()
                .anyMatch(Dish::isVegetarian);
        if (vegetarianExist) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        } else {
            System.out.println("Oops!");
        }
    }

    public static void find() {
        dishList().stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(d -> System.out.println(d.getName()));
    }

    public static void count() {
        int count = dishList().stream()
                .map(d -> 1)
                .reduce(0, Integer::sum);
        System.out.println(count);
    }

    public static List<Transaction> transactionList() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        return Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    public static void tradSortByValue() {
        List<Transaction> transactionList = transactionList().stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(toList());
        System.out.println(transactionList);
    }

    public static void uniqueCity() {
        List<String> uniqueCity = transactionList().stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(toList());
        System.out.println(uniqueCity);
    }

    public static void cambridgeTraderSortedByName() {
        List<Trader> traderList = transactionList().stream()
                .map(Transaction::getTrader)
                .filter(t -> "Cambridge".equalsIgnoreCase(t.getCity()))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(toList());
        System.out.println(traderList);
    }

    public static void traderNameStrSortedByCharacter() {
        String traderNameStr = transactionList().stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted()
                .collect(joining());
        System.out.println(traderNameStr);
    }

    public static void milanTraderExist() {
        boolean exist = transactionList().stream()
                .map(Transaction::getTrader)
                .anyMatch(t -> "Milan".equalsIgnoreCase(t.getCity()));
        if (exist) {
            System.out.println("There is more than one trader work in Milan.");
        }
    }

    public static void cambridgeTradeValue() {
        transactionList().stream()
                .filter(transaction -> "Cambridge".equalsIgnoreCase(transaction.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);
    }

    public static void maxTradeValue() {
        transactionList().stream()
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .ifPresent(v -> System.out.println("max trade value is " + v));
    }

    public static void minTradeValue() {
        transactionList().stream()
                .map(Transaction::getValue)
                .reduce(Integer::min)
                .ifPresent(v -> System.out.println("min trade value is " + v));
    }

    public static void pythagoreanTriples() {
        Stream<double[]> resultStream = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a + 1, 100)
                                .mapToObj(
                                        b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                                .filter(c -> c[2] % 1 == 0));
        resultStream.limit(3).forEach(r -> System.out.println("[" + r[0] + "," + r[1] + "," + r[2] + "]"));
    }

    public static void f() {
        String result = Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]})
                .limit(20)
                .map(n -> n[0] + "")
                .collect(joining(","));
        System.out.println(result);
        // reduce(0, (x, y) -> x + y) .... 1. x=0,y=1 (x, x+y)
    }

    public enum CaloricLevel {DIET, NORMAL, FAT}

    public static void groupingByCaloricLevel() {
        Map map = dishList().stream()
                .collect(groupingBy(Dish::getType,
                        groupingBy(dish -> {
                            if (dish.getCalories() < 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            return CaloricLevel.FAT;
                        })));
        System.out.println(map);

        Map result = dishList().stream()
                .collect(groupingBy(Dish::getType,
                        collectingAndThen(maxBy(Comparator.comparing(Dish::getCalories)), Optional::get)
                ));
        System.out.println(result);
    }
}
