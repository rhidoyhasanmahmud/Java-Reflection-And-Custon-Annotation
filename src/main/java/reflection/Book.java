package reflection;


public class Book {
    private String name;

    public Book() {
        this.name = "Bangla 2nd Paper";
    }

    public void noArgumentMethod() {
        System.out.println("No Argument Method Called. The Book Name is " + name);
    }

    public void argumentMethod(int number) {
        System.out.println("Argument Method Called. The Number is " + number);
    }

    private void noArgumentPrivateMethod() {
        System.out.println("No Argument Private Method Called. The Book Name is " + name);
    }
}
