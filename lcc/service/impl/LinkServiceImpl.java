package com.lcc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lcc.dao.LinkDao;
import com.lcc.entity.Link;
import com.lcc.service.LinkService;


@Service("linkService")
public class LinkServiceImpl implements LinkService{

	@Resource
	private LinkDao linkDao;
	
	public int add(Link link) {
		return linkDao.add(link);
	}

	public int update(Link link) {
		return linkDao.update(link);
	}

	public List<Link> list(Map<String, Object> map) {
		return linkDao.list(map);
	}

	public Long getTotal(Map<String, Object> map) {
		return linkDao.getTotal(map);
	}

	public Integer delete(Integer id) {
		return linkDao.delete(id);
	}

}
