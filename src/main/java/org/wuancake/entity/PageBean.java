package org.wuancake.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页查询相关JavaBean
 *
 * @author
 * @date
 */
public class PageBean implements Serializable {

    private Integer currPage;
    private Integer totalPage;
    private Integer pageSize;
    private Integer totalSize;
    private Integer weekNum;
    private List<KickBean> kickBeanList = new ArrayList<>();

    public List<KickBean> getKickBeanList() {
        return kickBeanList;
    }

    public void setKickBeanList(List<KickBean> kickBeanList) {
        this.kickBeanList = kickBeanList;
    }

    private List<GatherBean> gathers = new ArrayList<>();

    public Integer getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(Integer weekNum) {
        this.weekNum = weekNum;
    }


    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public List<GatherBean> getGathers() {
        return gathers;
    }

    public void setGathers(List<GatherBean> gathers) {
        this.gathers = gathers;
    }
}
