package cn.smart.smartlesson.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @desc: TODO
 * @author: pengysh
 * @createdTime: 2017/12/29 14:05
 */
public class LearnInfoBean {

    /**
     * message : 成功
     * status : 200
     * data : {"content":[{"Id":7,"path":"11","type":"11","category":"1","info":"","name":"1111","startDay":"2017-12-29","startTime":"00:30:00",
     * "endTime":"01:30:00","imagePath":"1111","createTime":"Dec 29, 2017 2:40:02 PM","updateTime":"2017-12-29 14:40:02","status":0},{"Id":2,
     * "path":"1","type":"1","category":"1","info":"","name":"kiss","startDay":"2017-12-07","startTime":"00:45:00","endTime":"01:45:00",
     * "imagePath":"1","updateTime":"2017-12-29 14:40:45","status":0},{"Id":3,"path":"1","type":"1","category":"1","info":"","name":"1",
     * "startDay":"2017-12-29","startTime":"00:45:00","endTime":"01:30:00","imagePath":"1","updateTime":"2017-12-29 14:40:42","status":0},{"Id":4,
     * "path":"s","type":"s","category":"d","info":"","name":"sd","startDay":"2017-12-29","startTime":"00:15:00","endTime":"00:15:00",
     * "imagePath":"ds","updateTime":"2017-12-29 14:40:38","status":0},{"Id":5,"path":"23","type":"32","category":"32","info":"","name":"2",
     * "startDay":"2017-12-29","startTime":"01:00:00","endTime":"01:15:00","imagePath":"23","updateTime":"2017-12-29 14:40:34","status":0},{"Id":6,
     * "path":"qw","type":"ewq","category":"wqe","info":"","name":"wqe","startDay":"2017-11-28","startTime":"00:15:00","endTime":"00:15:00",
     * "imagePath":"wq","updateTime":"2017-12-29 14:40:31","status":0}],"pageable":{"page":0,"size":9},"total":6}
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
         * content : [{"Id":7,"path":"11","type":"11","category":"1","info":"","name":"1111","startDay":"2017-12-29","startTime":"00:30:00",
         * "endTime":"01:30:00","imagePath":"1111","createTime":"Dec 29, 2017 2:40:02 PM","updateTime":"2017-12-29 14:40:02","status":0},{"Id":2,
         * "path":"1","type":"1","category":"1","info":"","name":"kiss","startDay":"2017-12-07","startTime":"00:45:00","endTime":"01:45:00",
         * "imagePath":"1","updateTime":"2017-12-29 14:40:45","status":0},{"Id":3,"path":"1","type":"1","category":"1","info":"","name":"1",
         * "startDay":"2017-12-29","startTime":"00:45:00","endTime":"01:30:00","imagePath":"1","updateTime":"2017-12-29 14:40:42","status":0},
         * {"Id":4,"path":"s","type":"s","category":"d","info":"","name":"sd","startDay":"2017-12-29","startTime":"00:15:00","endTime":"00:15:00",
         * "imagePath":"ds","updateTime":"2017-12-29 14:40:38","status":0},{"Id":5,"path":"23","type":"32","category":"32","info":"","name":"2",
         * "startDay":"2017-12-29","startTime":"01:00:00","endTime":"01:15:00","imagePath":"23","updateTime":"2017-12-29 14:40:34","status":0},
         * {"Id":6,"path":"qw","type":"ewq","category":"wqe","info":"","name":"wqe","startDay":"2017-11-28","startTime":"00:15:00",
         * "endTime":"00:15:00","imagePath":"wq","updateTime":"2017-12-29 14:40:31","status":0}]
         * pageable : {"page":0,"size":9}
         * total : 6
         */

        private PageableBean pageable;
        private int total;
        private List<ContentBean> content;

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

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class PageableBean {
            /**
             * page : 0
             * size : 9
             */

            private int page;
            private int size;

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }
        }

        public static class ContentBean implements Serializable {
            /**
             * Id : 7
             * path : 11
             * type : 11
             * category : 1
             * info :
             * name : 1111
             * startDay : 2017-12-29
             * startTime : 00:30:00
             * endTime : 01:30:00
             * imagePath : 1111
             * createTime : Dec 29, 2017 2:40:02 PM
             * updateTime : 2017-12-29 14:40:02
             * status : 0
             */

            private int Id;
            private String path;
            private String type;
            private String category;
            private String info;
            private String name;
            private String startDay;
            private String startTime;
            private String endTime;
            private String imagePath;
            private String createTime;
            private String updateTime;
            private int status;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getStartDay() {
                return startDay;
            }

            public void setStartDay(String startDay) {
                this.startDay = startDay;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getImagePath() {
                return imagePath;
            }

            public void setImagePath(String imagePath) {
                this.imagePath = imagePath;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
