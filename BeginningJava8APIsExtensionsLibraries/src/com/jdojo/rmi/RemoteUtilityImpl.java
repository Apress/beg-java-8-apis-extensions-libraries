// RemoteUtilityImpl.java
package com.jdojo.rmi;

import java.time.ZonedDateTime;

public class RemoteUtilityImpl implements RemoteUtility {
	public RemoteUtilityImpl() {
	}

	@Override
	public String echo(String msg) {
		return msg;
	}

	@Override
	public ZonedDateTime getServerTime() {	
		return ZonedDateTime.now();
	}

	@Override
	public int add(int n1, int n2) {	
		return n1 + n2;
	}
}
