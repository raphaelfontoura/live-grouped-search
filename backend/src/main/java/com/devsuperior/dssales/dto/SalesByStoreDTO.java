package com.devsuperior.dssales.dto;

import com.devsuperior.dssales.entities.Store;

public class SalesByStoreDTO {

	private String storeName;
	private Double sum;
	
	public SalesByStoreDTO() {
	}

	public SalesByStoreDTO(Store store, Double sum) {
		this.storeName = store.getName();
		this.sum = sum;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}
}
