package com.bolsadeideas.springboot.cliente.app.aspect;


import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;




@Aspect
@Component
public class EmployeeServiceAspect {

	/*
	@Before(value = "execution(* com.bolsadeideas.springboot.cliente.app.service.ClientServiceImplement.*(..))")
	//@Around("@annotation(Override)")
	public void beforeAdvice(ProceedingJoinPoint joinPoint,RegisterClientTO r) {
		System.out.println("Before method 2:" + r.getUsername());
		System.out.println("Before method:" + joinPoint.getSignature());
		
		
		
	}
	*/
}
