```
   一个Android端操作支付宝的工具
```
#### 最新版本
 [ ![Download](https://api.bintray.com/packages/lidiwo/lidiwo/alipay-util/images/download.svg) ](https://bintray.com/lidiwo/lidiwo/alipay-util/_latestVersion)

#### 一、功能介绍

1. **支持打开支付宝扫一扫**
2. **支持打开支付宝付款码和收款码页面**
3. **支持打开支付宝转账页面**
4. **支持打开支付宝付款页面(附带收款人信息)**
5. **支持一键领取支付宝红包(已失效)**


#### 二、用法

1. 添加依赖和配置
``` gradle
dependencies {

    implementation 'com.lidiwo:alipay-util:x.x.x'

    ...
}
// 旧版本gradle插件(< 2.2)，implementation 换成 compile
```

3. 使用
``` java
  if( AliPayUtil.getInstance().isInstalledAliPay(this)){
       AliPayUtil.getInstance().openAliPayment(this, "FKX01364DQGSGPGBCFYP26");
  }else{
       Toast.makeText(this,"未安装支付宝",Toast.LENGTH_SHORT).show();
  }
```

#### 三、注意事项
1. **在跳转支付宝之前最好先判断是否安装支付宝**
2. **带收款人信息到付款页面，需要收款人收款码id，获取方式通过微信扫收款人收款码获取，如下图红色部分**
![](https://github.com/lidiwo/AliPaySDK_Personal/blob/master/image_01.jpg?raw=true)





#### 四、问题反馈

 如果发现有使用问题，可以给我发邮件kolan9527@126.com