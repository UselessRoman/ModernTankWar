import org.json.simple.JSONObject;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cilent
{
    private static Socket socket;
    private static boolean connect_State=false;
    public static void run() {
        connect();
        if (connect_State)
        {
            new Thread(new Client_Listen(socket)).start();
            new Thread(new Client_send(socket)).start();
        }
    }

    private static void connect()
    {
        try {
            socket=new Socket("127.0.0.1",9999);
            connect_State=true;
        } catch (IOException e) {
            e.printStackTrace();
            connect_State=false;
        }
    }
}

class Client_Listen implements Runnable
{
    private Socket socket;

    Client_Listen(Socket socket)
    {
        this.socket=socket;
    }

    @Override
    public void run() {
        System.out.println("请输入");
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
    }
}

class Client_send implements Runnable
{
    private Socket socket;

    Client_send(Socket socket)
    {
        this.socket=socket;
    }
    @Override
    public void run() {
        try {
            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
            Scanner scanner=new Scanner(System.in);
            while (true)
            {
                String line =scanner.nextLine();
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
