package Message;

public class Contacts {
        String name;
        String phoneNo;

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

    // Method to display the contact information
    @Override
    public String toString() {
        return "Name: " + name + ", Phone Number: " + phoneNo;
    }
    }


