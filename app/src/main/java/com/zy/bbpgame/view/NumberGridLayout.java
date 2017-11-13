package com.zy.bbpgame.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

/**
 * Created by ZhaoYue on 2017/11/12.
 */
public class NumberGridLayout extends GridLayout {
    private static final String TAG = "NumberGridLayout";

    public NumberGridLayout(Context context) {
        this(context, null);
    }

    public NumberGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
    }

    public NumberGridLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void fillAll(int rowCount,int columnCount){
        setRowCount(rowCount);
        setColumnCount(columnCount);
        fillAll();
    }

    public void fillAll() {
        removeAllViews();
        for (int i = 0; i < getColumnCount() * getRowCount(); i++) {
            OneNumberView one = new OneNumberView(getContext());
//            addView(one,-1);
            Log.i(TAG, "fillAll: i:" + i);
            addView(one, getWidth()/getColumnCount(),getHeight()/getRowCount());
        }
        requestLayout();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        int cw = getMeasuredWidth()/getRowCount();
//        int ch = getMeasuredHeight()/getColumnCount();
//
//        int childCount = getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
//            params.width = 0;
//            params.height = ch;
//            OneNumberView one = (OneNumberView) getChildAt(i);
//            one.setLayoutParams(params);
//            one.requestLayout();
//            measureChild(one,MeasureSpec.AT_MOST,MeasureSpec.AT_MOST);
//        }
//        if (getChildCount() == 0 && w>0&&h>0){
//            fillAll();
//        }
    }
}
