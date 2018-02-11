package cn.smart.smartlesson.bean;

import java.util.List;

/**
 * @Desc: TODO
 * @Author: pengysh
 * @CreatedTime: 2017/9/18 10:19
 */
public class LearnDetailfBeans {

    /**
     * message : 成功
     * status : 200
     * data : [{"Id":1,"path":"http://videos.smart-dog.cn/pobomo_greeting.mp4","type":"2","word":"1","category":"read_word","isLesson":"true","ttsText":"1","learnId":4,"imagePath":"1","updateTime":"2017-09-22 09:29:09"},{"Id":2,"path":"http://videos.smart-dog.cn/good.mp4","type":"2","word":"good","category":"read_word","isLesson":"false","ttsText":"good,好,good","learnId":4,"imagePath":"1","updateTime":"2017-09-22 09:30:01"},{"Id":3,"path":"http://videos.smart-dog.cn/morning.mp4","type":"2","word":"morning","category":"read_word","isLesson":"false","ttsText":"morning,早上, morning","learnId":4,"imagePath":"1","updateTime":"2017-09-22 09:30:49"},{"Id":4,"path":"http://videos.smart-dog.cn/afterdnoon.mp4","type":"2","word":"afternoon","category":"read_word","isLesson":"false","ttsText":"afternoon,下午,afternoon","learnId":4,"imagePath":"afternoon","updateTime":"2017-09-22 09:31:47"},{"Id":5,"path":"http://videos.smart-dog.cn/evening.mp4","type":"2","word":"evening","category":"read_word","isLesson":"false","ttsText":"evening,傍晚,evening","learnId":4,"imagePath":"1","updateTime":"2017-09-22 09:33:00"},{"Id":6,"path":"http://videos.smart-dog.cn/night.mp4","type":"2","word":"night","category":"read_word","isLesson":"false","ttsText":"night,晚上,night","learnId":4,"imagePath":"1","updateTime":"2017-09-22 09:33:33"}]
     */

    private String message;
    private int status;
    private Data data;


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

    public Data getData()
    {
        return data;
    }

    public void setData(Data data)
    {
        this.data = data;
    }

    public static class Data{
        private List<DataBean> learnInfos;
        private List<Game> gameLists;

        public List<DataBean> getLearnInfos()
        {
            return learnInfos;
        }

        public void setLearnInfos(List<DataBean> learnInfos)
        {
            this.learnInfos = learnInfos;
        }

        public List<Game> getGameLists()
        {
            return gameLists;
        }

        public void setGameLists(List<Game> gameLists)
        {
            this.gameLists = gameLists;
        }
    }
    public static class Game{

    }

    public static class DataBean {
        /**
         * Id : 1
         * path : http://videos.smart-dog.cn/pobomo_greeting.mp4
         * type : 2
         * word : 1
         * category : read_word
         * isLesson : true
         * ttsText : 1
         * learnId : 4
         * imagePath : 1
         * updateTime : 2017-09-22 09:29:09
         */

        private int Id;
        private String path;
        private String type;
        private String word;
        private String category;
        private String isLesson;
        private String ttsText;
        private int learnId;
        private String imagePath;
        private String updateTime;

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

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getIsLesson() {
            return isLesson;
        }

        public void setIsLesson(String isLesson) {
            this.isLesson = isLesson;
        }

        public String getTtsText() {
            return ttsText;
        }

        public void setTtsText(String ttsText) {
            this.ttsText = ttsText;
        }

        public int getLearnId() {
            return learnId;
        }

        public void setLearnId(int learnId) {
            this.learnId = learnId;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
