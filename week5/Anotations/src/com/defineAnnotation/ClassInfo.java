package com.defineAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ClassInfo {

	String author();

	int currentRevision() default 1;
	
	boolean checked() default true;
	
	Class<?>[] relatedClasses();

	//String lastModified() default "N/A";

	//String lastModifiedBy() default "N/A";

}
