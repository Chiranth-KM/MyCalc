package com.example.mycalc;

public class calculator_hist {

    private String expression;
    private String ans;

    // Normal Constructors
    public calculator_hist(String expression, String ans) {
        this.expression = expression;
        this.ans = ans;
    }

    public calculator_hist() {
    }
    // to string method


    @Override
    public String toString() {
        return "calculator_hist{" +
                "expression='" + expression + '\'' +
                ", ans='" + ans + '\'' +
                '}';
    }

    // Setter and getters
    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

}
