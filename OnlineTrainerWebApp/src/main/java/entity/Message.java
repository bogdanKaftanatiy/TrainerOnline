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
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "from")
    private Account from;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "to")
    private Account to;

    public Message() {
        this.isRead = false;
    }

    public Message(String message, Date date, Account from, Account to) {
        this.message = message;
        this.date = date;
        this.from = from;
        this.to = to;
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

    public Account getFrom() {
        return from;
    }

    public void setFrom(Account from) {
        this.from = from;
    }

    public Account getTo() {
        return to;
    }

    public void setTo(Account to) {
        this.to = to;
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
        if (from != null ? !from.equals(message1.from) : message1.from != null) return false;
        return to != null ? to.equals(message1.to) : message1.to == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (isRead ? 1 : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", isRead=" + isRead +
                ", message='" + message + '\'' +
                ", date=" + date +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
