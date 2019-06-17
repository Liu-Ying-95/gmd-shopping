package com.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.dao.OrderDetailsMapper;
import com.springcloud.entity.OrderDetails;
import com.springcloud.service.OrderDetailsService;

/**
 * ������ϸģ�Ͳ��ʵ���࣬����ʵ�ֶ�����ϸģ��ķ���
 * 
 * @author ��ӱ
 *
 */
@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

	@Autowired
	private OrderDetailsMapper orderDetailsMapper;

	@Override
	public PageInfo<OrderDetails> selectByOrderId(Integer orderId, Integer pageNumber) {
		// ���÷�ҳ����Ϣ
		PageHelper.startPage(pageNumber + 1, 5);
		// ��ѯ���������Ķ�����ϸ��Ϣ
		List<OrderDetails> list = this.orderDetailsMapper.selectByOrderId(orderId);
		// ���ط�ҳ��Ϣ
		return new PageInfo<>(list);
	}

}
