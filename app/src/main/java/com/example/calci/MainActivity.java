package com.example.calci;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtSolution, txtOperation;
    private Button btnOne, btnTwo, btnThree, btnFour, btnFive,
            btnSix, btnSeven, btnEight, btnNine, btnZero,
            btnDecimal, btnEqualsTo, btnDiv, btnMul,
            btnSub, btnAdd, btnAc, btnC;
    private String operation = "", solution = "0", currentSym = "0";
    private double ans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSolution = findViewById(R.id.txtSolution);
        txtOperation = findViewById(R.id.txtOperation);
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);
        btnFive = findViewById(R.id.btnFive);
        btnSix = findViewById(R.id.btnSix);
        btnSeven = findViewById(R.id.btnSeven);
        btnEight = findViewById(R.id.btnEight);
        btnNine = findViewById(R.id.btnNine);
        btnZero = findViewById(R.id.btnZero);
        btnDecimal = findViewById(R.id.btnDecimal);
        btnEqualsTo = findViewById(R.id.btnEqualsTo);
        btnDiv = findViewById(R.id.btnDiv);
        btnMul = findViewById(R.id.btnMul);
        btnSub = findViewById(R.id.btnSub);
        btnAdd = findViewById(R.id.btnAdd);
        btnAc = findViewById(R.id.btnAc);
        btnC = findViewById(R.id.btnC);
        txtSolution.setText(solution);

    }

    @Override
    public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnOne:
                    operation = operation.concat("1");
                    txtOperation.setText(operation);
                    currentSym = "1";
                    break;

                case R.id.btnTwo:
                    operation = operation.concat("2");
                    txtOperation.setText(operation);
                    currentSym = "2";
                    break;

                case R.id.btnThree:
                    operation = operation.concat("3");
                    txtOperation.setText(operation);
                    currentSym = "3";
                    break;

                case R.id.btnFour:
                    operation = operation.concat("4");
                    txtOperation.setText(operation);
                    currentSym = "4";
                    break;

                case R.id.btnFive:
                    operation = operation.concat("5");
                    txtOperation.setText(operation);
                    currentSym = "5";
                    break;

                case R.id.btnSix:
                    operation = operation.concat("6");
                    txtOperation.setText(operation);
                    currentSym = "6";
                    break;

                case R.id.btnSeven:
                    operation = operation.concat("7");
                    txtOperation.setText(operation);
                    currentSym = "7";
                    break;

                case R.id.btnEight:
                    operation = operation.concat("8");
                    txtOperation.setText(operation);
                    currentSym = "8";
                    break;

                case R.id.btnNine:
                    operation = operation.concat("9");
                    txtOperation.setText(operation);
                    currentSym = "9";
                    break;

                case R.id.btnZero:
                    operation = operation.concat("0");
                    txtOperation.setText(operation);
                    currentSym = "0";
                    break;

                case R.id.btnDecimal:
                        operation = operation.concat(".");
                        txtOperation.setText(operation);
                        currentSym = ".";
                    break;


                case R.id.btnDiv:
                    if (!currentSym.equals("/") && !currentSym.equals("*") &&
                            !currentSym.equals("-") && !currentSym.equals("+") &&
                            !currentSym.equals(".")) {
                        operation = operation.concat("/");
                        txtOperation.setText(operation);
                        currentSym = "/";
                    }
                    break;


                case R.id.btnMul:
                    if (!currentSym.equals("/") && !currentSym.equals("*") &&
                            !currentSym.equals("-") && !currentSym.equals("+") &&
                            !currentSym.equals(".")) {
                        operation = operation.concat("*");
                        txtOperation.setText(operation);
                        currentSym = "*";
                    }
                    break;


                case R.id.btnSub:
                    if (!currentSym.equals("/") && !currentSym.equals("*") &&
                            !currentSym.equals("-") && !currentSym.equals("+") &&
                            !currentSym.equals(".")) {
                        operation = operation.concat("-");
                        txtOperation.setText(operation);
                        currentSym = "-";
                    }
                    break;


                case R.id.btnAdd:
                    if (!currentSym.equals("/") && !currentSym.equals("*") &&
                            !currentSym.equals("-") && !currentSym.equals("+") &&
                            !currentSym.equals(".")) {
                        operation = operation.concat("+");
                        txtOperation.setText(operation);
                        currentSym = "+";
                    }
                    break;

                    
                case R.id.btnAc:
                    txtOperation.setText("");
                    operation = "";
                    txtSolution.setText("0");
                    currentSym = "0";
                    break;

                case R.id.btnC:
                    txtOperation.setText("");
                    operation = "";
                    currentSym = "0";
                    break;

                case R.id.btnEqualsTo:
                    try {
                        if(!operation.equals("")) {
                            ans = eval(operation);
                            txtOperation.setText(ans + "");
                            txtSolution.setText(ans + "");
                            operation = (ans + "");
                            currentSym = "0";
                        }
                        } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Mathematical Error", Toast.LENGTH_SHORT).show();
                        }
                        break;
            }
    }

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}