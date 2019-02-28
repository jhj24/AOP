package com.jhj.aop;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jhj.aop.annotation.Async;
import com.jhj.aop.annotation.Main;
import com.jhj.aop.annotation.Permissions;
import com.jhj.aop.annotation.TimeStatistics;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private String[] phoneState = new String[]{Manifest.permission.READ_PHONE_STATE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        Button btnPermissions = findViewById(R.id.btn_permissions);
        Button btnStatistics = findViewById(R.id.btn_statistics);
        textView = findViewById(R.id.textView);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                print();
            }
        });

        btnPermissions.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        request();
                    }
                }
        );

        btnStatistics.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        statistics();
                    }
                }
        );


        //一步方法不能返回同步返回值
        //RetentionPolicy.CLASS 不允许反射 （使用反射时，用 RetentionPolicy.RUNTIME）
        //大量反射影响性能，但Aspectj不是反射
        //gradle 插件开发

        // 将业务逻辑和类不相关的通用功能分离出来，让多个类共享一个行为，一旦这个行为发生该白，不必修改类，而只修改这个行为即可

        /**
         * AOP 思想：
         * 日志
         * 持久化
         * 线程切换
         * 参数效验与判空
         * 动态权限
         * 埋点统计
         * 性能统计
         * 缓存
         */
    }

    @TimeStatistics
    private void statistics() {
        for (int i=0;i<10000000;i++){

        }
    }


    @Async
    private void print() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1000);
                a(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Main
    private void a(int i) {
        textView.setText(i + "");
    }

    @Permissions({Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA})
    public void request() {
        textView.setText("111");
    }

}
