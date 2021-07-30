package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionMainClass {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Book book = new Book();

        Class cls = book.getClass();
        System.out.println("The name of the class is " + cls.getName());

        Constructor constructor = cls.getConstructor();
        System.out.println("The name of constructor is " + constructor.getName());

        System.out.println("The public methods of class are : ");

        Method[] methods = cls.getMethods();
        for (Method method : methods)
            System.out.println(method.getName());

        Method secondMethodOb = cls.getDeclaredMethod("argumentMethod", int.class);

        secondMethodOb.invoke(book, 20);

        Field field = cls.getDeclaredField("name");

        // allows the object to access the field irrespective
        // of the access specifier used with the field
        field.setAccessible(true);

        // takes object and the new value to be assigned
        // to the field as arguments
        field.set(book, "Java You Rocks");

        Method thirdMethodOb = cls.getDeclaredMethod("noArgumentMethod");
        thirdMethodOb.invoke(book);

        Method fourMethodOb = cls.getDeclaredMethod("noArgumentPrivateMethod");
        fourMethodOb.setAccessible(true);
        fourMethodOb.invoke(book);
    }
}

/*
---------------------
Output
---------------------
The name of the class is reflection.Book
The name of constructor is reflection.Book
The public methods of class are :
argumentMethod
noArgumentMethod
wait
wait
wait
equals
toString
hashCode
getClass
notify
notifyAll
Argument Method Called. The Number is 20
No Argument Method Called. The Book Name is Java You Rocks
No Argument Private Method Called. The Book Name is Java You Rocks
---------------------
 */