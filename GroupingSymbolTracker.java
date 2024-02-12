import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class GroupingSymbolChecker {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java GroupingSymbolChecker <source-code-file>");
            return;
        }

        String fileName = args[0];
        if (checkGroupingSymbols(fileName)) {
            System.out.println("The source-code file has correct pairs of grouping symbols.");
        } else {
            System.out.println("The source-code file has incorrect pairs of grouping symbols.");
        }
    }

    private static boolean checkGroupingSymbols(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            Stack<Character> stack = new Stack<>();

            String line;
            while ((line = reader.readLine()) != null) {
                for (char ch : line.toCharArray()) {
                    if (isOpenSymbol(ch)) {
                        stack.push(ch);
                    } else if (isCloseSymbol(ch)) {
                        if (stack.isEmpty() || !isMatchingPair(stack.pop(), ch)) {
                            return false; // Incorrect pairs
                        }
                    }
                }
            }

            return stack.isEmpty();
        } catch (IOException e) {
            e.printStackTrace();
            return false; 
        }
    }

    private static boolean isOpenSymbol(char ch) {
        return ch == '(' || ch == '{' || ch == '[';
    }

    private static boolean isCloseSymbol(char ch) {
        return ch == ')' || ch == '}' || ch == ']';
    }

    private static boolean isMatchingPair(char openSymbol, char closeSymbol) {
        return (openSymbol == '(' && closeSymbol == ')') ||
               (openSymbol == '{' && closeSymbol == '}') ||
               (openSymbol == '[' && closeSymbol == ']');
    }
}
