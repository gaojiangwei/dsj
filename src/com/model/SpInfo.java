package com.model;

import org.springframework.stereotype.Component;

@Component
public class SpInfo {

	private int spId;
	private String spName;
	private String spAddr;
	private int spType;
	private int spAddrBm;
	private String spImg;
	
	public int getSpId() {
		return spId;
	}
	public void setSpId(int spId) {
		this.spId = spId;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public String getSpAddr() {
		return spAddr;
	}
	public void setSpAddr(String spAddr) {
		this.spAddr = spAddr;
	}
	public int getSpType() {
		return spType;
	}
	public void setSpType(int spType) {
		this.spType = spType;
	}
	public int getSpAddrBm() {
		return spAddrBm;
	}
	public void setSpAddrBm(int spAddrBm) {
		this.spAddrBm = spAddrBm;
	}
	
	@Override
	public String toString() {
		return "SpInfo [spId=" + spId + ", spName=" + spName + ", spAddr=" + spAddr + ", spType=" + spType
				+ ", spAddrBm=" + spAddrBm + ", spImg=" + spImg + "]";
	}
	public String getSpImg() {
		return spImg;
	}
	public void setSpImg(String spImg) {
		this.spImg = spImg;
	}
}
