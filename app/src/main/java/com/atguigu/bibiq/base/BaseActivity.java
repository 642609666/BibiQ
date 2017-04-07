package com.atguigu.bibiq.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import com.atguigu.bibiq.app.AppManager;
import com.atguigu.bibiq.search.SearchActivity;
import com.atguigu.bibiq.utils.ConstantAddress;
import com.atguigu.bibiq.utils_search.IOnSearchClickListener;
import com.atguigu.bibiq.utils_search.SearchFragment;
import com.atguigu.bibiq.zxing.android.CaptureActivity;
import com.atguigu.bibiq.zxing.camera.BarcodeEncoder;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.ButterKnife;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/20 0020.
 * 功能:
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
    public static final int REQUEST_CODE_SCAN = 0x0000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //实现抽象方法由子类去自己设置布局
        setContentView(getLayoutId());

        //butterknife绑定
        ButterKnife.inject(this);

        //初始化数据
        initData();
        //设置监听
        initListener();
    }


    /**
     * 由子类设置布局文件
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 1.由子类初始化数据
     * 2.由子类联网下载数据
     */
    protected abstract void initData();

    /**
     * 设置监听效果
     */
    protected abstract void initListener();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //butterknife解绑
        ButterKnife.reset(this);
        OkHttpUtils.delete().tag(this);
        AppManager.getInstance().remove(this);
    }

    /**
     * 显示搜索框
     */
    public void isShowSearch() {

        //实例化
        SearchFragment searchFragment = SearchFragment.newInstance();
        //搜索框内容回调
        searchFragment.setOnSearchClickListener(new IOnSearchClickListener() {
            @Override
            public void OnSearchClick(String keyword) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                //这里处理逻辑
                Intent intent = new Intent(BaseActivity.this, SearchActivity.class);
                intent.putExtra("url", ConstantAddress.SEARCH_HAND + keyword + ConstantAddress.SEARCH_TAIL);
                intent.putExtra("name", keyword);
                startActivity(intent);

            }

            //二维码回调
            @Override
            public void OnScanClick() {
              //  InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
              //  imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                Log.e("TAG", "二维码");
                //这里处理逻辑
                Intent intent = new Intent(BaseActivity.this,
                        CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SCAN);
            }
        });
        //显示
        searchFragment.show(getSupportFragmentManager(), SearchFragment.TAG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);

                Log.e("TAG", "content==" + content);
            }
        }
    }


    //以下这个方法，传入一个字符串，生成一个二维码的Bitmap，可以用于显示

    Bitmap encodeAsBitmap(String str) {
        Bitmap bitmap = null;
        BitMatrix result = null;
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            result = multiFormatWriter.encode(str, BarcodeFormat.QR_CODE, 200, 200);
            // 使用 ZXing Android Embedded 要写的代码
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            bitmap = barcodeEncoder.createBitmap(result);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException iae) { // ?
            return null;
        }
        // 如果不使用 ZXing Android Embedded 的话，要写的代码

//        int w = result.getWidth();
//        int h = result.getHeight();
//        int[] pixels = new int[w * h];
//        for (int y = 0; y < h; y++) {
//            int offset = y * w;
//            for (int x = 0; x < w; x++) {
//                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
//            }
//        }
//        bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
//        bitmap.setPixels(pixels,0,100,0,0,w,h);

        return bitmap;
    }
}
