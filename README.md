# charge
微信和支付宝充值功能，接入到第三方apk的接口

主要了解下微信和支付宝充值功能，其实客户端操作很简单，主要功能是服务器的

1、我们先来看看支付宝的支付
   1）支付流程
   https://doc.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.OfWfWT&treeId=193&articleId=105297&docType=1
   可以参考支付宝官网流程图

   2）使用方法可以参考支付宝官网
   https://doc.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.nnOemg&treeId=193&articleId=105300&docType=1

   主要使用方法
   方法名称：payTask.pay

	方法原型：PayTask payTask = new PayTask(activity); payTask.payV2(orderInfo,true);

	方法功能：提供给商户订单支付功能。

	方法参数： 实例化PayTask，传入参数activity 的实例。

	参数名称	参数说明
	String orderInfo	app支付请求参数字符串，主要包含商户的订单信息，key=value形式，以&连接。
	boolean isShowPayLoading	用户在商户app内部点击付款，是否需要一个loading做为在钱包唤起之前的过渡，这个值设置为true，将会在调用pay接口的时候直接唤起一个loading，直到唤起H5支付页面或者唤起外部的钱包付款页面loading才消失。（建议将该值设置为true，优化点击付款到支付唤起支付页面的过渡过程。）

	注意 ：
		orderStr示例如下，参数说明见"请求参数说明",orderStr的获取必须来源于服务端：

	3)参数说明
	https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.x7kkCI&treeId=204&articleId=105465&docType=1
	详情参考官网

	主要就是2的方法调用，可以参考demo里面的实现

2、微信支付








