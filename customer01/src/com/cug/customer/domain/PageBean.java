package com.cug.customer.domain;

import java.util.List;

public class PageBean<T> {
	private int tr;
	private int pc;
	private int ps;
//	private int tp;
	private List<T> beanList;
	
	public List<T> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}

	private String url;

	public int getTr() {
		return tr;
	}

	public void setTr(int tr) {
		this.tr = tr;
	}

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}

	public int getPs() {
		return ps;
	}

	public void setPs(int ps) {
		this.ps = ps;
	}

	public int getTp() {
		int tp = tr/ps;
		return tp==0?tp:tp+1;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
