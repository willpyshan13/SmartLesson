package cn.smart.smartlesson.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cn.smart.smartlesson.impl.OkHttpCallback;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author: huanghz
 * @createdTime: 2018/2/5 9:41
 * <p>
 * 这个为网络请求工具类，实现方法
 * </p>
 */

public class HttpRequestUtils {

    private static final String TAG = "HttpRequestUtils";

    private static volatile HttpRequestUtils instance;
    private OkHttpClient okHttpClient;

    private HttpRequestUtils() {
        okHttpClient = new OkHttpClient();
    }

    public static HttpRequestUtils getInstance() {
        if (instance == null) {
            synchronized (HttpRequestUtils.class) {
                instance = new HttpRequestUtils();
            }
        }
        return instance;
    }

    /**
     * 基础请求
     */
    private Observable<String> baseRequest(final String url) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) {
                final Request mRequest = new Request.Builder().url(url).build();
                try {
                    //OkHttp请求
                    Response response = okHttpClient.newCall(mRequest).execute();
                    if (response.isSuccessful()) {
                        emitter.onNext(response.body().string());
                    } else {
                        emitter.onError(new Exception(response.message()));
                    }
                } catch (IOException e) {
                    emitter.onError(e);
                }

            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 通用请求
     */
    public void CommonRequest(String url, HashMap<String, String> params, final OkHttpCallback okHttpCallback) {
        baseRequest(getUrl(url, params)).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                if (okHttpCallback != null) {
                    okHttpCallback.onSuccess(s);
                }
            }

            @Override
            public void onError(Throwable e) {
                if (okHttpCallback != null) {
                    okHttpCallback.onError(new IOException(e));
                }
            }

            @Override
            public void onComplete() {

            }
        });

    }


    /**
     * 解析参数，并生成url
     */
    private String getUrl(String url, HashMap<String, String> params) {
        url = url + "?";
        Iterator iter = params.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            url = url + key + "=" + val + "&";
        }
        return url.substring(0, url.length() - 1);
    }


    /**
     * 个人课程购买信息
     *
     * @param personId     用户id
     * @param mobileSerial 设备序列号
     * @param callback     回调
     */
    public void getLearnLevelList(String personId, String mobileSerial, OkHttpCallback callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("personId", personId);
        map.put("mobileSerial", mobileSerial);
        CommonRequest(RetrofitUtils.LEARN_LEVEL_LIST, map, callback);
    }

}
