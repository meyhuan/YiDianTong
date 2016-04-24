package com.xiaoya.yidiantong.view;
import java.util.ArrayList;

import android.content.*;
import android.graphics.*;
import android.text.*;
import android.util.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;
/**
 * Author: meyu
 * Date:   16/4/24
 * Email:  627655140@qq.com
 */


public class ScrollTextView extends TextView implements View.OnClickListener {

    private static ArrayList<ScrollTextView> list = new ArrayList<ScrollTextView>();

    // scrolling feature
    private Scroller mSlr;

    // milliseconds for a round of scrolling
    private int mRndDuration = 8000;

    // the X offset when paused
    private int mXPaused = 0;

    // whether it's being paused
    private boolean mPaused = true;

    // 선택된 상태인지 확인함
    private boolean selection = false;
    /*
    * constructor
    */
    public ScrollTextView(Context context) {
        this(context, null);
        // customize the TextView
        setSingleLine();
        setEllipsize(null);
        setVisibility(VISIBLE);
    }

    /*
    * constructor
    */
    public ScrollTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);
        // customize the TextView
        setSingleLine();
        setEllipsize(null);
        setVisibility(VISIBLE);
    }

    /*
    * constructor
    */
    public ScrollTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // customize the TextView
        setSingleLine();
        setEllipsize(null);
        setVisibility(VISIBLE);
        this.setOnClickListener(this);
        list.add(this);
    }

    /**
     * begin to scroll the text from the original position
     */
    public void startScroll() {
        setSelection(true);
        mPaused = true;
        resumeScroll();
        //Log.d("ScrollTextView", "startScroll()-----------------------------");
    }

    /**
     * resume the scroll from the pausing point
     */
    public void resumeScroll() {

        // Do not know why it would not scroll sometimes
        // if setHorizontallyScrolling is called in constructor.
        setHorizontallyScrolling(true);

        // use LinearInterpolator for steady scrolling
        mSlr = new Scroller(this.getContext(), new LinearInterpolator());
        mSlr.setFinalX(0);
        mSlr.setFinalY(0);
        setScroller(mSlr);
        if(!needScroll())return;
        int scrollingLen = calculateScrollingLen();
        int distance = scrollingLen - (getWidth() + mXPaused);
        int duration = (new Double(mRndDuration * distance * 1.00000 / scrollingLen)).intValue();

        mSlr.startScroll(0, 0, distance, 0, duration);
        mPaused = false;
        setSelection(true);
        //Log.d("ScrollTextView", "resumeScroll()-----------------------------");
    }

    /** 텍스트뷰의 크기보다 그 안에 들어가는 텍스트내용이 더 크다면 스크롤이 필요한 경우이다*/
    private boolean needScroll(){
        TextPaint tp = getPaint();
        Rect rect = new Rect();
        String strTxt = getText().toString();
        tp.getTextBounds(strTxt, 0, strTxt.length(), rect);
        return rect.width() > getWidth();
    }

    /**
     * calculate the scrolling length of the text in pixel
     *
     * @return the scrolling length in pixels
     */
    private int calculateScrollingLen() {
        TextPaint tp = getPaint();
        Rect rect = new Rect();
        String strTxt = getText().toString();
        tp.getTextBounds(strTxt, 0, strTxt.length(), rect);
        int scrollingLen = rect.width() + getWidth();
        rect = null;
        return scrollingLen;
    }

    /**
     * pause scrolling the text
     */
    public void pauseScroll() {
        if(mSlr!=null) mSlr.abortAnimation();
        mSlr = new Scroller(this.getContext(), new LinearInterpolator());
        setScroller(mSlr);
        mSlr.startScroll(0, 0, 0, 0, 0);
        mPaused = true;
        setSelection(false);
    }

    @Override
/*
* override the computeScroll to restart scrolling when finished so as that
* the text is scrolled forever
*/
    public void computeScroll() {
        super.computeScroll();

        if (null == mSlr) return;

        if (mSlr.isFinished() && (!mPaused)) {
            this.startScroll();
        }
    }

    public int getRndDuration() {
        return mRndDuration;
    }

    public void setRndDuration(int duration) {
        this.mRndDuration = duration;
    }

    public boolean isPaused() {
        return mPaused;
    }

    public boolean isSelection(){
        return selection;
    }

    public void setmPaused(boolean mPaused) {
        this.mPaused = mPaused;
    }

    public void setSelection(boolean selection) {
        this.selection = selection;
        Log.d("selected status changed", ""+selection);
    }

    @Override
    public void onClick(View v) {
        ScrollTextView target = (ScrollTextView)v;
        for(int i=0;i<list.size();i++){
            ScrollTextView stv = list.get(i);
            if(stv!=target){
                stv.pauseScroll();
                stv.setSelection(false);
                stv.mPaused = true;
                stv.setBackgroundColor(Color.WHITE);
            }
        }
        if(!isSelection()){//아직 선택되지 않은 텍스트를  클릭한 경우
            this.setSelection(true);
            this.mPaused = false;
            this.setBackgroundColor(Color.rgb(251,184,6));
            startScroll();
        }else {// 선택된 텍스트를 다시 클릭한 경우....
            String str = this.getText().toString();
            //Log.i("Scroll Text Selected", "=================>"+str);
            //Intent mapIntent = new Intent(v.getContext(), MapViewer.class);
            //mapIntent.putExtra("addressKey", str);
            //v.getContext().startActivity(mapIntent);
            this.pauseScroll();
            //this.setBackgroundColor(Color.WHITE);
            this.setSelection(false);
            this.mPaused = true;
        }
    }
}