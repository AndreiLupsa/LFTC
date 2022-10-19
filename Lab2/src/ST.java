import java.util.Arrays;
import java.util.Objects;
public class ST {

    String[] symbols;
    int size = 0;

    ST(int max_cap){
        symbols = new String[max_cap];
    }

    Boolean exists(String name){
        for (String s : symbols){
            if (Objects.equals(s, name)){
                return true;
            }
        }
        return false;
    }
    int add(String name){
        if(!exists(name)){
            if(size == symbols.length) {
                symbols = Arrays.copyOf(symbols, symbols.length * 2);
            }
            symbols[size++] = name;
        }
        return size-1;
    }
    
    int add(int val){
        return add(Integer.toString(val));
    }

    String[] getAll(){
        return symbols;
    }

    public String toString(){
        return Arrays.toString(symbols);
    }
}
