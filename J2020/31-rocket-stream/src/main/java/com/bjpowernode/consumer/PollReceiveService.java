package com.bjpowernode.consumer;

//@Component
//@EnableBinding(value = {MySink.class})
public class PollReceiveService {//implements CommandLineRunner {

    //@Autowired
    private MySink mySink;

    /*public void run(String... args) throws Exception {
        while (true) {
            mySink.input5().poll(m -> {
                    String payload = (String) m.getPayload();
                    System.out.println("input5 接收到的pull消息: " + payload);
                }, new ParameterizedTypeReference<String>() {}
            );
            Thread.sleep(2_000);
        }
    }*/
}