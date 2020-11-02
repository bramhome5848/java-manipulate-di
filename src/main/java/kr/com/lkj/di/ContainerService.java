package kr.com.lkj.di;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ContainerService {

    public static <T> T getObject(Class<T> classType) {
        //test 에서는 src 참고 가능하나 반대는 불가 -> reflection 이용
        T instance = createInstance(classType);

        Arrays.stream(classType.getDeclaredFields()).forEach(f-> {
            if(f.getAnnotation(Inject.class) != null) { //annotation 을 가지고 있다면
                Object filedInstance = createInstance(f.getType());
                f.setAccessible(true);
                try {
                    f.set(instance, filedInstance);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return instance;
    }

    private static <T> T createInstance(Class<T> classType) {
        try {
            return classType.getConstructor(null).newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
