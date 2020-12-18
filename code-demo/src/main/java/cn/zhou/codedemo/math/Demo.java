package cn.zhou.codedemo.math;

import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Demo {


    public static void main(String[] args) {
        Demo demo = new Demo();

//        demo.printRandom();
//        demo.reverseNumber();

        demo.getResult();
    }

    public void printRandom() {
        double random = Math.random(); //0-1之间的小数

        Random random1 = new Random(10);//默认当前系统时间的毫秒数作为种子数:


        System.out.println(random + "---Math.random():" + (int) (random * 10 % 10));

        System.out.println("new Random():" + random1.nextFloat());

        System.out.println("new Random():" + random1.nextInt(50));


    }

    public int getres() {
        Random random1 = new Random();//默认当前系统时间的毫秒数作为种子数:

        int a = random1.nextInt(10);
        int b = random1.nextInt(10);
        int s = random1.nextInt(4);
        String[] symb = {"+", "-", "*", "/"};
        int sum = 0;
        switch (symb[s]) {
            case "+":
                sum = a + b;
                break;
            case "-":
                sum = a - b;
                break;
            case "*":
                sum = a * b;
                break;
            case "/":
                sum = a / b;
                break;
        }

        System.out.println(a + symb[s] + b + "=???");
        return sum;

    }

    public void getResult() {
        System.out.println("开始游戏...");

        int getres = getres();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String next = scanner.next();
            if (next.equals(String.valueOf(getres))) {
                System.out.println("it is right...");
                System.out.println("如果需要继续游戏，请输入'go'...");
            } else if (next.equals("bye")) {
                System.out.println("game over...");
                return;
            } else if (next.equals("go")) {
                getres = getres();
            } else {
                System.out.println("错误，换一个...");
                getres = getres();
            }
        }
    }


    public void reverseNumber() {
        int a = 654321;

        int sum = 0;
        while (a > 0) {
            int b = a % 10;
            System.out.println("num:" + b + "---" + a);
            a = a / 10;
            System.out.println("---" + a);

            sum = sum * 10 + b;

        }

        System.out.println("------------------" + a + "------------------");
        System.out.println("--result---" + sum);

    }
}
