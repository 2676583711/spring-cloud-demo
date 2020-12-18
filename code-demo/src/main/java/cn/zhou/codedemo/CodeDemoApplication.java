package cn.zhou.codedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class CodeDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeDemoApplication.class, args);


        char c='汉';


        List<Integer> list = new ArrayList<Integer>();
        Collections.sort(list);

        Collections.reverse(list);


        Queue<Object> objects = new LinkedList<>();
    }

}
