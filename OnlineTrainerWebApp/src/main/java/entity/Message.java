package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Bogdan Kaftanatiy
 */
@Entity
@Table(name = "messages")
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "isRead")
    private boolean isRead;
    @Column(name = "message")
    private String message;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fromAccount")
    private Account fromAccount;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "toAccount")
    private Account toAccount;

    public Message() {
        this.isRead = false;
    }

    public Message(String message, Date date, Account from, Account to) {
        this.message = message;
        this.date = date;
        this.fromAccount = from;
        this.toAccount = to;
        this.isRead = false;
    }

    public Message(String message) {
        this.message = message;
        this.isRead = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public void setToAccount(Account to) {
        this.toAccount = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message1 = (Message) o;

        if (id != message1.id) return false;
        if (isRead != message1.isRead) return false;
        if (message != null ? !message.equals(message1.message) : message1.message != null) return false;
        if (date != null ? !date.equals(message1.date) : message1.date != null) return false;
        if (fromAccount != null ? !fromAccount.equals(message1.fromAccount) : message1.fromAccount != null) return false;
        return toAccount != null ? toAccount.equals(message1.toAccount) : message1.toAccount == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (isRead ? 1 : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (fromAccount != null ? fromAccount.hashCode() : 0);
        result = 31 * result + (toAccount != null ? toAccount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", isRead=" + isRead +
                ", message='" + message + '\'' +
                ", date=" + date +
                ", from=" + fromAccount +
                ", to=" + toAccount +
                '}';
    }
}
