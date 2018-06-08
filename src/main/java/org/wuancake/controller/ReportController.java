package org.wuancake.controller;

import java.util.List;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.wuancake.entity.AdminBean;
import org.wuancake.entity.Report;
import org.wuancake.service.impl.ReportServiceImpl;


@Controller
public class ReportController extends SuperController {

	@Autowired
	private ReportServiceImpl reportServiceImpl;
	
	//查看周报接口
	@RequestMapping(value="/lookReport",method=RequestMethod.POST)
	public Report lookReport(@RequestParam("weeks") int weeks,@RequestParam("groups") int groups,HttpServletRequest request) {
//		//获得当前登录用户判断其权限
//		AdminBean admin = (AdminBean) request.getSession().getAttribute("isAdmin");
//		
//		
////		if (admin.getAuth() == 1) {
////			//导师登录
////			reportServiceImpl.queryReportByWeekAndGroup();
////		}
		
		System.out.println(weeks);
		System.out.println(groups);
		return null;
	}
}
