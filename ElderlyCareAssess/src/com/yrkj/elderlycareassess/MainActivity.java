package com.yrkj.elderlycareassess;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction()
//			.add(arg0, arg1)
//			getSupportFragmentManager().beginTransaction()
			getFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment())
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		initActy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	public class StudentInfo extends Fragment
	{
	    // Fragment��Ӧ�ı�ǩ����Fragment������Activityʱ�õ�
	    private String tag;

	    @Override
	    public void onAttach(Activity activity)
	    {
	        super.onAttach(activity);
	        tag = getTag();
	    }

	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	    {
	        TextView textView = new TextView(getActivity());
	        textView.setText(tag);
	        return textView;
	    }
	}
	
	class DropDownListenser implements OnNavigationListener
    {
        // �õ���SpinnerAdapter��һ�µ��ַ�����
        String[] listNames = getResources().getStringArray(R.array.action_list);

        /* ��ѡ�������˵����ʱ�򣬽�Activity�е������û�Ϊ��Ӧ��Fragment */
        public boolean onNavigationItemSelected(int itemPosition, long itemId)
        {
            // �����Զ���Fragment
            StudentInfo student = new StudentInfo();
            FragmentManager manager = getFragmentManager();
//            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            // ��Activity�е������滻�ɶ�Ӧѡ���Fragment
            transaction.replace(R.id.container, student, listNames[itemPosition]);
            transaction.commit();
            return true;
        }
    }
	
	
	private void initActy(){
		SpinnerAdapter adapter = ArrayAdapter.createFromResource(this, R.array.action_list, android.R.layout.simple_spinner_dropdown_item);
	
		android.app.ActionBar actionBar = getActionBar();
        // ��ActionBar�Ĳ���ģ������ΪNAVIGATION_MODE_LIST
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        // ΪActionBar���������˵��ͼ�����
        actionBar.setListNavigationCallbacks(adapter, new DropDownListenser());
	}
	
}
