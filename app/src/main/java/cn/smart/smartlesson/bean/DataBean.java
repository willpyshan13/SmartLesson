package cn.smart.smartlesson.bean;

import java.io.Serializable;

/**
 * @desc: TODO
 * @author: pengysh
 * @createdTime: 2018-3-5 10:22
 */
public class DataBean implements Serializable {
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
