package com.zenolab.ax.colorcalculator.ui.hub;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zenolab.ax.colorcalculator.R;
import com.zenolab.ax.colorcalculator.ResultColor;
import com.zenolab.ax.colorcalculator.listeners.BackButtonHandlerInterface;
import com.zenolab.ax.colorcalculator.listeners.OnBackClickListener;
import com.zenolab.ax.colorcalculator.listeners.OnFragmentInteractionListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalculatorFragment extends Fragment implements View.OnClickListener,
        OnBackClickListener {

    private static final String LOG_TAG = CalculatorFragment.class.getSimpleName();

    private View.OnClickListener logicListener;
    private BackButtonHandlerInterface backButtonHandler;

    private EditText editText;
    private float valueOne, valueTwo;
    private boolean isAddition, isSubtract, isMultiplication, isDivision;

    private OnFragmentInteractionListener colorListener;

    public CalculatorFragment() {
        // Required empty public constructor
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            colorListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                                       + " must implement OnFragmentInteractionListener");
        }

        if (context instanceof BackButtonHandlerInterface) {
            backButtonHandler = (BackButtonHandlerInterface)  context;
            backButtonHandler.addBackClickListener(this);
        } else {
            throw new RuntimeException(context.toString()
                                       + " must implement OBackButtonHandlerInterface");
        }

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editText = (EditText) view.findViewById(R.id.io);

        Button button0 = (Button) view.findViewById(R.id.button0);
        Button button1 = (Button) view.findViewById(R.id.button1);
        Button button2 = (Button) view.findViewById(R.id.button2);
        Button button3 = (Button) view.findViewById(R.id.button3);
        Button button4 = (Button) view.findViewById(R.id.button4);
        Button button5 = (Button) view.findViewById(R.id.button5);
        Button button6 = (Button) view.findViewById(R.id.button6);
        Button button7 = (Button) view.findViewById(R.id.button7);
        Button button8 = (Button) view.findViewById(R.id.button8);
        Button button9 = (Button) view.findViewById(R.id.button9);
        Button buttonPoint = (Button) view.findViewById(R.id.buttonPoint);

        Button buttonAdd = (Button) view.findViewById(R.id.buttonadd);
        Button buttonSub = (Button) view.findViewById(R.id.buttonsub);
        Button buttonMul = (Button) view.findViewById(R.id.buttonmul);
        Button buttonDivision = (Button) view.findViewById(R.id.buttondiv);

        Button buttonC = (Button) view.findViewById(R.id.buttonC);
        Button buttonEqual = (Button) view.findViewById(R.id.button_eql);

        editText.setOnClickListener(this);

        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonPoint.setOnClickListener(this);

        buttonAdd.setOnClickListener(this);
        buttonSub.setOnClickListener(this);
        buttonMul.setOnClickListener(this);
        buttonDivision.setOnClickListener(this);

        buttonC.setOnClickListener(this);

        setLogic();
        buttonAdd.setOnClickListener(logicListener);
        buttonSub.setOnClickListener(logicListener);
        buttonMul.setOnClickListener(logicListener);
        buttonDivision.setOnClickListener(logicListener);
        buttonEqual.setOnClickListener(logicListener);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //InputOutput
            case R.id.io:
                valueTwo = Float.parseFloat(editText.getText() + "");
                break;
            //Operand
            case R.id.button0:
                editText.setText(String.format("%s0", editText.getText()));
                break;
            case R.id.button1:
                editText.setText(String.format("%s1", editText.getText()));
                break;
            case R.id.button2:
                editText.setText(String.format("%s2", editText.getText()));
                break;
            case R.id.button3:
                editText.setText(String.format("%s3", editText.getText()));
                break;
            case R.id.button4:
                editText.setText(String.format("%s4", editText.getText()));
                break;
            case R.id.button5:
                editText.setText(String.format("%s5", editText.getText()));
                break;
            case R.id.button6:
                editText.setText(String.format("%s6", editText.getText()));
                break;
            case R.id.button7:
                editText.setText(String.format("%s7", editText.getText()));
                break;
            case R.id.button8:
                editText.setText(String.format("%s8", editText.getText()));
                break;
            case R.id.button9:
                editText.setText(String.format("%s9", editText.getText()));
                break;
            case R.id.buttonPoint:
                editText.setText(String.format("%s.", editText.getText()));
                break;
            case R.id.buttonC:
                editText.setText("");
                break;
            default:
                break;
        }
    }


    private void setLogic() {
        logicListener = v -> {
            switch (v.getId()) {
                case R.id.buttonadd:
                    if (editText.getText().toString().trim().equals("")) {
                        editText.setText("");
                    } else {
                        valueOne = Float.parseFloat(editText.getText() + "");
                        isAddition = true;
                        editText.setText(null);
                    }
                    break;
                case R.id.buttonsub:
                    valueOne = Float.parseFloat(editText.getText() + "");
                    isSubtract = true;
                    editText.setText(null);
                    break;
                case R.id.buttonmul:
                    valueOne = Float.parseFloat(editText.getText() + "");
                    isMultiplication = true;
                    editText.setText(null);
                    break;
                case R.id.buttondiv:
                    valueOne = Float.parseFloat(editText.getText() + "");
                    isDivision = true;
                    editText.setText(null);
                    break;
                case R.id.button_eql:
                    valueTwo = Float.parseFloat(editText.getText() + "");
                    figureOutResult();
                    break;
            }
        };
    }

    private void figureOutResult() {
        if (isAddition) {
            String data = String.format("%s", valueOne + valueTwo);
            colorListener.onRouter(ResultColor.RED,  Double.parseDouble(data));
            isAddition = false;
        }

        if (isSubtract) {
            String data = String.format("%s", valueOne - valueTwo);
            colorListener.onRouter(ResultColor.BLUE,  Double.parseDouble(data));
            isSubtract = false;
        }

        if (isMultiplication) {
            String data = String.format("%s", valueOne * valueTwo);
            colorListener.onRouter(ResultColor.GREEN,  Double.parseDouble(data));
            isMultiplication = false;
        }

        if (isDivision) {
            String data = String.format("%s", valueOne / valueTwo);
            colorListener.onRouter(ResultColor.ORANGE, Double.parseDouble(data));
            isDivision = false;
        }
        editText.setText("");
    }

    public void onDetach() {
        super.onDetach();
        colorListener = null;
        backButtonHandler.removeBackClickListener(this);
        backButtonHandler = null;
    }



    @Override
    public boolean onBackClick() {
        return false;
    }


}
