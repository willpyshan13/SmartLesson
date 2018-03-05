package cn.smart.smartlesson.bean;

import java.util.List;

/**
 * @author: huanghz
 * @createdTime: 2018/2/5 9:35
 */

public class LessonPickBean {
    /**
     * {
     * "message":"成功",
     * "status":200,
     * "data":[
     * {"levelId":1,"levelName":"入门阶级","description":"入门阶级","createTime":"Feb 2, 2018 3:37:20 PM"},
     * {"levelId":2,"levelName":"一阶课程","description":"一阶课程","createTime":"Feb 2, 2018 3:37:37 PM"}
     * ]}
     */

    private String message;
    private int status;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int levelId;
        private String levelName;
        private String description;
        private int recordId;
        private String createTime;

        public int getLevelId() {
            return levelId;
        }

        public void setLevelId(int levelId) {
            this.levelId = levelId;
        }

        public String getLevelName() {
            return levelName;
        }

        public void setLevelName(String levelName) {
            this.levelName = levelName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getRecordId() {
            return recordId;
        }

        public void setRecordId(int recordId) {
            this.recordId = recordId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }

}
