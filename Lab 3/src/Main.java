import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        MyScanner sc = new MyScanner("C:\\Users\\andre\\OneDrive\\Documents\\GitHub\\LFTC\\Lab 3\\src\\P2.txt");
        System.out.println(sc.scan());
    }
}
