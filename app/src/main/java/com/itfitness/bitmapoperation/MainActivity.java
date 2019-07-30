package com.itfitness.bitmapoperation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBt_reverse,mBt_gray1,mBt_gray2,mBt_gray3,mBt_removeG,mBt_removeB,mBt_removeR;
    private ImageView mImageView;
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBt_reverse = findViewById(R.id.bt_reverse);
        mBt_gray1 = findViewById(R.id.bt_gray1);
        mBt_gray2 = findViewById(R.id.bt_gray2);
        mBt_gray3 = findViewById(R.id.bt_gray3);
        mBt_removeG = findViewById(R.id.bt_removeG);
        mBt_removeB = findViewById(R.id.bt_removeB);
        mBt_removeR = findViewById(R.id.bt_removeR);
        mImageView = findViewById(R.id.img);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.shuchang);
        mBt_reverse.setOnClickListener(this);
        mBt_gray1.setOnClickListener(this);
        mBt_gray2.setOnClickListener(this);
        mBt_gray3.setOnClickListener(this);
        mBt_removeG.setOnClickListener(this);
        mBt_removeB.setOnClickListener(this);
        mBt_removeR.setOnClickListener(this);
    }

    /**
     * 灰度化1
     */
    public native void bitmapGray1(Object bitmap);

    /**
     * 灰度化2
     */
    public native void bitmapGray2(Object bitmap);

    /**
     * 灰度化3
     */
    public native void bitmapGray3(Object bitmap);

    /**
     * 反相
     */
    public native void bitmapReverse(Object bitmap);

    /**
     * 去蓝
     */
    public native void bitmapRemoveB(Object bitmap);

    /**
     * 去绿
     */
    public native void bitmapRemoveG(Object bitmap);

    /**
     * 去红
     */
    public native void bitmapRemoveR(Object bitmap);
    @Override
    public void onClick(View v) {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.shuchang);
        switch (v.getId()){
            case R.id.bt_gray1:
                bitmapGray1(mBitmap);
                break;
            case R.id.bt_gray2:
                bitmapGray2(mBitmap);
                break;
            case R.id.bt_gray3:
                bitmapGray3(mBitmap);
                break;
            case R.id.bt_removeG:
                bitmapRemoveG(mBitmap);
                break;
            case R.id.bt_removeB:
                bitmapRemoveB(mBitmap);
                break;
            case R.id.bt_removeR:
                bitmapRemoveR(mBitmap);
                break;
            case R.id.bt_reverse:
                bitmapReverse(mBitmap);
                break;
        }
        mImageView.setImageBitmap(mBitmap);
    }
}
