package kr.com.lkj.annotation;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        function1();
    }

    /**
     * annotation -> 주석과 같은 취급을 받음, 그러나 바이트 코드를 로딩했을 때 메모리 상에 같이 남지 않음, Annotation 의 Retention 이용
     */
    public static void function1() {
        Arrays.stream(Book.class.getAnnotations()).forEach(System.out::println);
        System.out.println("------------------------------------------------");

        //상속 클래스에서도 조회 가능
        Arrays.stream(MyBook.class.getAnnotations()).forEach(System.out::println);
        System.out.println("------------------------------------------------");

        //상속 제외하고 해당 클래스에만 선언된 annotation -> only Mybook
        Arrays.stream(MyBook.class.getDeclaredAnnotations()).forEach(System.out::println);
    }
}
