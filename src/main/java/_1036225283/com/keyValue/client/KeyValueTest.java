package _1036225283.com.keyValue.client;

import _1036225283.com.keyValue.client.old.KeyValueClientOld;

/**
 * key value client test
 * Created by xws on 7/1/17.
 */
public class KeyValueTest {

    public static void main(String[] args) throws Exception {

//        set();
//        setOne();
        getOne();
        System.out.println("this is end");
    }


    // test get
    public static void get() throws Exception {

        KeyValueClientOld client = new KeyValueClientOld("localhost", 8888);

        for (int i = 0; i < 1000; i++) {
            long start = System.nanoTime();
            String value = client.get("我爱你第" + i + "次啊");

            long end = System.nanoTime();
            System.out.println((end - start) / 1000 + " " + i + " = " + value);
        }
    }


    //test set
    public static void set() throws Exception {
        KeyValueClient client = new KeyValueClient("localhost", 8888);

        for (int i = 0; i < 1000; i++) {
            long start = System.nanoTime();
            client.set("我爱你第" + i + "次啊", "你爱我" + i + "次");
            long end = System.nanoTime();
            System.out.println("微秒" + (end - start) / 1000);
        }
    }


    public static void getOne() throws Exception {

        KeyValueClient client = new KeyValueClient("localhost", 8888);

        long start = System.nanoTime();
        String value = client.get("我爱你第1次啊");
        System.out.println(value);
        long end = System.nanoTime();
        System.out.println("微秒" + (end - start) / 1000);
    }

    //test set
    public static void setOne() throws Exception {
        KeyValueClient client = new KeyValueClient("localhost", 8888);
        long start = System.nanoTime();
        client.set("我爱你第1次啊", "你爱我1次");
        long end = System.nanoTime();
        System.out.println("微秒" + (end - start) / 1000);
    }
}
