package com.blue.videosdk.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.blue.videosdk.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * @author: Blue
 * @date: 2017/10/10.
 * @function: 初始化，并用来加载网络图片。
 */

public class ImageLoaderManager {
    private static final int THREAD_COUNT = 4; // 最多可以有4个线程
    private static final int PROPRITY = 2; // 图片加载的优先级
    private static final int DISK_CACHE_SIZE = 50 * 1024;// 缓存大小
    private static final int CONNECTION_TIME_OUT = 5 * 1000; // 连接超时时间
    private static final int READ_TIME_OUT = 30 * 1000; // 读取超时时间

    private static ImageLoader mImageLoader = null;
    private static ImageLoaderManager mInstance = null;

    private static ImageLoaderManager getmInstance(Context context) {
        if (mInstance == null) {
            synchronized (ImageLoaderManager.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoaderManager(context);
                }
            }
        }
        return mInstance;
    }

    private ImageLoaderManager(Context context) {
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
                .threadPoolSize(THREAD_COUNT)
                .threadPriority(Thread.NORM_PRIORITY - PROPRITY)
                .denyCacheImageMultipleSizesInMemory() // 防止缓存多套尺寸图片
                .memoryCache(new WeakMemoryCache()) //使用弱引用内存缓存
                .diskCacheSize(DISK_CACHE_SIZE) // 分配硬盘缓存大小
                .diskCacheFileNameGenerator(new Md5FileNameGenerator()) //使用MD5命名文件
                .tasksProcessingOrder(QueueProcessingType.LIFO) // 图片下载顺序
                .defaultDisplayImageOptions(getDefaultOptions()) // 默认的图片加载Options
                .imageDownloader(new BaseImageDownloader(context, CONNECTION_TIME_OUT, READ_TIME_OUT))
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(configuration);
        mImageLoader = ImageLoader.getInstance();
    }

    private DisplayImageOptions getDefaultOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.xadsdk_img_error) // 地址为空加载显示错误图片
                .showImageOnFail(R.drawable.xadsdk_img_error) // 下载失败的时候
                .cacheInMemory(true) // 设置图片可以缓存在内存
                .cacheOnDisk(true) // 设置图片可以缓存在硬盘
                .bitmapConfig(Bitmap.Config.RGB_565) // 使用的解码类型
                .decodingOptions(new BitmapFactory.Options()) // 图片解码配置
                .build();
        return options;
    }

    /**
     * 加载图片API
     * @param imageView
     * @param url
     * @param options
     * @param loadingListener
     */

    public void displayImage(ImageView imageView, String url, DisplayImageOptions options, ImageLoadingListener loadingListener) {
        if (mImageLoader != null) {
            mImageLoader.displayImage(url,imageView, options, loadingListener);
        }
    }
    public void displayImage(ImageView imageView, String url) {
        displayImage(imageView, url, null, null);
    }
    public void displayImage(ImageView imageView, String url, ImageLoadingListener loadingListener) {
        displayImage(imageView, url, null, loadingListener);
    }
}
