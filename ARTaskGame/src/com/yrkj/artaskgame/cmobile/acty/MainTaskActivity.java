package com.yrkj.artaskgame.cmobile.acty;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yrkj.artaskgame.R;
import com.yrkj.artaskgame.acty.qcar.ARCameraActivity;
import com.yrkj.artaskgame.acty.qcar.QCARInitActivityActy;
import com.yrkj.artaskgame.base.SysMng;
import com.yrkj.artaskgame.cmobile.control.DBCtrl;
import com.yrkj.artaskgame.cmobile.control.TblTaskDetail;
import com.yrkj.util.dialog.DialogHelper;
import com.yrkj.util.dialog.DialogHelper.ConfirmDialogListener;
import com.yrkj.util.log.ToastUtil;

public class MainTaskActivity extends Activity implements
OnClickListener{

	final String TAG = "com.yrkj.artaskgame.cmobile.acty.MainTaskActivity";
	public final static String INTENT_KEY_CLOSEAPP = "closeApp";
	
	MainTaskActivity mActy = null;
	
	private Button mBtnTaskListView = null;
	private ImageView mImgTaskListCountView = null;
	private TextView mTxtTaskTitleView = null;
	private TextView mTxtTaskDescView = null;
	private Button mBtnGoView = null;
	
	private String mSelectTaskId = "";
	
	private ImageView mImgCurrentCountView;
	private ImageView mImgTotleTenCountView;
	private ImageView mImgTotleBitsCountView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_task);
		mActy = this;
		initData();
		initActy();
		loadData();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		// DebugTrace.Print("MenuGroup---dispatchKeyEvent = " +
		// event.getKeyCode() + " action = " + event.getAction());
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			// showDialog(DIALOG_LOGOUT);
			DialogHelper.createConfirmDialog(MainTaskActivity.this, "确认退出？",new ConfirmDialogListener() {
				
				@Override
				public void onClickListener(boolean result) {
					// TODO Auto-generated method stub
					
					if(result){
//						return;
//						Intent intent = new Intent(mActy,QCARInitActivityActy.class);
//						intent.putExtra(INTENT_KEY_CLOSEAPP, true);
//						mActy.startActivity(intent);
						SysMng.sys_closeApp = true;
						mActy.finish();
					}
				}
			});
			return false;
		}
		return super.dispatchKeyEvent(event);
	}

	private void initData() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		if(intent != null)
			mSelectTaskId = intent.getStringExtra(TaskListActivity.INTENT_KEY_SELECTTASK);
	}

	private void initActy() {
		
		mImgCurrentCountView = (ImageView) findViewById(R.id.imgCurrentCountView);
		mImgTotleTenCountView = (ImageView) findViewById(R.id.imgTotleTenCountView);
		mImgTotleBitsCountView = (ImageView) findViewById(R.id.imgTotleBitsCountView);
		
		mImgTaskListCountView = (ImageView) findViewById(R.id.imgTaskListCountView);
		mBtnTaskListView = (Button) findViewById(R.id.btnTaskListView);
		
		if(SysMng.hasBeenFinishFirstTask()){
			mBtnTaskListView.setOnClickListener(this);
		}else{
			mBtnTaskListView.setVisibility(View.GONE);
			mImgTaskListCountView.setVisibility(View.GONE);
		}
		
		mTxtTaskTitleView = (TextView) findViewById(R.id.txtTaskTitleView);
		mTxtTaskDescView = (TextView) findViewById(R.id.txtTaskDescView);
		
		mBtnGoView = (Button) findViewById(R.id.btnGoView);
		mBtnGoView.setOnClickListener(this);
	}

	private void loadData() {

		TblTaskDetail item = null;
		if(mSelectTaskId != null && !mSelectTaskId.isEmpty()){
			item = DBCtrl.getSelectTask(this, mSelectTaskId);
		}else{
			item = DBCtrl.getNextTask(this);
		}
		
		if(item != null){
			mTxtTaskTitleView.setText(item.TaskTitle);
			mTxtTaskDescView.setText(item.TaskDesc);
			SysMng.biz_currentTaskId = item.Id;
//			ToastUtil.show(this, DBCtrl.finishTask(this, item.Id) + " " + item.Id + " " +item.Finish) ;
		}
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.btnTaskListView:
			goActy(TaskListActivity.class);
			break;
		case R.id.btnGoView:
			if(SysMng.biz_currentTaskId != null && !SysMng.biz_currentTaskId.isEmpty()){
				goActy(ARCameraActivity.class);
//				goActy(ConfirmTaskActivity.class);
			}else{
				ToastUtil.show(this, "当前没有任务。");
			}
			break;
		default:
			break;
		}
	}

	private void goActy(Class<?> cls){
		Intent intent = new Intent(this, cls);
		this.startActivity(intent);
	}
	
	private void loadTaskCount(){
		int finishCount = 0;
		int totleCount = 0;
		setCurrentCount(finishCount,totleCount);
	}
	
	
	private void setCurrentCount(int curCount,int totle){
		if(totle >=10){
			mImgTotleTenCountView.setVisibility(View.VISIBLE);
		}else{
			mImgTotleTenCountView.setVisibility(View.GONE);
		}
		setCountView(curCount,mImgCurrentCountView);
		
		
		int bitsNum = totle%10;
		int tenNum = totle - bitsNum;
		setCountView(tenNum,mImgTotleTenCountView);
		setCountView(bitsNum,mImgTotleBitsCountView);
	}
	
	private void setCountView(int num,ImageView v){
		int resourceId = 0;
		switch (num) {
		case 0:
			resourceId = R.drawable.num_0;
			break;
		case 1:
			resourceId = R.drawable.num_1;
			break;
		case 2:
			resourceId = R.drawable.num_2;
			break;
		case 3:
			resourceId = R.drawable.num_3;
			break;
		case 4:
			resourceId = R.drawable.num_4;
			break;
		case 5:
			resourceId = R.drawable.num_5;
			break;
		case 6:
			resourceId = R.drawable.num_6;
			break;
		case 7:
			resourceId = R.drawable.num_7;
			break;
		case 8:
			resourceId = R.drawable.num_8;
			break;
		case 9:
			resourceId = R.drawable.num_9;
			break;
		default:
			resourceId = R.drawable.num_1;
			break;
		}
		v.setImageResource(resourceId);
	}
	
}
