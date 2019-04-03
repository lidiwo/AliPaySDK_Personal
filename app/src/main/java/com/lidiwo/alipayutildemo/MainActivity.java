package com.lidiwo.alipayutildemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lidiwo.alipayutil.AliPayUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv_alipay = findViewById(R.id.tv_alipay);
        TextView tv_alipay_version = findViewById(R.id.tv_alipay_version);

        if( AliPayUtil.getInstance().isInstalledAliPay(this)){
            tv_alipay.setText("是否安装支付宝：已安装");
            tv_alipay_version.setVisibility(View.VISIBLE);
            tv_alipay_version.setText("支付宝版本："+AliPayUtil.getInstance().getAliPayVersion(this));

        }else{
            tv_alipay.setText("是否安装支付宝：未安装");
            tv_alipay_version.setVisibility(View.GONE);
        }
    }

    public void aliPayScan(View view) {
        if( AliPayUtil.getInstance().isInstalledAliPay(this)){
            AliPayUtil.getInstance().openAliPayQrCodeScan(this);
        }else{
            Toast.makeText(this,"未安装支付宝",Toast.LENGTH_SHORT).show();
        }
    }

    public void aliPayCode(View view) {
        if( AliPayUtil.getInstance().isInstalledAliPay(this)){
            AliPayUtil.getInstance().openAliPayCode(this);
        }else{
            Toast.makeText(this,"未安装支付宝",Toast.LENGTH_SHORT).show();
        }
    }


    public void openAliPayment(View view) {
        if( AliPayUtil.getInstance().isInstalledAliPay(this)){
            AliPayUtil.getInstance().openAliPayment(this, "FKX01364DQGSGPGBCFYP26");
        }else{
            Toast.makeText(this,"未安装支付宝",Toast.LENGTH_SHORT).show();
        }
    }

    public void openAliPayTransfer(View view) {
        if( AliPayUtil.getInstance().isInstalledAliPay(this)){
            AliPayUtil.getInstance().openAliPayTransfer(this);
        }else{
            Toast.makeText(this,"未安装支付宝",Toast.LENGTH_SHORT).show();
        }
    }

    public void openAliPayCollectMoney(View view) {
        if( AliPayUtil.getInstance().isInstalledAliPay(this)){
            AliPayUtil.getInstance().openAliPayCollectMoney(this);
        }else{
            Toast.makeText(this,"未安装支付宝",Toast.LENGTH_SHORT).show();
        }
    }

    public void openAliPayRedPacket(View view) {
        if( AliPayUtil.getInstance().isInstalledAliPay(this)){
            AliPayUtil.getInstance().openRedPacket(this, "c1x00547gae01wb7nqnqj2a");
        }else{
            Toast.makeText(this,"未安装支付宝",Toast.LENGTH_SHORT).show();
        }
    }
}
