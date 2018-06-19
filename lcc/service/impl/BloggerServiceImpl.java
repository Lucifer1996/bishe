package com.lcc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lcc.dao.BloggerDao;
import com.lcc.entity.Blogger;
import com.lcc.service.BloggerService;


@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService{

	@Resource
	private BloggerDao bloggerDao;

	public Blogger find() {
		return bloggerDao.find();
	}

	public Blogger getByUserName(String userName) {
		return bloggerDao.getByUserName(userName);
	}

	public Integer update(Blogger blogger) {
		return bloggerDao.update(blogger);
	}
	
	
}
