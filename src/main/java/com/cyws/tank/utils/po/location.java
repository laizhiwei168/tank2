package com.cyws.tank.utils.po;

import java.util.List;

public class location {
	private String formatted_address;
	private addressComponent addressComponent;
	private List<pois> pois;
	
	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}

	public List<pois> getPois() {
		return pois;
	}

	public void setPois(List<pois> pois) {
		this.pois = pois;
	}

	public addressComponent getAddressComponent() {
		return addressComponent;
	}

	public void setAddressComponent(addressComponent addressComponent) {
		this.addressComponent = addressComponent;
	}

}
