package com.scnu.swimmingtrainingsystem.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.scnu.swimmingtrainingsystem.R;
import com.scnu.swimmingtrainingsystem.activity.MyApplication;
import com.scnu.swimmingtrainingsystem.adapter.ChooseAthleteAdapter;
import com.scnu.swimmingtrainingsystem.adapter.ShowChosenAthleteAdapter;
import com.scnu.swimmingtrainingsystem.db.DBManager;
import com.scnu.swimmingtrainingsystem.model.Athlete;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author lixinkun
 *
 * 2015年12月10日
 */
public class TimerFragment extends Fragment{

    private View v;
    private MyApplication app;
    private DBManager dbManager;
    private AutoCompleteTextView acTextView, actInterval;
    private EditText remarksEditText;
    /**
     * 展示全部运动员的ListView
     */
    private ListView athleteListView;
    /**
     * 展示全部运动员的adapter
     */
    private ChooseAthleteAdapter allAthleteAdapter;
    /**
     * 全部运动员
     */
    private List<Athlete> athletes;
    private List<String> athleteNames = new ArrayList<String>();
    /**
     * 显示在activity上的被选中要计时的运动员ListView
     */
    private ListView chosenListView;
    /**
     * 显示在activity上的被选中要计时的运动员数据适配器
     */
    private ShowChosenAthleteAdapter showChosenAthleteAdapter;

    /**
     * 已选中的运动员
     */
    private List<String> chosenAthletes = new ArrayList<String>();
    private Spinner poolSpinner;

    //泳姿选择
    private Spinner strokeSpinner;

    private Toast toast;
    private SparseBooleanArray map = new SparseBooleanArray();
    private int userid;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        app = (MyApplication) getActivity().getApplication();
        dbManager = DBManager.getInstance();
        String[] autoStrings = getResources().getStringArray(R.array.swim_length);
        ArrayAdapter<String> tipsAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, autoStrings);
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
