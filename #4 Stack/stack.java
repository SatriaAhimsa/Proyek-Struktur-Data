import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class stack {
    // Fungsi operator
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

    // Fungsi cek op itu right-associative
    static boolean isRightAssociative(char c) {
        return c == '^';
    }

    public static List<String> infixToPostfix(String s) {
        Stack<Character> st = new Stack<>();
        List<String> res = new ArrayList<>();
        int i = 0;

        while (i < s.length()) {
            char c = s.charAt(i);

            // Skip spasi
            if (c == ' ') {
                i++;
                continue;
            }

            // jika digit, jumlahkan seluruh angka
            if (Character.isDigit(c)) {
                StringBuilder num = new StringBuilder();
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num.append(s.charAt(i));
                    i++;
                }
                res.add(num.toString());
                continue;
            }

            // jika huruf (variable), tambahkan ke result
            else if (Character.isLetter(c)) {
                res.add(String.valueOf(c));
            }

            // jika '(', push to stack
            else if (c == '(') {
                st.push('(');
            }

            // jika ')', pop sampai '('
            else if (c == ')') {
                while (!st.isEmpty() && st.peek() != '(') {
                    res.add(String.valueOf(st.pop()));
                }
                st.pop();
            }

            // jika operator
            else {
                while (!st.isEmpty() && st.peek() != '(' &&
                       (prec(st.peek()) > prec(c) ||
                       (prec(st.peek()) == prec(c) && !isRightAssociative(c)))) {
                    res.add(String.valueOf(st.pop()));
                }
                st.push(c);
            }

            i++;
        }

        // Pop operator tersisa
        while (!st.isEmpty()) {
            res.add(String.valueOf(st.pop()));
        }

        return res;
    }

    static int floorDiv(int a, int b) {
        if (a * b < 0 && a % b != 0)
            return (a / b) - 1;
        return a / b;
    }
    
    public static int evaluatePostfix(List<String> arr) {
        Stack<Integer> st = new Stack<>();

        for (String token : arr) {
          
            // jika dia operand (number), masukkan ke stack
            if (Character.isDigit(token.charAt(0)) || 
                (token.length() > 1 && token.charAt(0) == '-')) {
                st.push(Integer.parseInt(token));
                System.out.println("Push: " + token + " -> Stack: " + st);
            } 
        
            // kalo tidak, harusnya operator
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

        List<String> postfix = infixToPostfix(exp);
        System.out.println("Ekspresi postfix: " + String.join(" ", postfix));
        System.out.println("perhitungan Postfix:");
        System.out.println("Hasil: " + evaluatePostfix(postfix));
        sc.close();
    }
}

