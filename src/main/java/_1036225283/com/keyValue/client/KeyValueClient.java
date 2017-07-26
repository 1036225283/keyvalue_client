package _1036225283.com.keyValue.client;


import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * key-value系统的客户端，单线程，
 */
public class KeyValueClient {


    private Socket socket;
    private String ip;
    private int port;
    private byte[] bytes = new byte[1024 * 8];

    public KeyValueClient setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public KeyValueClient setPort(int port) {
        this.port = port;
        return this;
    }

    public KeyValueClient connection() {
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
        byte[] writeBytes = UtilKeyValue.set(key, value);
        this.write(writeBytes);
        this.read();
    }

    public String get(String key) {
        byte[] writeBytes = UtilKeyValue.get(Operation.GET, key);
        this.write(writeBytes);
        String message = this.read();
        return message;

    }

    public void auth(String password) {
        byte[] writeBytes = UtilKeyValue.auth(password);
        write(writeBytes);
        read();
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
            int length = socket.getInputStream().read(bytes);
            if (bytes[0] == Operation.SUCCESS) {
                return UtilKeyValue.get(bytes, length);
            } else if (bytes[0] == Operation.ERROR) {
                socket.close();
                String error = new String(bytes, 1, length);
                Log.info(error, this);
                throw new RuntimeException(error);
            } else {
                Log.info("ERROR WHEN READ DATA FROM SERVER", this);
                throw new RuntimeException("ERROR WHEN READ DATA FROM SERVER");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public KeyValueClient(String ip, int port) throws IOException {
        // TODO Auto-generated constructor stub
        this.setIp(ip).setPort(port).connection();
    }

}
