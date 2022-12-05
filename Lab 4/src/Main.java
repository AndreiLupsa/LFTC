import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
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
