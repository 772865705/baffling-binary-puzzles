package com.zy.bbpgame.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.zy.bbpgame.R;

/**
 * Created by Zhaoyue on 2017/11/10 0010.
 */

public class OneNumberView extends View implements View.OnClickListener, View.OnLongClickListener {

    public OneNumberView(Context context) {
        this(context, null);
    }

    public OneNumberView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (attrs != null)
            initAttr(attrs);
        init();
    }

    public static final int STATE_0 = 0;
    public static final int STATE_1 = 1;
    public static final int STATE_NULL = 2;

    private int status = STATE_NULL;
    private boolean isDevelop = false; //是否正在设计题目
    private boolean isPre = false;//是否是预设

    private Paint mPaint;

    private void init() {
        setClickable(true);
        setOnClickListener(this);
        setOnLongClickListener(this);
        mPaint = new Paint();
    }

    private void initAttr(@NonNull AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.OneNumberView);
        isDevelop = ta.getBoolean(R.styleable.OneNumberView_isDevelop,isDevelop);
        isPre = ta.getBoolean(R.styleable.OneNumberView_isPre,isPre);
        status = ta.getInt(R.styleable.OneNumberView_status,status);
        ta.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //预设数据会有灰色背景填充
        if (isPre) {
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(Color.GRAY);
            mPaint.setStrokeWidth(10);
            canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
        }


        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(isDevelop ? Color.RED : Color.BLACK);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);

        //根据是否是预设数据选择文字颜色
        mPaint.setColor(isPre ? Color.BLACK : Color.RED);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(getWidth() / 2);

        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float fontTotalHeight = fontMetrics.bottom - fontMetrics.top;
        float offY = fontTotalHeight / 2 - fontMetrics.bottom;
        float newY = getHeight() / 2 + offY;
//            LogUtils.e("w:" + getWidth() / 2 + " h:" + getHeight() / 2);
//            canvas.drawText(name,getWidth()/2, getHeight()*1.25f/2,paint);
        //参数2：文字中心位置X坐标（相对View）
        //参数3：文字BaseLine Y值（相对View）
        canvas.drawText(getInsideText(), getWidth() / 2, newY, mPaint);
    }

    private String getInsideText() {
        switch (status) {
            case STATE_0:
                return "0";
            case STATE_1:
                return "1";
            default:
                return "";
        }
    }

    /**
     * 判断当前是否可以单击一次进行改写
     *
     * @return true ifCanBeWritten
     */
    public boolean checkCanWrite() {
        return isDevelop || !isPre;
    }

    @Override
    public void onClick(View v) {
        if (!checkCanWrite()) {
            Toast.makeText(getContext(), "想作弊？不存在的~", Toast.LENGTH_SHORT).show();
            return;
        }
        switch (status) {
            case STATE_0:
                status = STATE_1;
                break;
            case STATE_1:
                status = STATE_NULL;
                break;
            case STATE_NULL:
                status = STATE_0;
                break;
        }
        invalidate();
    }

    @Override
    public boolean onLongClick(View v) {
        Toast.makeText(getContext(), "按我这么久干嘛，我又没有超能力~", Toast.LENGTH_SHORT).show();
        return true;
    }

    public void player2developer() {
        isDevelop = true;
        isPre = true;
        postInvalidate();
    }

    public void developer2player() {
        isPre = status != STATE_NULL;
        isDevelop = false;
        postInvalidate();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isDevelop() {
        return isDevelop;
    }

    public void setDevelop(boolean develop) {
        isDevelop = develop;
    }

    public boolean isPre() {
        return isPre;
    }

    public void setPre(boolean pre) {
        isPre = pre;
    }

    public interface NumberChangeListener{
        void onNumberChange(int oldState,int newState);
    }
}
