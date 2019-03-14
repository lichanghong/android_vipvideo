package ivy.haihong.com.vipvideo_android.Home;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ivy.haihong.com.vipvideo_android.Home.Model.VideoModel;
import ivy.haihong.com.vipvideo_android.R;

/**
 * Created by lichanghong on 2019/3/8.
 */

public class HomeLostAdapter extends BaseAdapter {
    List<VideoModel> mHotList;
    Context mContext;

    public HomeLostAdapter(ArrayList<VideoModel> mHotList, Context mContext) {
        super();
        this.mHotList = mHotList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mHotList.size();
    }

    @Override
    public Object getItem(int position) {
        return mHotList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder1 = null;

        if (convertView == null)
        {
            convertView = View.inflate(mContext, R.layout.home_list_layout, null);
            holder1 = new ViewHolder();
            holder1.leftImageView = (ImageView) convertView.findViewById(R.id.home_left_img);
            convertView.setTag(holder1);
        }
        else
        {
            holder1 = (ViewHolder) convertView.getTag();
        }
        VideoModel keyValuePair = mHotList.get(position);
        Glide.with(mContext).load(keyValuePair.getImageUrl())
                .into(holder1.leftImageView);
        return convertView;
    }


    class ViewHolder
    {
        ImageView leftImageView;
    }
}
