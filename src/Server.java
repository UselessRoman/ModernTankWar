import org.json.simple.JSONObject;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server
{
    public static void run()
    {
        System.out.println("Socket服务器开始运行");
        try {
            ServerSocket serverSocket=new ServerSocket(9996);
            while (true)
            {
                Socket socket=serverSocket.accept();
                new Thread(new Server_listen(socket)).start();
                new Thread(new Sever_send(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class Server_listen implements Runnable
{
    private Socket socket;

    Server_listen(Socket socket)
    {
        this.socket=socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
            while (true)
            {
                try {
                    System.out.println(ois.readObject());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class Sever_send implements Runnable
{
    private Socket socket;

    Sever_send(Socket socket)
    {
        this.socket=socket;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
            Scanner input =new Scanner(System.in);
            while (true)
            {
                System.out.println("输入发送内容");
                String line =input.nextLine();
                JSONObject object=new JSONObject();
                object.put("message",line);
                oos.writeObject(object);
                oos.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
