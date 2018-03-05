package cn.smart.smartlesson.impl;

import java.io.IOException;

/**
 * @author: huanghz
 * @createdTime: 2018/2/7 9:34
 */

public interface OkHttpCallback {

    //成功获取数据
    void onSuccess(String response);

    //失败
    void onError(IOException e);

}
