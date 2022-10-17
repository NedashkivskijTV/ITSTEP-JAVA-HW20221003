package edu.itstep.hw20221003;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        User user = new User("Ivan", "Ivanenko", 27, "077-777-77-77", "iimail@gmail.com");
        System.out.println(user);
        System.out.println("--------------------------------------------");

        User userCopy = copyObject(user);
        user = null;
        System.out.print("user : ");
        System.out.println(user);
        System.out.print("userCopy : ");
        System.out.println(userCopy);
    }

    public static User copyObject(Object object) {
        if(object == null){
            return null;
        }

        Field[] fields = object.getClass().getDeclaredFields(); // отримання доступу до усіх оголошених полів
        List parameterList = new ArrayList();
        for (Field field : fields) { // перебір колекції полів
            field.setAccessible(true); // дозвіл на доступ до приватних полів

            String fieldType = field.getType().getSimpleName(); // отримання типу поля
            AllowCopy allowCopy = field.getAnnotation(AllowCopy.class); // отримання інф про аннотацію поля (при відсутності повертає null)
                if (fieldType.equals("String")) {
                    String parameterStr = new String();
                    if (allowCopy != null && allowCopy.value()){
                        try {
                            parameterStr = (String) field.get(object);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    parameterList.add(parameterStr);
                } else if (fieldType.equals("int")) {
                    int parameterInt = 0;
                    if (allowCopy != null && allowCopy.value()){
                        try {
                            parameterInt = (int) field.get(object);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    parameterList.add(parameterInt);
                }
        }
        User userTemp = null;
        try {
            Constructor constructor = User.class.getDeclaredConstructor(String.class, String.class, int.class,String.class,String.class);
            constructor.setAccessible(true);
            userTemp = (User) constructor.newInstance(parameterList.get(0), parameterList.get(1), parameterList.get(2), parameterList.get(3), parameterList.get(4));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return userTemp;
    }

}
