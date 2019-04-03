package com.lidiwo.alipayutil;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.service.quicksettings.TileService;

import java.net.URISyntaxException;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2019/4/2 17:37
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public class AliPayUtil {

    //支付宝包名
    private static final String ALIPAY_PACKAGE_NAME = "com.eg.android.AlipayGphone";

    //支付宝扫一扫
    private static final String ALIPAY_QR_CODE_SCAN = "alipayqr://platformapi/startapp?saId=10000007";

    //支付宝收钱码
    private static final String ALIPAY_MONEY_CODE = "alipays://platformapi/startapp?appId=20000123";

    //支付宝转账
    private static final String ALIPAY_TRANSFER = "alipayqr://platformapi/startapp?saId=20000116";

    //支付宝付款码
    private static final String ALIPAY_CODE = "alipayqr://platformapi/startapp?saId=20000056";

    //打开支付宝付款页面（带收款人信息）
    private static final String ALIPAY_PAY_MONEY = "intent://platformapi/startapp?saId=10000007&clientVersion=3.7.0.0718&qrcode=https://qr.alipay.com/#{@}#?_s=web-other&_t=1472443966571#Intent;scheme=alipayqr;package=com.eg.android.AlipayGphone;end";

    //支付宝红包
    private static final String ALIPAY_RED_PACKET = "intent://platformapi/startapp?saId=10000007&clientVersion=3.7.0.0718&qrcode=https://qr.alipay.com/#{@}#?_s=web-other&_t=1472443966571#Intent;scheme=alipayqr;package=com.eg.android.AlipayGphone;end";

    private AliPayUtil() {
    }

    private static class Holder {
        private static final AliPayUtil INSTANCE = new AliPayUtil();
    }

    public static AliPayUtil getInstance() {
        return Holder.INSTANCE;
    }


    /**
     * 打开支付宝扫一扫界面
     *
     * @param context
     * @return
     */

    public boolean openAliPayQrCodeScan(Context context) {
        try {
            operationAliPayOne(context, ALIPAY_QR_CODE_SCAN);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * 打开支付宝付款码页面
     *
     * @param context
     * @return
     */
    public boolean openAliPayCode(Context context) {
        try {
            operationAliPayOne(context, ALIPAY_CODE);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 打开支付宝转账页面
     * @param context
     * @return
     */
    public boolean openAliPayTransfer(Context context){
        try {
            operationAliPayOne(context, ALIPAY_TRANSFER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 打开支付宝收钱码
     * @param context
     * @return
     */
    public boolean openAliPayCollectMoney(Context context){
        try {
            operationAliPayOne(context, ALIPAY_MONEY_CODE);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private void operationAliPayOne(Context context, String uriString) {
        Uri uri = Uri.parse(uriString);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        if (context instanceof TileService) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                ((TileService) context).startActivityAndCollapse(intent);
            }
        } else {
            context.startActivity(intent);
        }
    }

    /**
     * 打开支付宝付款页面(带收钱人信息)
     *
     * @param context
     * @return
     */
    public boolean openAliPayment(Context context, String payCode) {
        try {
            operationAliPayTwo(context, ALIPAY_PAY_MONEY, payCode);
            return true;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return false;
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 一键领取支付宝红包
     *
     * @param context
     * @param redPacketCode
     * @return
     */
    @Deprecated
    public boolean openRedPacket(Context context, String redPacketCode) {
        try {
            operationAliPayTwo(context, ALIPAY_RED_PACKET, redPacketCode);
            return true;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return false;
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void operationAliPayTwo(Context context, String uriString, String codeString) throws URISyntaxException {
        Intent intent = Intent.parseUri(uriString.replace("#{@}#", codeString), Intent.URI_INTENT_SCHEME);
        context.startActivity(intent);
    }


    /**
     * 手机是否安装支付宝
     *
     * @param context
     * @return
     */
    public  boolean isInstalledAliPay(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(ALIPAY_PACKAGE_NAME, 0);
            return info != null;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取安装的支付宝版本
     * @param context
     * @return
     */
    public String getAliPayVersion(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(ALIPAY_PACKAGE_NAME, 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
