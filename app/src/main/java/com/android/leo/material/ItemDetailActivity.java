package com.android.leo.material;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by leo on 15/12/27.
 */
public class ItemDetailActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView img;
    private String title;
    public static final String TITLE_TO_SHOW = "title";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        title = intent.getStringExtra(TITLE_TO_SHOW);
        setContentView(R.layout.item_detail_activity);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        img = (ImageView) findViewById(R.id.img);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
        CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        mCollapsingToolbarLayout.setTitle(title);
        //通过CollapsingToolbarLayout修改字体颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色
        // imageView是一个ImageView实例
        // ImageLoader.getImageListener的第二个参数是默认的图片resource id
        // 第三个参数是请求失败时候的资源id，可以指定为0
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        ImageLoader imageLoader = new ImageLoader(queue, new BitmapLruCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(img, android.R.drawable.ic_menu_rotate, android.R.drawable.ic_delete);
        imageLoader.get("http://img008.hc360.cn/g6/M04/2C/C8/wKhQsFMx9uCEcqgyAAAAAOyuQ1g828.jpg", listener);
    }
}
