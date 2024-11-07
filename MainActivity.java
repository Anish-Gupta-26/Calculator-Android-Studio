package com.example.application_3;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private double firstNumber = 0;
    private double secondNumber = 0;
    private String currentOperation = "";
    private boolean isNewOperation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.textViewResult);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                String buttonText = button.getText().toString();

                // Number or dot button clicked
                if (buttonText.matches("[0-9]")) {
                    if (isNewOperation) {
                        textViewResult.setText(buttonText);
                        isNewOperation = false;
                    } else {
                        textViewResult.append(buttonText);
                    }
                }

                // Operator button clicked
                else if (buttonText.matches("[+\\-*/]")) {
                    firstNumber = Double.parseDouble(textViewResult.getText().toString());
                    currentOperation = buttonText;
                    isNewOperation = true;
                }

                // Equals button clicked
                else if (buttonText.equals("=")) {
                    secondNumber = Double.parseDouble(textViewResult.getText().toString());
                    double result = calculateResult();
                    textViewResult.setText(String.valueOf(result));
                    isNewOperation = true;
                }

                // Clear button clicked
                else if (buttonText.equals("C")) {
                    textViewResult.setText("0");
                    firstNumber = 0;
                    secondNumber = 0;
                    currentOperation = "";
                    isNewOperation = true;
                }
            }
        };

        // Set listener to all buttons
        int[] buttonIDs = {R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
                R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9,
                R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMultiply, R.id.buttonDivide,
                R.id.buttonEquals, R.id.buttonClear};

        for (int id : buttonIDs) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private double calculateResult() {
        switch (currentOperation) {
            case "+": return firstNumber + secondNumber;
            case "-": return firstNumber - secondNumber;
            case "*": return firstNumber * secondNumber;
            case "/": return (secondNumber != 0) ? firstNumber / secondNumber : 0;
            default: return 0;
        }
    }
}
