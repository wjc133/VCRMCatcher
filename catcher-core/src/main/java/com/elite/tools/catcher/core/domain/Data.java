package com.elite.tools.catcher.core.domain;

import java.util.List;

/**
 * Created by df on 16/5/12.
 */
public class Data {
    private List<Content> content;
    private int total;    //总数量
    private int numberOfElements;
    private boolean last;   //是否为最后一页
    private int totalElements;  //总数
    private int number;
    private boolean first;   //是否是第一页
    private int totalPages;  //总页数
    private int size;

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Data{" +
                "content=" + content +
                ", total=" + total +
                ", numberOfElements=" + numberOfElements +
                ", last=" + last +
                ", totalElements=" + totalElements +
                ", number=" + number +
                ", first=" + first +
                ", totalPages=" + totalPages +
                ", size=" + size +
                '}';
    }
}
