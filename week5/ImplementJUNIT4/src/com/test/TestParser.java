package com.test;

import java.beans.MethodDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestParser {

	public List<Method> getMethodsByAnnotation(Class<?> classWithTests,
			Class<? extends Annotation> currentAnnotation) {
		List<Method> methodsWithCurrentAnnotation = new ArrayList<>();
		Method[] methods = classWithTests.getMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(currentAnnotation)) {
				methodsWithCurrentAnnotation.add(method);
			}
		}
		return methodsWithCurrentAnnotation;
	}

	public void invokeMethods(List<Method> methods, Class<?> clazz)
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Object newInstance = clazz.newInstance();
		for (Method method : methods) {
			method.invoke(newInstance);
		}
	}

	public static void assertEquals(Integer expected, Integer recieved)
			throws AssertionException {
		if (!expected.equals(recieved)) {
			throw new AssertionException();
		}
	}

	public void parseBefores(Class<?> clazz) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		List<Method> methodsBefore = getMethodsByAnnotation(clazz, Before.class);
		Collections.sort(methodsBefore, new Comparator<Method>() {

			@Override
			public int compare(Method o1, Method o2) {
				return o1.getAnnotation(Before.class).order()
						- o2.getAnnotation(Before.class).order();
			}

		});
		invokeMethods(methodsBefore, clazz);
	}

	public void parseAfters(Class<?> clazz) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		List<Method> methodsAfter = getMethodsByAnnotation(clazz, After.class);
		Collections.sort(methodsAfter, new Comparator<Method>() {

			@Override
			public int compare(Method o1, Method o2) {
				return o1.getAnnotation(After.class).order()
						- o2.getAnnotation(After.class).order();
			}

		});
		invokeMethods(methodsAfter, clazz);
	}

	public void parseExecutes(Class<?> clazz) throws InstantiationException,
			IllegalAccessException {
		Object newInstance = clazz.newInstance();
		int pass = 0;
		int fail = 0;
		int total = 0;
		int ignore = 0;
		List<Method> methodsExecute = getMethodsByAnnotation(clazz,
				Execute.class);

		for (Method method : methodsExecute) {
			Execute test = method.getAnnotation(Execute.class);
			total++;
			if (test.enabled()) {
				Class<?> expected = test.value();
				try {
					method.invoke(newInstance);
					System.out.printf("%s - Test '%s' - passed%n", total,
							method.getName());
					pass++;
				} catch (Throwable e) {
					Throwable ex = e.getCause();
					while (ex.getCause() != null) {
						ex = ex.getCause();
					}
					if (ex.getClass() != expected) {
						System.out.printf("%s - Test '%s' - failed%n", total,
								method.getName());
						fail++;
					} else {
						System.out.printf("%s - Test '%s' - passed%n", total,
								method.getName());
						pass++;
					}
				}
			} else {
				System.out.printf("%s - Test '%s' - ignored%n", total,
						method.getName());
				ignore++;
			}
		}
		System.out.printf(
				"%nResults from Execute tests: Total: %d  Passed: %d Failed: %d Ignored: %d%n%n",
				total, pass, fail, ignore);

	}

	public void parse(Class<?> clazz) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		parseBefores(clazz);
		parseExecutes(clazz);
		parseAfters(clazz);

	}

	public static void main(String[] args) throws Exception {
		TestParser parser = new TestParser();
		parser.parse(MyTestClass.class);
		// you can use also Class.forName
		// to load from file system directly!
	}
}
