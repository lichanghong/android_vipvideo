package ivy.haihong.com.vipvideo_android.API;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by lichanghong on 2019/3/8.
 */


public class ServerManager extends Object {
    static String GitSERVER = "https://raw.githubusercontent.com/lichanghong/alive_server_point/master/index.html";
    static String  CodingSERVER = "https://coding.net/u/fengyunyifei/p/alive_server_point/git/raw/master/index.html";

    public List mServerList;
    String mCurrentServer;

    private int currentIndex,loadCount,loadServerlistCount; //加载服务器地址次数，超过三次不再加载
//    @property (nonatomic ,strong) MBProgressHUD *progressHUD;

    private static ServerManager instance = new ServerManager();
    public static ServerManager getInstance() {
        return instance;
    }

    public ServerManager() {
        this.currentIndex = 0;
        this.mServerList =  new ArrayList();
    }


    public void searchForServerList() {
        loadCount = 1;
        loadServerlistCount = 1;
        loadGitServer();
    }
    private void showErrorWithMsg(String msg)
    {
        Log.i("errormsg",msg);
//                 self.progressHUD = [MBProgressHUD showHUDAddedTo:[UIApplication sharedApplication].keyWindow animated:YES];
//        self.progressHUD.label.text = msg;
//        [self.progressHUD hideAnimated:YES afterDelay:3];
    }

    public String getCurrentServer(){
        if (currentIndex < mServerList.size())
        {
            return (String) mServerList.get(currentIndex);
        }
        return null;
    }

    public void changeServer() {
        currentIndex++;
        if (currentIndex>=mServerList.size())  {
             currentIndex--;
        }
    }

    private void loadGitServer() {
        if (loadCount >= 3) {
            showErrorWithMsg("服务器错误");
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
                    Request request = new Request.Builder()
                            .url(GitSERVER)//请求接口。如果需要传参拼接到接口后面。
                            .build();//创建Request 对象
                    Response response = null;
                    response = client.newCall(request).execute();//得到Response 对象

                    loadCount++;
                    if (response.isSuccessful()) {
                       String bodyString = response.body().string();
                        JSONObject json = new JSONObject(bodyString);
                        if (json != null && json.getString("server") != null)
                        {
                            loadCount = 0;
                            String server = json.getString("server");
                               Context application = MyApplication.getMyApplication().getApplicationContext();
                            SharedPreferences.Editor editor = application.getSharedPreferences("lock", MODE_PRIVATE).edit();
                             //步骤2-2：将获取过来的值放入文件
                            editor.putString("url", server);
                            //步骤3：提交
                            editor.commit();
                            loadServerlistWithServer(server);
                        }
                        else {
                            loadCoddingServer();
                        }

                    } else {
                        loadCoddingServer();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    loadCoddingServer();
                }
            }
        }).start();
    }

     private  void loadCoddingServer()
    {
        new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
                Request request = new Request.Builder()
                        .url(CodingSERVER)//请求接口。如果需要传参拼接到接口后面。
                        .build();//创建Request 对象
                Response response = null;
                response = client.newCall(request).execute();//得到Response 对象

                loadCount++;
                if (response.isSuccessful()) {
                    String bodyString = response.body().string();
                    JSONObject json = new JSONObject(bodyString);
                    if (json != null)
                    {
                        loadCount = 0;
                        String server = json.getString("server");
                        MyApplication application = (MyApplication) MyApplication.getMyApplication();
                        Context app = application.getApplicationContext();
                        SharedPreferences.Editor editor = app.getSharedPreferences("lock", MODE_PRIVATE).edit();
                        //步骤2-2：将获取过来的值放入文件
                        editor.putString("url", server);
                        //步骤3：提交
                        editor.commit();
                        loadServerlistWithServer(server);
                    }
                    else {
                        loadGitServer();
                    }

                } else {
                    loadGitServer();
                }
            } catch (Exception e) {
                e.printStackTrace();
                loadGitServer();
            }
        }
    }).start();
    }

    private void loadServerlistWithServer(final String server)
    {
        loadServerlistCount++;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
                    Request request = new Request.Builder()
                            .url(server)//请求接口。如果需要传参拼接到接口后面。
                            .build();//创建Request 对象
                    Response response = null;
                    response = client.newCall(request).execute();//得到Response 对象

                    loadCount++;
                    if (response.isSuccessful()) {
                        String bodyString = response.body().string();
                        JSONObject json = new JSONObject(bodyString);
                        if (json != null)
                        {
                            JSONArray array = (JSONArray) json.get("domains");
                            mServerList.clear();
                            for (int i=0;i<array.length();i++)
                            {
                                String item = (String) array.get(i);
                                mServerList.add(item);
                            }

                            Log.i("errormsg","hehe");

                            if (mServerList != null && mServerList.size() > 0) {
                                loadServerlistCount = 0;
                            }
                            else
                            {
                                if (loadServerlistCount > 4) {
                                    return ;
                                }

                                MyApplication application = (MyApplication) MyApplication.getMyApplication();
                                Context app = application.getApplicationContext();
                                SharedPreferences read = app.getSharedPreferences("lock", MODE_PRIVATE);
                                //步骤2-2：将获取过来的值放入文件
                                String value = read.getString("url",null);
                                loadServerlistWithServer(value);

                            }

                        }
                        else {
                            loadGitServer();
                        }

                    } else {
                        showErrorWithMsg("服务器列表错误");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showErrorWithMsg("服务器列表错误");
                }
            }
        }).start();

    }





}
