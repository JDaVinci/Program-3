package com.example.jj.calfunk;


public class CalcConstruct {

    private double Op;
    private double waitOp;
    private String mWaitOp;
    private double mCalculatorMemory;

    // operator types
    public static final String ADD = "+";
    public static final String SUBTRACT = "-";
    public static final String MULTIPLY = "*";
    public static final String DIVIDE = "/";
    public static final String CLEAR = "CLR" ;
    public static final String POWER = "^";
    public static final String TOGGLESIGN = "+/-";
    public static final String EQUALS = "=";

    // constructor
    public CalcConstruct() {
        // initialize variables upon start
        Op = 0;
        waitOp = 0;
        mWaitOp = "";
        mCalculatorMemory = 0;
    }

    public void setOperand(double operand) {
        Op = operand;
    }

    public double getResult() {
        return Op;
    }

    // used on screen orientation change
    public void setMemory(double calculatorMemory) {
        mCalculatorMemory = calculatorMemory;
    }

    public String toString() {
        return Double.toString(Op);
    }

    protected double performOperation(String operator) {

        if (operator.equals(CLEAR)) {
            Op = 0;
            mWaitOp = "";
            waitOp = 0;
            mCalculatorMemory = 0;
        }

         else if (operator.equals(TOGGLESIGN)) {
            Op = -Op;

        } else {
            performWaitingOperation();
            mWaitOp = operator;
            waitOp = Op;
        }

        return Op;
    }

    protected void performWaitingOperation() {

        if (mWaitOp.equals(ADD)) {
            Op = waitOp + Op;
        }
        else if(mWaitOp.equals(POWER)){
            Op =  Math.pow(waitOp,Op);
        }

        else if (mWaitOp.equals(SUBTRACT)) {
            Op = waitOp - Op;
        }
        else if (mWaitOp.equals(MULTIPLY)) {
            Op = waitOp * Op;
        }
        else if (mWaitOp.equals(DIVIDE)) {
            if (Op != 0) {
                Op = waitOp / Op;
            }
        }

    }
}