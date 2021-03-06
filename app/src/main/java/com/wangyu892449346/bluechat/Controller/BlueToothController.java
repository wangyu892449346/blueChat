package com.wangyu892449346.bluechat.Controller;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyu892449346 on 4/13/17.
 * 处理蓝牙逻辑
 */
public class BlueToothController {

    private BluetoothAdapter mAapter;

    public BlueToothController() {
        //创建蓝牙对象BluetoothAdapter
        mAapter = BluetoothAdapter.getDefaultAdapter();
    }

    public BluetoothAdapter getAdapter() {
        return mAapter;
    }

    /**
     * 打开蓝牙
     *
     * @param activity
     * @param requestCode
     */
    public void turnOnBlueTooth(Activity activity, int requestCode) {
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        activity.startActivityForResult(intent, requestCode);
        //不推荐使用BluetoothAdapter.enable()方法打开蓝牙，该方法一般有系统调用
//        mAdapter.enable();
    }

    /**
     * 打开蓝牙可见性，系统会发出广播
     *
     * @param context
     */
    public void enableVisibly(Context context) {
        Intent discoverableIntent = new
                Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        context.startActivity(discoverableIntent);
    }

    /**
     * 查找设备，系统会发出广播
     */
    public void findDevice() {
        assert (mAapter != null);
        mAapter.startDiscovery();
    }

    /**
     * 获取绑定设备
     *
     * @return
     */
    public List<BluetoothDevice> getBondedDeviceList() {
        return new ArrayList<>(mAapter.getBondedDevices());
    }
}
