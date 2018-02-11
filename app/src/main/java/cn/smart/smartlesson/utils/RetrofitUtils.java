package cn.smart.smartlesson.utils;

import okhttp3.MediaType;

/**
 * @Desc: TODO
 * @Author: pengysh
 * @CreatedTime: 2017/4/14 15:13
 */
public class RetrofitUtils {
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");//mdiatype 这个需要和服务端保持一致
    public static String DEFAULT_URL = "http://119.23.33.65:8888/smart";
    public static String DEFAULT_RESOURCE_URL = "http://119.23.33.65/data";
    public static String DEFAULT_VIDEO_URL = "http://videos.smart-dog.cn";
    public static String REGISTER_URL = DEFAULT_URL+"/mobile/user/register";
    private String UPDATE_USERINFO_URL = DEFAULT_URL+"/mobile/user/update";
    private String LOGIN_URL = DEFAULT_URL+"/mobile/user/login";
    public static String VERIFI_URL = DEFAULT_URL+"/mobile/user/verify";
    public static String MESSAGE_LIST = DEFAULT_URL+"/mobile/system/message/list";
    public static String VIDEO_LIST = DEFAULT_URL+"/mobile/video/list";
    public static String VIDEO_LIST_BY_LABEL = DEFAULT_URL+"/mobile/video/listbytype?labelid=";
    public static String VIDEO_TYPE_LIST = DEFAULT_URL+"/mobile/video/typelist";
    public static String LEARN_LIST = DEFAULT_URL+"/mobile/learn/list";
    public static String VIDEO_DETAIL_LIST = DEFAULT_URL+"/mobile/video/detail?videoId=";
    public static String LEARN_DETAIL_LIST = DEFAULT_URL+"/mobile/learn/detail?learnId=";

    public static String LEARN_LEVEL_LIST = DEFAULT_URL+"/mobile/learn/levelList";
    public static String LEARN_LESSON_LIST = DEFAULT_URL+"/mobile/learn/lessonList";
}
