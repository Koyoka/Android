package com.yrkj.mwrmobile.base;

import android.app.Application;
import android.content.SharedPreferences;

//import com.yrkj.elderlycareassess.bean.AssessUserData;
//import com.yrkj.elderlycareassess.gusturelock.LockPatternView;
//import com.yrkj.elderlycareassess.gusturelock.MainActivity;


public class SysMng {

	public static final boolean IsDebug = true;
//	public static final String TAG_FRAGMENT = "ECA_Fragment";
//	public static final String TAG_UCTRL = "ECA_UCtrl";
//	public static final String TAG_DB = "ECA_DB";
//	public static final String TAG_ASSESS = "ECA_Assess";
//	public static final String TAG_SERVICE = "ECA_Service";
//	public static final String TAG_NET = "ECA_Net";
	
	
	private final static String PREF_NAME = "MWR_pref";
//	private final static String PREF_KEY_USERID = "userName";
//	public  final static String PREF_KEY_PASSWORD = "password";
	public static final String LOCK = "lock";
	public static final String LOCK_KEY = "lock_key";
	
	private final static String PREF_KEY_Driver = "driver";
	private final static String PREF_KEY_DriverName = "drivername";
	private final static String PREF_KEY_Inspector = "inspector";
	private final static String PREF_Key_InspectorName = "inspectorname";
	private final static String PREF_KEY_WSCode = "wscode";
	private final static String PREF_KEY_ASSESSKEY = "assesskey";
	private final static String PREF_KEY_SECRETKEY = "secretkey";
	private final static String PREF_KEY_CrateMask = "HX###";
	private final static String PREF_KEY_CarCode = "carCode";
	public final static String PREF_KEY_ServiceHost = "ServiceHost";
	
	private static SharedPreferences mPrefs;
	private static SharedPreferences getPrefs(){
		if(mPrefs == null){
			mPrefs = BaseApplication.getInstance().getSharedPreferences(PREF_NAME, Application.MODE_PRIVATE);
		}
		return mPrefs;
	}
	
	public static void saveWSInfo(String wsCode,String accessKey,String secretKey,String crateMask){
		SharedPreferences.Editor editor = getPrefs().edit();
		
		editor.putString(PREF_KEY_CrateMask, crateMask);
		editor.putString(PREF_KEY_WSCode, wsCode);
		editor.putString(PREF_KEY_ASSESSKEY, accessKey);
		editor.putString(PREF_KEY_SECRETKEY, secretKey);
		
		editor.commit();
	}
	public static void saveAysnWSInfo(String crateMask){
		SharedPreferences.Editor editor = getPrefs().edit();
		editor.putString(PREF_KEY_CrateMask, crateMask);
		editor.commit();
	}
	public static void savePrefValue(String key,String val){
		
		SharedPreferences.Editor editor = getPrefs().edit();
		editor.putString(key, val);
		editor.commit();
	} 
	
	public static void saveTxnInfo(String carCode,String driver,String driverName,String inspector,String inspectorName){
		SharedPreferences.Editor editor = getPrefs().edit();
		
		editor.putString(PREF_KEY_CarCode, carCode);
		
		editor.putString(PREF_KEY_Driver, driver);
		editor.putString(PREF_KEY_DriverName, driverName);
		
		editor.putString(PREF_KEY_Inspector, inspector);
		editor.putString(PREF_Key_InspectorName, inspectorName);
		editor.commit();
	}
	
	public static SysInfo getSysInfo(){
		SysInfo sysInfo = new SysInfo();
		sysInfo.SerciveHost = getPrefs().getString(PREF_KEY_ServiceHost, "192.168.1.105");
		return sysInfo;
		
	}
	
	public static WSInfo getWSInfo(){
		
		WSInfo txnInfo = new WSInfo();
		txnInfo.CrateMask = getPrefs().getString(PREF_KEY_CrateMask, "");
		txnInfo.WSCode = getPrefs().getString(PREF_KEY_WSCode, "");
		txnInfo.AccessKey = getPrefs().getString(PREF_KEY_ASSESSKEY, "");
		txnInfo.SecretKey = getPrefs().getString(PREF_KEY_SECRETKEY, "");
		
		return txnInfo;
	}
	public static TxnInfo getTxnInfo(){
		
		TxnInfo txninfo = new TxnInfo();
		txninfo.CarCode =  getPrefs().getString(PREF_KEY_CarCode, "");
		txninfo.DriverCode = getPrefs().getString(PREF_KEY_Driver, "");
		txninfo.DriverName = getPrefs().getString(PREF_KEY_DriverName, "");
		txninfo.InspectorCode = getPrefs().getString(PREF_KEY_Inspector, "");
		txninfo.InspectroName = getPrefs().getString(PREF_Key_InspectorName, "");
		return txninfo;
	}
	
	
//	public static AssessUserData getUserInfo(){
//		AssessUserData d = new AssessUserData();
//		d.UserId = getPrefs().getString(PREF_KEY_USERID, "").trim();
//		d.LocPassword = getPrefs().getString(PREF_KEY_PASSWORD, "");
//		if(d.UserId.isEmpty()){
//			return null;
//		}
//		
//		return d;
//	}
	
//	public static String getLockPattenString(){
//		SharedPreferences preferences = 
//				BaseApplication.getInstance().
//				getSharedPreferences(LOCK, Application.MODE_PRIVATE);
//		return preferences.getString(LOCK_KEY, null);
//	}
//	public static void clearLockPatten(){
//		BaseApplication.getInstance().
//			getSharedPreferences(LOCK, Application.MODE_PRIVATE).edit().clear().commit();
//	}
//	public static void setLockPattenString(String patternString){
//		 SharedPreferences preferences = BaseApplication.getInstance().
//				 getSharedPreferences(
//                 LOCK, Application.MODE_PRIVATE);
//         preferences
//                 .edit()
//                 .putString(LOCK_KEY,
//                		 patternString)
//                 .commit();
//	}
//	
}
