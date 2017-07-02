package _1036225283.com.keyValue.client;

/**
 * key value util
 * Created by 1036225283 on 2016/11/30.
 */

public class UtilKeyValue {

    //client
    // 针对key-value的序列化方案
    // bs[0] = 1 //set key value
    // bs[0] = 2 // get key
    // bs[0] = 3 // result
    // bs[0] = 4 // remove key
    // bs[0] = 5 // error
    // bs[0] = 6 // ok

    //server
    //返回结果序列化
    // set key value    :ok
    // get key          :value
    // remove key       :ok


    // 客户端封装set请求(bs[1]+bs[2] == key.lenth)
    public static byte[] set(String key, String value) {
        int nKeyLength = key.getBytes().length;
        String total = "123" + key + value;
        byte[] bs = total.getBytes();
        bs[0] = 1;
        bs[1] = (byte) (nKeyLength / 256);
        bs[2] = (byte) (nKeyLength % 256);
        return bs;
    }

    // 客户端封装get请求
    public static byte[] get(String key) {
        String total = 1 + key;
        byte[] bs = total.getBytes();
        bs[0] = 1;
        return bs;
    }

    // 客户端封装remove请求
    public static byte[] remove(String key) {
        String total = 1 + key;
        byte[] bs = total.getBytes();
        bs[0] = 4;
        return bs;
    }


    //服务端解析get请求
    public static KvNode set(byte[] bs) {
        int high = bs[1] << 8;
        int low = bs[2];
        int nKeyLength = high + low;
        String key = new String(bs, 3, nKeyLength);
        String value = new String(bs, nKeyLength, bs.length - 1);
        KvNode node = new KvNode();
        node.setKey(key);
        node.setValue(value);
        return node;
    }

    //服务端解析get请求
    public static String get(byte[] bs) {
        String key = new String(bs, 1, bs.length - 1);
        return key;
    }


    //服务端解析remove请求
    public static String remove(byte[] bs) {
        String key = new String(bs, 1, bs.length - 1);
        return key;
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        UtilKeyValue test = new UtilKeyValue();
        byte[] bs = test.get("123");

        System.out.println(bytesToHexString(bs));

        System.out.println(bs.length);
        System.out.println(new String(bs, 1, bs.length - 1));

        bs = test.remove("2345");
        System.out.println(new String(bs, 1, bs.length - 1));

        int xws = 258;
        System.out.println(xws / 256);
        System.out.println(xws % 256);

    }

}