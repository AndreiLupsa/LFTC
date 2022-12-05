import java.util.Arrays;
import java.util.Objects;

public class SymTable {

    String[] symbols;
    int size = 0;

    SymTable(int max_cap){
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
            symbols[size] = name;
            size++;
        }
        return size-1;
    }
    
    int add(int val){
        return add(Integer.toString(val));
    }

    int getPosition(String name){
        int pos = 0;
        for (String s : symbols){
            if (Objects.equals(s, name)){
                return pos;
            }
            pos++;
        }
        return -1;
    }

    String[] getAll(){
        return symbols;
    }

    public String toString(){
        return Arrays.toString(symbols);
    }

}
