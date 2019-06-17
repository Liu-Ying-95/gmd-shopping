package com.springcloud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.entity.Goods;
import com.springcloud.service.GoodsService;
import com.springcloud.vo.ResultValue;

/**
 * 商品控制层
 * 
 * @author 刘颖
 *
 */
@RestController
@RequestMapping("goods")
public class GoodsController {

	@Autowired
	private GoodsService goodsService;

	/**
	 * 添加新商品信息
	 * 
	 * @param goods 新商品信息
	 * @return
	 */
	@RequestMapping(value = "/insert")
	public ResultValue insert(Goods goods) {
		ResultValue rv = new ResultValue();

		try {
			Integer insert = this.goodsService.insert(goods);
			if (insert > 0) {
				rv.setCode(0);
				rv.setMessage("商品信息录入成功！！！");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("商品信息录入失败！！！");
		return rv;
	}

	/**
	 * 查询满足条件的商品信息
	 * 
	 * @param goods      查询条件
	 * @param pageNumber 页数
	 * @return
	 */
	@RequestMapping(value = "/select")
	public ResultValue select(Goods goods, @RequestParam("pageNumber") Integer pageNumber) {
		ResultValue rv = new ResultValue();

		try {
			// 查询满足条件的商品信息
			PageInfo<Goods> pageInfo = this.goodsService.select(goods, pageNumber);
			// 从分页信息中获得商品信息
			List<Goods> list = pageInfo.getList();
			// 如果查询到了满足条件的商品信息
			if (list != null && list.size() > 0) {
				rv.setCode(0);

				Map<String, Object> map = new HashMap<>();
				// 将商品信息以指定的键存入Map集合中
				map.put("goodsList", list);

				PageUtils pagetUtils = new PageUtils(pageInfo.getPages() * PageUtils.PAGE_ROW_COUNT);
				pagetUtils.setPageNumber(pageNumber);
				// 将分入信息以指定的名字存入Map集合中
				map.put("pageUtils", pagetUtils);

				rv.setDataMap(map);
				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		rv.setCode(1);
		rv.setMessage("没有找到满足条件的商品信息！！！");
		return rv;
	}

	@RequestMapping(value = "/updateById")
	public ResultValue updateById(Goods goods) {
		ResultValue rv = new ResultValue();

		try {
			Integer update = this.goodsService.updateGoodsById(goods);
			if (update > 0) {
				rv.setCode(0);
				rv.setMessage("商品信息更新成功！！！");
				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("商品信息更新失败！！！");
		return rv;
	}

	/**
	 * 修改指定编号商品的信息
	 * 
	 * @param goods 修改的商品信息
	 * @return
	 */
	@RequestMapping(value = "/update")
	public ResultValue update(Goods goods) {
		ResultValue rv = new ResultValue();
		
		try {
			Integer update = this.goodsService.update(goods);
			if (update > 0) {
				rv.setCode(0);
				rv.setMessage("商品信息更新成功！！！");
				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("商品信息更新失败！！！");
		return rv;
	}
	
	/**
	 * 查询销量前十的商品信息
	 * @return
	 */
	@RequestMapping(value="/selectGroup")
	public ResultValue selectGroup() {
		ResultValue rv=new ResultValue();
		
		try {
			List<Goods> list = this.goodsService.selectGroup();
			if(list != null && list.size() >0) {
				rv.setCode(0);
				//创建两个集合，用于保存柱状图x轴与y轴的数据
				List<String> x=new ArrayList<>();
				List<Integer> y=new ArrayList<>();
				//将查询结果中商品名称存入x集合中；销量存入y集合
				for(Goods goods:list) {
					x.add(goods.getGoodsName());
					y.add(goods.getGoodsSum());
				}
				Map<String,Object> map=new HashMap<>();
				map.put("x", x);
				map.put("y", y);
				rv.setDataMap(map);
				
				//返回数据，结束结果
				return rv;
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		return rv;
		
	}
}
