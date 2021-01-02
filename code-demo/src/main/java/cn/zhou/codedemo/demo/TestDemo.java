package cn.zhou.codedemo.demo;

import lombok.SneakyThrows;

public class TestDemo {
    public static void main(String[] args) {
        TestDemo td = new TestDemo();
        td.sleepSort();
    }

    public void sleepSort() {
        int[] array = {5, 6, 4, 9, 7, 8, 3, 1, 2};

        System.out.println("sort before:");
        for (int i : array) {
            System.out.print("\t" + i);
        }

/*
        new Thread(() -> {

        }).start();

        */

        System.out.println("\n after--");


        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                for (int i : array) {
                    Thread.sleep(i);
                    System.out.print("\t" + i);
                }
            }
        }).start();


    }
}
