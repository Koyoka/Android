package com.yrkj.mwrmobile.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.mwrmobile.R;


public class ActivityFragment extends BaseLayout{

    public static final int ContainerId = R.id.container;

    protected android.widget.RelativeLayout mContainer;

    protected Activity mCurActy;

    public ActivityFragment(Activity acty){
        mCurActy = acty;
        mContainer = (android.widget.RelativeLayout) acty.findViewById(ContainerId);
    }   

    public ActivityFragment(android.view.View acty){
        mContainer = (android.widget.RelativeLayout) acty.findViewById(ContainerId);
    }   
    public android.widget.RelativeLayout getContainer() {
        return mContainer;
    }

    public void bindData(LayoutDataAdapter adp,BaseBean data){
        if(adp == null || data == null){
            return;
        }
        
        if(adp.BindJoinFiledList != null){
            java.util.Iterator iter = adp.BindJoinFiledList.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                Object key = entry.getKey();
                Object val = entry.getValue();
                int viewKey = (Integer) key;
                LayoutDataAdapter.JoinData joinData = (LayoutDataAdapter.JoinData) val;
                
                switch (viewKey) {
                case ContainerId:
                    setViewData(adp,getContainer(),data,joinData.formatString,joinData.data);
                    break;
                }
            }
        }
        
        if(adp.BindFiledList != null){
            java.util.Iterator iter = adp.BindFiledList.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                Object key = entry.getKey();
                Object val = entry.getValue();
                int viewKey = (Integer) key;
                String path = (String) val;
                switch (viewKey) {
                case ContainerId:
                    setViewData(adp,getContainer(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
