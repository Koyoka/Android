//package com.yrkj.elderlycareassess.acty;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
//import android.support.v4.app.FragmentTransaction;
//import android.view.KeyEvent;
//import android.view.View;
//import android.view.View.OnClickListener;
//
//import com.google.gson.Gson;
//import com.yrkj.elderlycareassess.R;
//import com.yrkj.elderlycareassess.base.SysMng;
//import com.yrkj.elderlycareassess.bean.AssessTaskDetailData;
//import com.yrkj.elderlycareassess.bean.AssessTaskHeaderData;
//import com.yrkj.elderlycareassess.bean.AssessTaskServiceData;
//import com.yrkj.elderlycareassess.bean.CustomerData;
//import com.yrkj.elderlycareassess.bean.QCategoryData;
//import com.yrkj.elderlycareassess.bean.QItemTagData;
//import com.yrkj.elderlycareassess.bean.QSubcategoryData;
//import com.yrkj.elderlycareassess.dao.AssessDBCtrl;
//import com.yrkj.elderlycareassess.dao.QuesDBCtrl;
//import com.yrkj.elderlycareassess.dao.SysDBCtrl;
//import com.yrkj.elderlycareassess.fragment.assess.AssessBaseFragment;
//import com.yrkj.elderlycareassess.fragment.assess.AssessLivingFragment;
//import com.yrkj.elderlycareassess.fragment.assess.AssessNewFragment;
//import com.yrkj.elderlycareassess.fragment.assess.AssessNewFragment.OnBtnStratClickListener;
//import com.yrkj.elderlycareassess.fragment.assess.AssessPersonalInfoFragment;
//import com.yrkj.elderlycareassess.fragment.assess.AssessQuestionnaireListFragment;
//import com.yrkj.elderlycareassess.fragment.assess.AssessServiceFragment;
//import com.yrkj.elderlycareassess.layout.ActivityMainAssess;
//import com.yrkj.elderlycareassess.service.SyncService;
//import com.yrkj.util.log.DLog;
//import com.yrkj.util.log.ToastUtil;
//
//public class MainAssessMenuActivity extends 
////ActionBarActivity 
//FragmentActivity 
//implements 
//OnClickListener, 
//OnBtnStratClickListener
//{
//
//	public static final String INTENT_KEY_CUSTID = "custId";
//
//	public static final String INTENT_KEY_ASSESSID = "assessId";
//	
//	MainAssessMenuActivity mActy;
//
//	private String[] mTitleList = null;
//	
//	private ActivityMainAssess mLayout;
//	
//	private String mCustId = null;
//	private String mAssessId = null;
//	private CustomerData mCust = null;
//	private AssessTaskHeaderData mTask = null;
//	private ArrayList<AssessBaseFragment> mAssessFragmentList;
//	
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main_assess);
//		mActy = this;
//		mLayout = new ActivityMainAssess(this);
//		if(getIntent()!=null){
//			mCustId = getIntent().getStringExtra(INTENT_KEY_CUSTID);
//			mAssessId = getIntent().getStringExtra(INTENT_KEY_ASSESSID);
//		}
//		
//		initData();
//		initActy();
//		
//		AssessNewFragment f = new AssessNewFragment(this,mCust,mTask);
//		f.setOnBtnStratClickListener(this);
//		
//		getSupportFragmentManager().beginTransaction()
//		.add(R.id.layoutBodyView,f, AssessNewFragment.class.getName())
//		.commit();
//	}
//	
//	
//	@Override
//	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
//		// TODO Auto-generated method stub
//		super.onActivityResult(arg0, arg1, arg2);
//		List<Fragment> frags = getSupportFragmentManager().getFragments();
//	    if (frags != null) {
//	        for (Fragment f : frags) {
//	            if (f != null){
//	            	if(!f.isHidden()){
//	            		f.onActivityResult(arg0, arg1, arg2);
//	            	}
//	            }
//	        }
//	    }
//	}
//	
//	
//	@Override
//	public boolean dispatchKeyEvent(KeyEvent event) {
//		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
//				&& event.getAction() == KeyEvent.ACTION_DOWN) {
//			finish();
//			
//			return false;
//		}
//		return super.dispatchKeyEvent(event);
//	}
//
//	private void initData(){
//		
//		if(mCustId != null){
//			mCust = AssessDBCtrl.getCustomerByCustId(this, mCustId);
//		}
//		
//		if(mAssessId != null){
//			mTask = AssessDBCtrl.getAssessTaskById(this,mAssessId);
//		}
//		
//		ArrayList<AssessBaseFragment> fList = 
//				new ArrayList<AssessBaseFragment>();
//		ArrayList<String> titleList = new ArrayList<String>();
//		titleList.add("评估信息");//default first page name
//		
//		fList.add(new AssessPersonalInfoFragment(this,mCust));
//		titleList.add(getResources().getString(R.string.assess_title_person));
//		
//		 
//		fList.add(new AssessLivingFragment(this,mCust));
//		titleList.add(getResources().getString(R.string.assess_title_living));
//		
//		ArrayList<QCategoryData> qitemList = 
//				getQCategoryList();
//		
//		for (QCategoryData qCategoryData : qitemList) {
//			fList.add(
//					AssessQuestionnaireListFragment.getInstance(this,qCategoryData,mCust,
////							mTaskDetailList,
//							mAssessId)
//					);
//			titleList.add(qCategoryData.CateName);
//		}
//		
//		fList.add(new AssessServiceFragment(this,mCust,mAssessId));
//		titleList.add(getResources().getString(R.string.assess_title_service));
//		
//		mTitleList =  titleList.toArray(new String[titleList.size()]);
//		mAssessFragmentList = fList;
//	}
//	
//	private ArrayList<QCategoryData> getQCategoryList(){
//		ArrayList<QCategoryData> qitemList
//			= QuesDBCtrl.getQCategoryList(this);
//		
//		for (QCategoryData item : qitemList) {
//			item.SubcateList = 
//					QuesDBCtrl.getQSubcategoryList(this,item.CateId);
//			for (QSubcategoryData subitem : item.SubcateList) {
//				setQSubcate(subitem);
//			}
//		}
//		return qitemList;
//	}
//	private QSubcategoryData setQSubcate(QSubcategoryData item){
//
//		item.ItemList =		
//		QuesDBCtrl.getQItemListBySubcateId(this, item.SubcateId);
//		
//		item.ItemTagList = 
//				QuesDBCtrl.getQItemTagListBySubcateId(this, item.SubcateId);
//		return item;
//	}
//	
//	private QItemTagData getQItemLab(int id,String name){
//		QItemTagData item = new QItemTagData();
//		item.ItemTagId = id;
//		item.ItemTagName = name;
//		return item;
//	}
//
//	private void initActy() {
//		mLayout.getBtnGoView().setOnClickListener(this);
//		mLayout.getBtnBackView().setOnClickListener(this);
//		mLayout.getBtnFinishView().setOnClickListener(this);
//		
//		FragmentManager fMng = getSupportFragmentManager();
//		
//		fMng.addOnBackStackChangedListener(new OnBackStackChangedListener() {
//			
//			@Override
//			public void onBackStackChanged() {
////				DLog.LOG(SysMng.TAG_FRAGMENT,getSupportFragmentManager().getBackStackEntryCount()+" = addOnBackStackChangedListener");
//				setNavBtn(getSupportFragmentManager().getBackStackEntryCount());
//			}
//		});
//		
//		
//		setAssessTitle(0);
//	}
//	
//	private void setNavBtn(int index){
//		
//		setAssessTitle(index);
//		
//		if(index == 0){
//			mLayout.getLayoutFootView().setVisibility(View.GONE);
//		}else{
//			mLayout.getLayoutFootView().setVisibility(View.VISIBLE);
//		}
//		if(index+1 < mTitleList.length)
//			mLayout.getBtnGoView().setText("("+(index+2)+"/"+mTitleList.length+")"+mTitleList[index+1]);
//		else{
//			mLayout.getBtnGoView().setText("提交");
//		}
//		
//		if(index-1 >= 0)
//			mLayout.getBtnBackView().setText("("+(index)+"/"+mTitleList.length+")"+mTitleList[index-1]);
//		else{
//			mLayout.getBtnBackView().setText("");
//		}
//	}
//
//	public void goBack(){
//		FragmentManager fMng = getSupportFragmentManager();
//		fMng.popBackStack();
//	}
//	
//	public synchronized void addFragment() {
//		
//		FragmentManager fMng = getSupportFragmentManager();
//		int i = fMng.getBackStackEntryCount();
//				
//		if(i >= mAssessFragmentList.size()){
//			return;
//		}
//		String tag = (i+1)+"";
////		DLog.LOG(SysMng.TAG_FRAGMENT, "begin "+tag
////				+" size["+mAssessFragmentList.size()+"] haveSize["+fMng.getFragments().size()+"] index["+i+"]=================================");
//		
//		Fragment lastFragment = fMng.getFragments().get(i);//fMng.findFragmentById(R.id.layoutBodyView);
//		AssessBaseFragment mFragment = mAssessFragmentList.get(i);
//		{
//			
//			if(!mFragment.isAdded() /*&& fMng.findFragmentByTag(tag) == null*/){
//				FragmentTransaction ft = fMng.beginTransaction();
//				ft.add(R.id.layoutBodyView, mFragment, tag);
////				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//				ft.commit();
//				fMng.executePendingTransactions();
//			}
//		}
//		
//		if(fMng.getFragments().contains(mFragment))
//		{
//			FragmentTransaction ft = fMng.beginTransaction();
////			ft.setCustomAnimations(R.anim.fragment_slide_left_enter,
////		                R.anim.fragment_slide_left_exit,
////		                R.anim.fragment_slide_right_enter,
////		                R.anim.fragment_slide_right_exit);
//			ft.hide(lastFragment);
////			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//			ft.show(mFragment);
//			ft.addToBackStack(null);
////			ft.commitAllowingStateLoss();
//			ft.commit();
//			fMng.executePendingTransactions();
//			
//		}
////		DLog.LOG(SysMng.TAG_FRAGMENT, "end "+tag+" "+ mFragment.isAdded()+ " =================================");
//		
//		
//	}
//	
//	
//	public void setAssessTitle(int i){
//		if(i<mTitleList.length){
//			String s = mTitleList[i];
//			mLayout.getTxtMainAssessTitleView().setText(s);
//		}
//	}
//
//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//		case ActivityMainAssess.BtnFinishViewId:
//			finish();
//			break;
//		case ActivityMainAssess.BtnBackViewId:
//			goBack();
//			break;
//		case ActivityMainAssess.BtnGoViewId:
//			if(mLayout.getBtnGoView().getText().equals("提交")){
//				
//				mAssessFragmentList.get(mAssessFragmentList.size()-1).saveData();
//
//				if(mTask != null){
//					mTask.AssessState = AssessTaskHeaderData.ASSESS_STATE_DONE;
//					mTask.NeedSync = true;
//					AssessDBCtrl.updateAssessTaskHeaderById(this, mTask);
//					
//					SysDBCtrl.addSubmitAssessLog(this, mTask.AssessNum);
//					
//					ToastUtil.show(mActy, "提交成功。");
////					a();
//					setResult(RESULT_SUBMIT);
//					this.finish();
//				}
//			}
//			addFragment();
//			break;
//
//		default:
//			break;
//		}
//	}
//	class TaskData{
//		CustomerData cust = null;
//		AssessTaskHeaderData header = null;
//		ArrayList<AssessTaskDetailData> detail = null;
//		ArrayList<AssessTaskServiceData> service = null;
//	}
//	
////	void a(){
//////		
//////		
////		AssessTaskHeaderData data = AssessDBCtrl.getAssessTaskById(this, mAssessId);
////		data.NetTaskHeaderId = "NetTaskHeaderId";
////		ArrayList<AssessTaskDetailData> dataDetailList = AssessDBCtrl.getAssessTaskDetailByTaskId(this, mAssessId);
////		ArrayList<AssessTaskServiceData> dataServiceList =  AssessDBCtrl.getAssessTaskServiceByTaskId(this, mAssessId);
////		CustomerData cust = AssessDBCtrl.getCustomerByCustId(this, data.CustId);
////		
////		TaskData td = new TaskData();
////		td.cust = cust;
////		td.header = data;
////		td.detail = dataDetailList;
////		td.service = dataServiceList;
////		Gson gson = new Gson();
//////		AssessTaskHeaderData d = new AssessTaskHeaderData();
//////		d.AssessNum = "111";
//////		d.AssessState = "dd";
////		 String s = gson.toJson(td);
////		 DLog.LOG(SysMng.TAG_DB,s);
//////		 autoRun = gson.fromJson(s, AutoRun.class);
////
////
////	}
//	
//	
//	public static final int RESULT_SUBMIT = 101; 
//
//	@Override
//	public void onBtnStratClick() {
//		addFragment();
//	}
//
//}
