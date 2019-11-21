package com.bank.fakeapi.beans;

import javax.validation.constraints.NotNull;

public class StatusPayload {
	
	@NotNull(message = "The reference is mandatory")
	private String reference;
	
	@NotNull(message = "The channel is mandatory")
	private ChannelEnum channel;
	
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public ChannelEnum getChannel() {
		return channel;
	}
	public void setChannel(ChannelEnum channel) {
		this.channel = channel;
	}
	
}
