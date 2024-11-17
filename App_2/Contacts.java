

public class Contacts {
        String name;
        String phoneNo;

        public Contacts(String name){
            this.name=name;
        }
        // Constructor to initialize name and phone number
        public Contacts(String name, String phoneNo) {
            this.name = name;
            this.phoneNo = phoneNo;
        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void display_cont(){
        System.out.println(name);
    }

    // Method to display the contact information
    @Override
    public String toString() {
        return "Name: " + name + ", Phone Number: " + phoneNo;
    }
    }



