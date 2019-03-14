package ivy.haihong.com.vipvideo_android.Home;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import ivy.haihong.com.vipvideo_android.API.ServerManager;
import ivy.haihong.com.vipvideo_android.Constants.StuConstants;
import ivy.haihong.com.vipvideo_android.Fragment.FragmentFactory;
import ivy.haihong.com.vipvideo_android.Fragment.TopBar;
import ivy.haihong.com.vipvideo_android.R;

public class MainActivity extends Activity {

    private FragmentManager fragmentManager;
    private RadioGroup radioGroup;
    private TopBar topbar1;
    FragmentFactory fragmentFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

         setContentView(R.layout.activity_main);
        fragmentManager = getFragmentManager();
        radioGroup = (RadioGroup) findViewById(R.id.rg_tab);
        final RadioButton radioButton = (RadioButton) radioGroup.getChildAt(0);
        topbar1 = (TopBar) findViewById(R.id.topbar1);
        fragmentFactory = new FragmentFactory();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Fragment fragment = fragmentFactory.getInstanceByIndexOfChild(radioGroup.indexOfChild(findViewById(checkedId)));
                transaction.replace(R.id.content, fragment);
                transaction.commit();
                //切换tab时更改topbar样式
                topbarState(radioGroup.indexOfChild(findViewById(checkedId)));
            }
        });
        radioButton.setChecked(true);

        //topbar init
        initEvent();

        ServerManager.getInstance().searchForServerList();
    }

    private void topbarState(int checkid) {
        checkid = checkid <= 0 ? 1 : checkid + 1;
        switch (checkid) {
            case 1: {
                topbar1.getLeftButton().setVisibility(View.INVISIBLE);
                topbar1.getRightButton().setVisibility(View.INVISIBLE);
                topbar1.setTitle(StuConstants.Tab_Name1);
            }
            break;
            case 2: {
                topbar1.getLeftButton().setVisibility(View.INVISIBLE);
                topbar1.getRightButton().setVisibility(View.INVISIBLE);
                topbar1.setTitle(StuConstants.Tab_Name2);
            }
            break;
            case 3: {
                topbar1.getLeftButton().setVisibility(View.INVISIBLE);
                topbar1.getRightButton().setVisibility(View.INVISIBLE);
                topbar1.setTitle(StuConstants.Tab_Name3);
            }
            break;
            case 4: {
                topbar1.getLeftButton().setVisibility(View.INVISIBLE);
                topbar1.getRightButton().setVisibility(View.INVISIBLE);
                topbar1.setTitle(StuConstants.Tab_Name4);
            }
            break;
        }
    }


    private void initEvent() {
        topbar1.setTitle(StuConstants.App_NAME);
        topbar1.setTitleTextSize(20);
        topbar1.setTitleTextColor(Color.WHITE);

        topbar1.setRightHeight(20);
        topbar1.setRightWidth(20);
        topbar1.setRightBackground(R.drawable.tab_mine_l);

        topbar1.getLeftButton().setEnabled(false);
        topbar1.setLeftBackground(R.mipmap.ic_launcher);

    }
}
