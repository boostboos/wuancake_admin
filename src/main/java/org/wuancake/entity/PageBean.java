package org.wuancake.entity;

import java.util.List;

/**
 * 分页查询相关JavaBean
 */
public class PageBean {

    //当前页
    private Integer currPage;
    //总页数
    private Integer totalPage;
    //每页显示纪录数
    private Integer pageSize;
    //总记录数
    private Integer totalSize;

    //考勤汇总
    private List<GatherBean> gathers;

    @Override
    public String toString() {
        return "PageBean{" +
                "currPage=" + currPage +
                ", totalPage=" + totalPage +
                ", pageSize=" + pageSize +
                ", totalSize=" + totalSize +
                ", gathers=" + gathers +
                '}';
    }

    public List<GatherBean> getGathers() {
        return gathers;
    }

    public void setGathers(List<GatherBean> gathers) {
        this.gathers = gathers;
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
}
