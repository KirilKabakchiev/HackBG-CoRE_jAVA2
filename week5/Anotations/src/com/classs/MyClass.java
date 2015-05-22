package com.classs;

import java.lang.annotation.Annotation;
import java.util.Arrays;

import com.defineAnnotation.ClassInfo;

@ClassInfo(author = "John Doe", currentRevision = 6, checked = true, relatedClasses = { String.class })
public class MyClass {

	public static void main(String[] args) {

		MyClass instance = new MyClass();

		System.out.println("All annottaions of the class: "
				+ Arrays.toString(instance.getClass().getAnnotations()));
		System.out.println("My annotation is "
				+ instance.getClass().getAnnotation(ClassInfo.class));
		System.out.println();

		Class aClass = MyClass.class;
		Annotation[] annotations = aClass.getAnnotations();
		for (Annotation annotation : annotations) {
			if (annotation instanceof ClassInfo) {
				ClassInfo myAnnotation = (ClassInfo) annotation;
				System.out.println("author: " + myAnnotation.author());
				System.out.println("currentRevision: "
						+ myAnnotation.currentRevision());
				System.out.println("checked: " + myAnnotation.checked());
				System.out.println("relatedClasses: "
						+ Arrays.toString(myAnnotation.relatedClasses()));

			}
		}
	}

}
