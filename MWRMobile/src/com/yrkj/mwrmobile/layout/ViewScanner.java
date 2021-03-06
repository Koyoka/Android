package com.yrkj.mwrmobile.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.mwrmobile.R;


public class ViewScanner extends BaseLayout{

    public static final int BtnScannerBackId = R.id.btnScannerBack;
    public static final int TxtScannerTitleId = R.id.txtScannerTitle;

    protected android.widget.Button mBtnScannerBack;
    protected android.widget.TextView mTxtScannerTitle;

    protected Activity mCurActy;

    public ViewScanner(Activity acty){
        mCurActy = acty;
        mBtnScannerBack = (android.widget.Button) acty.findViewById(BtnScannerBackId);
        mTxtScannerTitle = (android.widget.TextView) acty.findViewById(TxtScannerTitleId);
    }   

    public ViewScanner(android.view.View acty){
        mBtnScannerBack = (android.widget.Button) acty.findViewById(BtnScannerBackId);
        mTxtScannerTitle = (android.widget.TextView) acty.findViewById(TxtScannerTitleId);
    }   
    public android.widget.Button getBtnScannerBack() {
        return mBtnScannerBack;
    }
    public android.widget.TextView getTxtScannerTitle() {
        return mTxtScannerTitle;
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
                case BtnScannerBackId:
                    setViewData(adp,getBtnScannerBack(),data,joinData.formatString,joinData.data);
                    break;
                case TxtScannerTitleId:
                    setViewData(adp,getTxtScannerTitle(),data,joinData.formatString,joinData.data);
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
                case BtnScannerBackId:
                    setViewData(adp,getBtnScannerBack(),data,"",path);
                    break;
                case TxtScannerTitleId:
                    setViewData(adp,getTxtScannerTitle(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
