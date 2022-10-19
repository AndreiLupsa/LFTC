import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        //ST type 1b
        ST sti = new ST(3);
        ST stc = new ST(5);
        int v;
        sti.add("a");
        sti.add("b");
        sti.add("c");
        sti.add("c");
        v = sti.add("c");
        sti.add("c");
        sti.add("c");
        stc.add(1);
        stc.add(2);
        sti.add(3);
        System.out.println(Arrays.toString(sti.getAll()));
        System.out.println(stc);
        System.out.println(v);
    }
}
