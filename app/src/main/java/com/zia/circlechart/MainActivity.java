package com.zia.circlechart;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ChartData> datas = new ArrayList<>();
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        final CircleChart circleChart = (CircleChart)findViewById(R.id.chart);
        circleChart.openLog();
        //circleChart.setPaintWidth(30);//设置线宽默认为70
        //circleChart.setSpeed(2);//设置默认速度为1.具体计算见代码注释这里设置为所有圆的速度，可单独设置
        //circleChart.setDefaultColor(Color.parseColor("#bfbfbf"));//设置默认线颜色，可单独设置
        //circleChart.setDefaultTextColor(Color.parseColor("#bfbfbf"));//设置默认字体颜色，可单独设置
        circleChart.setSpace(90);//设置间距，默认自动计算间距
        circleChart.setData(datas);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //开始动画，在这之前不会动
                circleChart.run();
            }
        });
    }

    private void initData(){
        ChartData data1 = new ChartData();
        data1.setText("75%");//黄色
        data1.setPercentage(75);
        data1.setBackgroundStrokeColor(Color.parseColor("#FFFBF1"));
        data1.setBackgroundColor(Color.parseColor("#FFFFFC"));
        data1.setColor(Color.parseColor("#FBFDBA"));
        data1.setStrokeColor(Color.parseColor("#EED66D"));
        ChartData data2 = new ChartData();
        data2.setText("60%");//绿色
        data2.setPercentage(60);
        data2.setBackgroundStrokeColor(Color.parseColor("#ECFFFD"));
        data2.setBackgroundColor(Color.parseColor("#F9FEFF"));
        data2.setColor(Color.parseColor("#9EFBEF"));
        data2.setStrokeColor(Color.parseColor("#74EAD9"));
        ChartData data3 = new ChartData();
        data3.setText("50%");
        data3.setPercentage(50);//蓝色
        data3.setBackgroundStrokeColor(Color.parseColor("#D4F6FF"));
        data3.setBackgroundColor(Color.parseColor("#F9FBFF"));
        data3.setColor(Color.parseColor("#BAE5FF"));
        data3.setStrokeColor(Color.parseColor("#7CC8F1"));
        datas.add(data1);
        datas.add(data2);
        datas.add(data3);
        button = (Button)findViewById(R.id.button);
    }
}
