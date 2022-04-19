package com.seo.app.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutCommon {
	//로그에서 공통으로 참조할 포인트 컷
	@Pointcut("execution(* com.seo.app..*Impl.*(..))")
	public void aPointcut() {} // 참조 메서드
	@Pointcut("execution(* com.seo.app..*Impl.get*(..))")
	public void bPointcut() {}
}