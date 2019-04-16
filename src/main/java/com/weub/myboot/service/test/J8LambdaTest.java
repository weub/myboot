package com.weub.myboot.service.test;

import com.weub.myboot.model.test.Apple;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;


public class J8LambdaTest {
    public static void main(String[] args) {
        BiFunction<Integer, String, Apple> appleBiFunction = Apple::new;

        List<Apple> appleList = new ArrayList<>();
        appleList.add(appleBiFunction.apply(49, "green"));
        appleList.add(appleBiFunction.apply(32, "red"));
        appleList.add(appleBiFunction.apply(18, "green"));
        appleList.add(appleBiFunction.apply(56, "red"));
        appleList.add(appleBiFunction.apply(43, "green"));
        appleList.add(appleBiFunction.apply(27, "red"));
        appleList.add(appleBiFunction.apply(99, "green"));

        System.out.println(map(appleList));
    }

    public static List map(List<Apple> dataList) {
        return dataList.stream()
                .filter(apple -> "green".equals(apple.getColor()))
                .sorted(comparing(Apple::getWeight).reversed())
                .map(Apple::getColor)
                .collect(toList());
    }

    /**
     * 3.9 数学中的类似思想 积分
     */
    public static void calculateIntegral() {
        double result = integrate(x -> x + 10, 3, 7);
        System.out.println(result);
    }

    /**
     * 环绕执行模式（即在方法所必需的代码中间，你需要执行点儿什么操作，比如资源分配
     * 和清理）可以配合Lambda提高灵活性和可重用性。
     * @return
     * @throws IOException
     */
    public static String surroudingProcess() throws IOException {
        String line = processFile((BufferedReader br) -> {
            try {
                return br.readLine() + br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        });
        return line;
    }

    /**
     * sort
     * @param appleList
     */
    public static void sortApple(List<Apple> appleList) {
        appleList.sort(Comparator.comparingInt(Apple::getWeight));
    }

    /**
     * 3.8 复合 Lambda 表达式的有用方法
     * @param appleList
     * @return
     */
    public static List<Apple> predicateApple(List<Apple> appleList) {
        Predicate<Apple> redApple = apple -> "red".equals(apple.getColor());
        Predicate<Apple> redAndHeavyApple = redApple.and(apple -> apple.getWeight() > 30);
        Predicate<Apple> redHeavyOrGreenApple = redAndHeavyApple.or(apple -> "green".equals(apple.getColor()));
        return appleList.stream().filter(redHeavyOrGreenApple).collect(toList());
    }

    public static class AppleComparator implements Comparator<Apple> {
        @Override
        public int compare(Apple o1, Apple o2) {
            return o1.getWeight() - o2.getWeight();
        }
    }

    public static double integrate(DoubleFunction<Double> f, double x, double y) {
        return (f.apply(x) + f.apply(y)) * (y - x) / 2.0;
    }

    public static String processFile(Function<BufferedReader, String > function) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("d://data.txt"))) {
            return function.apply(reader);
        }
    }

    @FunctionalInterface
    public interface BufferedReaderProcessor {
        String process(BufferedReader b) throws IOException;
    }
}
