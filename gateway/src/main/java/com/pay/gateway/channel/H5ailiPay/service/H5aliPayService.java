package com.pay.gateway.channel.H5ailiPay.service;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.gateway.api.DealContorller;
import com.pay.gateway.channel.H5ailiPay.util.BankUtil;
import com.pay.gateway.config.common.Common;
import com.pay.gateway.config.entity.PayOrder;
import com.pay.gateway.config.service.PayOrderService;
import com.pay.gateway.entity.Account;
import com.pay.gateway.entity.AccountFee;
import com.pay.gateway.entity.BankCard;
import com.pay.gateway.entity.DealOrder;
import com.pay.gateway.entity.OrderAll;
import com.pay.gateway.entity.dealEntity.Deal;
import com.pay.gateway.entity.dealEntity.ResultDeal;
@Service
public class H5aliPayService extends PayOrderService{
	Logger log = LoggerFactory.getLogger(H5aliPayService.class);
	@Autowired
	BankCardService bankCardServiceImpl;
	@Resource(name = "BankUtil")
	BankUtil bankUtil;
	@Override
	public ResultDeal deal(Deal deal, Account account, AccountFee accountFee, OrderAll orderAll) {
		log.info("===========【H5本地支付宝处理类】======");
		ResultDeal result = new ResultDeal();
		List<BankCard> findBankCardAll = bankCardServiceImpl.findBankCardAll();
		BigDecimal amount = bankUtil.findDealAmount(new BigDecimal(orderAll.getOrderAmount()));
		log.info("===========【缓存取到页面金额："+amount+"======");
		//加密
		String param = "order="+orderAll.getOrderId();
		
		param += "|amount="+amount;
		param += "|systemTime="+System.currentTimeMillis();
		result.setReturnUrl("/api/startOrder?order="+orderAll.getOrderId()+"&amount="+amount);
		log.info("===========【转发的get请求路径："+result.getReturnUrl()+"======");
		result.setCod(Common.RESPONSE_STATUS_SU);
 		return result;
	}
	

	
	


}
