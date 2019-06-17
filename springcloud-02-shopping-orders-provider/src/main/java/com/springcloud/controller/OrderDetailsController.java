package com.springcloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.entity.OrderDetails;
import com.springcloud.entity.Orders;
import com.springcloud.service.OrderDetailsService;
import com.springcloud.vo.ResultValue;

/**
 * 订单明细模块的控制层
 * @author 刘颖
 *
 */
@RestController
@RequestMapping("orderDetails")
public class OrderDetailsController {

	@Autowired
	private OrderDetailsService orderDetailsService;
	
	@RequestMapping(value = "selectByOrderId")
	public ResultValue selectByOrderId(@RequestParam("orderId") Integer orderId,@RequestParam("pageNumber") Integer pageNumber) {
		ResultValue rv= new ResultValue();
		
		try {
			PageInfo<OrderDetails> pageInfo = this.orderDetailsService.selectByOrderId(orderId, pageNumber);
			List<OrderDetails> list = pageInfo.getList();
			if (list != null && list.size() > 0) {
				rv.setCode(0);
				
				Map<String, Object> map = new HashMap<>();
				map.put("orderDetailsList", list);
				
				PageUtils pagetUtils = new PageUtils(5,pageInfo.getPages() * 5);
				pagetUtils.setPageNumber(pageNumber);
				map.put("pageUtils", pagetUtils);
				
				rv.setDataMap(map);
				return rv;
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rv.setCode(1);
		rv.setMessage("没有找到满足条件的订单明细信息！！！");
		return rv;
	}
}
