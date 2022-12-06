import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        p 0 p
//        p 0 q
//        p 1 q
//        q 0 p
//        q 1 r
//        r 0 p
//        r 1 r
        System.out.println("1. FA");
        System.out.println("2. Scanner");
        System.out.println("Command: ");

        Scanner reader = new Scanner(System.in);
        int option = reader.nextInt();

        switch (option) {
            case 1 -> runDFA();
            case 2 -> runScanner();
        }
    }

    private static void printDFAMenu(){
        System.out.println("Menu");
        System.out.println("1. Display all states.");
        System.out.println("2. Display initial state.");
        System.out.println("3. Display final states.");
        System.out.println("4. Display alphabet.");
        System.out.println("5. Display transitions.");
        System.out.println("6. Check sequence.");
        System.out.println("0. Exit");
    }

    private static void runDFA(){
        FA fa = new FA("src/Files/FA.in");
        printDFAMenu();
        System.out.println("Command: ");

        Scanner reader = new Scanner(System.in);
        int option = reader.nextInt();

        while(option != 0) {
            switch (option) {
                case 1 -> System.out.println(fa.getAllStates());
                case 2 -> System.out.println(fa.getInitialState());
                case 3 -> System.out.println(fa.getFinalStates());
                case 4 -> System.out.println(fa.getAlphabet());
                case 5 -> System.out.println(fa.getTransitions());
                case 6 -> {
                    if(fa.checkIfDFA()) {
                        System.out.println("Your sequence: ");
                        Scanner reader2 = new Scanner(System.in);
                        String sequence = reader2.next();

                        if (fa.checkSequence(sequence))
                            System.out.println("Sequence is valid");
                        else
                            System.out.println("Invalid sequence");
                    }
                    else {
                        System.out.println("FA is not deterministic.");
                    }
                }
            }
            System.out.println();
            printDFAMenu();
            System.out.println("Command: ");
            option = reader.nextInt();
        }
    }

    private static void runScanner(){
        System.out.println("P1:");
        MyScanner scannerP1 = new MyScanner("C:\\Users\\andre\\OneDrive\\Documents\\GitHub\\LFTC\\Lab 3\\src\\Files\\P1.txt",
                "C:\\Users\\andre\\OneDrive\\Documents\\GitHub\\LFTC\\Lab 3\\src\\Files\\PIF1.txt",
                "C:\\Users\\andre\\OneDrive\\Documents\\GitHub\\LFTC\\Lab 3\\src\\Files\\ST1.txt");
        scannerP1.scan();
        System.out.println("P1err:");
        MyScanner scannerP1err = new MyScanner("C:\\Users\\andre\\OneDrive\\Documents\\GitHub\\LFTC\\Lab 3\\src\\Files\\P1err.txt",
                "C:\\Users\\andre\\OneDrive\\Documents\\GitHub\\LFTC\\Lab 3\\src\\Files\\PIF1err.txt",
                "C:\\Users\\andre\\OneDrive\\Documents\\GitHub\\LFTC\\Lab 3\\src\\Files\\ST1err.txt");
        scannerP1err.scan();
        System.out.println("P2:");
        MyScanner scannerP2 = new MyScanner("C:\\Users\\andre\\OneDrive\\Documents\\GitHub\\LFTC\\Lab 3\\src\\Files\\P2.txt",
                "C:\\Users\\andre\\OneDrive\\Documents\\GitHub\\LFTC\\Lab 3\\src\\Files\\PIF2.txt",
                "C:\\Users\\andre\\OneDrive\\Documents\\GitHub\\LFTC\\Lab 3\\src\\Files\\ST2.txt");
        scannerP2.scan();
        System.out.println("P3:");
        MyScanner scannerP3 = new MyScanner("C:\\Users\\andre\\OneDrive\\Documents\\GitHub\\LFTC\\Lab 3\\src\\Files\\P3.txt",
                "C:\\Users\\andre\\OneDrive\\Documents\\GitHub\\LFTC\\Lab 3\\src\\Files\\PIF3.txt",
                "C:\\Users\\andre\\OneDrive\\Documents\\GitHub\\LFTC\\Lab 3\\src\\Files\\ST3.txt");
        scannerP3.scan();
    }
}