package ivy.haihong.com.vipvideo_android.Home.Model;

/**
 * Created by lichanghong on 2019/3/8.
 */

public class VideoModel extends Object {
    private String imageUrl;
    private String targetURL;

    public VideoModel(String imageUrl, String targetURL) {
        this.imageUrl = imageUrl;
        this.targetURL = targetURL;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTargetURL() {
        return targetURL;
    }

    public void setTargetURL(String targetURL) {
        this.targetURL = targetURL;
    }

    @Override
    public String toString() {
        return "VideoModel{" +
                "imageUrl='" + imageUrl + '\'' +
                ", targetURL='" + targetURL + '\'' +
                '}';
    }
}
