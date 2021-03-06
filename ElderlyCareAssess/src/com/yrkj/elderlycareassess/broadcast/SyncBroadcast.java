package com.yrkj.elderlycareassess.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class SyncBroadcast extends BroadcastReceiver {

	public final static String ACTION_NAME = "com.yrkj.elderlycareassess.sync";
	
	public final static String INTENT_KEY_BROADCASTTYPE = "BroadcastType";
	
	final static String BROADCAST_UPLOAD = "broadcateupload";
	final static String BROADCAST_UPLOAD_PROCESS = "broadcateuploadProcess";
	final static String BROADCAST_UNSYNCCOUNT = "broadcateunsyncCount";
	
	public final static String INTENT_KEY_TASKHEADERID = "taskheaderid";
	public final static String INTENT_KEY_UPLOAD_PROCESS = "uploadProcessValue";
	public final static String INTENT_KEY_ASSESSNUM = "assessNum";
	public final static String INTENT_KEY_UNSYNCCOUNT = "unsyncCount";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		if(intent == null){
			return;
		}
		
		String BroadcastType = intent.getStringExtra(INTENT_KEY_BROADCASTTYPE);
		if(BroadcastType == null){
			return;
		}
		
		if(BroadcastType.equals(BROADCAST_UPLOAD)
				&& mUploadSyncListener != null){
			Bundle bundle = intent.getExtras();
			int taskHeaderId = intent.getIntExtra(INTENT_KEY_TASKHEADERID,0);
			mUploadSyncListener.onListener(bundle,taskHeaderId);
			return;
		}
		
		if(BroadcastType.equals(BROADCAST_UPLOAD_PROCESS)
				&& mUploadProcessSyncListener != null){
			Bundle bundle = intent.getExtras();
			int taskHeaderId = intent.getIntExtra(INTENT_KEY_TASKHEADERID,0);
			int processVal= intent.getIntExtra(INTENT_KEY_UPLOAD_PROCESS,0);
			String assessNum = intent.getStringExtra(INTENT_KEY_ASSESSNUM);
			mUploadProcessSyncListener.onListener(bundle,taskHeaderId,assessNum,processVal);
			return;
		}
		
		if(BroadcastType.equals(BROADCAST_UNSYNCCOUNT)
				&& mUnSyncCountListener != null){
			Bundle bundle = intent.getExtras();
			int count = intent.getIntExtra(INTENT_KEY_UNSYNCCOUNT,0);
			mUnSyncCountListener.onListener(bundle, count);
		}
	}
	
	
	public static SyncBroadcast registUnSyncCountBroadcast(Context context,UnSyncCountListener l){
		SyncBroadcast bc = new SyncBroadcast();
		bc.mUnSyncCountListener = l;
		IntentFilter filter = new IntentFilter(); 
		filter.addAction(ACTION_NAME);  
		context.registerReceiver(bc, filter); 
		return bc;
	}
	public static void sendUnSyncCountBroadcast(Context context,int count){
		Intent intent = new Intent(ACTION_NAME); 
		intent.putExtra(INTENT_KEY_BROADCASTTYPE, BROADCAST_UNSYNCCOUNT);
		intent.putExtra(INTENT_KEY_UNSYNCCOUNT, count);
		context.sendBroadcast(intent); 
	}
	
	public static SyncBroadcast registUploadProcessSyncBroadcast(Context context,UploadProcessSyncListener l){
		SyncBroadcast bc = new SyncBroadcast();
		bc.mUploadProcessSyncListener = l;
		IntentFilter filter = new IntentFilter(); 
	    filter.addAction(ACTION_NAME);  
	    context.registerReceiver(bc, filter); 
	    return bc;
	}
	public static void sendUploadProcessSyncBroadcast(Context context,int taskHeaderId,String assessNum,int processVal){
		Intent intent = new Intent(ACTION_NAME); 
		intent.putExtra(INTENT_KEY_BROADCASTTYPE, BROADCAST_UPLOAD_PROCESS);
		intent.putExtra(INTENT_KEY_TASKHEADERID, taskHeaderId);
		intent.putExtra(INTENT_KEY_ASSESSNUM, assessNum);
		intent.putExtra(INTENT_KEY_UPLOAD_PROCESS, processVal);
	    context.sendBroadcast(intent); 
	}
	
	public static SyncBroadcast registUploadSyncBroadcast(Context context,UploadSyncListener l){
		SyncBroadcast bc = new SyncBroadcast();
		bc.mUploadSyncListener = l;
		IntentFilter filter = new IntentFilter(); 
	    filter.addAction(ACTION_NAME);  
	    context.registerReceiver(bc, filter); 
	    return bc;
	}
	public static void sendUploadSyncBroadcast(Context context,int taskHeaderId){
		Intent intent = new Intent(ACTION_NAME); 
		intent.putExtra(INTENT_KEY_BROADCASTTYPE, BROADCAST_UPLOAD);
		intent.putExtra(INTENT_KEY_TASKHEADERID, taskHeaderId);
	    context.sendBroadcast(intent); 
	}
	
	public interface UploadSyncListener{
		public void onListener(Bundle bundle,int taskHeaderId);
	}
	public interface UploadProcessSyncListener{
		public void onListener(Bundle bundle,int taskHeaderId,String assessNum,int processVal);
	}
	public interface UnSyncCountListener{
		public void onListener(Bundle bundle,int count);
	}
	
	public UploadSyncListener mUploadSyncListener;
	public UploadProcessSyncListener mUploadProcessSyncListener;
	public UnSyncCountListener mUnSyncCountListener;

}
