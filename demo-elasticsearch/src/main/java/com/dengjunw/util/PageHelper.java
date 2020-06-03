package com.dengjunw.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageHelper<T> {
    private int totalRows;         //总记录数，从数据库查询
    private int currentPage;       //当前页，  从前端传递过来
    private int pageSize;          //每页条数，自定义
    private int totalPages;        //总页数，  计算
    private int offset;            //偏移量，  计算
    private List<T> data;          //查询出来的数据
    private boolean hasPrev;       //是否有前一页
    private boolean hasNext;       //是否有下一页

    public PageHelper(int totalRows, int currentPage, int pageSize) {
        super();
        this.totalRows = totalRows;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.offset = (this.currentPage - 1) * this.pageSize;
        this.totalPages = this.totalRows % this.pageSize == 0
                ? this.totalRows / this.pageSize : this.totalRows / this.pageSize + 1;
        this.hasPrev = this.currentPage != 1;
        this.hasNext = this.totalPages != this.currentPage;
    }

    public static PageHelper<Object> empty(int page, int size) {
        PageHelper<Object> p = new PageHelper<>(0, page, size);
        p.setData(new ArrayList<>());
        return p;
    }
}
