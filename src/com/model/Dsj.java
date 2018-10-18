package com.model;

import org.springframework.stereotype.Component;

@Component
public class Dsj {

	private int dsjId;
	private String dsjName;
	private String dsjAddr;
	private String dsjHb;
	private int addrId;
	
	public int getDsjId() {
		return dsjId;
	}
	public void setDsjId(int dsjId) {
		this.dsjId = dsjId;
	}
	public String getDsjName() {
		return dsjName;
	}
	public void setDsjName(String dsjName) {
		this.dsjName = dsjName;
	}
	public String getDsjAddr() {
		return dsjAddr;
	}
	public void setDsjAddr(String dsjAddr) {
		this.dsjAddr = dsjAddr;
	}
	public String getDsjHb() {
		return dsjHb;
	}
	public void setDsjHb(String dsjHb) {
		this.dsjHb = dsjHb;
	}
	public int getAddrId() {
		return addrId;
	}
	public void setAddrId(int addrId) {
		this.addrId = addrId;
	}
	
}
