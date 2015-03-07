package com.n11.usermanager.validation;

public class ListingParameters {
	private Integer page;
	private Integer max;
	private String sorter;
	private Boolean asc;

	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getMax() {
		return max;
	}
	public void setMax(Integer max) {
		this.max = max;
	}
	public String getSorter() {
		return sorter;
	}
	public void setSorter(String sorter) {
		this.sorter = sorter;
	}
	public Boolean getAsc() {
		return asc;
	}
	public void setAsc(Boolean asc) {
		this.asc = asc;
	}

}
