package example_for_readme_file;

public class DeprecatedAnimal {
    void dance(){
        System.out.println("I can Dance.");
    }
    @Deprecated
    void talk(){
        System.out.println("I can Talk");
    }
}

class TestDeprecate{
    public static void main(String[] args) {
        DeprecatedAnimal deprecatedAnimal = new DeprecatedAnimal();
        deprecatedAnimal.talk();
    }
}


