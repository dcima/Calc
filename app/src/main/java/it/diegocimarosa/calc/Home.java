package it.diegocimarosa.calc;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.KeyEvent.KEYCODE_0;
import static android.view.KeyEvent.KEYCODE_1;
import static android.view.KeyEvent.KEYCODE_2;
import static android.view.KeyEvent.KEYCODE_3;
import static android.view.KeyEvent.KEYCODE_4;
import static android.view.KeyEvent.KEYCODE_5;
import static android.view.KeyEvent.KEYCODE_6;
import static android.view.KeyEvent.KEYCODE_7;
import static android.view.KeyEvent.KEYCODE_8;
import static android.view.KeyEvent.KEYCODE_9;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
    TextView resultField;
    Operation currentOperation = null;
    String runnningNumber = "";
    String leftValue = "";
    String rightValue = "";
    double result = 0;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        getActivity().setTitle(R.string.home);
        ;
        View home = inflater.inflate(R.layout.fragment_home, container, false);
        final Button btn_0 = home.findViewById(R.id.button_0);
        final Button btn_1 = home.findViewById(R.id.button_1);
        final Button btn_2 = home.findViewById(R.id.button_2);
        final Button btn_3 = home.findViewById(R.id.button_3);
        final Button btn_4 = home.findViewById(R.id.button_4);
        final Button btn_5 = home.findViewById(R.id.button_5);
        final Button btn_6 = home.findViewById(R.id.button_6);
        final Button btn_7 = home.findViewById(R.id.button_7);
        final Button btn_8 = home.findViewById(R.id.button_8);
        final Button btn_9 = home.findViewById(R.id.button_9);

        final Button btn_clear = home.findViewById(R.id.button_clear);
        final Button btn_dot = home.findViewById(R.id.button_dot);

        final ImageButton btn_plus = home.findViewById(R.id.button_plus);
        final ImageButton btn_minus = home.findViewById(R.id.button_minus);
        final ImageButton btn_divide = home.findViewById(R.id.button_divide);
        final ImageButton btn_multiply = home.findViewById(R.id.button_multiply);
        final ImageButton btn_equal = home.findViewById(R.id.button_equal);

        resultField = home.findViewById(R.id.resulText);
        resultField.setText("0.0");

        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(btn_0, v, 0);
            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(btn_1, v, 1);
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(btn_2, v, 2);
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(btn_3, v, 3);
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(btn_4, v, 4);
            }
        });

        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(btn_5, v, 5);
            }
        });

        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(btn_6, v, 6);
            }
        });

        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(btn_7, v, 7);
            }
        });

        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(btn_8, v, 8);
            }
        });

        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(btn_9, v, 9);
            }
        });

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOperation(btn_plus, Operation.ADD);
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOperation(btn_minus, Operation.SUBTRACT);
            }
        });

        btn_divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOperation(btn_divide, Operation.DIVIDE);
            }
        });

        btn_multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOperation(btn_multiply, Operation.MULTIPLY);
            }
        });

        btn_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOperation(btn_equal, Operation.EQUAL);
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doAnim(btn_clear);

                leftValue = "";
                rightValue = "";
                result = 0;
                runnningNumber = "";
                currentOperation = null;
                resultField.setText("0");
            }
        });

        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(btn_dot, v, -1);
            }
        });

        return home;
    }

    void processOperation(ImageButton btn, Operation operation) {
        doAnim(btn);

        if (currentOperation != null) {
            if (runnningNumber != "") {
                rightValue = runnningNumber;
                runnningNumber = "";

                leftValue = leftValue == "" ? "0" : leftValue;

                switch (currentOperation) {
                    case ADD:
                        result = Double.parseDouble(leftValue) + Double.parseDouble(rightValue);
                        break;
                    case SUBTRACT:
                        result = Double.parseDouble(leftValue) - Double.parseDouble(rightValue);
                        break;
                    case DIVIDE:
                        if( Double.parseDouble(rightValue) == 0 ) {
                            Toast.makeText(this.getContext(), R.string.divide_by_zero, Toast.LENGTH_SHORT).show();
                            result = 0;
                        } else {
                            result = Double.parseDouble(leftValue) / Double.parseDouble(rightValue);
                        }
                        break;
                    case MULTIPLY:
                        result = Double.parseDouble(leftValue) * Double.parseDouble(rightValue);
                        break;
                }

                leftValue = String.valueOf(result);
                resultField.setText(leftValue);
            }

            currentOperation = operation;
        } else {
            leftValue = runnningNumber;
            runnningNumber = "";
            currentOperation = operation;
        }
    }

    void numberPressed(Button btn, View v, int number) {
        doAnim(btn);
        runnningNumber += number == -1 ? '.' : String.valueOf(number);
        resultField.setText(runnningNumber);
    }

    public enum Operation {
        ADD, SUBTRACT, DIVIDE, MULTIPLY, EQUAL
    }

    void doAnim( Button btn ) {
        final Animation bounce = AnimationUtils.loadAnimation(btn.getContext(), R.anim.bounce);
        BounceInterpolator interpolator = new BounceInterpolator(0.2, 20);
        bounce.setInterpolator((Interpolator) interpolator);
        btn.startAnimation(bounce);
    }

    void doAnim( ImageButton btn ) {
        final Animation bounce = AnimationUtils.loadAnimation(btn.getContext(), R.anim.bounce);
        BounceInterpolator interpolator = new BounceInterpolator(0.2, 20);
        bounce.setInterpolator((Interpolator) interpolator);
        btn.startAnimation(bounce);
    }
}
