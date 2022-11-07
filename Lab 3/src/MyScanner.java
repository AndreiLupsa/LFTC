import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyScanner {
    private final ArrayList<String> OPERATORS = new ArrayList<>(
            List.of("+", "-", "*", "/", "<", ">", "=", "==", "<=", "=>", "not", "or", "and"));
    private final ArrayList<String> SEPARATORS = new ArrayList<>(
            List.of("(", ")", "[", "]", "{", "}", ":", ";", ",", ".", " ", "    ", "\n"));
    private final ArrayList<String> RESERVED_WORDS = new ArrayList<>(
            List.of("START", "END", "read", "write", "Array", "Integer", "Char", "String", "Boolean", "true", "false", "?", "!", "&"));
    private final SymbolTable STi;
    private final SymbolTable STc;
    private final String programFilePath;

    public MyScanner(String programFilePath) {
        this.STi = new SymbolTable(16);
        this.STc = new SymbolTable(16);
        this.programFilePath = programFilePath;
    }

    public String scan() throws FileNotFoundException {
        return this.readFile();
    }

    private String readFile() throws FileNotFoundException {
        StringBuilder fileContent = new StringBuilder();
        Scanner scanner = new Scanner(new File(this.programFilePath));
        while (scanner.hasNextLine()) {
            fileContent.append(scanner.nextLine()).append("\n");
        }
        return fileContent.toString();
    }

    private String tokenize() throws FileNotFoundException {
        String fileContent = this.readFile();
        return fileContent;

    }
}
