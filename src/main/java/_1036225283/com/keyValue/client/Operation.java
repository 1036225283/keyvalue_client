package _1036225283.com.keyValue.client;

/**
 * 操作指令
 * Created by xws on 7/7/17.
 */
public class Operation {
    // bs[0] = 0 // default protocol error
    // bs[0] = 1 // set key value
    // bs[0] = 2 // get key
    // bs[0] = 3 // result
    // bs[0] = 4 // remove key
    // bs[0] = 5 // error
    // bs[0] = 6 // ok
    // bs[0] = 7 // null
    public static byte DEFAULT = 0;
    public static byte SET = 1;
    public static byte GET = 2;
    public static byte REMOVE = 4;
    public static byte ERROR = 5;
    public static byte SUCCESS = 6;
    public static byte NULL = 7;


}
