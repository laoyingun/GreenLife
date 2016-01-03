package com.greenlife.services;

import com.greenlife.dao.GoodsOrderDao;
import com.greenlife.model.GoodsOrder;
import com.greenlife.wechatservice.WechatService;

public class GoodsOrderService {
	public static boolean cancleOrder(GoodsOrder order){
		
		int state = order.getOrderState();
		
		if(state < 10){
			if(state == 1){
				WechatService.closeOrder(order);
			}else if(state == 2){
				if(!WechatService.refund(order)){
					return false;
				}
			}
			order.setOrderState(9);
		}else{
			if(state == 11){
				WechatService.closeOrder(order);
			}
			order.setOrderState(19);
		}
		
		if(!GoodsOrderDao.updateGoodsOrder(order)){
			return false;
		}
		return true;
	}
	
	
}
