package com.springcloud.service;

import com.github.pagehelper.PageInfo;
import com.springcloud.entity.OrderDetails;

/**
 * ������ϸģ���ģ�Ͳ㣬���ڶ��嶩����ϸģ��ķ���
 * 
 * @author ��ӱ
 *
 */
public interface OrderDetailsService {

	/**
	 * ��ѯָ��������ŵĶ�����ϸ��Ϣ (��ҳ����)
	 * @param orderId �������
	 * @param PageNumber ҳ��
	 * @return ����com.github.pagehelper.PageInfo<OrderDetails>���͵�ʵ��
	 */
	public abstract PageInfo<OrderDetails> selectByOrderId(Integer orderId,Integer pageNumber);
}