package com.hu.video.controller;
import cn.hutool.json.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.hu.video.config.AlipayConfig;
import com.hu.video.entity.AliPay;

import com.hu.video.entity.Order;
import com.hu.video.mapper.OrderMapper;
import com.hu.video.mapper.TUserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/alipay")
public class AliPayController {
    private static final String GATEWAY_URL = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
    private static final String FORMAT = "JSON";
    private static final String CHARSET = "UTF-8";
    //签名方式
    private static final String SIGN_TYPE = "RSA2";

    @Resource
    private AlipayConfig aliPayConfig;

    @Resource
    private OrderMapper ordersMapper;

    @Resource
    private TUserMapper userMapper;

    @GetMapping("/pay") // localhost:8080/alipay/pay?subject=会员充值&traceNo=10000000&totalAmount=100
    public void pay(AliPay aliPay, HttpServletResponse httpResponse) throws Exception {

        //  ckhqjr2186@sandbox.com

        // 1. 创建Client，通用SDK提供的Client，负责调用支付宝的API
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, aliPayConfig.getAppId(),
                aliPayConfig.getAppPrivateKey(), FORMAT, CHARSET, aliPayConfig.getAlipayPublicKey(), SIGN_TYPE);



        // 2. 创建 Request并设置Request参数
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();  // 发送请求的 Request类
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        JSONObject bizContent = new JSONObject();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(aliPay.getUid());
        System.out.println(aliPay.getSubject());
        System.out.println(aliPay.getTotalAmount());
        System.out.println(aliPay.getTraceNo());
        System.out.println(aliPay.getAlipayTraceNo());
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");


        bizContent.set("out_trade_no", aliPay.getTraceNo());  // 我们自己生成的订单编号
        bizContent.set("total_amount", aliPay.getTotalAmount()); // 订单的总金额
        bizContent.set("subject", aliPay.getSubject());   // 支付的名称
        bizContent.set("product_code", "FAST_INSTANT_TRADE_PAY");  // 固定配置
        request.setBizContent(bizContent.toString());


                //将订单信息插入到数据库中
        Order order=new Order();

        order.setAlipayId(aliPay.getAlipayTraceNo());
        order.setPrice(25.00);
        order.setUserStateId(9);// 9->已取消

        long s=new Date().getTime();

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date(s);
        order.setCreateTime(date);

        order.setUserId(aliPay.getUid());
        if (order.getAlipayId()==null){
            order.setAlipayId("2023070322001483140500213779");
            int i=ordersMapper.insertOrder(order);
        }else {
            int i=ordersMapper.insertOrder(order);
        }





//         执行请求，拿到响应的结果，返回给浏览器
        String form = "";
        try {
            form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    @PostMapping("/notify")  // 注意这里必须是POST接口
    public String payNotify(HttpServletRequest request) throws Exception {
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            System.out.println("=========支付宝异步回调========");

            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
                // System.out.println(name + " = " + request.getParameter(name));
            }

            String outTradeNo = params.get("out_trade_no");
            String gmtPayment = params.get("gmt_payment");
            String alipayTradeNo = params.get("trade_no");

            String sign = params.get("sign");
            String content = AlipaySignature.getSignCheckContentV1(params);
            boolean checkSignature = AlipaySignature.rsa256CheckContent(content, sign, aliPayConfig.getAlipayPublicKey(), "UTF-8"); // 验证签名
            // 支付宝验签
            if (checkSignature) {
                String pattern = "yyyy-MM-dd HH:mm:ss";
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);

                // 验签通过
                System.out.println("交易名称: " + params.get("subject"));
                System.out.println("交易状态: " + params.get("trade_status"));
                System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
                System.out.println("商户订单号: " + params.get("out_trade_no"));
                System.out.println("交易金额: " + params.get("total_amount"));
                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
                System.out.println("买家付款时间: " + params.get("gmt_payment"));
                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));

                // 查询订单
//                QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();

                Order orders=ordersMapper.querybyid(Long.parseLong(params.get("out_trade_no")));
                System.out.println(orders+"查询用户的订单");
                if (orders != null) {
                    System.out.println("进来了");
                    orders.setAlipayId(alipayTradeNo);
                    orders.setPayTime(sdf.parse(params.get("gmt_payment")));
                    orders.setUserStateId(8);//已支付 ->充值成功 ->8
                   int res =  ordersMapper.updateById(orders);

                   int res1 =  userMapper.updateuserinfovip(orders.getUserId());
                    System.out.println(res+"是否修改成功！！！！！！！"+res1);

                }
            }
        }
        return "success";
    }

}
