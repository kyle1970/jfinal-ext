package test.jfinal.plugin.jms;

import java.io.Serializable;

import jfinal.ext.plugin.jms.ReceiveResolver;


public class AReceiveResolver implements ReceiveResolver {

	@Override
	public void resolve(Serializable objectMessage) throws Exception {
		System.out.println("AReceiveResolver");
	}

}
