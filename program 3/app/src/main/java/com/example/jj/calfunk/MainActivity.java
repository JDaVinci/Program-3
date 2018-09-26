//Jeremy Reynolds
//received help from Langdon Himebaugh
//COSC 4730


package com.example.jj.calfunk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends Activity implements OnClickListener {

    private TextView calcDisplay;
    private Boolean typingNum = false;
    private CalcConstruct calcBrain;
    private static final String DIGITS = "0123456789.";

    DecimalFormat df = new DecimalFormat("@###########");

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // hide the window title.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // hide the status bar and other OS-level chrome
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calcBrain = new CalcConstruct();
        calcDisplay = (TextView) findViewById(R.id.textView1);

        df.setMinimumFractionDigits(0);
        df.setMinimumIntegerDigits(1);
        df.setMaximumIntegerDigits(8);

        findViewById(R.id.button0).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);

        findViewById(R.id.buttonAdd).setOnClickListener(this);
        findViewById(R.id.buttonSubtract).setOnClickListener(this);
        findViewById(R.id.buttonMultiply).setOnClickListener(this);
        findViewById(R.id.buttonDivide).setOnClickListener(this);
        findViewById(R.id.buttonToggleSign).setOnClickListener(this);
        findViewById(R.id.buttonDecimalPoint).setOnClickListener(this);
        findViewById(R.id.buttonEquals).setOnClickListener(this);
        findViewById(R.id.buttonClear).setOnClickListener(this);


        // The following buttons only exist in layout-land (Landscape mode) and require extra attention.
        // The messier option is to place the buttons in the regular layout too and set android:visibility="invisible".

        if (findViewById(R.id.buttonPower) != null) {
            findViewById(R.id.buttonPower).setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {

        String buttonPressed = ((Button) v).getText().toString();

        if (DIGITS.contains(buttonPressed)) {

            // Number button was pressed
            if (typingNum) {

                if (buttonPressed.equals(".") && calcDisplay.getText().toString().contains(".")) {

                    // Eliminate entering multiple decimals
                } else {
                    calcDisplay.append(buttonPressed);
                }

            } else {

                if (buttonPressed.equals(".")) {

                    // error prevent
                    calcDisplay.setText(0 + buttonPressed);
                } else {
                    calcDisplay.setText(buttonPressed);
                }

                typingNum = true;
            }

        } else {
            // operation was pressed
            if (typingNum) {

                calcBrain.setOperand(Double.parseDouble(calcDisplay.getText().toString()));
                typingNum = false;
            }

            calcBrain.performOperation(buttonPressed);
            calcDisplay.setText(df.format(calcBrain.getResult()));

        }

    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        // Save variables on screen orientation change
//        outState.putDouble("OPERAND", calcBrain.getResult());
//        outState.putDouble("MEMORY", calcBrain.getMemory());
//    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore variables on screen orientation change
        calcBrain.setOperand(savedInstanceState.getDouble("OPERAND"));
        calcBrain.setMemory(savedInstanceState.getDouble("MEMORY"));
        calcDisplay.setText(df.format(calcBrain.getResult()));
    }

}