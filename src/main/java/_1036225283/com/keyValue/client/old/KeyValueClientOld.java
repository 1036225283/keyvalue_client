package _1036225283.com.keyValue.client.old;


import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Map;

/**
 * key-value系统的客户端，单线程，
 */
public class KeyValueClientOld {


    private Socket socket;
    private String ip;
    private int port;
    private byte[] bytes = new byte[1024 * 8];

    public KeyValueClientOld setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public KeyValueClientOld setPort(int port) {
        this.port = port;
        return this;
    }

    public KeyValueClientOld connection() {
        try {
            InetAddress inetAddress = InetAddress.getByName(ip);
            socket = new Socket(inetAddress.getHostAddress(), port);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public void close() {
        try {
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void set(String key, String value) {
        String writeString = UtilProtocol.write("key-value/set", key + "=" + value, ip, port);
        this.write(writeString.getBytes());
        String message = this.read();
        Map<String, String> map = UtilProtocol.read(message);
        System.out.println(map);
    }

    public String get(String key) {

        String writeString = UtilProtocol.write("/key-value/get", "key=" + UtilProtocol.encode(key), ip, port);
        this.write(writeString.getBytes());
        String message = this.read();
        Map<String, String> map = UtilProtocol.read(message);
        String param = map.get("");
        Map<String, String> map1 = null;
        return map1.get("value");

    }

    public void remove(String key) {

    }


    private void write(byte[] bytes) {
        try {
            OutputStream out = socket.getOutputStream();
            out.write(bytes);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String read() {
        try {
            int size = socket.getInputStream().read(bytes);
            return new String(bytes, 0, size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public KeyValueClientOld(String ip, int port) throws IOException {
        // TODO Auto-generated constructor stub
        this.setIp(ip).setPort(port).connection();
    }

}