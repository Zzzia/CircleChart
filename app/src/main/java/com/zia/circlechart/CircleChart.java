package com.zia.circlechart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by zia on 17-8-1.
 */

public class CircleChart extends android.support.v7.widget.AppCompatTextView {

    private boolean isLog = true;
    private Paint paint;//画笔
    private float radius = 100;//半径
    private float pointWidth = 100;//原点宽度
    private float spaceWidth = 20;//间隔宽度
    private int speed = 1;//移动速度
    private float centerPointX;//圆心x位置
    private float centerPointY;
    private int rotate = 0;//转动


    public CircleChart(Context context) {
        super(context);
    }

    public CircleChart(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CircleChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);//描边宽度
        paint.setAntiAlias(true);//抗锯齿
        paint.setDither(true);//防抖动

    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        new Thread(new Runnable() {
            @Override
            public void run() {
                drawArc(canvas,50,Color.RED,100);
                drawArc(canvas,80,Color.GREEN,200);
            }
        }).run();
    }

    void drawArc(Canvas canvas, float percentage, int color, float radius){
        float centerX = getWidth()/2;
        float centerY = getHeight()/2;
        RectF oval = new RectF( centerX-radius, centerY-radius, centerX+radius, centerY+radius);//用一个正方形包裹圆形
        paint.setColor(color);
        if((float)rotate/360*100 < percentage){//还没到达终点
            rotate++;
            canvas.drawArc(oval,-90,rotate,false,paint);
            log(rotate+"");
        }else{//到达终点
            canvas.drawArc(oval,-90,percentage/100*360,false,paint);

        }
        invalidate();
    }



    void log(String msg){
        if(isLog) Log.d("CircleChartTest",msg);
    }
}
