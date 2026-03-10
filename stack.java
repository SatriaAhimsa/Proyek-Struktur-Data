import java.util.Scanner;
import java.util.Stack;

public class stack {
    // Function to return precedence of operators
    static int prec(char c) {
        if (c == '^')
            return 3;
        else if (c == '/' || c == '*')
            return 2;
        else if (c == '+' || c == '-')
            return 1;
        else
            return -1;
    }

    // Function to check if operator is right-associative
    static boolean isRightAssociative(char c) {
        return c == '^';
    }

    public static String infixToPostfix(String s) {
        Stack<Character> st = new Stack<>();
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // If operand, add to result
            if ((c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z') ||
                (c >= '0' && c <= '9'))
                res.append(c);

            // If '(', push to stack
            else if (c == '(')
                st.push('(');

            // If ')', pop until '( '
            else if (c == ')') {
                while (!st.isEmpty() && st.peek() != '(') {
                    res.append(st.pop());
                }
                st.pop();
            }

            // If operator
            else {
                while (!st.isEmpty() && st.peek() != '(' &&
                       (prec(st.peek()) > prec(c) ||
                       (prec(st.peek()) == prec(c) && !isRightAssociative(c)))) {
                    res.append(st.pop());
                }
                st.push(c);
            }
        }

        // Pop remaining operators
        while (!st.isEmpty()) {
            res.append(st.pop());
        }

        return res.toString();
    }

    static int floorDiv(int a, int b) {
        if (a * b < 0 && a % b != 0)
            return (a / b) - 1;
        return a / b;
    }
    
    public static int evaluatePostfix(String[] arr) {
        Stack<Integer> st = new Stack<>();

        for (String token : arr) {
          
            // If it's an operand (number), push it onto the stack
            if (Character.isDigit(token.charAt(0)) || 
                (token.length() > 1 && token.charAt(0) == '-')) {
                st.push(Integer.parseInt(token));
                System.out.println("Push: " + token + " -> Stack: " + st);
            } 
        
            // Otherwise, it must be an operator
            else {
                int val1 = st.pop();
                int val2 = st.pop();

                System.out.println("Pop: " + val1 + ", " + val2 + " -> Operator: " + token);
                if (token.equals("+")) {
                    int result = val2 + val1;
                    st.push(result);
                    System.out.println(val2 + " + " + val1 + " = " + result);
                    System.out.println("Push: " + result + " -> Stack: " + st);
                }
                else if (token.equals("-")) {
                    int result = val2 - val1;
                    st.push(result);
                    System.out.println(val2 + " - " + val1 + " = " + result);
                    System.out.println("Push: " + result + " -> Stack: " + st);
                }
                else if (token.equals("*")) {
                    int result = val2 * val1;
                    st.push(result);
                    System.out.println(val2 + " * " + val1 + " = " + result);
                    System.out.println("Push: " + result + " -> Stack: " + st);
                }
                else if (token.equals("/")) {
                    int result = floorDiv(val2, val1);
                    st.push(result);
                    System.out.println(val2 + " / " + val1 + " = " + result);
                    System.out.println("Push: " + result + " -> Stack: " + st);
                }
                else if (token.equals("^")) {
                    int result = (int)Math.pow(val2, val1);
                    st.push(result);
                    System.out.println(val2 + " ^ " + val1 + " = " + result);
                    System.out.println("Push: " + result + " -> Stack: " + st);
                }
            }
        }
        return st.pop();
    }
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan ekspresi infix: ");
        String exp = sc.nextLine();

        System.out.println("Ekspresi postfix: " + infixToPostfix(exp));
        String[] arr = infixToPostfix(exp).split("");
        System.out.println("Hasil: " + evaluatePostfix(arr));
        sc.close();
    }
}

