package com.xinlan.mainframe;

import java.io.File;

import lib.imageloader.cache.disc.impl.UnlimitedDiskCache;
import lib.imageloader.cache.disc.naming.HashCodeFileNameGenerator;
import lib.imageloader.cache.memory.impl.LruMemoryCache;
import lib.imageloader.core.DisplayImageOptions;
import lib.imageloader.core.ImageLoader;
import lib.imageloader.core.ImageLoaderConfiguration;
import lib.imageloader.core.assist.QueueProcessingType;
import lib.imageloader.core.decode.BaseImageDecoder;
import lib.imageloader.core.download.BaseImageDownloader;
import lib.imageloader.utils.StorageUtils;
import android.app.Application;

public class MainApplication extends Application {
	
	@Override
	public void onCreate() {
		super.onCreate();
		initImageLoader();
	}

	/**
	 * 
	 * 初始化图片载入控件
	 */
	private void initImageLoader() {
		File cacheDir = StorageUtils.getCacheDirectory(this);
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
        .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
        .diskCacheExtraOptions(480, 800, null)
        .threadPoolSize(3) // default
        .threadPriority(Thread.NORM_PRIORITY - 2) // default
        .tasksProcessingOrder(QueueProcessingType.FIFO) // default
        .denyCacheImageMultipleSizesInMemory()
        .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
        .memoryCacheSize(2 * 1024 * 1024)
        .memoryCacheSizePercentage(13) // default
        .diskCache(new UnlimitedDiskCache(cacheDir)) // default
        .diskCacheSize(50 * 1024 * 1024)
        .diskCacheFileCount(100)
        .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
        .imageDownloader(new BaseImageDownloader(this)) // default
        .imageDecoder(new BaseImageDecoder(false)) // default
        .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
        .writeDebugLogs()
        .build();
		ImageLoader.getInstance().init(config);
	}
}// end class
