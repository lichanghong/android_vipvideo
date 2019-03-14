package ivy.haihong.com.vipvideo_android.API;

import android.app.Application;
import android.content.Context;

/**
 * Created by lichanghong on 2019/3/8.
 */

public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    // 获取Application
    public static Context getMyApplication() {
        return instance;
    }
}