package persistance.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by tkachdan on 03-Dec-14.
 */
@Entity
public class Contest {
    private int id;
    private String name;
    private int capacity;
    private Date date;
    private Date registrationfrom;
    private Date registrationtill;
    private boolean isregistrationopen;
    private Contest nextContest;

    public Contest() {
    }

    public Contest(String name, int capacity, Date date, Date registrationfrom, Date registrationtill, boolean isregistrationopen) {
        this.name = name;
        this.capacity = capacity;
        this.date = date;
        this.registrationfrom = registrationfrom;
        this.registrationtill = registrationtill;
        this.isregistrationopen = isregistrationopen;
    }

    public Contest(Contest contest) {
        this.name = contest.getName();
        this.capacity = contest.getCapacity();
        this.date = contest.getDate();
        this.registrationfrom = contest.getRegistrationfrom();
        this.registrationtill = contest.getRegistrationtill();
        this.isregistrationopen = contest.isIsregistrationopen();
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(name = "capacity")
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Column(name = "registrationfrom")
    public Date getRegistrationfrom() {
        return registrationfrom;
    }

    public void setRegistrationfrom(Date registrationfrom) {
        this.registrationfrom = registrationfrom;
    }


    @OneToOne
    public Contest getNextContest() {
        return nextContest;
    }

    public void setNextContest(Contest nextContest) {
        this.nextContest = nextContest;
    }


    @Column(name = "registrationtill")
    public Date getRegistrationtill() {
        return registrationtill;
    }

    public void setRegistrationtill(Date registrationtill) {
        this.registrationtill = registrationtill;
    }


    @Column(name = "isregistrationopen")
    public boolean isIsregistrationopen() {
        return isregistrationopen;
    }

    public void setIsregistrationopen(boolean isregistrationopen) {
        this.isregistrationopen = isregistrationopen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contest contest = (Contest) o;

        if (capacity != contest.capacity) return false;
        if (id != contest.id) return false;
        if (isregistrationopen != contest.isregistrationopen) return false;
        if (date != null ? !date.equals(contest.date) : contest.date != null) return false;
        if (name != null ? !name.equals(contest.name) : contest.name != null) return false;
        if (registrationfrom != null ? !registrationfrom.equals(contest.registrationfrom) : contest.registrationfrom != null)
            return false;
        if (registrationtill != null ? !registrationtill.equals(contest.registrationtill) : contest.registrationtill != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + capacity;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (registrationfrom != null ? registrationfrom.hashCode() : 0);
        result = 31 * result + (registrationtill != null ? registrationtill.hashCode() : 0);
        result = 31 * result + (isregistrationopen ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Contest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", date=" + date +
                ", registrationfrom=" + registrationfrom +
                ", registrationtill=" + registrationtill +
                ", isregistrationopen=" + isregistrationopen +
                ", nextContest=" + nextContest +
                '}';
    }
}
