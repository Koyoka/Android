package com.yrkj.elderlycareassess.fragment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.acty.AlbumActivity;
import com.yrkj.elderlycareassess.bean.AssessTaskAttachmentDiseaseData;
import com.yrkj.elderlycareassess.bean.AssessTaskAttachmentImageData;
import com.yrkj.elderlycareassess.bean.AssessTaskAttachmentSoundData;
import com.yrkj.elderlycareassess.dao.AttachmentDBCtrl;
import com.yrkj.elderlycareassess.fragment.widget.MyDialogFragment;
import com.yrkj.elderlycareassess.fragment.widget.MyDialogFragment.onDateSelected;
import com.yrkj.elderlycareassess.layout.FragmentAttachment;
import com.yrkj.elderlycareassess.util.AudioHelper;
import com.yrkj.elderlycareassess.util.FragmentMediaHelper;
import com.yrkj.elderlycareassess.widget.UIRecordButton.OnFinishedRecordListener;
import com.yrkj.util.bitmap.BitmapHelper;
import com.yrkj.util.bitmap.MediaHelper;
import com.yrkj.util.date.DateHelper;
import com.yrkj.util.dialog.DialogHelper;
import com.yrkj.util.log.ToastUtil;

public class AttachmentFragment extends Fragment implements OnClickListener {
	
	private String mSavePath = "";
	private int mTaskHeaderId = 0;
	private int mCateId = 0;
	private FragmentAttachment mLayout;
	AudioHelper mAudioHelper;
	private boolean mNeedSync = false;
	
	private OnRecorLisenter mOnRecorLisenter;
	private static final File SOUND_DIR = 
			new File(Environment.getExternalStorageDirectory() + "/ECA_Sound");//图片的存储目录

	
	public AttachmentFragment(int taskHeaderId,int cateId,boolean e){
		mTaskHeaderId = taskHeaderId;
		mCateId = cateId;
		mNeedSync = e;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_attachment, container,
				false);
		
		mLayout = new FragmentAttachment(v);
		setFrontBody(v);
		initFragment();
		return v;
	}
	
	
	private void initFragment(){
		
		mLayout.getBtnShowDiseaseView().setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					mLayout.getLayoutContentView().setVisibility(isChecked?View.VISIBLE:View.GONE);
			}
		});
		
		mLayout.getBtnShowDiseaseContentView().setOnClickListener(this);
		
		mLayout.getBtnSaveDiseaseInfoView().setOnClickListener(this);
		mLayout.getBtnDiseaseDateView().setOnClickListener(this);
		
		mLayout.getBtnDiseaseView().setOnClickListener(this);
		mLayout.getBtnCameraView().setOnClickListener(this);
//		mLayout.getBtnSoundView().setSavePath(getSaveSoundFileName());
		mAudioHelper = new AudioHelper(mLayout.getProgressBar1());
		mLayout.getBtnSoundView().setOnClickListener(this);
//		mLayout.getBtnSoundView().setOnFinishedRecordListener(new OnFinishedRecordListener() {
//
//			@Override
//			public void onFinishedRecord(String audioPath) {
////				Toast.makeText(RecordingActivity.this,audioPath, Toast.LENGTH_SHORT).show();
//				AssessTaskAttachmentSoundData d = new AssessTaskAttachmentSoundData();
//				d.TaskHeaderId = mTaskHeaderId;
//				d.CateId = mCateId;
//				d.SoundPath = audioPath;
//				AttachmentDBCtrl.addAttachmentSound(getActivity(), d);	
//				bindSound();
////				ToastUtil.show(getActivity(), audioPath);
//				mLayout.getBtnSoundView().setSavePath(getSaveSoundFileName());
////				openFile(new File(audioPath));
////				
//			}
//		});
		
		bindImg();
		bindSound();
		bindDisease();
	}
	
	public void saveSound(String audioPath){
		
		if(audioPath.isEmpty()){
			return;
		}
		AssessTaskAttachmentSoundData d = new AssessTaskAttachmentSoundData();
		d.TaskHeaderId = mTaskHeaderId;
		d.CateId = mCateId;
		d.SoundPath = audioPath;
		AttachmentDBCtrl.addAttachmentSound(getActivity(), d);	
		bindSound();
//		ToastUtil.show(getActivity(), audioPath);
//		mLayout.getBtnSoundView().setSavePath(getSaveSoundFileName());
	}

	private void popDisease(){
		PopupMenu popup = new PopupMenu(getActivity(), mLayout.getBtnDiseaseView());
		popup.getMenuInflater().inflate(R.menu.disease, popup.getMenu());
		
		popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem item) {
				mLayout.getBtnDiseaseView().setText(item.getTitle());
				return true;
			}
		});
		
		popup.show();
	}
	
	private void saveDisease(){
		if(DateHelper.parseDate(mLayout.getBtnDiseaseDateView().getText()+"-1")
				== null){
			ToastUtil.show(getActivity(), "请选择日期。");
			return;
		}
		
		String s1 = mLayout.getTxtDiseaseListView().getText().toString();
		String s2 = mLayout.getBtnDiseaseView().getText().toString();
		
		if(s1.indexOf(s2) != -1){
			DialogHelper.createTextDialog(getActivity(), "消息", "该疾病已添加");
			return;
		}
		
//		long t = Date.parse(mLayout.getBtnDiseaseDateView().getText()+"-1");
//		ToastUtil.show(getActivity(), t+"");
		
		AssessTaskAttachmentDiseaseData 
		d = new AssessTaskAttachmentDiseaseData();
		d.TaskHeaderId = mTaskHeaderId;
		d.CateId = mCateId;
		d.DiseaseDesc = mLayout.getTxtDiseaseDescView().getText().toString();
		d.IsMedication = mLayout.getRdoIsMedicationView().getCheckedRadioButtonId()
				== mLayout.getRdoMedicationYView().getId()?true:false;
		d.SickDate = mLayout.getBtnDiseaseDateView().getText().toString();
		d.DiseaseName = mLayout.getBtnDiseaseView().getText().toString();
		
		AttachmentDBCtrl.addAttachmentDisease(getActivity(), d);
		mLayout.getTxtDiseaseDescView().setText("");
		bindDisease();
		
	}
	
	public static int getSDKVersionNumber() {
        int sdkVersion;
        try {
            sdkVersion = Integer.valueOf(android.os.Build.VERSION.SDK_INT);
        } catch (NumberFormatException e) {
            sdkVersion = 0;
        }
        return sdkVersion;
    }
	
	 private DatePickerDialog customDatePicker(Date d) {
		 
		final Calendar cd = Calendar.getInstance();
		if(d==null){
			Date date = new Date();
			cd.setTime(date);
		}else{
			cd.setTime(d);
		}
		DatePickerDialog dpd = new DatePickerDialog(getActivity(),
				new android.app.DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						mLayout.getBtnDiseaseDateView().setText(
								year + "-" + (monthOfYear + 1));
					}
				}, cd.get(Calendar.YEAR), cd.get(Calendar.MONTH),
				cd.get(Calendar.DAY_OF_MONTH));
		dpd.getDatePicker().setCalendarViewShown(false);
		try {
			java.lang.reflect.Field[] datePickerDialogFields = dpd.getClass()
					.getDeclaredFields();
			for (java.lang.reflect.Field datePickerDialogField : datePickerDialogFields) {
				if (datePickerDialogField.getName().equals("mDatePicker")) {
					datePickerDialogField.setAccessible(true);
					DatePicker datePicker = (DatePicker) datePickerDialogField
							.get(dpd);

					java.lang.reflect.Field datePickerFields[] = datePickerDialogField
							.getType().getDeclaredFields();
					for (java.lang.reflect.Field datePickerField : datePickerFields) {
						if ("mDayPicker".equals(datePickerField.getName())
								|| "mDaySpinner".equals(datePickerField
										.getName())) {
							datePickerField.setAccessible(true);
							Object dayPicker = new Object();
							dayPicker = datePickerField.get(datePicker);
							((View) dayPicker).setVisibility(View.GONE);
						}
					}
				}
			}
		} catch (Exception ex) {
		}
	        return dpd;
	    }
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case FragmentAttachment.BtnShowDiseaseContentViewId:
			mLayout.getBtnShowDiseaseView().setChecked(!mLayout.getBtnShowDiseaseView().isChecked());
			break;
		}
		if(!mNeedSync){
			return;
		}
		
		switch (v.getId()) {
//		case FragmentAttachment.BtnShowDiseaseContentViewId:
//			mLayout.getBtnShowDiseaseView().setChecked(!mLayout.getBtnShowDiseaseView().isChecked());
//			break;
		case FragmentAttachment.BtnSaveDiseaseInfoViewId:
			saveDisease();
			break;
		case FragmentAttachment.BtnDiseaseDateViewId:
			Date d = null;
			if(mLayout.getBtnDiseaseDateView().getText()!= null 
					&& !mLayout.getBtnDiseaseDateView().getText().toString().isEmpty()){
				d =DateHelper.parseDate(mLayout.getBtnDiseaseDateView().getText()+"-1");
			}
			customDatePicker(d).show();
			break;
		case FragmentAttachment.BtnDiseaseViewId:
			popDisease();
			break;

		case FragmentAttachment.BtnCameraViewId:
			
			mSavePath = FragmentMediaHelper.getSavePath();
			
//			MediaHelper.setMedia(getActivity(), MediaHelper.MEDIA_IMG_CAMERA,mSavePath);
			FragmentMediaHelper.setMedia(this,MediaHelper.MEDIA_IMG_CAMERA /*| MediaHelper.MEDIA_IMG_CROP*/,mSavePath);
			break;
		case FragmentAttachment.BtnSoundViewId:
			if(mOnRecorLisenter!=null){
				mOnRecorLisenter.onStart();
			}
			break;
		default:
			break;
		}
			
	}
	
	public void setOnRecorLisenter(OnRecorLisenter l){
		mOnRecorLisenter = l;
	}
	public interface OnRecorLisenter{
		public void onStart();
	}
	
	
	public String getSaveSoundFileName(){
		if(!SOUND_DIR.exists()){
			SOUND_DIR.mkdir();
		}
		
		Date date = new Date(System.currentTimeMillis());  
	    SimpleDateFormat dateFormat = new SimpleDateFormat("'SOUND'_yyyyMMdd_HHmmss");  
//	    String saveSoundFileName = dateFormat.format(date) + ".mp3";  
	    String saveSoundFileName = dateFormat.format(date) + ".amr";  
	    
	    File f = new File(SOUND_DIR, saveSoundFileName); // 用当前时间给取得的图片命名
		return f.getPath();
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		String imgPath = FragmentMediaHelper.getMedioResultPath(this,data,requestCode,mSavePath);
//		String imgPath = MediaHelper.getMedioResultPath(getActivity(),data,requestCode,mSavePath);
//		ToastUtil.show(getActivity(), imgPath+"  " +new File(imgPath).exists());
		if(new File(imgPath).exists()){
			AssessTaskAttachmentImageData d = new AssessTaskAttachmentImageData();
			d.TaskHeaderId = mTaskHeaderId;
			d.CateId = mCateId;
			d.ImgPath = imgPath;
			
			long i = AttachmentDBCtrl.addAttachmentImg(getActivity(), d);
			
			bindImg();
		}
	}
	
	private void bindImg(){
		mLayout.getLayoutImgList().removeAllViews();
		ArrayList<AssessTaskAttachmentImageData> itemlist =
				AttachmentDBCtrl.getAttachmentImgList(getActivity(),mTaskHeaderId,mCateId);
		for (AssessTaskAttachmentImageData item : itemlist) {
			
			if(new File(item.ImgPath).exists()){
				Bitmap b = BitmapHelper.decodeSampledBitmapFromLacolPath(item.ImgPath,40,40);
				if(b != null){
					int degree = MediaHelper.readPictureDegree(item.ImgPath);
					b = MediaHelper.rotaingImageView(degree,b);
					
					ImageView v = new ImageView(getActivity());
					
					LinearLayout.LayoutParams lp =
							new LinearLayout.LayoutParams(120, 120);
					lp.setMargins(10, 0, 10, 0);
					v.setLayoutParams(lp);
					
					v.setScaleType(ImageView.ScaleType.CENTER_CROP);
		            v.setImageBitmap(b);
		            mLayout.getLayoutImgList().addView(v);
		            v.setOnClickListener(imgClick);
				}
				
			}
		}
		
	}
	private void bindSound(){
		mLayout.getLayoutSoundList().removeAllViews();
		ArrayList<AssessTaskAttachmentSoundData> itemlist =
				AttachmentDBCtrl.getAttachmentSoundList(getActivity(),mTaskHeaderId,mCateId);
		for (AssessTaskAttachmentSoundData item : itemlist) {
			
			ImageView v = new ImageView(getActivity());
			
			LinearLayout.LayoutParams lp =
					new LinearLayout.LayoutParams(120, 120);
			lp.setMargins(10, 0, 10, 0);
			v.setLayoutParams(lp);
			
			v.setScaleType(ImageView.ScaleType.CENTER_CROP);
			v.setTag(item.SoundPath);
			
			v.setImageResource(R.drawable.icon_audio);
			mLayout.getLayoutSoundList().addView(v);
			v.setOnClickListener(soundClick);
			
		}
		
	}
	
	private void bindDisease(){
		ArrayList<AssessTaskAttachmentDiseaseData> dlist =
				AttachmentDBCtrl.getAttachmentDiseaseList(getActivity(), mTaskHeaderId, mCateId);
	
		String s = "";
		for (AssessTaskAttachmentDiseaseData item : dlist) {
			s+=item.DiseaseName+" ";
		}
		mLayout.getTxtDiseaseListView().setText(s);
	}
	
	OnClickListener imgClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {

			Intent intent = new Intent(getActivity(), AlbumActivity.class);
			intent.putExtra(AlbumActivity.INTENT_KEY_TASKHEADER_ID, mTaskHeaderId);
			intent.putExtra(AlbumActivity.INTENT_KEY_CATE_ID, mCateId);
			startActivity(intent);
		}
	};
	OnClickListener soundClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
//			v.setBackgroundColor(Color.parseColor("red"));
			mAudioHelper.setFile(v.getTag().toString()).play();
//			Intent intent = new Intent(getActivity(), AlbumActivity.class);
//			startActivity(intent);
		}
	};
	
	protected void setFrontBody(View v){
		if(!mNeedSync){
			
			if(mFrontClick == null){
				mFrontClick = new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
//						ToastUtil.show(getActivity(), mNeedSync+"");
					}
				};
				v.findViewById(R.id.layoutFrontView).setOnClickListener(mFrontClick);
			}
			v.findViewById(R.id.layoutFrontView).setVisibility(View.VISIBLE);
		}else{
			v.findViewById(R.id.layoutFrontView).setVisibility(View.GONE);
			
		}
	}
	OnClickListener mFrontClick = null;
}
