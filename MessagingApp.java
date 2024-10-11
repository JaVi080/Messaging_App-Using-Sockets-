package Message;
import java.util.Arrays;
import java.util.Scanner;
public class MessagingApp {
    Contacts[][] contact = new Contacts[50][2];
    Msg[] messages = new Msg[100];
    int count = 0;
    static int msg_count = 0;
    int id = 0;


    //method for adding contact
    public void add_contact() {
        char choice;
        do {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the Contact details : ");
            System.out.println("Enter Name : ");
            String name = input.nextLine();
            System.out.println("Enter Phone number : ");
            String num = input.nextLine();
            boolean cont_add = false;
            for (int i = 0; i < contact.length; i++) {
                for (int j = 0; j < contact[i].length; j++) {
                    if (contact[i][j] == null) {
                        contact[i][j] = new Contacts(name, num);
                        count++;
                        System.out.println("Contact added successfully");
                        cont_add = true;
                        break;
                    }
                }
                if (cont_add == true) {
                    break;
                }
            }
            if(cont_add==false){
                System.out.println("Error : this contact is not in the list");
            }
            System.out.println("Want to add more (y/n) ?");
            choice = input.next().charAt(0);

        }while(Character.toLowerCase(choice) == 'y');
    }

    //method for deleting Contact
    public void del_contact() {
        char choice;
        do{
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the Contact details : ");
        System.out.println("Enter Name : ");
        String name = input.nextLine();
        System.out.println("Enter Phone number : ");
        String num = input.nextLine();
        boolean cont_del = false;
        for (int i = 0; i < contact.length; i++) {
            for (int j = 0; j < contact[i].length; j++) {
                if (contact[i][j] != null&&(contact[i][j].getName().equals(name) || contact[i][j].getPhoneNo().equals(num))) {
                    contact[i][j] = null;
                    count--;
                    System.out.println("Contact deleted successfully.");
                    cont_del = true;
                    break;
                }
            }
            if (cont_del == true) {
                break;
            }
        }
        if(cont_del==false){
            System.out.println("Error : Not in The Contact List");
        }
            System.out.println("Want to delete more Contacts (y/n) ?");
            choice = input.next().charAt(0);

        }while(Character.toLowerCase(choice) == 'y');
    }

    //method for displaying contacts
    public void cont_display() {
        System.out.println("Contacts Information: ");
        for (int i=0;i<contact.length;i++) {
            for (int j=0;j<contact[i].length;j++) {
                if (contact[i][j] != null) {
                    System.out.println(contact[i][j]);
                }
            }
        }
    }

    //send messaged method
    public void send_msgs() {
        char choice;
        do{
        Scanner input = new Scanner(System.in);
        System.out.println("U have these Contacts :");
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < contact[i].length; j++) {
                if (contact[i][j] != null) {
                    System.out.println(contact[i][j]);
                }
            }
        }
        System.out.println("Send Msg to which Contact : ");
        String n = input.nextLine();
        boolean cont=false;
            for (Contacts[] contacts : contact) {
                for (Contacts value : contacts) {
                    if (value != null && value.getName().equals(n)) {
                        System.out.println("Write a message: ");
                        String msg = input.nextLine();

                        // for(int i=0;i<messages.length;i++)
                        if (msg_count < messages.length) {

                            messages[msg_count] = new Msg((id + 1), value, msg, true, "seen");

                            System.out.println("Message sent to " + value);
                            if (msg_count % 4 == 0) {
                                messages[msg_count].setStatus(false);
                                messages[msg_count].setSeen("Msg not send so can't be seen");
                            }
                            if (msg_count % 3 == 0 && messages[msg_count].isStatus()) {
                                messages[msg_count].setSeen("unseen");

                            }
                            msg_count++;
                            id++;
                        }
                        cont = true;
                        break;
                    }
                }
            }
        if(cont==false){
            System.out.println("Error : This Contact is not in the Contact List");
        }
            System.out.println("Want to send more Messages (y/n) ?");
            choice = input.next().charAt(0);

        }while(Character.toLowerCase(choice) == 'y');
    }

    //sorting arrays(in descending order) according to timestamp
    public void sort_time(){
        for(int i=0;i<msg_count-1;i++){
            for (int j=0;j<msg_count-1-i;j++){
                if (messages[j] != null && messages[j + 1] != null) {
                    if(messages[j].getTimestamp().compareTo(messages[j+1].getTimestamp())<0) {
                        //works when messages[j] is earlier (sent in past ) than the next message
                        Msg temp = messages[j];
                        messages[j] = messages[j + 1];
                        messages[j + 1] = temp;
                    }

                }
            }
        }
    }
    //method for displaying messages

    public void Msg_display() {
        sort_time();
        for (int i = 0; i < msg_count; i++) {
            System.out.println(messages[i]);
        }
    }

    public void del_msg() {
        char choice;
        do{
        System.out.println("Which Message U want to delete? Tell me its Msg Id");
        Scanner input = new Scanner(System.in);
        int d = input.nextInt();
        boolean check=false;
        for (int i = 0; i < msg_count; i++) {
            if (messages[i] != null && d == messages[i].getMsg_id()) {
                for (int j = i; j < msg_count - 1; j++) {
                    messages[j] = messages[j + 1];  // Shift messages
                }
                messages[msg_count - 1] = null;
               // messages[i] = null;
                System.out.println("Message deleted Successfully");
                msg_count--;
                check=true;
                break;
            }
        }
        if(check==false){
            System.out.println("Error : Invalid Message Id");
        }
            System.out.println("Want to delete more Msgs (y/n) ?");
            choice = input.next().charAt(0);

        }while(Character.toLowerCase(choice) == 'y');
    }

    //Method to check seen and unseen Messages
    public void status() {
        System.out.println("Press 's' for reading all seen messages : ");
        System.out.println("Press 'us' for reading all unseen messages : ");
        System.out.println("Press 'st' for reading all sent messages : ");
        System.out.println("Press 'ust' for reading all unsent messages : ");
        System.out.println("Press 'e' to exit : ");
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("Press : ");
            String c = input.nextLine();
            switch (c) {
                case "s":
                    seen();
                    break;
                case "us":
                    unseen();
                    break;
                case "st":
                    sent();
                    break;
                case "ust":
                    unsent();
                    break;
                case "e":
                    return;
                default:
                    System.out.println("Error : Invalid Key : Not in the Options");
            }
        }
    }

    public void seen() {
        for (int i = 0; i < msg_count; i++) {
            if (messages[i] != null && messages[i].isStatus() == true && messages[i].getSeen().equals("seen")) {
                System.out.println(messages[i]);
            }
        }
    }

    public void unseen() {
        for (int i = 0; i < msg_count; i++) {
            if (messages[i] != null && messages[i].isStatus() == true && messages[i].getSeen().equals("unseen")) {
                System.out.println(messages[i]);
            }
        }
    }

    public void sent() {
        for (int i = 0; i < msg_count; i++) {
            if (messages[i] != null && messages[i].isStatus() == true) {
                System.out.println(messages[i]);
            }
        }
    }

    public void unsent() {
        for (int i = 0; i < msg_count; i++) {
            if (messages[i] != null && messages[i].isStatus() == false) {
                System.out.println(messages[i]);
            }
        }
    }

    //Search Specific  Contact for Messages
    public void search() {
        char choice;
        do {
            System.out.println("Search contact to read msgs \n" +
                               "(U can also see read ,unread ,sent ,not sent msgs of that contcat \n" +
                               " U can alse delete any message that was sent to that contact of that Contact");
            Scanner n = new Scanner(System.in);
            System.out.println("\nName of that Contact : ");
            String n1 = n.nextLine();
            boolean check = false;
            for (int i = 0; i < contact.length; i++) {
                for (int j = 0; j < contact[i].length; j++) {
                    if (contact[i][j] != null && contact[i][j].getName().equals(n1)) {
                        System.out.println("Messages of this Contact : ");
                        for (int k = 0; k < msg_count; k++) {
                            if (messages[k] != null && messages[k].getReceiver().equals(contact[i][j])) {

                                System.out.println(messages[k]);
                            }
                        }


                            System.out.println("Want to del any message from this Contact : ? Press 1");
                            System.out.println("Want to see seen messages of this Contact : ? Press 2");
                            System.out.println("Want to see unseen Msgs of this Contact : Press 3");
                            System.out.println("Want to see sent msgs of this Contact ? Press 4");
                            System.out.println("Want to see Unsent messages of this Contact : ? Press 5");
                            System.out.println("Don't want anything then Press 6");
                        while (true) {
                            System.out.println("Press Key acc to given Option");
                            int num = n.nextInt();
                            if (num == 1) {
                                System.out.println("Tell me msg_id : ");
                                int d = n.nextInt();
                                for (int k = 0; k < msg_count; k++) {
                                    if (messages[k] != null&& messages[k].getReceiver().equals(contact[i][j]) && d == messages[k].getMsg_id()) {
                                        messages[k] = null;
                                        System.out.println("Message deleted Successfully");
                                        msg_count--;
                                        break;
                                    }
                                }
                            }
                            if (num == 2) {
                                for (int k = 0; k < msg_count; k++) {
                                    if (messages[k] != null&& messages[k].getReceiver().equals(contact[i][j]) && messages[k].isStatus() == true && messages[k].getSeen().equals("seen")) {
                                        System.out.println(messages[k]);
                                    }
                                }
                            }
                            if (num == 3) {
                                for (int k = 0; k < msg_count; k++) {
                                    if (messages[k] != null&& messages[k].getReceiver().equals(contact[i][j]) && messages[k].isStatus() == true && messages[k].getSeen().equals("unseen")) {
                                        System.out.println(messages[k]);
                                    }
                                }
                            }
                            if (num == 4) {
                                for (int k = 0; k < msg_count; k++) {
                                    if (messages[k] != null&& messages[k].getReceiver().equals(contact[i][j]) && messages[k].isStatus() == true) {
                                        System.out.println(messages[k]);
                                    }
                                }
                            }
                            if (num == 5) {
                                for (int k = 0; k < msg_count; k++) {
                                    if (messages[k] != null && messages[k].getReceiver().equals(contact[i][j])&& messages[k].isStatus() == false) {
                                        System.out.println(messages[k]);
                                    }
                                }
                            }
                            if (num == 6)
                                return;
                        }
                    }
                    check = true;
                }
            }

        if(check==false){
            System.out.println("Error : This Contact Not In the Contact List");
        }
            System.out.println("Want to add more (y/n) ?");
            choice = n.next().charAt(0);

        }while(Character.toLowerCase(choice) == 'y');
            }

        }




