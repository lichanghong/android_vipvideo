package ivy.haihong.com.vipvideo_android;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by lichanghong on 2019/3/7.
 */

public abstract class TabBaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, null);
        TextView textView = (TextView) view.findViewById(R.id.txt_content);
        textView.setText(initContent());
        return view;
    }

    public abstract String initContent();

}
