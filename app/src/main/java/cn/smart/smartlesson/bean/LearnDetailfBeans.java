package cn.smart.smartlesson.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @Desc: TODO
 * @Author: pengysh
 * @CreatedTime: 2017/9/18 10:19
 */
public class LearnDetailfBeans implements Serializable{

    /**
     * message : 成功
     * status : 200
     * data : [{"Id":1,"path":"http://videos.smart-dog.cn/pobomo_greeting.mp4","type":"2","word":"1","category":"read_word","isLesson":"true",
     * "ttsText":"1","learnId":4,"imagePath":"1","updateTime":"2017-09-22 09:29:09"},{"Id":2,"path":"http://videos.smart-dog.cn/good.mp4",
     * "type":"2","word":"good","category":"read_word","isLesson":"false","ttsText":"good,好,good","learnId":4,"imagePath":"1",
     * "updateTime":"2017-09-22 09:30:01"},{"Id":3,"path":"http://videos.smart-dog.cn/morning.mp4","type":"2","word":"morning",
     * "category":"read_word","isLesson":"false","ttsText":"morning,早上, morning","learnId":4,"imagePath":"1","updateTime":"2017-09-22 09:30:49"},
     * {"Id":4,"path":"http://videos.smart-dog.cn/afterdnoon.mp4","type":"2","word":"afternoon","category":"read_word","isLesson":"false",
     * "ttsText":"afternoon,下午,afternoon","learnId":4,"imagePath":"afternoon","updateTime":"2017-09-22 09:31:47"},{"Id":5,"path":"http://videos
     * .smart-dog.cn/evening.mp4","type":"2","word":"evening","category":"read_word","isLesson":"false","ttsText":"evening,傍晚,evening","learnId":4,
     * "imagePath":"1","updateTime":"2017-09-22 09:33:00"},{"Id":6,"path":"http://videos.smart-dog.cn/night.mp4","type":"2","word":"night",
     * "category":"read_word","isLesson":"false","ttsText":"night,晚上,night","learnId":4,"imagePath":"1","updateTime":"2017-09-22 09:33:33"}]
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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

<<<<<<< .mine
    public static class Data implements Serializable{
||||||| .r187
    public static class Data{
=======
    public static class Data {
>>>>>>> .r194
        private List<DataBean> learnInfos;
        private List<GameListsBean> gameLists;

        public List<DataBean> getLearnInfos() {
            return learnInfos;
        }

        public void setLearnInfos(List<DataBean> learnInfos) {
            this.learnInfos = learnInfos;
        }

<<<<<<< .mine
        public List<GameListsBean> getGameLists() {
||||||| .r187
        public List<Game> getGameLists()
        {
=======
        public List<Game> getGameLists() {
>>>>>>> .r194
            return gameLists;
        }

<<<<<<< .mine
        public void setGameLists(List<GameListsBean> gameLists) {
||||||| .r187
        public void setGameLists(List<Game> gameLists)
        {
=======
        public void setGameLists(List<Game> gameLists) {
>>>>>>> .r194
            this.gameLists = gameLists;
        }
    }

<<<<<<< .mine
    public static class GameListsBean implements Serializable{
        /**
         * infoId : 1
         * type : 1
         * gameInfo : A
         * gameName : A
         * imagePath : AAA
         * mp3Path : AAA
         * createTime : Feb 7, 2018 9:28:39 AM
         * updateTime : 2018-02-07 09:28:39
         * status : 0
         */

        private int infoId;
        private int type;
        private String gameInfo;
        private String gameName;
        private String imagePath;
        private String mp3Path;
        private String createTime;
        private String updateTime;
        private int status;

        public int getInfoId() {
            return infoId;
        }

        public void setInfoId(int infoId) {
            this.infoId = infoId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getGameInfo() {
            return gameInfo;
        }

        public void setGameInfo(String gameInfo) {
            this.gameInfo = gameInfo;
        }

        public String getGameName() {
            return gameName;
        }

        public void setGameName(String gameName) {
            this.gameName = gameName;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public String getMp3Path() {
            return mp3Path;
        }

        public void setMp3Path(String mp3Path) {
            this.mp3Path = mp3Path;
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

        public int getStatusX() {
            return status;
        }

        public void setStatusX(int statusX) {
            this.status = statusX;
        }
||||||| .r187
=======
    public static class Game {

>>>>>>> .r194
    }

    public static class DataBean implements Serializable{
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
