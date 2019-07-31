package com.itfitness.bitmapoperation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBt_reverse,mBt_gray1,mBt_gray2,mBt_gray3,mBt_removeG,mBt_removeB,mBt_removeR;
    private ImageView mImageView;
    private SeekBar mSeek_brightness,mSeek_contrast;
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
        mSeek_brightness = findViewById(R.id.seek_brightness);
        mSeek_contrast = findViewById(R.id.seek_contrast);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.shuchang);
        mBt_reverse.setOnClickListener(this);
        mBt_gray1.setOnClickListener(this);
        mBt_gray2.setOnClickListener(this);
        mBt_gray3.setOnClickListener(this);
        mBt_removeG.setOnClickListener(this);
        mBt_removeB.setOnClickListener(this);
        mBt_removeR.setOnClickListener(this);
        //亮度
        mSeek_brightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.shuchang);
                int brightness = progress-50;
                bitmapBrightness(mBitmap,brightness);
                mImageView.setImageBitmap(mBitmap);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //对比度
        mSeek_contrast.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.shuchang);
                float contrast = progress/100f;
                bitmapContrast(mBitmap,contrast);
                mImageView.setImageBitmap(mBitmap);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    /**
     * 亮度
     * @param bitmap
     */
    public native void bitmapBrightness(Object bitmap,int brightness);

    /**
     * 对比度
     * @param bitmap
     */
    public native void bitmapContrast(Object bitmap,float contrast);
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
