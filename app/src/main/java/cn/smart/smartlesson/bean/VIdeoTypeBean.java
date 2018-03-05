package cn.smart.smartlesson.bean;

import java.util.List;

/**
 * @Desc: TODO
 * @Author: pengysh
 * @CreatedTime: 2017/9/20 17:13
 */
public class VIdeoTypeBean {

    /**
     * message : 成功
     * data : [{"typeId":8,"typeParent":1,"typeName":"story","typeDesc":"故事"},{"typeId":9,"typeParent":1,"typeName":"music","typeDesc":"歌曲"},
     * {"typeId":10,"typeParent":1,"typeName":"cartoon","typeDesc":"动画片"},{"typeId":11,"typeParent":1,"typeName":"movie","typeDesc":"电影"}]
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
         * typeId : 8
         * typeParent : 1
         * typeName : story
         * typeDesc : 故事
         */

        private int typeId;
        private int typeParent;
        private String typeName;
        private String typeDesc;

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public int getTypeParent() {
            return typeParent;
        }

        public void setTypeParent(int typeParent) {
            this.typeParent = typeParent;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getTypeDesc() {
            return typeDesc;
        }

        public void setTypeDesc(String typeDesc) {
            this.typeDesc = typeDesc;
        }
    }
}
