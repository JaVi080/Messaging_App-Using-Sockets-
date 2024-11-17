import java.io.*;
import java.net.*;
import java.util.Scanner;
 class Client extends MessagingApp  implements Runnable {
    //192.168.148.223
   // void Msg_display();

     Contacts reciever;
     Msg sender;
     Msg seen;
     Msg status;
     Client( Msg sender,Contacts reciever, Msg[] messages, int msg_count,Msg seen,Msg status) {
         this.sender=sender;
         this.reciever = reciever;
         this.messages = messages; // Share the messages array
         this.msg_count = msg_count;
         this.seen=seen;
         this.status=status;// Share the msg_count
     }


    @Override
    public void run() {
        try (Socket s = new Socket("localhost", 6666);) {
            System.out.println("Connected to Server");
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());

            DataInputStream d = new DataInputStream(s.getInputStream());
            Scanner sc = new Scanner(System.in);

             Thread sendThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    sending_msgs(dout, sc);
                }
            });

            // Start the thread for receiving messages
            Thread receiveThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    recieving_msgs(d);
                }
            });


            // Start both threads
            sendThread.start();
            receiveThread.start();

            sendThread.join();
            receiveThread.join();
            System.out.println("Returning to menu");

            dout.close();
            d.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void sending_msgs(DataOutputStream dout, Scanner sc) {
        String input;
        System.out.println("Write the msg : ");
        try {
            while (true) {
                //String
                // System.out.print(" Client : ");
                input = sc.nextLine();
                sender.setSender("Javairia");
                seen.setSeen("unseen");
                status.isStatus(true);
                super.store_msgs(sender.getSender(), input,reciever,status.getStatus(),seen.getSeen());
                dout.writeUTF(input);
                dout.flush();
                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Client ended the chat");
                    break;
                }
            }
        } catch (Exception s) {
            System.out.println(s.getMessage());
        }
    }


    public void recieving_msgs(DataInputStream d) {
        String str;
        try {
            while (true) {
                str = (String) d.readUTF();
                sender.setSender(reciever.getName());
                Contacts reciever=new Contacts("Javairia");
                seen.setSeen("seen");
                status.isStatus(true);
                super.store_msgs(sender.getSender(),str,reciever,status.getStatus(),seen.getSeen());
                System.out.println(sender.getSender()+" : " + str);
                if (str.equalsIgnoreCase("exit")) {
                    System.out.println("Server ended the chat");
                    break;
                }
            }
        } catch (Exception r) {
            System.out.println(r.getMessage());
        }
    }
}


//System.out.println("Ending the Chat");



/*public class Client{
   // void Msg_display();
    public static void main(String[] arg) {
        // Client_1 c= new Client();
        Thread t=new Thread(new Client_1());
        t.start();

    }
}*/
