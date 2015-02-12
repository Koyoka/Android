package com.yrkj.mwrmobile.base;



import com.yrkj.util.log.DLog;

import android.app.Application;

public class BaseApplication extends Application {
	
	private static BaseApplication mApplication = null;
	
	public static BaseApplication getInstance(){
		return mApplication;
	}
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		DLog.LOGTAG = "MWR";
		mApplication = this;
		if(!MWRDBMng.init(this,null/*SysMng.IsDebug*/)){
			DLog.LOG("create db error");
		}else{
//			sys_DBCreated = true;
		}
//		SysMng.Launch(this);
	}
	
	
}
