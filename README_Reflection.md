Reflection is an API which is used to examine or modify the behavior of methods, classes, interfaces at runtime.

- The required classes for reflection are provided under java.lang.reflect package.
- Reflection gives us information about the class to which an object belongs and also the methods of that class which
  can be executed by using the object.
- Through reflection, we can invoke methods at runtime irrespective of the access specifier used with them.

Reflection can be used to get information about –

1. Class The getClass() method is used to get the name of the class to which an object belongs.
2. Constructors The getConstructors() method is used to get the public constructors of the class to which an object
   belongs.
3. Methods The getMethods() method is used to get the public methods of the class to which an objects belongs.

```java
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
```

> Important observations :

1. We can invoke an method through reflection if we know its name and parameter types. We use below two methods for this
   purpose getDeclaredMethod() : To create an object of method to be invoked. The syntax for this method is

```text
Class.getDeclaredMethod(name,parametertype)
name-the name of method whose object is to be created
parametertype-parameter is an array of Class objects
```

2. invoke() : To invoke a method of the class at runtime we use following method–

```text
Method.invoke(Object, parameter)
If the method of the class does’t accepts any 
parameter then null is passed as argument.
```

3. Through reflection we can access the private variables and methods of a class with the help of its class object and
   invoke the method by using the object as discussed above. We use below two methods for this purpose.
   Class.getDeclaredField(FieldName) : Used to get the private field. Returns an object of type Field for specified
   field name. Field.setAccessible(true) : Allows to access the field irrespective of the access modifier used with the
   field.

> Advantages of Using Reflection:

1. Extensibility Features: An application may make use of external, user-defined classes by creating instances of
   extensibility objects using their fully-qualified names.
2. Debugging and testing tools: Debuggers use the property of reflection to examine private members on classes.

> Drawbacks:

1. Performance Overhead: Reflective operations have slower performance than their non-reflective counterparts, and
   should be avoided in sections of code which are called frequently in performance-sensitive applications.
2. Exposure of Internals: Reflective code breaks abstractions and therefore may change behavior with upgrades of the
   platform.