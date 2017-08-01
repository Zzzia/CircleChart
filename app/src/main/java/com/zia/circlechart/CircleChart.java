package com.zia.circlechart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

import java.util.List;

/**
 * Created by zia on 17-8-1.
 */

public class CircleChart extends android.support.v7.widget.AppCompatTextView {

    private boolean isLog = false;
    private Paint paint;//画笔
    private int paintWidth = 30;//线宽
    private int speed = 1;//移动速度
    private int rotate = 0;//转动角度
    private boolean isRun = false;
    private List<ChartData> list;//数据
    private int space = 100;//设置为自动计算间距，此项废弃
    private float centerX,centerY;
    static int defaultColor = Color.RED,defaultTextColor = Color.BLACK;


    public CircleChart(Context context) {
        super(context);
    }

    public CircleChart(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CircleChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();

    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if(isRun) {
            int count = 0;
            int space = (getWidth()/2-10)/list.size();
            for(ChartData data : list){
                if(data.getRadius() == 0){
                    data.setRadius(getWidth()/2-20-space*count);
                    count++;
                }
                //drawArc(canvas, data.getPercentage(), data.getColor()+200, data.getRadius()+3,data.getSpeed());
                drawArc(canvas, data.getPercentage(), data.getColor(), data.getRadius(),data.getSpeed());
                drawText(canvas,data.getText(),paintWidth,data.getTextColor(),data.getRadius());
            }
        }
    }

    /**
     * 画圆方法封装
     * @param canvas 画布
     * @param percentage 百分比
     * @param color 圆圈颜色
     * @param radius 半径
     * @param s 速度
     */
    private void drawArc(Canvas canvas, float percentage, int color, float radius, int s){
        centerX = getWidth()/2;
        centerY = getHeight()/2;
        RectF oval = new RectF( centerX-radius, centerY-radius, centerX+radius, centerY+radius);//用一个正方形包裹圆形
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(paintWidth);//描边宽度
        paint.setAntiAlias(true);//抗锯齿
        paint.setDither(true);//防抖动
        paint.setColor(color);
        if((float)rotate/360*100 < percentage){//还没到达终点
            rotate = rotate + s;
            canvas.drawArc(oval,-90,rotate,false,paint);
            log(rotate+"");
            invalidate();
        }else{//到达终点
            canvas.drawArc(oval,-90,percentage/100*360,false,paint);
        }
    }

    private void drawText(Canvas canvas,String text,int textSize,int color,float radius){
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(textSize);
        canvas.drawText(text,centerX-paint.measureText(text)-15,centerY-radius+10,paint);
    }

    public void setData(List<ChartData> list){
        this.list = list;
    }

    public void setDefaultColor(int color){
        defaultColor = color;
    }

    public void setDefaultTextColor(int color){
        defaultTextColor = color;
    }

    /**
     * 设置速度，默认为1，每秒走1/6圆
     * 若设置为2，每秒走1/3圈
     * 公式 6/速度
     * @param speed 速度
     */
    public void setSpeed(int speed){
        this.speed = speed;
    }

    /**
     * 设置圆圈半径粗细
     * @param width 宽度
     */
    public void setPaintWidth(int width){
        paintWidth = width;
    }

    /**
     * 开始动画
     */
    public void run(){
        isRun = true;
    }

    public void openLog(){
        isLog = true;
    }

    private void log(String msg){
        if(isLog) Log.d("CircleChartTest",msg);
    }
}

class ChartData{
    private int percentage = 0;
    private int color = CircleChart.defaultColor;
    private int textColor = CircleChart.defaultTextColor;
    private int speed = 1;
    private String text = "data";
    private float radius = 0;

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
