import java.io.File;
import java.util.*;

public class FA {
    public Set<String> alphabet, states, finalStates;
    public String initialState;
    public Map< Pair<String, String>, Set<String> > transitions;

    public FA(String filename) {
        this.states = new HashSet<>();
        this.alphabet = new HashSet<>();
        this.finalStates = new HashSet<>();
        this.transitions = new HashMap<>();

        readFA(filename);
    }

    private void readFA(String filename){
        try {
            File file = new File(filename);
            Scanner reader = new Scanner(file);

            String statesLine = reader.nextLine();
            states = new HashSet<>(Arrays.asList(statesLine.split(" ")));

            String alphabetLine = reader.nextLine();
            alphabet = new HashSet<>(Arrays.asList(alphabetLine.split(" ")));

            initialState = reader.nextLine();

            String finalStatesLine = reader.nextLine();
            finalStates = new HashSet<>(Arrays.asList(finalStatesLine.split(" ")));

            while(reader.hasNextLine()){
                String transitionLine = reader.nextLine();
                String[] transElems = transitionLine.split(" ");

                if(states.contains(transElems[0]) && alphabet.contains(transElems[1]) && states.contains(transElems[2])) {

                    Pair<String, String> transition = new Pair<>(transElems[0], transElems[1]);

                    if (!(transitions.containsKey(transition))) {
                        Set<String> transitionStatesSet = new HashSet<>();
                        transitionStatesSet.add(transElems[2]);
                        transitions.put(transition, transitionStatesSet);
                    } else {
                        System.out.println("aaa");
                        transitions.get(transition).add(transElems[2]);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean checkIfDFA(){
        return this.transitions.values().stream().noneMatch(list -> list.size() > 1);
    }

    public boolean checkSequence(String sequence){
        if(sequence.length() == 0)
            return finalStates.contains(initialState);

        String state = initialState;
        for(int i=0;i<sequence.length();++i){
            Pair<String, String> key = new Pair<>(state, String.valueOf(sequence.charAt(i)));
            if(transitions.containsKey(key))
                state = transitions.get(key).iterator().next();
            else
                return false;
        }

        return finalStates.contains(state);
    }

    public String getAllStates(){
        StringBuilder builder = new StringBuilder();
        builder.append("States: ");
        for (String s : states){
            builder.append(s).append(" ");
        }

        return builder.toString();
    }

    public String getInitialState(){
        return "Initial state: " +
                initialState + " ";
    }

    public String getFinalStates(){
        StringBuilder builder = new StringBuilder();
        builder.append("Final states: ");
        for (String fs : finalStates){
            builder.append(fs).append(" ");
        }

        return builder.toString();
    }

    public String getAlphabet(){
        StringBuilder builder = new StringBuilder();
        builder.append("Alphabet: ");
        for (String a : alphabet){
            builder.append(a).append(" ");
        }

        return builder.toString();
    }

    public String getTransitions(){
        StringBuilder builder = new StringBuilder();
        builder.append("Transitions: \n");
        transitions.forEach((K, V) -> {
            builder.append("(").append(K.key).append(",").append(K.value).append(") = ").append(V).append("\n");
        });

        return builder.toString();
    }

    @Override
    public String toString() {
        return "FiniteAutomaton{" +
                ", states=" + states +
                ", alphabet=" + alphabet +
                ", initialState='" + initialState + '\'' +
                ", finalStates=" + finalStates +
                ", transitions=" + transitions +
                '}';
    }
}
