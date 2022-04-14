package com.volunteer.util;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 * @param <T>
 */

public class PageUtil<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 总记录数
     */
    private Long totalCount;

    /**
     * 每页记录数
     */
    private Long pagesize;

    /**
     * 总页数
     */
    private Long totalPage;

    /**
     * 当前页数
     */
    private Long pagenum;

    /**
     * 列表数据
     */
    private List<T> list;

    /**
     * 分页
     * @param list        列表数据
     * @param totalCount  总记录数
     * @param pagesize    每页记录数
     * @param pagenum    当前页数
     */
    public PageUtil(List<T> list, Long totalCount, Long pagenum, Long pagesize) {
        this.list = list;
        this.totalCount = totalCount;
        this.pagesize = pagesize;
        this.pagenum = pagenum;
        this.totalPage = (long)Math.ceil((double)totalCount/pagesize);
    }

    /**
     * 分页
     */
    public PageUtil(IPage<T> page) {
        this.list = page.getRecords();
        this.totalCount = (long)page.getTotal();
        this.pagesize = (long)page.getSize();
        this.pagenum = (long)page.getCurrent();
        this.totalPage = (long)page.getPages();
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getPageSize() {
        return pagesize;
    }

    public void setPageSize(long pagesize) {
        this.pagesize = pagesize;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public long getCurrPage() {
        return pagenum;
    }

    public void setCurrPage(long pagenum) {
        this.pagenum = pagenum;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
