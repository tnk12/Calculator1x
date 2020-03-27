package com.example.calculator1x;



import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.service.autofill.FieldClassification;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.util.regex.MatchResult;


public class Калькулятор extends Fragment {

    Button btn0; Button btn1; Button btn2; Button btn3; Button btn4; Button btn5; Button btn6; Button btn7; Button btn8; Button btn9; Button btnPlus; Button btnMinus; Button btnMultiply; Button btnDivision; Button btnEqual; Button btnClear; Button btnDot; Button btnKvadrat; Button btnBack;Button btnPercent; Button btnBracket;

    TextView CalcIn,CalcOut; String usage; String val;
    Boolean checkBracket = false;
    int tap = 2;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_tab4, container, false);

       btn0 =  view.findViewById(R.id.btn0);
       btn1 =  view.findViewById(R.id.btn1);
       btn2 =  view.findViewById(R.id.btn2);
       btn3 =  view.findViewById(R.id.btn3);
       btn4 =  view.findViewById(R.id.btn4);
       btn5 =  view.findViewById(R.id.btn5);
       btn6 =  view.findViewById(R.id.btn6);
       btn7 =  view.findViewById(R.id.btn7);
       btn8 =  view.findViewById(R.id.btn8);
       btn9 =  view.findViewById(R.id.btn9);


       btnClear = view.findViewById(R.id.btnClear);
       btnPlus = view.findViewById(R.id.btnPlus);
       btnMinus = view.findViewById(R.id.btnMinus);
       btnMultiply = view.findViewById(R.id.btnMultiply);
       btnDivision = view.findViewById(R.id.btnDivision);
       btnBracket = view.findViewById(R.id.btnBracket);
       btnEqual = view.findViewById(R.id.btnEqual);
       btnBack = view.findViewById(R.id.btnBack);
       btnDot = view.findViewById(R.id.btnDot);
       btnPercent = view.findViewById(R.id.btnPercent);

       CalcIn = view.findViewById(R.id.CalcIn);
       CalcOut = view.findViewById(R.id.CalcOut);

       btnBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if(CalcIn.length()>0){
                   String s=CalcIn.getText().toString();
                   s = s.substring(0,s.length()-1);
                   CalcIn.setText(s);
               }else {
                   CalcIn.setText("");
               }


             }

       });


       btnClear.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               CalcIn.setText("");
               CalcOut.setText("");
           }
       }) ;


       btn0.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               usage = CalcIn.getText().toString();
               CalcIn.setText(usage + "0");
           }
       });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usage = CalcIn.getText().toString();
                CalcIn.setText(usage + "1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usage = CalcIn.getText().toString();
                CalcIn.setText(usage + "2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usage = CalcIn.getText().toString();
                CalcIn.setText(usage + "3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usage = CalcIn.getText().toString();
                CalcIn.setText(usage + "4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usage = CalcIn.getText().toString();
                CalcIn.setText(usage + "5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usage = CalcIn.getText().toString();
                CalcIn.setText(usage + "6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usage = CalcIn.getText().toString();
                CalcIn.setText(usage + "7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usage = CalcIn.getText().toString();
                CalcIn.setText(usage + "8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usage = CalcIn.getText().toString();
                CalcIn.setText(usage + "9");
            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usage = CalcIn.getText().toString();
                CalcIn.setText(usage + "+");
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usage = CalcIn.getText().toString();
                CalcIn.setText(usage + "-");
            }
        });
        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usage = CalcIn.getText().toString();
                CalcIn.setText(usage + "×");
            }
        });
        btnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usage = CalcIn.getText().toString();
                CalcIn.setText(usage + "÷");
            }
        });
        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usage = CalcIn.getText().toString();
                CalcIn.setText(usage + ".");
            }
        });
        btnPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usage = CalcIn.getText().toString();
                CalcIn.setText(usage + "%");
            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usage = CalcIn.getText().toString();
                usage = usage.replaceAll("×","*");
                usage = usage.replaceAll("%","/100");
                usage = usage.replaceAll("÷","/");
                Context rhino = Context.enter();

                rhino.setOptimizationLevel(-1);

                String finalResult = "";

                try {
                    Scriptable scriptable = rhino.initStandardObjects();
                    finalResult = rhino.evaluateString(scriptable,usage,"javascript",1,null).toString();
                }catch (Exception e){
                    finalResult = "0";
                }
                CalcOut.setText(finalResult);
            }
        });
        btnBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if (checkBracket){
                     usage = CalcIn.getText().toString();
                     CalcIn.setText(usage + ")");
                     checkBracket = false;
                 }else {
                     usage = CalcIn.getText().toString();
                     CalcIn.setText(usage + "(");
                      checkBracket = true;

                 }

            }
        });
     //Speech recognizer->CalcIn

        





        return view;


    }


































    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}