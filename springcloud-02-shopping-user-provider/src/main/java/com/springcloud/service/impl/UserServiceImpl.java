package com.springcloud.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springcloud.common.PageUtils;
import com.springcloud.entity.Users;
import com.springcloud.repository.UsersRespository;
import com.springcloud.service.UsersService;
/**
 * 模型层的实现类：实现用户模块的方法
 * @author 刘颖
 *
 */
@Service
public class UserServiceImpl implements UsersService {
	
	@Autowired
	private UsersRespository usersRespository;

	@Override
	public Users login(Integer userId, String userPsw, Integer permissionId) {
	
		return this.usersRespository.login(userId, userPsw, permissionId);
	}
	
	@Transactional
	@Override
	public Users insert(Users users) {
		
		return this.usersRespository.save(users);
	}

	@Override
	public Page<Users> select(Users users, Integer pageNumber) {
		//根据查询数据，创建动态条件
		@SuppressWarnings("serial")
		Specification<Users> specification = new Specification<Users>() {

			@Override
			public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				//创建List集合，用于保存所有的where条件
				List<Predicate> whereList = new ArrayList<>();
				
				//根据Users中的查询数据，动态创建查询条件
				if(users.getUserName() != null && !users.getUserName().trim().equals("")) {
					whereList.add(criteriaBuilder.like(root.get("userName"), "%" + users.getUserName() + "%"));
				}
				if(users.getUserStatus()!=-1) {
					whereList.add(criteriaBuilder.equal(root.get("userStatus"), users.getUserStatus()));
				}
				//将所有的条件以and关系连接在一起，并返回
				return criteriaBuilder.and(whereList.toArray(new Predicate[whereList.size()]));
			}
		};
		//创建JPA的分页信息
		PageRequest of = PageRequest.of(pageNumber, PageUtils.PAGE_ROW_COUNT);
		
		return this.usersRespository.findAll(specification,of);
	}
	
	@Transactional
	@Override
	public Integer updateStatus(Integer userId, Integer userStatus) {
		return this.usersRespository.updateStatus(userId, userStatus);
	}

	@Override
	public Users selectById(Integer userId) {
		
		return this.usersRespository.findById(userId).get();
	}
	
	@Transactional
	@Override
	public Integer update(Users users) {
		return this.usersRespository.update(users);
	}
	
	@Transactional
	@Override
	public Integer updateMessage(Users users) {
		if(users.getUserImage() != null && !users.getUserImage().trim().equals("")) {
			return this.usersRespository.updateImage(users);
		}else if (users.getUserPsw() != null && !users.getUserPsw().trim().equals("")) {
			return this.usersRespository.updatePassword(users);
		}else if (users.getUserName() != null && !users.getUserName().trim().equals("")) {
			return this.usersRespository.updateUserName(users);
		}
		return 0;
	}

}
