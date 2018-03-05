package cn.smart.smartlesson.bean;

import java.io.Serializable;

/**
 * @desc: TODO
 * @author: pengysh
 * @createdTime: 2018-3-5 10:22
 */
public class GameListsBean implements Serializable {
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
}
