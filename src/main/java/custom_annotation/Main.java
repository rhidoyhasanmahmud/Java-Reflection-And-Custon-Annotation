package custom_annotation;

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
