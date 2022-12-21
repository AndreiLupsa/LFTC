import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyScanner {
    private final LangSpec ls = new LangSpec();
    private final PIF pif = new PIF();
    private final SymTable STi = new SymTable(1);
    private final SymTable STc = new SymTable(1);

    private final String programFilePath;
    private final String PIFFilePath;
    private final String STFilePath;

    public MyScanner(String programFilePath ,String PIFFilePath, String STFilePath) {
        this.programFilePath = programFilePath;
        this.PIFFilePath = PIFFilePath;
        this.STFilePath = STFilePath;
    }
    public void scan() {
        List<Pair<String, Integer>> tokenPairs = new ArrayList<>();
        try {
            File file = new File(programFilePath);
            Scanner reader = new Scanner(file);

            int lineNr = 1;

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                List<String> tokens = tokenize(line);

                for (String token : tokens) {
                    tokenPairs.add(new Pair<>(token, lineNr));
                }

                ++lineNr;
            }

            reader.close();

            buildPIF(tokenPairs);
            writeResults();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<String> tokenize(String line) {
        ArrayList<String> tokens = new ArrayList<>();

        for (int i = 0; i < line.length(); ++i) {
            if (ls.isSeparator(String.valueOf(line.charAt(i))) && !(String.valueOf(line.charAt(i))).equals(" ")) {
                tokens.add(String.valueOf(line.charAt(i)));
            } else if (line.charAt(i) == '\"') {
                String constant = identifyStringConst(line, i);
                tokens.add(constant);
                i += constant.length() - 1;
            } else if (line.charAt(i) == '\'') {
                String constant = identifyCharConst(line, i);
                tokens.add(constant);
                i += constant.length() - 1;
            } else if (line.charAt(i) == '-') {
                String token = checkMinusToken(line, i, tokens);
                tokens.add(token);
                i += token.length() - 1;
            } else if (line.charAt(i) == '+') {
                String token = checkPlusToken(line, i, tokens);
                tokens.add(token);
                i += token.length() - 1;
            } else if (ls.isPartOfOperator(line.charAt(i))) {
                String operator = identifyOperator(line, i);
                tokens.add(operator);
                i += operator.length() - 1;
            } else if (line.charAt(i) != ' ') {
                String token = identifyToken(line, i);
                tokens.add(token);
                i += token.length() - 1;
            }
        }
        return tokens;
    }

    public String identifyStringConst(String line, int position) {
        StringBuilder str = new StringBuilder();
        for (int i = position; i < line.length(); i++) {
            str.append(line.charAt(i));
            if (line.charAt(i) == '\"' && i != position)
                break;
        }
        return str.toString();
    }

    public String identifyCharConst(String line, int position) {
        StringBuilder ch = new StringBuilder();
        for (int i = position; i < line.length(); i++) {
            ch.append(line.charAt(i));
            if (line.charAt(i) == '\'' && i != position)
                break;
        }
        return ch.toString();
    }

    public String checkMinusToken(String line, int position, ArrayList<String> tokens) {
        if (ls.isIdentifier(tokens.get(tokens.size() - 1)) || ls.isConstant(tokens.get(tokens.size() - 1))) {
            return "-";
        }

        StringBuilder token = new StringBuilder();
        token.append('-');
        for (int i = position + 1; i < line.length() && (Character.isDigit(line.charAt(i)) || line.charAt(i) == '.'); ++i) {
            token.append(line.charAt(i));
        }
        return token.toString();
    }

    public String checkPlusToken(String line, int position, ArrayList<String> tokens) {
        if (ls.isIdentifier(tokens.get(tokens.size() - 1)) || ls.isConstant(tokens.get(tokens.size() - 1))) {
            return "+";
        }

        StringBuilder token = new StringBuilder();
        token.append('+');
        for (int i = position + 1; i < line.length() && (Character.isDigit(line.charAt(i)) || line.charAt(i) == '.'); ++i) {
            token.append(line.charAt(i));
        }
        return token.toString();
    }

    public String identifyOperator(String line, int position) {
        StringBuilder operator = new StringBuilder();
        operator.append(line.charAt(position));
        operator.append(line.charAt(position + 1));
        if (ls.isOperator(operator.toString()))
            return operator.toString();
        return String.valueOf(line.charAt(position));
    }

    public String identifyToken(String line, int position) {
        StringBuilder token = new StringBuilder();

        for (int i = position; i < line.length()
                && !ls.isSeparator(String.valueOf(line.charAt(i)))
                && !ls.isPartOfOperator(line.charAt(i))
                && line.charAt(i) != ' '; ++i) {
            token.append(line.charAt(i));
        }

        return token.toString();
    }

    public void buildPIF(List<Pair<String, Integer>> tokens) {
        List<String> invalidTokens = new ArrayList<>();
        boolean isLexicallyCorrect = true;
        for (Pair<String, Integer> tokenPair : tokens) {
            String token = tokenPair.getKey();
            if (ls.isOperator(token) || ls.isReservedWord(token) || ls.isSeparator(token)) {
                pif.add(token, -1);
            } else if (ls.isIdentifier(token)) {
                STi.add(token);
                int position = STi.getPosition(token);
                pif.add("identifier", position);
            } else if (ls.isConstant(token)) {
                STc.add(token);
                int position = STc.getPosition(token);
                pif.add("constant", position);
            } else if (!invalidTokens.contains(token)) {
                invalidTokens.add(token);
                isLexicallyCorrect = false;
                System.out.println("Error at line " + tokenPair.getValue() + ": invalid token " + token);
            }
        }

        if (isLexicallyCorrect) {
            System.out.println("Program is lexically correct");
        }
    }

    public void writeResults() {
        try {
            File pifFile = new File(PIFFilePath);
            FileWriter pifFileWriter = new FileWriter(pifFile, false);
            BufferedWriter pifWriter = new BufferedWriter(pifFileWriter);
            pifWriter.write(pif.toString());
            pifWriter.close();

            File symbolTableFile = new File(STFilePath);
            FileWriter symbolTableFileWriter = new FileWriter(symbolTableFile, false);
            BufferedWriter symbolTableWriter = new BufferedWriter(symbolTableFileWriter);
            symbolTableWriter.write("Identifier Symbol Table:\n");
            symbolTableWriter.write(STi.toString());
            symbolTableWriter.write("\n\nConstant Symbol Table:\n");
            symbolTableWriter.write(STc.toString());
            symbolTableWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
