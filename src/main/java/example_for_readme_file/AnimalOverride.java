package example_for_readme_file;

public class AnimalOverride {
    void eatSomething() {
        System.out.println("Eat for live.");
    }
}

class Dog extends AnimalOverride {

    @Override
    void eatSomething() {
        System.out.println("Eat Green Food.");
    }
}

class TestAnnotation {
    public static void main(String[] args) {
        AnimalOverride animal = new AnimalOverride();
        animal.eatSomething();

        Dog dog = new Dog();
        dog.eatSomething();
    }
}