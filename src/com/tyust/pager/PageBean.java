package com.tyust.pager;

import java.io.Serializable;
import java.util.List;

public class PageBean<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int pc;//当前页码---pagecurrent
	private int tr;//总记录数
	private int ps;//每页记录数
	private String url;//请求路径和参数
	private List<T> beanList;
	//分页等于记录总数据除每一页显示的数据
	/*
	 * 计算总页数
	 */
	public int getTp(){
		//100/10
		//一共10页
		int tp = tr/ps;
		//101/10......1
		//10....1解释11页
		//三木运算符
		return tr % ps == 0 ? tp : tp+1;
	}
	
	public int getPc() {
		return pc;
	}
	public void setPc(int pc) {
		this.pc = pc;
	}
	public int getTr() {
		return tr;
	}
	public void setTr(int tr) {
		this.tr = tr;
	}
	public int getPs() {
		return ps;
	}
	public void setPs(int ps) {
		this.ps = ps;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
	@Override
	public String toString() {
		return "PageBean [pc=" + pc + ", tr=" + tr + ", ps=" + ps + ", url=" + url + ", beanList=" + beanList + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beanList == null) ? 0 : beanList.hashCode());
		result = prime * result + pc;
		result = prime * result + ps;
		result = prime * result + tr;
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		PageBean<T> other = (PageBean<T>) obj;
		if (beanList == null) {
			if (other.beanList != null)
				return false;
		} else if (!beanList.equals(other.beanList))
			return false;
		if (pc != other.pc)
			return false;
		if (ps != other.ps)
			return false;
		if (tr != other.tr)
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	

}
