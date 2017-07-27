package org.world.jedis;

import java.util.Date;

public class SuperTest extends Date {

	private static final long serialVersionUID = 1L;

	private void test() {
		System.out.println(super.getClass().getName());
	}

	public static void main(String[] args) {
		new SuperTest().test();

		// 2int x=4;
		// System.out.println("value is "+ ((x>4) ? 99.9:9));
	}
	
	
	
}
