package com.mm.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ccs_compared_back")
public class CcsComparedBack {
	@Id
	private String id;
	@Column(name="compare_channel")
	private String compareChannel;
	@Column(name="channel_Desc")
	private String channelDesc;
	@Column(name="txn_amt")
	private int txnAmt;
	@Column(name="count")
	private int count;
	@Column(name="return_code")
	private int returnCode;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCompareChannel() {
		return compareChannel;
	}
	public void setCompareChannel(String compareChannel) {
		this.compareChannel = compareChannel;
	}
	public String getChannelDesc() {
		return channelDesc;
	}
	public void setChannelDesc(String channelDesc) {
		this.channelDesc = channelDesc;
	}
	public int getTxnAmt() {
		return txnAmt;
	}
	public void setTxnAmt(int txnAmt) {
		this.txnAmt = txnAmt;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
	@Override
	public String toString() {
		return "CcsComparedBack [id=" + id + ", compareChannel=" + compareChannel + ", channelDesc=" + channelDesc
				+ ", txnAmt=" + txnAmt + ", count=" + count + ", returnCode=" + returnCode + "]";
	}
	public CcsComparedBack(String id, String compareChannel, String channelDesc, int txnAmt, int count,
			int returnCode) {
		super();
		this.id = id;
		this.compareChannel = compareChannel;
		this.channelDesc = channelDesc;
		this.txnAmt = txnAmt;
		this.count = count;
		this.returnCode = returnCode;
	}
	public CcsComparedBack() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
