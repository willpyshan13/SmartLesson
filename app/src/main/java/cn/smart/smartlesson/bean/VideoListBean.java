package cn.smart.smartlesson.bean;

import java.util.List;

/**
 * @Desc: TODO
 * @Author: pengysh
 * @CreatedTime: 2017/9/6 10:22
 */
public class VideoListBean {

    /**
     * message : 成功
     * data : {"content":[{"videoId":1,"videoTitle":"小猪佩奇","videoWheelPicUrl":"/image/upload/wheel/9/5/1504593087433.png","videoPreviewPicUrl":"/image/upload/wheel/9/5/1504593087433.png","videoBrief":"小猪佩奇","videoDesc":"小猪佩奇","videoDirector":"小猪佩奇","videoScriptwriter":"小猪佩奇","videoActor":"小猪佩奇","status":0,"createTime":"Sep 5, 2017 2:32:10 PM","hot":false,"wheel":false}],"pageable":{"page":0,"size":9},"total":1}
     */

    private String message;
    private DataBean data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * content : [{"videoId":1,"videoTitle":"小猪佩奇","videoWheelPicUrl":"/image/upload/wheel/9/5/1504593087433.png","videoPreviewPicUrl":"/image/upload/wheel/9/5/1504593087433.png","videoBrief":"小猪佩奇","videoDesc":"小猪佩奇","videoDirector":"小猪佩奇","videoScriptwriter":"小猪佩奇","videoActor":"小猪佩奇","status":0,"createTime":"Sep 5, 2017 2:32:10 PM","hot":false,"wheel":false}]
         * pageable : {"page":0,"size":9}
         * total : 1
         */

        private PageableBean pageable;
        private int total;
        public List<VideoBeans> content;

        public PageableBean getPageable() {
            return pageable;
        }

        public void setPageable(PageableBean pageable) {
            this.pageable = pageable;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

    }
}
