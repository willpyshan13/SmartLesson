package cn.smart.smartlesson.bean;

import java.util.List;

/**
 * @Desc: TODO
 * @Author: pengysh
 * @CreatedTime: 2017/9/6 11:04
 */
public class VideoDetailBean {

    /**
     * message : 获取成功
     * status : 200
     * data : {"videoId":1,"videoTitle":"小猪佩奇","videoPreviewPicUrl":"/image/upload/wheel/9/5/1504593087433.png","videoDirector":"小猪佩奇","videoActor":"小猪佩奇","videoDesc":"小猪佩奇","isCollect":false,"isPraised":false,"list":[{"videoThumbmailUrl":"施迈特","videoUrlIndex":1,"videoTitle":"Best Friend","videoPlayUrl":"/video/Cartoon/Best Friend.mp4"},{"videoThumbmailUrl":"施迈特","videoUrlIndex":2,"videoTitle":"Hide and Seek","videoPlayUrl":"/video/Cartoon/Hide and Seek.mp4"},{"videoThumbmailUrl":"施迈特","videoUrlIndex":3,"videoTitle":"Polly Parrot","videoPlayUrl":"/video/Cartoon/Polly Parrot.mp4"},{"videoThumbmailUrl":"施迈特","videoUrlIndex":4,"videoTitle":"Muddy Puddles","videoPlayUrl":"/video/Cartoon/Muddy Puddles.mp4"},{"videoThumbmailUrl":"施迈特","videoUrlIndex":5,"videoTitle":"Mr Dinosaur is Los","videoPlayUrl":"/video/Cartoon/Mr Dinosaur is Lost.mp4"}]}
     */

    private String message;
    private int status;
    private DataBean data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * videoId : 1
         * videoTitle : 小猪佩奇
         * videoPreviewPicUrl : /image/upload/wheel/9/5/1504593087433.png
         * videoDirector : 小猪佩奇
         * videoActor : 小猪佩奇
         * videoDesc : 小猪佩奇
         * isCollect : false
         * isPraised : false
         * list : [{"videoThumbmailUrl":"施迈特","videoUrlIndex":1,"videoTitle":"Best Friend","videoPlayUrl":"/video/Cartoon/Best Friend.mp4"},{"videoThumbmailUrl":"施迈特","videoUrlIndex":2,"videoTitle":"Hide and Seek","videoPlayUrl":"/video/Cartoon/Hide and Seek.mp4"},{"videoThumbmailUrl":"施迈特","videoUrlIndex":3,"videoTitle":"Polly Parrot","videoPlayUrl":"/video/Cartoon/Polly Parrot.mp4"},{"videoThumbmailUrl":"施迈特","videoUrlIndex":4,"videoTitle":"Muddy Puddles","videoPlayUrl":"/video/Cartoon/Muddy Puddles.mp4"},{"videoThumbmailUrl":"施迈特","videoUrlIndex":5,"videoTitle":"Mr Dinosaur is Los","videoPlayUrl":"/video/Cartoon/Mr Dinosaur is Lost.mp4"}]
         */

        private int videoId;
        private String videoTitle;
        private String videoPreviewPicUrl;
        private String videoDirector;
        private String videoActor;
        private String videoDesc;
        private boolean isCollect;
        private boolean isPraised;
        private List<ListBean> list;

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

        public String getVideoPreviewPicUrl() {
            return videoPreviewPicUrl;
        }

        public void setVideoPreviewPicUrl(String videoPreviewPicUrl) {
            this.videoPreviewPicUrl = videoPreviewPicUrl;
        }

        public String getVideoDirector() {
            return videoDirector;
        }

        public void setVideoDirector(String videoDirector) {
            this.videoDirector = videoDirector;
        }

        public String getVideoActor() {
            return videoActor;
        }

        public void setVideoActor(String videoActor) {
            this.videoActor = videoActor;
        }

        public String getVideoDesc() {
            return videoDesc;
        }

        public void setVideoDesc(String videoDesc) {
            this.videoDesc = videoDesc;
        }

        public boolean isIsCollect() {
            return isCollect;
        }

        public void setIsCollect(boolean isCollect) {
            this.isCollect = isCollect;
        }

        public boolean isIsPraised() {
            return isPraised;
        }

        public void setIsPraised(boolean isPraised) {
            this.isPraised = isPraised;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * videoThumbmailUrl : 施迈特
             * videoUrlIndex : 1
             * videoTitle : Best Friend
             * videoPlayUrl : /video/Cartoon/Best Friend.mp4
             */

            private String videoThumbmailUrl;
            private int videoUrlIndex;
            private String videoTitle;
            private String videoPlayUrl;

            public String getVideoThumbmailUrl() {
                return videoThumbmailUrl;
            }

            public void setVideoThumbmailUrl(String videoThumbmailUrl) {
                this.videoThumbmailUrl = videoThumbmailUrl;
            }

            public int getVideoUrlIndex() {
                return videoUrlIndex;
            }

            public void setVideoUrlIndex(int videoUrlIndex) {
                this.videoUrlIndex = videoUrlIndex;
            }

            public String getVideoTitle() {
                return videoTitle;
            }

            public void setVideoTitle(String videoTitle) {
                this.videoTitle = videoTitle;
            }

            public String getVideoPlayUrl() {
                return videoPlayUrl;
            }

            public void setVideoPlayUrl(String videoPlayUrl) {
                this.videoPlayUrl = videoPlayUrl;
            }
        }
    }
}
