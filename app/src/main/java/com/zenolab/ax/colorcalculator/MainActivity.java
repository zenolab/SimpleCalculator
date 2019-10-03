package com.zenolab.ax.colorcalculator;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.zenolab.ax.colorcalculator.ui.hub.CalculatorFragment;
import com.zenolab.ax.colorcalculator.ui.screens.AdditionFragment;
import com.zenolab.ax.colorcalculator.ui.screens.DivisionFragment;
import com.zenolab.ax.colorcalculator.ui.screens.MultiplicationFragment;
import com.zenolab.ax.colorcalculator.ui.screens.SubtractFragment;
import com.zenolab.ax.colorcalculator.listeners.BackButtonHandlerInterface;
import com.zenolab.ax.colorcalculator.listeners.OnBackClickListener;
import com.zenolab.ax.colorcalculator.listeners.OnFragmentInteractionListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener,
        BackButtonHandlerInterface {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private CalculatorFragment calculatorFragment;
    private String hostFragmentTag;
    private FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    private ArrayList<WeakReference<OnBackClickListener>> backClickListenersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            calculatorFragment = new CalculatorFragment();
            hostFragmentTag = calculatorFragment.toString();
            replaceFragment(calculatorFragment);
        }
    }

    @Override
    public void onRouter(ResultColor resultColor, double result) {
        switch(resultColor){
            case RED:
                replaceFragment(AdditionFragment.newInstance(result));
                break;
            case ORANGE:
                replaceFragment(SubtractFragment.newInstance(result));
                break;
            case GREEN:
                replaceFragment(MultiplicationFragment.newInstance(result));
                break;
            case BLUE:
                replaceFragment(DivisionFragment.newInstance(result));
                break;
            default:
                throw new RuntimeException("Not found any exists fragment");
        }
    }

    public void replaceFragment(Fragment fragment) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
        Log.d(LOG_TAG, "onDestroy()");
    }


    @Override
    public void addBackClickListener(OnBackClickListener onBackClickListener) {
        backClickListenersList.add(new WeakReference<>(onBackClickListener));
    }

    @Override
    public void removeBackClickListener(OnBackClickListener onBackClickListener) {
        for (Iterator<WeakReference<OnBackClickListener>> iterator = backClickListenersList.iterator();
             iterator.hasNext();){
            WeakReference<OnBackClickListener> weakRef = iterator.next();
            if (weakRef.get() == onBackClickListener){
                iterator.remove();
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(!fragmentsBackKeyIntercept()){
            super.onBackPressed();
            getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                public void onBackStackChanged() {
                    Log.i(LOG_TAG, "back stack changed ");
                    int backCount = getSupportFragmentManager().getBackStackEntryCount();
                    if (backCount == 0){
                        fragmentTransaction.remove(calculatorFragment);
                        finish();
                    }
                }
            });
        }
    }

    private boolean fragmentsBackKeyIntercept() {
        boolean isIntercept = false;
        for (WeakReference<OnBackClickListener> weakRef : backClickListenersList) {
            OnBackClickListener onBackClickListener = weakRef.get();
            if (onBackClickListener != null) {
                boolean isFragmIntercept = onBackClickListener.onBackClick();
                if (!isIntercept) isIntercept = isFragmIntercept;
            }
        }
        return isIntercept;
    }


}
