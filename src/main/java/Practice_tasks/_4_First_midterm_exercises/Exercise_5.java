/*
Да се напише алгоритам кој ќе пресметува (евалуира) математички израз составен од броеви и операциите за собирање (+) и множење (*).

Забелешка: Операцијата множење има предност пред операцијата собирање.

/

Write an algorithm that will calculate (evaluate) a mathematical expression that consists of numbers and operations for adding (*) and multiplying (*).

Note: The operation of multiplying has precedence before the operation of adding.

For example:
Input 	Result

2+2*2*2*2*2*2+2*2



70


 */

package Practice_tasks._4_First_midterm_exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ExpressionEvaluator {

    public static int evaluateExpression(String expression) {
        // Stack to hold the numbers and intermediate results
        Stack<Integer> stack = new Stack<>();

        // Temporary variable to accumulate the current number
        int currentNumber = 0;
        char prevOperator = '+';

        // Iterate through the expression
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            // If the character is a digit, build the current number
            if (Character.isDigit(ch)) {
                currentNumber = currentNumber * 10 + (ch - '0');
            }

            // If the character is an operator or it's the end of the string
            if (!Character.isDigit(ch) && ch != ' ' || i == expression.length() - 1) {
                if (prevOperator == '+') {
                    stack.push(currentNumber);  // Push the current number to the stack
                } else if (prevOperator == '*') {
                    // Pop the last element, multiply with the current number, and push the result back
                    int top = stack.pop();
                    stack.push(top * currentNumber);
                }

                // Reset current number and update the operator
                currentNumber = 0;
                prevOperator = ch;
            }
        }

        // Sum all numbers left in the stack
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    public static void main(String[] args)throws IOException {
        BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression(input.readLine()));
    }
}
