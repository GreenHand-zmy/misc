package chap05;

import java.math.BigDecimal;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumericStreams {

    public static void main(String[] args) {
        // 生成三元组
        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).boxed()
                        .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));
//        pythagoreanTriples.forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
        // 生成斐波那契数列
        Stream.iterate(new BigDecimal[]{new BigDecimal(0), new BigDecimal(1)}, t -> new BigDecimal[]{t[1], t[0].add(t[1])})
                .limit(10000)
                .skip(9999)
                .forEach(t -> System.out.println(t[1]));
    }
}
