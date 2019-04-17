package com.example.module_base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.example.module_base.R;

/**
 * 天气环形指数
 */
public class WeatherProgressView extends View {

    /**外环颜色*/
    private int mOuterColor = getResources().getColor(R.color.white);
    /**内环颜色*/
    private int mInnerColor = getResources().getColor(R.color.blue1);
    /**字体颜色*/
    private int mTextColor = getResources().getColor(R.color.white);
    /**字体大小*/
    private float mTextSize = 14;
    /**圆环宽度*/
    private float mBorderWidth = 20;

    private Paint mOuterPaint,mInnerPaint,mTextPaint;

    private int mStepMax;//总进度
    private int mCurrentStep;//当前进度

    public WeatherProgressView(Context context) {
        super(context);
    }

    public WeatherProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTypeArray(context, attrs);
    }

    public WeatherProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypeArray(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width > height ? height : width, width > height ? height : width);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //1.画外圆弧   边缘没有显示完整
        //RectF recf = new RectF(0, 0, getWidth(), getHeight());

        int center = getWidth() / 2;
        int radius = (int) (getWidth() / 2 - mBorderWidth / 2);
        //mBorderWidth/2,mBorderWidth/2,getWidth()-mBorderWidth/2,getWidth()-mBorderWidth/2
        RectF recf = new RectF(center - radius,
                center - radius,
                center + radius,
                center + radius);
        canvas.drawArc(recf, 135, 270, false, mOuterPaint);
        //2.绘制内圆弧
        float sweepAngle = (float) mCurrentStep / mStepMax;
        canvas.drawArc(recf, 135, sweepAngle * 270, false, mInnerPaint);

        //3.绘制文字
        String stepText = mCurrentStep + "";
        Rect rect = new Rect();
        mTextPaint.getTextBounds(stepText,0,stepText.length(),rect);
        int dx = getWidth() / 2 - rect.width() / 2;
        //第一种方式获取高度
        //int dy = getWidth() / 2 + rect.width()/2;
        //第二种表达方式获取高度
        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
        //获取中心(fontMetrics.bottom - fontMetrics.top) / 2
        int dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        int baseLine = getHeight() / 2 + dy;
        canvas.drawText(stepText, dx, baseLine, mTextPaint);

    }

    /**初始化view属性*/
    private void initTypeArray(Context context,AttributeSet attrs){
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.WeatherProgressView);
        try {
            mOuterColor=ta.getColor(R.styleable.WeatherProgressView_outerColor,mOuterColor);
            mInnerColor=ta.getColor(R.styleable.WeatherProgressView_innerColor,mInnerColor);
            mTextColor=ta.getColor(R.styleable.WeatherProgressView_textColor,mTextColor);
            mTextSize = ta.getDimension(R.styleable.WeatherProgressView_textSize,mTextSize);
            mBorderWidth = ta.getDimension(R.styleable.WeatherProgressView_borderWidth,mBorderWidth);

            //内弧
            mOuterPaint = new Paint();
            mOuterPaint.setAntiAlias(true);
            mOuterPaint.setStrokeWidth(mBorderWidth);
            mOuterPaint.setColor(mOuterColor);
            mOuterPaint.setStrokeCap(Paint.Cap.ROUND);//设置下方为圆形
            mOuterPaint.setStyle(Paint.Style.STROKE);//设置内部为空心

            //外弧
            mInnerPaint = new Paint();
            mInnerPaint.setAntiAlias(true);
            mInnerPaint.setStrokeWidth(mBorderWidth);
            mInnerPaint.setColor(mInnerColor);
            mInnerPaint.setStrokeCap(Paint.Cap.ROUND);//设置下方为圆形
            mInnerPaint.setStyle(Paint.Style.STROKE);//设置内部为空心

            //文字
            mTextPaint = new Paint();
            mTextPaint.setAntiAlias(true);
            mTextPaint.setTextSize(mTextSize);
            mTextPaint.setColor(mTextColor);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //不论结果如何 array对象必须释放回收 避免内存泄漏
            ta.recycle();
        }
    }

    public void setStepMax(int mStepMax) {
        this.mStepMax = mStepMax;
    }

    public void setCurrentStep(int mCurrentStep) {
        this.mCurrentStep = mCurrentStep;
        //不断绘制 onDraw()
        invalidate();
    }

}
