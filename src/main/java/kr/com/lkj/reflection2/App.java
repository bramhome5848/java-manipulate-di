package kr.com.lkj.reflection2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        function1();
        function2();
        function3();
    }

    /**
     * 생성자로 인스턴스 만들기
     */
    public static void function1() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class<?> bookClass = Class.forName("kr.com.lkj.reflection2.Book");
        Constructor<?> constructor1 = bookClass.getConstructor(null);    //파라미터가 없는 생성자
        Book book1 = (Book)constructor1.newInstance();
        System.out.println("book1 = " + book1);

        Constructor<?> constructor2 = bookClass.getConstructor(String.class);   //파라미터가 있는 생성자
        Book book2 = (Book)constructor2.newInstance("myBook");
        System.out.println("book2 = " + book2);
    }

    /**
     * 필드 값 접근하기/설정하기
     */
    public static void function2() throws NoSuchFieldException, IllegalAccessException {
        Field a = Book.class.getDeclaredField("A");

        //static filed
        System.out.println(a.get(null));    //static filed 의 경우 object -> null
        a.set(null, "AAAAAAA");
        System.out.println(a.get(null));

        //instance filed -> instance 필요
        Book book = new Book();
        Field b = Book.class.getDeclaredField("B");
        b.setAccessible(true);  //private filed 이기 때문에 true 로 변경 -> 접근 가능
        System.out.println(b.get(book));
        b.set(book, "BBBBBBB"); //값 변경
        System.out.println(b.get(book));
    }

    /**
     * 메소드 실행하기
     */
    public static void function3() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Book book = new Book();
        Method c = Book.class.getDeclaredMethod("c");
        c.setAccessible(true);  //private 이기 때문에 true -> 접근 가능
        c.invoke(book);  //함수 실행, 특정 instance 함수이면 instance 를 함께 넘겨줘야함

        Method sum = Book.class.getDeclaredMethod("sum", int.class, int.class);
        int invoke = (int) sum.invoke(book, 1, 2);
        System.out.println(invoke);
    }
}
