package com.queryinterface.sapiosample;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "product")
public class Product {
	
	private String id;
	private String title;
	private String productGroup;
	private String productType;
	private String ean;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProductGroup() {
		return productGroup;
	}
	public void setProductGroup(String productGroup) {
		this.productGroup = productGroup;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", ean=" + ean + ", productGroup=" + productGroup + ", productType="
				+ productType + "]";
	}
	
	public String getEAN() {
		return ean;
	}
	
	public void setEAN(String ean) {
		this.ean = ean;
	}
}
