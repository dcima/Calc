package it.diegocimarosa.calc;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
    TextView resultField;
    Operation currentOperation = null;
    String runnningNumber = "";
    String leftValue = "";
    String rightValue = "";
    int result = 0;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.home);
        View home = inflater.inflate(R.layout.fragment_home, container, false);
        Button btn_0 = home.findViewById(R.id.button_0);
        Button btn_1 = home.findViewById(R.id.button_1);
        Button btn_2 = home.findViewById(R.id.button_2);
        Button btn_3 = home.findViewById(R.id.button_3);
        Button btn_4 = home.findViewById(R.id.button_4);
        Button btn_5 = home.findViewById(R.id.button_5);
        Button btn_6 = home.findViewById(R.id.button_6);
        Button btn_7 = home.findViewById(R.id.button_7);
        Button btn_8 = home.findViewById(R.id.button_8);
        Button btn_9 = home.findViewById(R.id.button_9);

        Button btn_clear = home.findViewById(R.id.button_clear);

        ImageButton btn_plus = home.findViewById(R.id.button_plus);
        ImageButton btn_minus = home.findViewById(R.id.button_minus);
        ImageButton btn_divide = home.findViewById(R.id.button_divide);
        ImageButton btn_multiply = home.findViewById(R.id.button_multiply);
        ImageButton btn_equal = home.findViewById(R.id.button_equal);

        resultField = home.findViewById(R.id.resulText);
        resultField.setText("0");


        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(v, 0);
            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(v, 1);
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(v, 2);
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(v, 3);
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(v, 4);
            }
        });

        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(v, 5);
            }
        });

        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(v, 6);
            }
        });

        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(v, 7);
            }
        });

        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(v, 8);
            }
        });

        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(v, 9);
            }
        });

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOperation(Operation.ADD);
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOperation(Operation.SUBTRACT);
            }
        });

        btn_divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOperation(Operation.DIVIDE);
            }
        });

        btn_multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOperation(Operation.MULTIPLY);
            }
        });

        btn_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOperation(Operation.EQUAL);
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftValue = "";
                rightValue = "";
                result = 0;
                runnningNumber = "";
                currentOperation = null;
                resultField.setText("0");
            }
        });


        return home;
    }

    void processOperation(Operation operation) {
        if (currentOperation != null) {
            if (runnningNumber != "") {
                rightValue = runnningNumber;
                runnningNumber = "";

                switch (currentOperation) {
                    case ADD:
                        result = Integer.parseInt(leftValue) + Integer.parseInt(rightValue);
                        break;
                    case SUBTRACT:
                        result = Integer.parseInt(leftValue) - Integer.parseInt(rightValue);
                        break;
                    case DIVIDE:
                        result = Integer.parseInt(leftValue) / Integer.parseInt(rightValue);
                        break;
                    case MULTIPLY:
                        result = Integer.parseInt(leftValue) * Integer.parseInt(rightValue);
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

    void numberPressed(View v, int number) {
        v.startAnimation(buttonClick);

        runnningNumber += String.valueOf(number);
        resultField.setText(runnningNumber);
    }

    public enum Operation {
        ADD, SUBTRACT, DIVIDE, MULTIPLY, EQUAL
    }
}
