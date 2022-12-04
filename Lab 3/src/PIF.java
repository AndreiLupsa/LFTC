import java.util.ArrayList;
import java.util.List;

public class PIF {
    private List<Pair<String, Integer>> pif = new ArrayList<>();

    public void add(String code, int value){
        Pair<String, Integer> pair = new Pair<>(code, value);
        pif.add(pair);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Pair<String, Integer> pair : pif) {
            result.append(pair.getKey()).append(" (").append(pair.getValue()).append(")\n");
        }
        return result.toString();
    }
}
