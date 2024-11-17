

import java.util.Scanner;

public class MessageDriver {

        public static void main(String[] args) {
            MessagingApp messageApp=new MessagingApp();

            System.out.println("Press 1 for add Contacts");
            System.out.println("Press 2 for delete Contacts");
            System.out.println("Press 3 for displaying contact Information");
            System.out.println("Press 4 for sending Messages/Communication1" );
            System.out.println("Press 5 for displaying all Messages");
            System.out.println("Press 6 for deleting Messages");
            System.out.println("Press 7 to read all seen,unseen ,sent and unsent messages");
            System.out.println("Press 8 for Search specific Contact ");
            System.out.println("Press 0 for exit programme : ");
            Scanner sc = new Scanner(System.in);
            while(true) {
                System.out.println("Press Key : ");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        messageApp.add_contact();
                        break;
                    case 2:
                       messageApp.del_contact();
                        break;
                    case 3:
                      messageApp.cont_display();
                        break;
                    case 4:
                        messageApp.send_msgs();
                        break;
                    case 5:
                       messageApp.Msg_display();
                        break;
                    case 6:
                        messageApp.del_msg();
                        break;
                    case 7:
                        messageApp.status();
                        break;
                    case 8:
                        messageApp.search();
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Not in The Options");
                }
            }
        }
    }

