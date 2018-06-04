package org.wuancake.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.wuancake.entity.Report;

@Controller
public class ReportController {

	
	@RequestMapping(value="/lookReport",method=RequestMethod.POST)
	public Report lookReport(@RequestParam("weeks") String weekNum,@RequestParam("groups") String groupNum) {
		Report report = new Report("Java", "陶陶", "135", "已提交", "aaaaaaaaa");
		return report;
	}
}
