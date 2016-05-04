package com.smartydroid.android.starter.kit.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.smartydroid.android.starter.kit.R;
import com.smartydroid.android.starter.kit.helper.StatusBarHelper;
import com.smartydroid.android.starter.kit.helper.ToolBarHelper;

/**
 * Created by moon.zhong on 2015/6/12.
 * time : 10:26
 */
public abstract class ToolBarActivity extends AppCompatActivity {
    private ToolBarHelper mToolBarHelper ;
    public Toolbar toolbar ;
    public boolean isAddToolBar = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {

        if(isAddToolBar){
            mToolBarHelper = new ToolBarHelper(this,layoutResID) ;
            toolbar = mToolBarHelper.getToolBar() ;
            setContentView(mToolBarHelper.getContentView());
        /*自定义的一些操作*/
            onCreateCustomToolBar(toolbar) ;
        /*把 toolbar 设置到Activity 中*/
            setSupportActionBar(toolbar);
        }else {
            super.setContentView(layoutResID);
        }
        setStatusBar();
    }

    public void onCreateCustomToolBar(Toolbar toolbar){
        toolbar.setContentInsetsRelative(0,0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true ;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void setStatusBar() {
        StatusBarHelper.setColor(this, getResources().getColor(R.color.colorPrimaryDark));
    }

    public void showToastShort(int resId){
        Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show();
    }
    public void showToastLong(int resId){
        Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show();
    }


}