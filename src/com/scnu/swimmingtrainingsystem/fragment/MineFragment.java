package com.scnu.swimmingtrainingsystem.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scnu.swimmingtrainingsystem.R;
import com.scnu.swimmingtrainingsystem.activity.MyApplication;
import com.scnu.swimmingtrainingsystem.util.AppController;
import com.scnu.swimmingtrainingsystem.util.Constants;

/**
 * 
 * @author lixinkun
 *
 * 2015年12月10日
 */
public class MineFragment extends BaseFragment implements View.OnClickListener{

    private View v;
    private LinearLayout gotoAboutUs;
    private LinearLayout gotoModifyPwd;
    private LinearLayout gotoLogout;
    private LinearLayout gotoQuestionHelp;

    private TextView tvAthleteNum;
    private TextView tvPlanNum;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(v == null){
            v = inflater.inflate(R.layout.fragment_more,null);

            tvAthleteNum = (TextView) v.findViewById(R.id.tv_athlete_num);
            tvPlanNum = (TextView) v.findViewById(R.id.tv_user_plans);

            gotoQuestionHelp = (LinearLayout) v.findViewById(R.id.question_help);
            gotoModifyPwd = (LinearLayout) v.findViewById(R.id.setting_modify_password);
            gotoAboutUs = (LinearLayout) v.findViewById(R.id.about_us);
            gotoLogout = (LinearLayout) v.findViewById(R.id.logout);
            gotoLogout.setOnClickListener(this);
            gotoQuestionHelp.setOnClickListener(this);
            gotoModifyPwd.setOnClickListener(this);
            gotoAboutUs.setOnClickListener(this);
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
            case R.id.setting_modify_password:
                AppController.gotoModifyPwd(getActivity());
                break;
            case R.id.question_help:
                AppController.gotoQuestionHelp(getActivity());
                break;
            case R.id.about_us:
                AppController.gotoAboutUs(getActivity());
                break;
            case R.id.logout:
                createDialog();
                break;
            default:
                break;
        }
    }

    /**
     * 创建注销对话框
     */
    private void createDialog() {
        AlertDialog.Builder build = new AlertDialog.Builder(getActivity());
        build.setTitle(getString(R.string.system_hint)).setMessage(getString(R.string.confirm_to_quit_app));
        build.setPositiveButton(Constants.OK_STRING,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyApplication app = (MyApplication) getActivity().getApplication();
                        AppController.reset(app);
                        //恢复数据
                        AppController.logout(getActivity());
                        AppController.gotoLogin(getActivity());
                    }
                });
        build.setNegativeButton(Constants.CANCLE_STRING,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }
}
