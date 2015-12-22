package com.scnu.swimmingtrainingsystem.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by lixinkun on 15/12/22.
 */
public class BaseFragment extends Fragment {
    @Override
    public void onHiddenChanged(boolean hidden) {
        if(hidden){

        }else{
            onReShow();
        }
    }

    public void onReShow(){

    }
}
