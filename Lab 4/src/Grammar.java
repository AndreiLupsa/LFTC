import java.io.File;
import java.util.*;

public class Grammar {
    private Set<String> N, E;
    private String S = "";
    private HashMap<Set<String>, Set<List<String>>> P;

    public Grammar(String filename) {
        this.N = new HashSet<>();
        this.E = new HashSet<>();
        this.P = new HashMap<>();

        readG(filename);
    }
    public void readG(String filename){
        try {
            File file = new File(filename);
            Scanner reader = new Scanner(file);

            String NLine = reader.nextLine();
            N = new HashSet<>(Arrays.asList(NLine.split(" ")));

            String ELine = reader.nextLine();
            E = new HashSet<>(Arrays.asList(ELine.split(" ")));

            S = reader.nextLine();

            while(reader.hasNextLine()) {
                String production = reader.nextLine();
                String[] prElems = production.split(" ");
            }

            System.out.println(N);
            System.out.println(E);
            System.out.println(S);

        } catch (Exception ex) {
        ex.printStackTrace();
    }
    }
}
