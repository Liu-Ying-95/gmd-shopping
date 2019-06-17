package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TYPE_ONE表对应的实体类，用于保存表中一行一级类别信息
 * @author 刘颖
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeOne implements java.io.Serializable{

	private static final long serialVersionUID = -2690542590151848525L;

	/**
	 * 一级类别编号
	 */
	private Integer typeOneId;

	/**
	 * 一级类别名称
	 */
	private String typeOneName;

	/**
	 * 序号
	 */
	private Integer typeOneNum;

	/**
	 * 备注
	 */
	private String typeOneRemark;

}