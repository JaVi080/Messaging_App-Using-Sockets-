import java.io.*;
import java.net.*;
import java.util.Scanner;

class Server extends MessagingApp implements Runnable {
   // MessagingApp m=new MessagingApp();
    /*public void startServer() {
        Thread serverThread = new Thread(new Server());
        serverThread.start();
    }*/
   // MessagingApp m=new MessagingApp();
    Contacts reciever;
   Msg sender;
   Msg seen;
   Msg status;

   Server( Msg sender,Contacts reciever, Msg[] messages, int msg_count,Msg seen,Msg status) {
       this.sender=sender;
       this.reciever = reciever;
       this.messages = messages;
       this.msg_count = msg_count;
       this.seen=seen;
       this.status=status;
   }
    @Override
    public void run() {
        try (ServerSocket ss = new ServerSocket(6666);) {
            System.out.println("Waiting for the Client");
            Socket s = ss.accept();
            System.out.println("Client Connected");
            //for reading
            DataInputStream d = new DataInputStream(s.getInputStream());

            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            Scanner sc = new Scanner(System.in);

            Thread s_thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    send_msg(sc,dout);
                }
            });
            Thread r_thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    rcv_msgs(d);
                }
            });
         
            s_thread.start();
            r_thread.start();

            s_thread.join();
            r_thread.join();

            //  System.out.println("Ending the Chat");
            System.out.println("Returning to menu");
            d.close();
            dout.close();
        } catch (Exception e) {
            System.out.println("In Server Exception ");
        }
    }

    public void send_msg(Scanner sc, DataOutputStream dout) {
        String msg;
        System.out.println("Write the message : ");
        try {
            while (true) {
                // System.out.print(" Server : ");
                //String
                msg = sc.nextLine();
               // System.out.println("Sending message: " + msg + " to " + reciever);
                sender.setSender("Javairia");
                seen.setSeen("unseen");
                status.isStatus(true);
                super.store_msgs(sender.getSender(), msg,reciever,status.getStatus(),seen.getSeen());
                dout.writeUTF(msg);
                dout.flush();
                if (msg.equalsIgnoreCase("exit")) {
                    System.out.println("Server ended the chat");
                    break;
                }
            }
        } catch (Exception r) {
            System.out.println(r.getMessage());
        }
    }

    public void rcv_msgs(DataInputStream d) {
        String str;
        try {
            while (true) {
                str = (String) d.readUTF();
                //Msg sender=new Msg(reciever.getName());
                sender.setSender(reciever.getName());
                Contacts reciever=new Contacts("Javairia");
                seen.setSeen("seen");
                status.isStatus(true);
                super.store_msgs(sender.getSender(),str,reciever,status.getStatus(),seen.getSeen());
                System.out.println(sender.getSender()+" : "+ str);
                if (str.equalsIgnoreCase("exit")) {
                    System.out.println("Client ended the chat");
                    break;
                }

            }
        } catch (Exception r) {
            System.out.println(r.getMessage());
        }
    }
}

/*public class Server {
    public static void main(String[] args){
        Thread t2=new Thread(new Server_1());
        t2.start();
    }
}*/

