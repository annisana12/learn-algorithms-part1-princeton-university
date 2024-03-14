package week1;

public class HelloGoodbye {
    public static void main(String[] args) {
        if (args.length == 2) {
            String firstName = args[0];
            String secondName = args[1];

            System.out.println("Hello " + firstName + " and " + secondName + ".");
            System.out.println("Goodbye " + secondName + " and " + firstName + ".");
        } else {
            System.out.println("Please insert two names as command-line arguments");
        }
    }
}
