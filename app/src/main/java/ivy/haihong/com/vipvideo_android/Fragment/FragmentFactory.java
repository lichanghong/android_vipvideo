package ivy.haihong.com.vipvideo_android.Fragment;


import android.app.Fragment;
import android.view.View;
import android.widget.RadioGroup;

import ivy.haihong.com.vipvideo_android.Buy.TabBuyFragment;
import ivy.haihong.com.vipvideo_android.Collect.TabCollectFragment;
import ivy.haihong.com.vipvideo_android.Home.TabHomeFragment;
import ivy.haihong.com.vipvideo_android.Setting.TabSettingFragment;

//import group.haihong.com.stu.Groups.TabGroupFragmentTab;
//import group.haihong.com.stu.Hot.TabHotFragmentTab;
//import group.haihong.com.stu.More.TabMoreFragmentTab;

/**
 * Created by admin on 13-11-23.
 */
public class FragmentFactory {
    TabHomeFragment homeFragment;
    TabBuyFragment buyFragmentTab;
    TabSettingFragment settingFragmentTab;
    TabCollectFragment collectFragmentTab;

    public FragmentFactory() {
        homeFragment     = new TabHomeFragment();
        buyFragmentTab = new TabBuyFragment();
        settingFragmentTab   = new TabSettingFragment();
        collectFragmentTab  = new TabCollectFragment();
    }

    public Fragment getInstanceByIndexOfChild(int index) {
         Fragment fragment = null;
        index = index<=0?1:index+1;
        switch (index) {
            case 1:
                fragment = homeFragment;
                break;
            case 2:
                fragment = buyFragmentTab;
                break;
            case 3:
                fragment = settingFragmentTab;
                break;
            case 4:
                fragment = collectFragmentTab;
                break;

        }
        return fragment;
    }

    //隐藏tabbar的静态方法
    public static void hiddenRadioGroup(RadioGroup group)
    {
        group.setVisibility(View.GONE);
    }

}
