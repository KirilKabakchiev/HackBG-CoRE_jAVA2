package com.test;

public class MyTestClass {

	@Before(order = 1)
	public void before1() {
		System.out.println("Call to before1, prio = 1");
	}

	@Before(order = 2)
	public void before2() {
		System.out.println("Call to before2, prio = 2");
	}

	@Before(order = 3)
	public void before3() {
		System.out.println("Call to before3, prio = 3");
	}

	@Execute(value = ArithmeticException.class)
	public void ExecuteA() {

		int a = 5 / 0;

	}

	@Execute(value = NullPointerException.class)
	public void ExecuteA1() {

		int a = 5 / 0;

	}

	@Execute(enabled = true)
	public void ExecuteB() {

		TestParser.assertEquals(1, 2);

	}

	@Execute(enabled = false)
	public void ExecuteC() {

		int a = 5 / 1;

	}

	@Execute
	public void ExecuteD() {

		int a = 5 / 2;

	}

	@Execute
	public void ExecuteE() {

		TestParser.assertEquals(2, 2);

	}
	
	@After(order = 1)
	public void after1() {
		System.out.println("Call to after1, prio = 1");
	}

	@After(order = 2)
	public void after2() {
		System.out.println("Call to after2, prio = 2");
	}

	@After(order = 3)
	public void after3() {
		System.out.println("Call to after3, prio = 3");
	}


}
