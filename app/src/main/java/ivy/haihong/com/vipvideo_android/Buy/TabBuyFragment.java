package ivy.haihong.com.vipvideo_android.Buy;
import ivy.haihong.com.vipvideo_android.R;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lichanghong on 2019/3/7.
 */

public class TabBuyFragment extends Fragment {
    View _view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
         if (null != _view) {
                ViewGroup parent = (ViewGroup) _view.getParent();
                if (null != parent) {
                    parent.removeView(_view);
                }
          } else {
                _view = inflater.inflate(R.layout.fragment_buy, null);
                initView();
            }
            return _view;

    }
    private void initView() {

    }




}
