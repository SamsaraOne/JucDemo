package stream;


import java.util.function.Predicate;

public class juf_function {

    public static void main(String[] args) {
        Predicate<String> predicate = s-> s.isEmpty();;
        System.out.printf(String.valueOf(predicate.test("ddd")));
    }


}
