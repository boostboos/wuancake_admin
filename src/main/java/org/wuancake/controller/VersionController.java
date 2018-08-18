package org.wuancake.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wuancake.entity.PageBean;
import org.wuancake.entity.VersionBean;
import org.wuancake.service.VersionService;
import org.wuancake.utils.ResultAjax;
import org.wuancake.utils.ResultUtil;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * AppVersion相关控制层类
 *
 * @author Lord
 */
@Controller
public class VersionController extends SuperController {

    @Value("${customConfig.pageSize}")
    private Integer pageSize;

    @Autowired
    private VersionService versionService;

    @RequestMapping(value = "findAllVersion/**")
    public String findAllVersion(Model model, HttpServletRequest request) {

        int currPage = Integer.parseInt(request.getQueryString().replace("%20", "").substring(9));

        Integer startIndex = (currPage - 1) * pageSize;

        int totalSize = versionService.findVersionCount();

        PageBean pageBean = new PageBean();
        pageBean.setCurrPage(currPage);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalSize(totalSize);
        pageBean.setTotalPage((int) Math.ceil((double) totalSize / pageSize));

        List<VersionBean> list = versionService.findAllVersion(startIndex, pageSize);

        model.addAttribute("page", pageBean);
        model.addAttribute("list", list);

        return "versionList";
    }

    @RequestMapping(value= "findOneVersion")
    @ResponseBody
    public ResultAjax<VersionBean> versionFindOne(Integer vid){
        return ResultUtil.success(versionService.findOne(vid));
    }

    @RequestMapping(value = "saveVersion")
    @ResponseBody
    public ResultAjax<VersionBean> saveVersion(@Valid VersionBean versionBean, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(versionService.saveOrUpdate(versionBean));
    }

}
