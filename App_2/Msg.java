

import java.time.LocalDateTime;

public class Msg {
    //Contacts[] c=new Contacts[50];
    private int msg_id;
    private String sender;
    private int no_of_cont;
    private Contacts receiver;
   // private String receiver;
    private String content;
    private String seen;
    private boolean status;
        LocalDateTime timestamp;

        public Msg(String sender){
            this.sender=sender;
        }

        public Msg(int msg_id,String sender,Contacts receiver, String content, boolean status, String seen) {
            this.msg_id=msg_id;
            //sender = "Javairia";
            this.sender=sender;
            this.receiver=receiver;
            this.content = content;
            this.status = status;
            this.seen=seen;
            timestamp = LocalDateTime.now();
        }

    public void setSender(String sender) {
        this.sender = sender;
    }
    public String getSender() {
        return sender;
    }

    public int getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(int msg_id) {
        this.msg_id = msg_id;
    }

    public int getNo_of_cont() {
        return no_of_cont;
    }

    public void setNo_of_cont(int no_of_cont) {
        this.no_of_cont = no_of_cont;
    }

    public Contacts getReceiver() {
            return receiver;
        }

        public void setReceiver(Contacts receiver) {
            this.receiver = receiver;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public boolean getStatus() {
            return status;
        }

        public void isStatus(boolean status) {
            this.status = status;
        }

    public String getSeen() {
        return seen;
    }

    public void setSeen(String seen) {
        this.seen = seen;
    }

    public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }

    @Override
    public String toString() {
        return "Msg{" +
                "msg_id=" + msg_id +
                ", sender='" + sender + '\'' +
                ", receiver=" + receiver.getName() +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", seen='" + seen + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}

