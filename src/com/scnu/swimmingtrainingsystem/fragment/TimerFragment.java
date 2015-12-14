package com.scnu.swimmingtrainingsystem.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scnu.swimmingtrainingsystem.R;

/**
 * 
 * @author lixinkun
 *
 * 2015年12月10日
 */
public class TimerFragment extends Fragment{

    private View v;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(v == null){
            v = inflater.inflate(R.layout.fragment_timer,null);
        }
        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
