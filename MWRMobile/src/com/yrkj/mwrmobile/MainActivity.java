package com.yrkj.mwrmobile;

import java.util.ArrayList;

import com.dtr.zxing.activity.CaptureActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.yrkj.mwrmobile.R;
import com.yrkj.mwrmobile.bean.TxnDetailData;
import com.yrkj.mwrmobile.dao.TxnDao;
import com.yrkj.mwrmobile.fragment.EntryCrateDialogFragment;
import com.yrkj.mwrmobile.layout.ActivityMain;
import com.yrkj.util.log.DLog;
import com.yrkj.util.log.ToastUtil;

public class MainActivity extends Activity implements OnClickListener {

	private ActivityMain mLayout = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initActy();
	}
	
	private void initActy(){
		mLayout = new ActivityMain(this);
		
		mLayout.getBtnRecoverToInventroy().setOnClickListener(this);
		
		ArrayList<TxnDetailData> ds =
		 TxnDao.getTxnDetail(this);
		 DLog.LOGD(ds + " size:" + ds.size());
		findViewById(R.id.btnRecoverCrate).setOnClickListener(this);
	}
	

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btnRecoverCrate:
			Intent intent = new Intent(this, RecoverActivity.class);
			startActivity(intent);
			break;
		case ActivityMain.BtnRecoverToInventroyId:
			EntryCrateDialogFragment.getInstance().show(getFragmentManager(), "dialog");
			
			break;
		default:
			break;
		}
	}
	
}

/*
 * 
 * 	
	
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		if(1==resultCode){
//			
//			int width = data.getExtras().getInt("width");//.getIntExtra("width", 0);
//			int height =  data.getExtras().getInt("height");
//			String result = data.getExtras().getString("result");
//			Toast.makeText(this, "result:" + result, Toast.LENGTH_LONG).show();
//		
//		}
//	}

 * //		Intent intent = getIntent();
//		if(intent != null)
//		{
//			String result = intent.getStringExtra("result");
//			ToastUtil.show(this, result +"   eleven");
//		}

 * Intent intent = new Intent(MainActivity.this,CaptureActivity.class);
if(false){
	intent.putExtra(CaptureActivity.INTENT_KEY_ResultType,
			CaptureActivity.INTENT_VAL_ResultType_SetResult);
	startActivityForResult(intent, 9);
}else{
	
	intent.putExtra(CaptureActivity.INTENT_KEY_ResultType,
			CaptureActivity.INTENT_VAL_ResultType_NewActy);
	intent.putExtra(CaptureActivity.INTENT_KEY_PKG,
			"com.yrkj.mwrmobile");
	intent.putExtra(CaptureActivity.INTENT_KEY_CLZName,
			"com.yrkj.mwrmobile.MainActivity");
	startActivity(intent);
}
		
 * */
