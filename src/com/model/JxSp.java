package com.model;

import org.springframework.stereotype.Component;

@Component
public class JxSp {

	private int addrId;
	private String jxAddr;
	private String jxName;
	
	public int getAddrId() {
		return addrId;
	}
	public void setAddrId(int addrId) {
		this.addrId = addrId;
	}
	public String getJxAddr() {
		return jxAddr;
	}
	public void setJxAddr(String jxAddr) {
		this.jxAddr = jxAddr;
	}
	public String getJxName() {
		return jxName;
	}
	public void setJxName(String jxName) {
		this.jxName = jxName;
	}
	@Override
	public String toString() {
		return "JxSp [addrId=" + addrId + ", jxAddr=" + jxAddr + ", jxBm=" + jxName + "]";
	}
	
}
