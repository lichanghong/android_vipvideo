package ivy.haihong.com.vipvideo_android.Home.WebView;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import ivy.haihong.com.vipvideo_android.API.ServerManager;
import ivy.haihong.com.vipvideo_android.Fragment.TopBar;
import ivy.haihong.com.vipvideo_android.R;

public class WebViewActivity extends AppCompatActivity {
    String currentLoadURL;
    TopBar mTopBar;
    WebView mWebView;
    ImageButton mBack,mForward,mPlay,mFresh,mCollect;


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Bundle bundle = getIntent().getExtras();
        currentLoadURL = bundle.getString("url");
        initUI();
        initAction();
        setupWebView();

    }

    private void initUI()
    {
        mTopBar = (TopBar) findViewById(R.id.webtopbar);
        mWebView = findViewById(R.id.webview);
        mBack = findViewById(R.id.back);
        mForward = findViewById(R.id.forward);
        mPlay = findViewById(R.id.play);
        mFresh = findViewById(R.id.refresh);
        mCollect = findViewById(R.id.collect);

    }

    private void initAction()
    {
        mTopBar.getLeftButton().setEnabled(true);
        mTopBar.setLeftBackground(R.drawable.backbutton);
        mTopBar.setTitleTextColor(Color.WHITE);
        mTopBar.setOnTopbarLeftClickListener(new TopBar.TopbarLeftClickListener() {
            @Override
            public void leftClick() {
                finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWebView.goBack();
            }
        });
        mForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWebView.goForward();
            }
        });
        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServerManager manager = ServerManager.getInstance();
                String current = manager.getCurrentServer();
                String webViewurl = mWebView.getUrl();
                if (webViewurl.contains("url="))
                {
                    mWebView.reload();
                }
                else
                {
                    String target = current+webViewurl;
//                    Uri uri = Uri.parse(target);
//                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                    startActivity(intent);
                    mWebView.loadUrl(target);
                }

                Log.i("urlget",webViewurl);

            }
        });
        mFresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWebView.reload();
            }
        });
        mCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String originUR = mWebView.getOriginalUrl();
                Log.i("urlget",originUR);
            }
        });
    }

    private void setupWebView()
    {
        WebSettings ws = mWebView.getSettings();
        ws.setBuiltInZoomControls(false);// 隐藏缩放按钮
        ws.setUseWideViewPort(true);// 可任意比例缩放
        ws.setLoadWithOverviewMode(true);// setUseWideViewPort方法设置webview推荐使用的窗口。setLoadWithOverviewMode方法是设置webview加载的页面的模式。
        ws.setSaveFormData(true);// 保存表单数据
        ws.setJavaScriptEnabled(true);
        ws.setGeolocationEnabled(true);
        ws.setBlockNetworkImage(false);
        ws.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        ws.setPluginState(WebSettings.PluginState.ON);
        ws.setDomStorageEnabled(true);
        ws.setSupportMultipleWindows(true);// 新加
        ws.setSupportZoom(true);
        ws.supportMultipleWindows();
//当webview调用requestFocus时为webview设置节点
        ws.setNeedInitialFocus(true);
//设置支持缩放
        ws.setBuiltInZoomControls(true);
//支持通过JS打开新窗口
        ws.setJavaScriptCanOpenWindowsAutomatically(true);
//支持自动加载图片
        ws.setLoadsImagesAutomatically(true);
//优先使用缓存:
        ws.setCacheMode(WebSettings.LOAD_NO_CACHE);
//提高渲染的优先级
        ws.setRenderPriority(WebSettings.RenderPriority.HIGH);
// 开启H5(APPCache)缓存功能
        ws.setAppCacheEnabled(true);
// 开启 DOM storage 功能
        ws.setDomStorageEnabled(false);
// 应用可以有数据库
        ws.setDatabaseEnabled(true);
// 可以读取文件缓存(manifest生效)
        ws.setAllowFileAccess(true);
/*允许跨域访问*/
        ws.setAllowUniversalAccessFromFileURLs(true);
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        //这行很关键
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public View getVideoLoadingProgressView() {
                FrameLayout frameLayout = new FrameLayout(WebViewActivity.this);
                frameLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
                return frameLayout;
            }

            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                super.onShowCustomView(view, callback);

            }
        });
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.canGoBack();
        mWebView.loadUrl(currentLoadURL);
    }

}
