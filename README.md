# CircleChart
圆形数据图表封装

效果如图：

<img src="https://github.com/Zzzia/Files/blob/master/gifs/CircleChart.gif" width="360" height="640" >

功能：自定义数据个数，可以单独设置每个数据的线条颜色，字体颜色，滚动速度，半径，进度。也可以统一设置以上属性，线条宽度。

特点：可以控制动画的开始时间，而不是页面一加载出来就开始跑。继承自TextView，自适应屏幕，各个线条平均分布，可以把该控件放在任意地方。

使用代码（仅需三行）：

```java
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
        //circleChart.setPaintWidth(30);//设置线宽默认为30
        //circleChart.setSpeed(2);//设置默认速度为1.具体计算见代码注释这里设置为所有圆的速度，可单独设置
        //circleChart.setDefaultColor(Color.parseColor("#bfbfbf"));//设置默认线颜色，可单独设置
        //circleChart.setDefaultTextColor(Color.parseColor("#bfbfbf"));//设置默认字体颜色，可单独设置
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
        ChartData data2 = new ChartData();
        ChartData data3 = new ChartData();
        ChartData data4 = new ChartData();
        data1.setPercentage(90);
        data1.setColor(Color.RED);
        data1.setText("妹子: 90%");
        data1.setTextColor(Color.parseColor("#FFCCCC"));
        data2.setPercentage(70);
        data2.setColor(Color.GREEN);
        data2.setText("学渣: 70%");
        data3.setPercentage(50);
        data3.setColor(Color.BLACK);
        data3.setText("好盆友: 50%");
        data4.setPercentage(30);
        data4.setColor(Color.GRAY);
        data4.setText("土豪: 30%");
        datas.add(data1);
        datas.add(data2);
        datas.add(data3);
        datas.add(data4);
        button = (Button)findViewById(R.id.button);
    }
}

```