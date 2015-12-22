package com.scnu.swimmingtrainingsystem.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.scnu.swimmingtrainingsystem.R;
import com.scnu.swimmingtrainingsystem.util.AppController;

/**
 * 
 * @author lixinkun
 *
 * 2015年12月10日
 */
public class IndexFragment extends BaseFragment implements View.OnClickListener{

    private LinearLayout gotoManageAthlete;
    private LinearLayout gotoQueryScore;
    private LinearLayout gotoOtherFunction;

    private View v;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(v == null){
            v = inflater.inflate(R.layout.fragment_index,null);
            gotoManageAthlete = (LinearLayout) v.findViewById(R.id.manage_athlete);
            gotoQueryScore = (LinearLayout) v.findViewById(R.id.query_score);
            gotoOtherFunction = (LinearLayout) v.findViewById(R.id.other_function);
            gotoManageAthlete.setOnClickListener(this);
            gotoQueryScore.setOnClickListener(this);
            gotoOtherFunction.setOnClickListener(this);


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

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.manage_athlete:
                AppController.gotoAthlete(getActivity());
                break;
            case R.id.query_score:
                AppController.gotoQueryScore(getActivity());
                break;
            case R.id.other_function:
                AppController.gotoOtherFunction(getActivity());
                break;
            default:
                break;
        }
    }
}
