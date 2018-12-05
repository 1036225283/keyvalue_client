package _1036225283.com.keyValue.client;

/**
 * key value client test
 * Created by xws on 7/1/17.
 */
public class KeyValueTest {

    private static int port = 9999;
    private static String host = "www.1036225283.com";

    private static KeyValueClient client = new KeyValueClient(host, port);


    public static void main(String[] args) throws Exception {

        client.auth("xwsKeyValue@#$");
        client.set("xws","xws");
        String xws = client.get("xws");
        System.out.println(xws);
//        set();
//        get();
//        getTotal();
//        setOne();
//        getOne();
//        test();
        System.out.println("this is end");
    }


    public static void test() {
        byte[] bs = UtilKeyValue.get(Operation.GET, "12");
        System.out.println("bs.length = " + bs.length);
        String key = UtilKeyValue.get(bs, bs.length);
        System.out.println("key = " + key);
    }

    public static void getTotal() throws Exception {

        long start = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            String value = client.get("我爱你第" + i + "次啊");
        }

        long end = System.nanoTime();
        System.out.println((end - start) / 1000);

    }

    // test get
    public static void get() throws Exception {


        for (int i = 0; i < 1000; i++) {
            long start = System.nanoTime();
            String value = client.get("我爱你第" + i + "次啊");

            long end = System.nanoTime();
            System.out.println((end - start) / 1000 + " = " + value);
        }
    }


    //test set
    public static void set() throws Exception {

        for (int i = 0; i < 10000; i++) {
            long start = System.nanoTime();
            client.set("我爱你第" + i + "次啊", "你爱我" + i + "次");
            long end = System.nanoTime();
            System.out.println("微秒" + (end - start) / 1000);
        }
    }


    public static void getOne() throws Exception {


        long start = System.nanoTime();
        String value = client.get("我爱你第1次啊");
        System.out.println(value);
        long end = System.nanoTime();
        System.out.println("微秒" + (end - start) / 1000);
    }

    //test set
    public static void setOne() throws Exception {

        long start = System.nanoTime();
        client.set("我爱你第1次啊", "你爱我1次");
        long end = System.nanoTime();
        System.out.println("微秒" + (end - start) / 1000);
    }
}
