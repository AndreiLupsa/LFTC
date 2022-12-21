import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LangSpec {
    private final ArrayList<String> OPERATORS = new ArrayList<>(
            List.of("+", "-", "*", "/", "%", "<", ">", "<=", ">=", "=", "==", "not"));
    private final ArrayList<String> SEPARATORS = new ArrayList<>(
            List.of("(", ")", "[", "]", "{", "}", ":", ";", ",", ".", " ", "\t","\n"));
    private final ArrayList<String> RESERVED_WORDS = new ArrayList<>(
            List.of("START", "END", "EXIT", "read", "write", "Array", "Integer", "Char", "String", "Boolean", "true", "false", "if", "else", "loop"));


    public boolean isOperator(String token) {
        return OPERATORS.contains(token);
    }

    public boolean isPartOfOperator(char op) {
        return isOperator(String.valueOf(op));
    }

    public boolean isSeparator(String token) {
        return SEPARATORS.contains(token);
    }

    public boolean isReservedWord(String token) {
        return RESERVED_WORDS.contains(token);
    }

    public boolean isIdentifier(String token) {
        String pattern = "^[a-zA-Z]([a-z|A-Z|0-9|_])*$";
        return token.matches(pattern);
    }
    public boolean isConstant(String token) {
        String numberPattern = "^0|[1-9]([0-9])*|[+|-][1-9]([0-9])*$";
        String charPattern = "^'[a-zA-Z0-9_?!#*.;/%+\\-=<>{}() ]'$";
        String stringPattern = "^\"[a-zA-Z0-9_?!#*.;/%+\\-=<>{}() ]+\"$";
        return token.matches(numberPattern) ||
                token.matches(charPattern) ||
                token.matches(stringPattern);
    }
}
