package com.lcc.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.lcc.entity.Blog;
import com.lcc.entity.BlogType;
import com.lcc.entity.Blogger;
import com.lcc.entity.Link;
import com.lcc.service.BlogService;
import com.lcc.service.BlogTypeService;
import com.lcc.service.BloggerService;
import com.lcc.service.LinkService;
import com.lcc.util.ResponseUtil;

import net.sf.json.JSONObject;


@Controller
@RequestMapping("/admin/system")
public class SystemAdminController {

	@Resource
	private BloggerService bloggerService;
	
	@Resource
	private BlogTypeService blogTypeService;
	
	@Resource
	private BlogService blogService;
	
	@Resource
	private LinkService linkService;
	
	/**
	 * 刷新系统缓存
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/refreshSystem")
	public String refreshSystem(HttpServletResponse response,HttpServletRequest request)throws Exception{
		ServletContext application=RequestContextUtils.getWebApplicationContext(request).getServletContext();
		Blogger blogger=bloggerService.find(); // 查询博主信息
		blogger.setPassword(null);
		application.setAttribute("blogger", blogger);
		
		List<BlogType> blogTypeCountList=blogTypeService.countList(); // 查询博客类别以及博客的数量
		application.setAttribute("blogTypeCountList", blogTypeCountList);
		
		List<Blog> blogCountList=blogService.countList(); // 根据日期分组查询博客
		application.setAttribute("blogCountList", blogCountList);
		
		List<Link> linkList=linkService.list(null); // 获取所有友情链接
		application.setAttribute("linkList", linkList);
		
		List<Blog> blogClickList=blogService.clickTopList();
		application.setAttribute("blogClickList", blogClickList); 
		
		List<Blog> blogCommentList=blogService.commentTopList();
		application.setAttribute("blogCommentList", blogCommentList);
		
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
}
