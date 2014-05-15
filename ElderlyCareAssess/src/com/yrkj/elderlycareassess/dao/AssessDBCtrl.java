package com.yrkj.elderlycareassess.dao;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;

import com.yrkj.elderlycareassess.base.ECAQuesDBMng;
import com.yrkj.elderlycareassess.bean.AssessTaskHeaderData;
import com.yrkj.elderlycareassess.bean.CustomerAssessTask;
import com.yrkj.elderlycareassess.bean.CustomerData;
import com.yrkj.elderlycareassess.bean.QCategoryData;
import com.yrkj.util.db.DBCondition;
import com.yrkj.util.db.DBMng;

public class AssessDBCtrl {

	public static ArrayList<CustomerAssessTask> getDoingAssessTaskList(Context c){
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		
		dbMng.open();
		StringBuilder sb = new StringBuilder();
		sb.append("Select a.*, ");
		sb.append("c.[customername],c.[sex],c.[mobliephone],c.[address] ");
		sb.append("From MAIN.[AssessTaskHeader] as a ");
		sb.append("left join MAIN.[t_customer] as c where c.[id] = a.[CustId] ");
		
		Cursor cursor = 
				dbMng.rawQuery(sb.toString());
		dbMng.close();
		
		ArrayList<CustomerAssessTask> itemList = new ArrayList<CustomerAssessTask>();
		if(cursor.moveToFirst()){
			do {

				CustomerAssessTask itemHeader = new CustomerAssessTask();
				
				itemHeader.mTask = AssessTaskHeaderData.convertDataToModule(cursor);
				CustomerData item = new CustomerData();
				item.customername = DBMng.GetDataString(cursor, CustomerData.Col_customername);
				item.sex = DBMng.GetDataString(cursor, CustomerData.Col_sex);
				item.mobliephone = DBMng.GetDataString(cursor, CustomerData.Col_mobliephone);
				item.address = DBMng.GetDataString(cursor, CustomerData.Col_address);
				itemHeader.mCust = item;
				itemList.add(itemHeader);
			} while (cursor.moveToNext());
		}
		cursor.close();
		
		return itemList;
	}
	
	public void getDoneAssessTaskList(){
		
	}
	
	
	public static CustomerData getCustomerByCustId(Context c,String custId){
		
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		
		DBCondition cdt = new DBCondition();
		cdt.Selection = CustomerData.Col_id+" = '" + custId+"'";//QCategoryData.Col_IsActive + " = " + QCategoryData.ISACTIVE_YES;
		cdt.OrderBy = "";//QCategoryData.Col_SortIndex + " ASC";
		dbMng.open();
		Cursor cursor = dbMng.query(CustomerData.TblName, 
				CustomerData.getColumnColl(), cdt);
		dbMng.close();
		
		
		CustomerData item = null;
		if(cursor.moveToFirst()){
			item = CustomerData.convertDataToModule(cursor);
		}
		
		return item;
		
	}
	
//	public class CustomerAssessTask{
//		public CustomerAssessTask getInstance(){
//			return new CustomerAssessTask();
//		}
//		
//	}
}
