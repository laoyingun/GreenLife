package com.greenlife.services;

import com.greenlife.dao.GoodsOrderDao;
import com.greenlife.model.GoodsOrder;

public class GoodsOrderService {
	public static void cancleOrder(GoodsOrder order){
		order.setOrderState(20);
		GoodsOrderDao.updateGoodsOrder(order);
	}
}
