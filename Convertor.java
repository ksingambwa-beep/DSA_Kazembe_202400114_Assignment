import java.util.Stack;

public class Convertor {

    // Function to check precedence of operators
    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    // Function to convert infix to postfix
    public static String infixToPostfix(String infix) {
        Stack<Character> stack = new Stack<>();
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < infix.length(); i++) {
            char symbol = infix.charAt(i);

            // If operand, add to output
            if (Character.isLetterOrDigit(symbol)) {
                output.append(symbol);
            }
            // If '(', push to stack
            else if (symbol == '(') {
                stack.push(symbol);
            }
            // If ')', pop until '('
            else if (symbol == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output.append(stack.pop());
                }
                stack.pop(); // remove '('
            }
            // If operator
            else {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(symbol)) {
                    output.append(stack.pop());
                }
                stack.push(symbol);
            }
        }

        // Pop remaining operators
        while (!stack.isEmpty()) {
            output.append(stack.pop());
        }

        return output.toString();
    }

    // Function to reverse postfix to get prefix
    public static String postfixToPrefix(String postfix) {
        return new StringBuilder(postfix).reverse().toString();
    }

    public static void main(String[] args) {
        String infixExpression = "(A+B)*C-D";
        System.out.println("Infix Expression: " + infixExpression);

        String postfix = infixToPostfix(infixExpression);
        System.out.println("Postfix Expression: " + postfix);

        String prefix = postfixToPrefix(postfix);
        System.out.println("Prefix Expression: " + prefix);
    }
}
