### Java Annotation

> Introduction

Java Annotation is a tag that represents the metadata i.e. attached with class, interface, methods or fields to indicate
some additional information which can be used by java compiler and JVM.

> Built-In Java Annotations

There are several built-in annotations in Java. Some annotations are applied to Java code and some to other annotations.

Built-In Java Annotations used in Java code:

1. @Override
2. @SuppressWarnings
3. @Deprecated

Built-In Java Annotations used in other annotations

1. @Target
2. @Retention
3. @Inherited
4. @Documented

> Understanding Built-In Annotations

Let's understand the built-in annotations first.

@Override annotation assures that the subclass method is overriding the parent class method. If it is not so, compile
time error occurs. So, it is better to mark @Override annotation that provides assurity that method is overridden.

@SuppressWarnings annotation: is used to suppress warnings issued by the compiler.

@Deprecated annotation marks that this method is deprecated so compiler prints warning. It informs user that it may be
removed in the future versions. So, it is better not to use such methods.

> Java Custom Annotations

Java Custom annotations or Java User-defined annotations are easy to create and use. The @interface element is used to
declare an annotation. For example:

```java
@interface AntiCodeAnnotation {
}
```

Here, AntiCodeAnnotation is the custom annotation name.

Points to remember for java custom annotation signature:

1. Method should not have any throws clauses
2. Method should return one of the following: primitive data types, String, Class, enum or array of these data types.
3. Method should not have any parameter.
4. We should attach @ just before interface keyword to define annotation.
5. It may assign a default value to the method.

> Types of Annotation

There are three types of annotations.

1. Marker Annotation (An annotation that has no method, is called marker annotation.)

```java
@interface AntiCodeAnnotation {
}
```

2. Single-Value Annotation (An annotation that has one method, is called single-value annotation.)

```java
@interface AntiCodeAnnotation {
    int value();
}  
```

We can provide the default value also. For example:

```java
@interface AntiCodeAnnotation {
    int value() default 0;
} 
```

Let's see the code to apply the single value annotation.

```java
@AntiCodeAnnotation(value = 10) # The value can be anything.
```

3. Multi-Value Annotation (An annotation that has more than one method, is called Multi-Value annotation)

```java
@interface AntiCodeAnnotation {
    int value1();

    String value2();

    String value3();
}
```

We can provide the default value also.

```java
@interface AntiCodeAnnotation {
    int value1() default 1;

    String value2() default "";

    String value3() default "codemechanix";
}  
```

Let's see the code to apply the multi-value annotation.

```java
@AntiCodeAnnotation(value1 = 10, value2 = "Hasan", value3 = "Mahmud")
```

> Built-in Annotations used in custom annotations in java

@Target tag is used to specify at which type, the annotation is used.

```text
TYPE            -       class, interface or enumeration
FIELD           -	fields
METHOD          -	methods
CONSTRUCTOR     -	constructors
LOCAL_VARIABLE  -	local variables
ANNOTATION_TYPE -	annotation type
PARAMETER       - 	parameter
```

```java

@Target(ElementType.TYPE)
@interface AntiCodeAnnotation {
    int value1();

    String value2();
}  
```

Example to specify annotation for a class, methods or fields

```java

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@interface AntiCodeAnnotation {
    int value1();

    String value2();
}  
```

@Retention annotation is used to specify to what level annotation will be available.

```text
RetentionPolicy.SOURCE	- refers to the source code, discarded during compilation. It will not be available in the compiled class.
RetentionPolicy.CLASS	- refers to the .class file, available to java compiler but not to JVM . It is included in the class file.
RetentionPolicy.RUNTIME	- refers to the runtime, available to java compiler and JVM .
```

Example to specify the RetentionPolicy

```java

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface AntiCodeAnnotation {
    int value1();

    String value2();
}  
```

> Example of custom annotation: creating, applying and accessing annotation

For our JSON serializer, we will create a field annotation that allows a developer to mark a field to be included when
serializing an object. For example, if we create a car class, we can annotate the fields of the car (such as make and
model) with our annotation. When we serialize a car object, the resulting JSON will include make and model keys, where
the values represent the value of the make and model fields, respectively. we will assume that this annotation will be
used only for fields of type String, ensuring that the value of the field can be directly serialized as a string.

To create such a field annotation, we declare a new annotation using the @interface keyword:

```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Creating annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface JsonField {
    String value() default "";
}
```

public @interface JsonField -> which declares an annotation type with a public modifier, allowing our annotation to be
used in any package (assuming the package is properly imported if in another module)

The body of the annotation declares a single String parameter, named value, that has a type of String and a default
value of an empty string.

The variable name 'value' has a special meaning: It defines a Single-Element Annotation and allows users of our
annotation to supply a single parameter to the annotation without specifying the name of the parameter.

A user can annotate a field using @JsonField("someFieldName") and is not required to declare the annotation as
@JsonField(value = "someFieldName"), although the latter may still be used (but it is not required).

The inclusion of a default value of empty string allows for the value to be omitted, resulting in value holding an empty
string if no value is explicitly specified. For example, if a user declares the above annotation using the form
@JsonField, then the value parameter is set to an empty string.

we created a public, single-element annotation named JsonField, which is retained by the JVM during runtime and may only
be applied to field. This annotation has a single parameter, value, of type String with a default value of an empty
string. With our annotation created, we can now annotate fields to be serialized.

> How Are Annotations User?

```java
public class Car {
    @JsonField("manufacturer")
    private final String make;

    @JsonField
    private final String model;

    private final String year;

    public Car(String make, String model, String year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    @Override
    public String toString() {
        return year + " " + make + " " + model;
    }

}
```

This class exercises the two major uses of the @JsonField annotation: (1) with an explicit value and (2) with a default
value.

Given the above uses of the @JsonField annotation, we would expect that a Car object is serialized into a JSON string of
the form

```json
{
  "manufacturer": "someMake",
  "model": "someModel"
}
```

Before we proceed, it is important to note that adding the @JsonField annotations does not change the runtime behavior
of the Car class. If we compile this class, the inclusion of @JsonField annotations does not enhance the behavior of the
Car class any more than had we omitted the annotations. These annotations are simply recorded, along with the value of
the value parameter, in the class file for the Car class. Altering the runtime behavior of our system requires that we
process these annotations.

> How are Annotations Processed?

Processing annotations is accomplished through the Java Reflection Application Programming Interface (API). The
reflection API allows us to write code that will inspect the class, methods, fields, etc. of an object.

For example, if we create a method that accepts a Car object, we can inspect the class of this object (namely, Car) and
discover that this class has three fields: (1) make, (2) model, and (3) year. Furthermore, we can inspect these fields
to discover if each is annotated with a specific annotation.

Using this capability, we can iterate through each field of the class associated with the object passed to our method
and discover which of these fields are annotated with the @JsonField annotation. If the field is annotated with the
@JsonField annotation, we record the name of the field and its value. Once all the fields have been processed, then we
can create the JSON string using these field names and values.

Determining the name of the field requires more complex logic than determining the value. If the @JsonField includes a
provided value for the value parameter (such as "manufacturer" in the previous @JsonField("manufacturer") use), we will
use this provided field name. If the value of the value parameter is an empty string, we know that no field name was
explicitly provided (since this is the default value for the value parameter), or else, an empty string was explicitly
provided. In either case, we will use the variable name of the field as the field name (for example, model in the
private final String model declaration).

Combining this logic into a JsonSerializer class, we can create the following class declaration:

```java
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class JsonSerializer {
    public String serialize(Object object) throws JsonSerializeException, IllegalAccessException {
        Class<?> objectClass = Objects.requireNonNull(object, "Class must not be null").getClass();
        Map<String, String> jsonElements = new HashMap<>();

        for (Field field : objectClass.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(JsonField.class)) {
                jsonElements.put(getSerializedKey(field), (String) field.get(object));
            }
        }

        System.out.println(toJsonString(jsonElements));
        return toJsonString(jsonElements);
    }

    private String toJsonString(Map<String, String> jsonMap) {
        String elementsString = jsonMap.entrySet()
                .stream()
                .map(entry -> "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"")
                .collect(Collectors.joining(","));
        return "{" + elementsString + "}";
    }

    private static String getSerializedKey(Field field) {
        String annotationValue = field.getAnnotation(JsonField.class).value();

        if (annotationValue.isEmpty()) {
            return field.getName();
        } else {
            return annotationValue;
        }
    }
}
```

Although the JsonSerializer class appears complex, it consists of three main tasks: (1) finding all fields of the
supplied class annotated with the @JsonField annotation, (2) recording the field name (or the explicitly provided field
name) and value for all fields that include the @JsonField annotation, and (3) converting the recorded field name and
value pairs into a JSON string.

The line requireNonNull(object).getClass() simply checks that the supplied object is not null (and throws a
NullPointerException if it is) and obtains the Class object associated with the supplied object.

We will use this Class object shortly to obtain the fields associated with the class. Next, we create a Map of Strings
to Strings, which will be used store the field name and value pairs.

In order to access these private fields, we must instruct the reflection API to suppress the standard Java access
checking for this field using the setAccessible method. The setAccessible(boolean)

A value of true indicates that the reflected object should suppress Java language access checking when it is used. A
value of false indicates that the reflected object should enforce Java language access checks.

After gaining access to the field, we check if the field is annotated with the @JsonField. If it is, we determine the
name of the field (either through an explicit name provided in the @JsonField annotation or the default name, which
equals the variable name of the field) and record the name and field value in our previously constructed map. Once all
fields have been processed, we then convert the map of field names to field values (jsonElements) into a JSON string.

We accomplish by converting the map into a stream of entries (key-value pairs for each entry in the map), mapping each
entry to a string of the form "<fieldName>":"<fieldValue>", where <fieldName> is the key for the entry and <fieldValue>
is the value for the entry. Once all entries have been processed, we combine all of these entry strings with a comma.
This results in a string of the form "<fieldName1>":"<fieldValue1>","<fieldName2>":"<fieldValue2>",.... Once this
terminal string has been joined, we surround it with curly braces, creating a valid JSON string.

```java
public class Main {
    public static void main(String[] args) throws JsonSerializeException, IllegalAccessException {
        Car car = new Car("Ford", "F150", "2018");
        JsonSerializer serializer = new JsonSerializer();
        serializer.serialize(car);

        /*
        Output: 
        {"year":"2018","model":"F150","manufacturer":"Ford"}
         */
    }
}
```

Note: The elements in the returned array are not sorted and are not in any particular order.

Java annotations are a very powerful feature in the Java language, but most often, we are the users of standard
annotations (such as @Override) or common framework annotations (such as @Autowired), rather than their developers.
While annotations should not be used in place of interfaces or other language constructs that properly accomplish a task
in an object-oriented manner, they can greatly simplify repetitive logic. For example, rather than creating a
toJsonStringmethod within an interface and having all classes that can be serialized implement this interface, we can
annotate each serializable field. This takes the repetitive logic of the serialization process (mapping field names to
field values) and places it into a single serializer class. It also decouples the serialization logic from the domain
logic, removing the clutter of manual serialization from the conciseness of the domain logic.


Reference:
1. DZone
2. JavaPoint
