package com.github.sioncheng.rx.io;

import org.springframework.web.reactive.function.client.WebClient;

public class Web {

    public static void main(String[] args) throws Exception{
        WebClient.create("http://www.baidu.com")
                .get()
                .uri("/")
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(System.out::println);

        Thread.sleep(3000);
    }
}
