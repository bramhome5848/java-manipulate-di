package kr.com.lkj.reflection1;

import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException {

        //function1();
        //function2();
        //function3();
        //function4();
        function5();
    }

    /**
     * class type access
     */
    public static void function1() throws ClassNotFoundException {
        Class<Book> bClass1 = Book.class; //class instance 접근

        Book book = new Book();
        Class<? extends Book> bClass2 = book.getClass();    //만든 class instance 접근

        Class<?> bClass3 = Class.forName("kr.com.lkj.reflection1.Book");// 문자열을 통한 class instance 접근
    }

    /**
     * field access
     */
    public static void function2() {
        //field 접근 -> public filed 만 리턴됨
        Arrays.stream(Book.class.getFields()).forEach(System.out::println);

        System.out.println("------------------------------------------------");
        //field 접근 -> 모든 filed 리턴
        Arrays.stream(Book.class.getDeclaredFields()).forEach(System.out::println);
    }

    /**
     * filed value access -> instance 필요
     */
    public static void function3() {

        Class<? extends Book> bookClass = Book.class;
        Book book = new Book();

        Arrays.stream(bookClass.getDeclaredFields()).forEach(f -> {
            try {
                f.setAccessible(true);  //접근이 불가능한 필드에 접근을 하기 위해 필요함
                System.out.printf("%s %s\n", f, f.get(book));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * method access
     */
    public static void function4() {
        Class<Book> bookClass = Book.class;

        //method
        Arrays.stream(bookClass.getMethods()).forEach(System.out::println);
        System.out.println("------------------------------------------------");

        //constructor
        Arrays.stream(bookClass.getConstructors()).forEach(System.out::println);
        System.out.println("------------------------------------------------");

        //parentClass
        System.out.println(MyBook.class.getSuperclass());
        System.out.println("------------------------------------------------");

        //interface
        Arrays.stream(MyBook.class.getInterfaces()).forEach(System.out::println);
    }

    /**
     * modifier
     */
    public static void function5() {
        //filed
        Arrays.stream(Book.class.getDeclaredFields()).forEach(f -> {
            int modifiers = f.getModifiers();
            System.out.println(f);
            System.out.println(Modifier.isPrivate(modifiers));
            System.out.println(Modifier.isStatic(modifiers));
        });

        System.out.println("------------------------------------------------");

        //method
        Arrays.stream(Book.class.getMethods()).forEach(m -> {
            int modifiers = m.getModifiers();
            System.out.println(m);
            System.out.println(Modifier.isAbstract(modifiers));
            System.out.println(Modifier.isStatic(modifiers));

        });
    }
}
