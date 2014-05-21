package com.yrkj.util.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetHelper {

	public static final int CMNET = 3;
	public static final int CMWAP = 2;
	public static final int WIFI = 1;

	/**
	 * @author sky Email ��ȡ��ǰ������״̬ -1��û������
	 *         1��WIFI����2��wap����3��net����
	 * @param context
	 * @return
	 */
	public static int getAPNType(Context context) {
		int netType = -1;
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo == null) {
			return netType;
		}
		int nType = networkInfo.getType();
		if (nType == ConnectivityManager.TYPE_MOBILE) {
			// Log.e("networkInfo.getExtraInfo()",
			// "networkInfo.getExtraInfo() is "+networkInfo.getExtraInfo());
			if (networkInfo.getExtraInfo().toLowerCase().equals("cmnet")) {

				netType = CMNET;
			} else {
				netType = CMWAP;
			}
		} else if (nType == ConnectivityManager.TYPE_WIFI) {
			netType = WIFI;
		}
		return netType;
	}

}

/*
 @Override 
public void onReceive(Context context, Intent intent) { 
Log.e(TAG, "����״̬�ı�"); 
boolean success = false; 
//����������ӷ��� 
ConnectivityManager connManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE); 
// State state = connManager.getActiveNetworkInfo().getState(); 
State state = connManager.getNetworkInfo( 
ConnectivityManager.TYPE_WIFI).getState(); // ��ȡ��������״̬ 
if (State.CONNECTED == state) { // �ж��Ƿ�����ʹ��WIFI���� 
success = true; 
} 
state = connManager.getNetworkInfo( 
ConnectivityManager.TYPE_MOBILE).getState(); // ��ȡ��������״̬ 
if (State.CONNECTED != state) { // �ж��Ƿ�����ʹ��GPRS���� 
success = true; 
} 
if (!success) { 
Toast.makeText(LocationMapActivity.this, "���������������ж�", Toast.LENGTH_LONG).show(); 
} 
}
 * 
 * */
