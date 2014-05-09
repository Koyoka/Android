package com.yrkj.elderlycareassess.fragment;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.acty.MainAssessActivity;
import com.yrkj.elderlycareassess.layout.ListItemDoingTask;

public class AssessTaskListFragment extends ListFragment implements
		OnItemClickListener {
	

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initData();

	}

	private void initData() {
		mDataSource = new ArrayList<TaskData>();
		addData();
		addData();
		addData();
		addData();
		addData();
		addData();
		addData();
		addData();
		addData();

		mTaskAdapter = new TaskAdapter(getActivity());

		setListAdapter(mTaskAdapter);
		ListView listView = getListView();
		listView.setDivider(new ColorDrawable(Color.WHITE));
		listView.setDividerHeight(0);
		listView.setOnItemClickListener(this);
		// getListView().setDividerHeight(1); // 不显示分割线
		listView.setCacheColorHint(Color.TRANSPARENT); // 防止listview背景变黑
		mTaskAdapter.notifyDataSetChanged();

	}

	private void addData() {
		TaskData item = new TaskData();
		item.taskNum = mDataSource.size() + "00000AS";
		item.taskState = "进行中";
		;
		item.taskLastDoDate = "2014-05-19";
		;
		item.taskEndDate = "2014-05-19";
		item.userName = "任正";
		;
		item.sex = "男";
		;
		item.phoneNum = "1300000000";
		;
		item.address = "湖北省 武汉市 武昌区  XXXXXX";
		;
		mDataSource.add(item);
	}

	private void initFragment() {

	}

	ArrayList<TaskData> mDataSource;
	TaskAdapter mTaskAdapter;

	class TaskData {
		public String taskNum;
		public String taskState;
		public String taskLastDoDate;
		public String taskEndDate;
		public String userName;
		public String sex;
		public String phoneNum;
		public String address;
	}

	class ViewHolder {

	}

	// class TaskListAdapter extends ArrayAdapter<TaskData>{
	//
	// public TaskListAdapter(Context context, int textViewResourceId) {
	// super(context, textViewResourceId);
	// // TODO Auto-generated constructor stub
	// }
	//
	//
	// // public TaskListAdapter(Context context) {
	// // super(context, android.R.layout.simple_list_item_2);
	// // // TODO Auto-generated constructor stub
	// // }
	//
	// }

	class TaskAdapter extends BaseAdapter {

		private LayoutInflater mInflater;

		public TaskAdapter(Context c) {
			mInflater = LayoutInflater.from(c);
		}

		@Override
		public int getCount() {
			if (mDataSource != null) {
				return mDataSource.size();
			}
			return 0;
		}

		@Override
		public Object getItem(int position) {
			if (mDataSource != null) {
				return mDataSource.get(position);
			}
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ListItemDoingTask viewHolder = null;
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.list_item_doing_task,
						null);
				viewHolder = new ListItemDoingTask(convertView);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ListItemDoingTask) convertView.getTag();
			}

			TaskData item = mDataSource.get(position);

			viewHolder.getTxtTaskNumView().setText(item.taskNum);
			viewHolder.getTxtTaskAddressView().setText(item.address);
			viewHolder.getTxtTaskEndDateView().setText(item.taskEndDate);
			viewHolder.getTxtTaskLastDoDateView().setText(item.taskLastDoDate);
			viewHolder.getTxtTaskPhoneNumView().setText(item.phoneNum);
			viewHolder.getTxtTaskSexView().setText(item.sex);
			viewHolder.getTxtTaskStateView().setText(item.taskState);
			viewHolder.getTxtTaskUserNameView().setText(item.userName);

			return convertView;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(getActivity(), MainAssessActivity.class);
		getActivity().startActivity(intent);
	}

}
//@Override
	// public View onCreateView(LayoutInflater inflater, ViewGroup container,
	// Bundle savedInstanceState) {
	// View v = inflater.inflate(R.layout.fragment_assess_task_list, container,
	// false);
	//
	// v.findViewById(R.id.btnRedirectView).setOnClickListener(new
	// OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// Intent intent = new Intent(getActivity(), MainAssessActivity.class);
	// getActivity().startActivity(intent);
	// }
	// });
	// return v;
	// }