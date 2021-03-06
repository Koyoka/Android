package com.yrkj.elderlycareassess.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.yrkj.elderlycareassess.base.ECAQuesDBMng;
import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.elderlycareassess.bean.AssessTaskDetailData;
import com.yrkj.elderlycareassess.bean.AssessTaskHeaderData;
import com.yrkj.elderlycareassess.bean.QCategoryData;
import com.yrkj.elderlycareassess.bean.SysLogData;
import com.yrkj.elderlycareassess.bean.SysSyncData;
import com.yrkj.util.date.DateHelper;
import com.yrkj.util.db.DBCondition;
import com.yrkj.util.db.DBMng;
import com.yrkj.util.log.DLog;

public class SysDBCtrl {

	public static ArrayList<SysLogData> getAllLog(Context c,int size){
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		
		DBCondition cdt = new DBCondition();
		cdt.OrderBy = SysLogData.Col_Id+" desc";
		cdt.Limit = "0,"+size;
		
		dbMng.open();
		Cursor cursor = dbMng.query(SysLogData.TblName, 
				SysLogData.getColumnColl(), cdt);
		dbMng.close();
		
		ArrayList<SysLogData> itemList = new ArrayList<SysLogData>();
		if(cursor.moveToFirst()){
			do {
				SysLogData item = SysLogData.convertDataToModule(cursor);
				itemList.add(item);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return itemList;
	}
	
	public static long addSysLoginLog(Context c,String userId){
		
		SysLogData data = new SysLogData();
		data.LogDesc = userId;//+" ["+userName+"]";
		data.LogType = SysLogData.LOGTYPE_SYS_LOGIN;
		data.LogDate = DateHelper.getTodayAndTime();
		DLog.LOG(SysMng.TAG_DB,data.LogDate);
		
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		dbMng.open();
		long result = 0;
		result = dbMng.insert(SysLogData.TblName, 
				SysLogData.getContentValues(data));
		dbMng.close();
		return result;
	}
	
	public static long addSubmitAssessLog(Context c,String assessNum){
		SysLogData data = new SysLogData();
		data.LogDesc = assessNum;//userId;//+" ["+userName+"]";
		data.LogType = SysLogData.LOGTYPE_ASSESS;
		data.LogDate = DateHelper.getTodayAndTime();
		DLog.LOG(SysMng.TAG_DB,data.LogDate);
		
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		dbMng.open();
		long result = 0;
		result = dbMng.insert(SysLogData.TblName, 
				SysLogData.getContentValues(data));
		dbMng.close();
		return result;
	}
	
	public static long addSyncAssessLog(Context c,String assessNum){
		SysLogData data = new SysLogData();
		data.LogDesc = assessNum;//userId;//+" ["+userName+"]";
		data.LogType = SysLogData.LOGTYPE_SYNC;
		data.LogDate = DateHelper.getTodayAndTime();
		DLog.LOG(SysMng.TAG_DB,data.LogDate);
		
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		dbMng.open();
		long result = 0;
		result = dbMng.insert(SysLogData.TblName, 
				SysLogData.getContentValues(data));
		dbMng.close();
		return result;
	}
	
	
	
	public static String getLastLoginDate(Context c){
		
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		
		DBCondition cdt = new DBCondition();
		cdt.Selection = SysLogData.Col_LogType+" = '" + SysLogData.LOGTYPE_SYS_LOGIN+"'";//QCategoryData.Col_IsActive + " = " + QCategoryData.ISACTIVE_YES;
		cdt.OrderBy = SysLogData.Col_Id+" desc";
		cdt.Limit = "1,1";
		
		dbMng.open();
		Cursor cursor = dbMng.query(SysLogData.TblName, 
				SysLogData.getColumnColl(), cdt);
		dbMng.close();
		
		
		SysLogData item = null;
		
		if(cursor.moveToFirst()){
			item = SysLogData.convertDataToModule(cursor);
			
		}
		cursor.close();
		if(item != null)
			return item.LogDate;
		else
			return "";
	}

	public static long addWaitingSyncTask(Context c,SysSyncData data){
	
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		dbMng.open();
		long result = 0;
		result = dbMng.insert(SysSyncData.TblName, 
				SysSyncData.getContentValues(data));
		dbMng.close();
		return result;
	}
	
	public static boolean doingSyncTaskState(Context c,int id){
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		String condition = SysSyncData.Col_TaskHeaderId+"="+id;
		boolean result = false;
		
		ContentValues values = new ContentValues();
		values.put(SysSyncData.Col_State,SysSyncData.SYNC_STATE_DOING);
		dbMng.open();
		result = dbMng.update(SysSyncData.TblName, 
				values
				,condition);
		dbMng.close();
		return result;
	}
	public static boolean finishSyncTaskState(Context c,int id){
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		String condition = SysSyncData.Col_TaskHeaderId+"="+id;
		boolean result = false;
		
//		ContentValues values = new ContentValues();
//		values.put(SysSyncData.Col_State,SysSyncData.SYNC_STATE_FINISH);
//		values.put(SysSyncData.Col_EndTime,DateHelper.getTodayAndTime());
		
		dbMng.open();
		boolean r = dbMng.delete(SysSyncData.TblName, condition);
		dbMng.close();
		return result;
	}
	public static boolean waitingSyncTaskState(Context c,int id){
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		String condition = SysSyncData.Col_TaskHeaderId+"="+id;
		boolean result = false;
		
		ContentValues values = new ContentValues();
		values.put(SysSyncData.Col_State,SysSyncData.SYNC_STATE_WAIT);
		values.put(SysSyncData.Col_EndTime,DateHelper.getTodayAndTime());
		
		dbMng.open();
		result = dbMng.update(SysSyncData.TblName, 
				values
				,condition);
		dbMng.close();
		return result;
	}
	
	public static SysSyncData getSyncDataByTaskHeaderId(Context c,String taskHeaderId){
		
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		
		DBCondition cdt = new DBCondition();
		cdt.Selection =  SysSyncData.Col_TaskHeaderId + "=" + taskHeaderId;
		
		dbMng.open();
		Cursor cursor = dbMng.query(SysSyncData.TblName, 
				SysSyncData.getColumnColl(), cdt);
		dbMng.close();
		
		SysSyncData data = null;
		if(cursor.moveToFirst()){
			data = SysSyncData.convertDataToModule(cursor);
		}
		cursor.close();
		return data;
	}
	
	public static boolean checkTaskHasBeenAddToSyncList(Context c,String taskHeaderId){
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		
		DBCondition cdt = new DBCondition();
		cdt.Selection = SysSyncData.Col_State + "='"+SysSyncData.SYNC_STATE_WAIT+"' and "
				+ SysSyncData.Col_TaskHeaderId + "=" + taskHeaderId;
		
		dbMng.open();
		Cursor cursor = dbMng.query(SysSyncData.TblName, 
				SysSyncData.getColumnColl(), cdt);
		dbMng.close();
		
		boolean has = false;
		if(cursor.moveToFirst()){
			has = true;
		}
		cursor.close();
		
		return has;
	}
	
	public static ArrayList<SysSyncData> getWaitingSyncTask(Context c,int count){
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		
		DBCondition cdt = new DBCondition();
		cdt.Selection = SysSyncData.Col_State + "='"+SysSyncData.SYNC_STATE_WAIT+"'";
//		cdt.OrderBy = SysLogData.Col_Id+" desc";
		if(count!=0)
			cdt.Limit = "0,"+count;
		
		dbMng.open();
		Cursor cursor = dbMng.query(SysSyncData.TblName, 
				SysSyncData.getColumnColl(), cdt);
		dbMng.close();
		
		ArrayList<SysSyncData> itemList = new ArrayList<SysSyncData>();
		if(cursor.moveToFirst()){
			do {
				SysSyncData item = SysSyncData.convertDataToModule(cursor);
				itemList.add(item);
			} while (cursor.moveToNext());
		}
		cursor.close();
		
		return itemList;
	}

}
