package com.wuxi;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

public class CallableTest {

    @Test
    public void mainTest() {
        ExecutorService ex = Executors.newFixedThreadPool(3);
        Callable<String> call = new Callable<String>() {

            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                System.out.println("执行。。");
                return "over";
            }
        };
        Future<String> future = ex.submit(call);
        while (!future.isDone()) {
            System.out.println(System.currentTimeMillis());
            try {
                if (future.isDone()) {
                    System.out.println(future.get());
                    break;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
    }
}
