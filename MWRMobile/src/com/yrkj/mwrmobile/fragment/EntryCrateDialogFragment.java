package com.yrkj.mwrmobile.fragment;


import java.util.ArrayList;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;

import com.yrkj.mwrmobile.R;
import com.yrkj.mwrmobile.bean.TxnDetailData;
import com.yrkj.mwrmobile.bean.VendorData;
import com.yrkj.mwrmobile.bean.WasteCategoryData;
import com.yrkj.mwrmobile.dao.BaseDataDao;
import com.yrkj.mwrmobile.dao.TxnDao;
import com.yrkj.mwrmobile.layout.FragmentEntryCrateDialog;
import com.yrkj.util.log.ToastUtil;

public class EntryCrateDialogFragment extends DialogFragment implements OnClickListener {

	public final String Tag = "com.yrkj.mwrmobile.fragment.EntryCrateDialogFragment";
	
	private FragmentEntryCrateDialog mLayout = null;
	private ArrayList<VendorData> mVendorList = null;
	private ArrayList<WasteCategoryData> mWasterList = null;
	
	private TxnDetailData mTxnDetailData = null;
	private String mCrateCode = "";
	private VendorData mCurVendor = null;
	private WasteCategoryData mCurWaste = null;
	
	private OnConfirmTxnListener mOnConfirmTxnListener = null;
	public interface OnConfirmTxnListener{
		public void onConfirmTxn();
	}
	public void SetOnConfirmTxnListener(OnConfirmTxnListener l){
		mOnConfirmTxnListener = l;
	}
	
	public static EntryCrateDialogFragment getInstance(String crateCode){
		
		EntryCrateDialogFragment f = new EntryCrateDialogFragment(crateCode);
//		Bundle args = new Bundle();
//        args.putInt("num", num);
//        f.setArguments(args);
		return f;
	}
	public static EntryCrateDialogFragment getInstance(String crateCode,OnConfirmTxnListener l){
		
		EntryCrateDialogFragment f = new EntryCrateDialogFragment(crateCode);
		f.SetOnConfirmTxnListener(l);
//		Bundle args = new Bundle();
//        args.putInt("num", num);
//        f.setArguments(args);
		return f;
	}
	
	public EntryCrateDialogFragment(String crateCode){
		mCrateCode = crateCode;
	}
	
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
//		Dialog d = super.onCreateDialog(savedInstanceState);
//		d.setCancelable(false);
		return super.onCreateDialog(savedInstanceState);
	}
	
	@Override
	public void onResume() {
		super.onResume();
//		int width = getResources().getDimensionPixelSize(android.R.dimen.popup_width);
//		int height = getResources().getDimensionPixelSize(R.dimen.popup_height);        
//		getDialog().getWindow().setLayout(width, height);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		getArguments()
		setCancelable(false);
		
		setStyle(DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_Holo_Light_Dialog);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		
		 View v = inflater.inflate(R.layout.fragment_entry_crate_dialog, container, false);
		 if(!initData()){
			 return v;
		 }
		 initFragment(v);
		 return v;
//		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	private boolean initData(){
		try{
			mTxnDetailData = new TxnDetailData();
			mTxnDetailData.CrateCode = mCrateCode;
			
			mVendorList = 
					BaseDataDao.getVendorList(getActivity());
			VendorData vendor = new VendorData();
			vendor.Vendor = "����";
			vendor.VendorCode = "-1";
			mVendorList.add(vendor);
			
			mWasterList = 
					BaseDataDao.getWasterList(getActivity());
			WasteCategoryData waste = new WasteCategoryData();
			waste.Waste = "����";
			waste.WasteCode = "-1";
			mWasterList.add(waste);
			
		}catch(Exception ex){
			ToastUtil.show(getActivity(), ex.getMessage());
			return false;
		}
		return true;
		
	}
	
	
	private void initFragment(View v){
		mLayout = new FragmentEntryCrateDialog(v);
		mLayout.getBtnCloseDialog().setOnClickListener(this);
		mLayout.getBtnConfirmDialog().setOnClickListener(this);
		mLayout.getTxtCrateCode().setText(mCrateCode);
		
		ArrayList<String> mVendors = new ArrayList<String>();
		ArrayList<String> mWasters = new ArrayList<String>();
		for(VendorData item : mVendorList){
			mVendors.add(item.Vendor);
		}
		
		for(WasteCategoryData item : mWasterList){
			mWasters.add(item.Waste);
		}

		{
			ArrayAdapter<String> adapter = null;
			adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, mVendors);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	     
			mLayout.getSpnVendor().setAdapter(adapter);
			mLayout.getSpnVendor().setOnItemSelectedListener(
		                new OnItemSelectedListener() {
		                    public void onItemSelected(
		                            AdapterView<?> parent, View view, int position, long id) {
		                    	ToastUtil.show(getActivity(), mVendorList.get(position).Vendor);
		                    	mTxnDetailData.Vendor = mVendorList.get(position).Vendor;
		                    	mTxnDetailData.VendorCode = mVendorList.get(position).VendorCode;
		                    	mCurVendor = mVendorList.get(position);	
		                    }
	
		                    public void onNothingSelected(AdapterView<?> parent) {
	//	                        showToast("Spinner1: unselected");
		                    }
		                });
		}
		{
			
			ArrayAdapter<String> adapter = null;
			adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, mWasters);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	     
			mLayout.getSpnWaster().setAdapter(adapter);
			mLayout.getSpnWaster().setOnItemSelectedListener(
		                new OnItemSelectedListener() {
		                    public void onItemSelected(
		                            AdapterView<?> parent, View view, int position, long id) {
		                    	ToastUtil.show(getActivity(), mWasterList.get(position).Waste);
		                    	mTxnDetailData.Waste = mWasterList.get(position).Waste;
		                    	mTxnDetailData.WasteCode = mWasterList.get(position).WasteCode;
		                    	mCurWaste = mWasterList.get(position);
		                    }
	
		                    public void onNothingSelected(AdapterView<?> parent) {
	//	                        showToast("Spinner1: unselected");
		                    }
		                });
		}
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == FragmentEntryCrateDialog.BtnCloseDialogId){
			dismiss();
		}else if(v.getId() == FragmentEntryCrateDialog.BtnConfirmDialogId){
			ToastUtil.show(this.getActivity(), "confirm");
			String weight = mLayout.getTxtWeight().getText().toString();
			if(weight.trim().length() == 0
					|| weight.equals("0")){
				ToastUtil.show(getActivity(), "������������������");
				return;
			}
			
//			if(true){
//				return;
//			}
			
			if(mCurVendor == null || mCurWaste == null){
				ToastUtil.show(getActivity(), "��ѡ��ҽԺ�ͷ������͡�");
			}
			
			mTxnDetailData.SubWeight = weight;
			
			Handler handler = new Handler(){
				
				@Override
				public void handleMessage(Message msg) {
					if(msg.what == TxnDao.Txn_failed){
						ToastUtil.show(getActivity(), msg.obj.toString());
					}
					
				}
			};
			
			long result = TxnDao.AddTxn(getActivity(), mTxnDetailData.CrateCode, 
					mTxnDetailData.SubWeight, 
					mCurVendor, mCurWaste,handler);
			
			if(result !=0){
				if(mOnConfirmTxnListener != null){
					mOnConfirmTxnListener.onConfirmTxn();
				}
			}
			dismiss();
			
		}
		
	}
	
	
}
