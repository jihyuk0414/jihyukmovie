package com.example.movieprojectfinal.Aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

import java.io.PrintStream;

public class TimeTraceAop {
    @Around("execution(* com.example.movieprojectfinal..*(..)) && !target(com.example.movieprojectfinal.UserConfiguration)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("start : " + joinPoint.toString());
        try {
            return joinPoint.proceed();

        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start ;
            System.out.println("END : " + joinPoint.toString()+ " " + timeMs + "ms");


        }
    }
}
