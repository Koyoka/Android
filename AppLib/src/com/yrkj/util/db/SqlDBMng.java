package com.yrkj.util.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.yrkj.util.file.FileHelper;
import com.yrkj.util.log.DLog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;




public abstract class SqlDBMng {
	
	
	
	final static String TAG = "DBMng";

	String DB_EXPNAme = "";
	String DB_NAME = "";
	int DB_VERSION = 0;
	private String TEMP_DBNAME = "temp";
	
	private Context mContext = null;
	private  SQLiteDatabase mDB = null;
	public  DBHelper mDBHelper = null;
	private boolean mIsOpen = false;
	
	public SqlDBMng(Context context,String dbExpName){
		mContext = context;
		DB_NAME = getDBName();
		DB_EXPNAme = dbExpName;
		DB_VERSION = getDBVersion();
		mDBHelper = new DBHelper(context,DB_NAME,DB_VERSION);
	}
	public SqlDBMng(Context context){
		mContext = context;
		DB_NAME = getDBName();
		DB_VERSION = getDBVersion();
		mDBHelper = new DBHelper(context,DB_NAME,DB_VERSION);
	}
	
	protected abstract String getDBName();
	protected abstract int getDBVersion();
	
 	public boolean initDB(boolean reCreate){
		String DATABASEDIR = "/data/data/" + mContext.getPackageName() + "/databases";
		String destPath = 
				DATABASEDIR+"/"
				+DB_NAME+DB_EXPNAme;
		
		File f = new File(destPath);
		DLog.LOG("exists");
		if(!f.exists() || reCreate){
			InputStream inputStream;
			OutputStream outputStream;
			try {
				File dir = new File(DATABASEDIR);
            	if(!dir.exists()){
            		dir.mkdir(); 
            	}
				
            	File dbFile = new File(destPath);
            	if(!dbFile.exists()){
            		inputStream = mContext.getAssets().open(TEMP_DBNAME);
            		
    				outputStream = new FileOutputStream(destPath);
    				
    				if(!FileHelper.CopyFile(inputStream, new FileOutputStream(destPath))){
    					DLog.LOG("CopyFile err");
    					return false;
    				}
    				DLog.LOG(DB_NAME+DB_EXPNAme + " CopyFile Temp DB Done");
    				log("copy --------");
    				excCreatTableSql();
            	}else{
            		log("update --------");
	            	if(mDBHelper.NeedUpdate){
	            		excCreatTableSql();
	            	}
            	}
            	
//				MessageBox.getInstance(context).Show("Creat DB Done!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}else{
			DLog.LOG("DBMng.getInstance");
			CheckDBVersion(mContext);
		}
		return true;
	}
 	
 	private void log(String s){
 		DLog.LOG("LibDBMng", s);
 	}
 	
 	private void excCreatTableSql(){
 		String s = FileHelper.readAssessFile(mContext, DB_NAME+".sql");
 		log("exc 1-------- ");
 		log(s);
 		this.open();
 		this.execSQL(s);
		this.close();
		log("exc 2--------");
 	}
	
	private void CheckDBVersion(Context context){
		this.open();
		this.close();
		if(mDBHelper.NeedUpdate){
			initDB(true);
		}
	}
	
	public static String GetDataString(Cursor cursor,String columnName){
    	int columnIndex = cursor.getColumnIndex(columnName);
    	return cursor.getString(columnIndex);
	}
	public static int getDataInt(Cursor cursor,String columnName){
		int columnIndex = cursor.getColumnIndex(columnName);
		return cursor.getInt(columnIndex);
	}
	public static boolean getDataBoolean(Cursor cursor,String columnName){
		int columnIndex = cursor.getColumnIndex(columnName);
//		cursor.getColumnNames()
		 
		int defineIntData = cursor.getInt(columnIndex);
		if(defineIntData == 1){
			return true;
		}
		return false;
	}
	
	private void a(){
	}
	
	public void open(){
		mDB = mDBHelper.getWritableDatabase();
		mIsOpen = true;
	}
	
	public void close(){
		if(mIsOpen){
			mDB.close();
		}
		
	}

	public  long insert(String tabName,ContentValues values){
		if(!mIsOpen){
			return 0;
		}
		return mDB.insert(tabName, null, values);
	}
	
	public  boolean delete(String tabName,String condition){
		return  mDB.delete(tabName, condition, null) > 0;
	}
	
	public  boolean update(String tabName,ContentValues values,String condition){
		return mDB.update(tabName, values, condition, null) > 0;
	}
	
	public void execSQL(String sql,Object[] bindArgs){
		mDB.execSQL(sql, bindArgs);
	}
	public void execSQL(String sql){
		mDB.execSQL(sql);
	}
	
	public  Cursor query(String tabName,String[] columns,DBCondition condition){
		if(!mIsOpen){
			return null;
		}
		Cursor cursor = null;
		if(condition != null){
			cursor = mDB.query(true, tabName, columns,
					condition.Selection, 
					condition.SelectionArgs, 
					condition.GroupBy, 
					condition.Having, 
					condition.OrderBy, 
					condition.Limit);
		}else{
			cursor = mDB.query(true, tabName, columns,null,null,null,null,null,null);
		}
//		DebugTrace.Print("===begin check cursor=====");
		if(cursor != null){
//			DebugTrace.Print("===cursor != null=====");
			boolean hasData = cursor.moveToFirst();
//			DebugTrace.Print(hasData+" = hasData");
		}
		return cursor;
	}
	
	public Cursor rawQuery(String s){
		if(!mIsOpen){
			return null;
		}
		Cursor cursor = null;
		{
			cursor = mDB.rawQuery(s, null);
		}
//		else{
//			cursor = mDB.query(true, tabName, columns,null,null,null,null,null,null);
//		}
		if(cursor != null){
			boolean hasData = cursor.moveToFirst();
		}
		return cursor;
	}
	
}
