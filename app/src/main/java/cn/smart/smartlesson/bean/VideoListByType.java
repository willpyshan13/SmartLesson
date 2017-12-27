package cn.smart.smartlesson.bean;

import java.util.List;

/**
 * @Desc: TODO
 * @Author: pengysh
 * @CreatedTime: 2017/9/20 17:44
 */
public class VideoListByType {

    /**
     * message : 成功
     * data : [{"videoId":11,"videoTitle":"英语小故事","videoWheelPicUrl":"/image/upload/wheel/9/6/1504678434126.png","videoPreviewPicUrl":"/image/upload/preview/9/6/1504678439175.png","videoBrief":"英语小故事","videoDesc":"英语小故事","videoDirector":"英语小故事","videoScriptwriter":"","videoActor":"英语小故事","status":0,"createTime":"Sep 6, 2017 2:14:01 PM","hot":false,"wheel":false},{"videoId":10,"videoTitle":"启蒙小故事","videoWheelPicUrl":"/image/upload/wheel/9/6/1504678373648.jpg","videoPreviewPicUrl":"/image/upload/preview/9/6/1504678379676.jpg","videoBrief":"启蒙小故事","videoDesc":"启蒙小故事","videoDirector":"启蒙小故事","videoScriptwriter":"","videoActor":"启蒙小故事","status":0,"createTime":"Sep 6, 2017 2:13:02 PM","hot":false,"wheel":false}]
     */

    private String message;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * videoId : 11
         * videoTitle : 英语小故事
         * videoWheelPicUrl : /image/upload/wheel/9/6/1504678434126.png
         * videoPreviewPicUrl : /image/upload/preview/9/6/1504678439175.png
         * videoBrief : 英语小故事
         * videoDesc : 英语小故事
         * videoDirector : 英语小故事
         * videoScriptwriter :
         * videoActor : 英语小故事
         * status : 0
         * createTime : Sep 6, 2017 2:14:01 PM
         * hot : false
         * wheel : false
         */

        private int videoId;
        private String videoTitle;
        private String videoWheelPicUrl;
        private String videoPreviewPicUrl;
        private String videoBrief;
        private String videoDesc;
        private String videoDirector;
        private String videoScriptwriter;
        private String videoActor;
        private int status;
        private String createTime;
        private boolean hot;
        private boolean wheel;

        public int getVideoId() {
            return videoId;
        }

        public void setVideoId(int videoId) {
            this.videoId = videoId;
        }

        public String getVideoTitle() {
            return videoTitle;
        }

        public void setVideoTitle(String videoTitle) {
            this.videoTitle = videoTitle;
        }

        public String getVideoWheelPicUrl() {
            return videoWheelPicUrl;
        }

        public void setVideoWheelPicUrl(String videoWheelPicUrl) {
            this.videoWheelPicUrl = videoWheelPicUrl;
        }

        public String getVideoPreviewPicUrl() {
            return videoPreviewPicUrl;
        }

        public void setVideoPreviewPicUrl(String videoPreviewPicUrl) {
            this.videoPreviewPicUrl = videoPreviewPicUrl;
        }

        public String getVideoBrief() {
            return videoBrief;
        }

        public void setVideoBrief(String videoBrief) {
            this.videoBrief = videoBrief;
        }

        public String getVideoDesc() {
            return videoDesc;
        }

        public void setVideoDesc(String videoDesc) {
            this.videoDesc = videoDesc;
        }

        public String getVideoDirector() {
            return videoDirector;
        }

        public void setVideoDirector(String videoDirector) {
            this.videoDirector = videoDirector;
        }

        public String getVideoScriptwriter() {
            return videoScriptwriter;
        }

        public void setVideoScriptwriter(String videoScriptwriter) {
            this.videoScriptwriter = videoScriptwriter;
        }

        public String getVideoActor() {
            return videoActor;
        }

        public void setVideoActor(String videoActor) {
            this.videoActor = videoActor;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public boolean isHot() {
            return hot;
        }

        public void setHot(boolean hot) {
            this.hot = hot;
        }

        public boolean isWheel() {
            return wheel;
        }

        public void setWheel(boolean wheel) {
            this.wheel = wheel;
        }
    }
}
