package com.example.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2019/5/15.
 */
public class threadTest {

    public static void Sum(int N){
        long start1 = System.currentTimeMillis();
        long sum = 0;
        for(int i=1; i<=N; i++){
            sum += i;
        }
        long end1 = System.currentTimeMillis();
        System.out.println("串行计算耗时：" + (end1 - start1) + " ms");
        System.out.println("串行计算的结果：" + sum);
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int N = 90000000;
        //Sum(N);
        //mutilSum(N,20);
        countDownLatchSum(N,5);
    }

    public  static  class  SumThread implements Callable<Long>{
        private  int  start;
        private  int end;

        public SumThread(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public Long  call() throws Exception {
            Long sum = 0L;
            for (int i = start; i < end; i++) {
                sum += i;
            }
            return sum;
        }
    }
    public static void mutilSum(int N, int numThread) throws ExecutionException,
            InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(numThread);
        long start1 = System.currentTimeMillis();
        List<Future<Long>> ans = new ArrayList<>();
        for(int i=0; i<numThread; i++){
            Future<Long> a = executor.submit(
                    new SumThread(i*N/numThread, (i+1)*N/numThread));
            ans.add(a);
        }
        long sum = 0;
        for (Future<Long> i : ans) {
            long tmp = i.get();
            System.out.println("线程 "+i +" 的结果是: "+tmp);
            sum += tmp;
        }
        //并行计算
        long end1 = System.currentTimeMillis();
        System.out.println("并行计算耗时：" + (end1 - start1) + " ms");
        System.out.println("并行计算的结果：" + sum);
    }

    public static class SumCThread implements Runnable{

        private long start;
        private long end;
        private long[] result;
        private CountDownLatch cdl;
        private int num;

        public SumCThread(CountDownLatch cdl, long[] result, long start, long end,
                          int num){
            this.result = result;
            this.start = start;
            this.end = end;
            this.cdl = cdl;
            this.num = num;
        }

        @Override
        public void run(){
            long sum = 0L;
            for(long i=start; i<end; i++){
                sum += i;
            }
            result[num] = sum;
            cdl.countDown();
        }
    }

    //每个线程结果怎么返回  线程如何等待最终求值
    //使用CountDownLatch
    public static void countDownLatchSum(int N, int numThread) throws
            InterruptedException {
        long start1 = System.currentTimeMillis();
        CountDownLatch cdl = new CountDownLatch(numThread);
        long[] result = new long[numThread];
        long sum = 0L;
        for(int i=0; i<numThread; i++){
            new Thread(
                    new SumCThread(cdl, result,i*N/numThread, (i+1)*N/numThread, i))
                    .start();
        }
        cdl.await();
        for(int i=0; i<numThread; i++){
            sum += result[i];
        }
        //并行计算
        long end1 = System.currentTimeMillis();
        System.out.println("并行计算耗时：" + (end1 - start1) + " ms");
        System.out.println("并行计算的结果：" + sum);
    }
}
