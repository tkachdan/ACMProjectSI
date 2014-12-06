package persistance.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by tkachdan on 03-Dec-14.
 */
@Entity
@Table(name = "person", schema = "public", catalog = "acm")
public class Person {
    private int id;
    private String name;
    private String email;
    private String university;
    private Date birthday;
    private Set<Contest> managedContests;
    private Set<Team> coachedTeams;
    private boolean isContestManager;
    private boolean isCoach;


    public Person() {
        isContestManager = false;
        isCoach = false;
    }

    public Person(String name, String email, String university, Date birthday) {
        this.name = name;
        this.email = email;
        this.university = university;
        this.birthday = birthday;
        isContestManager = false;
        isCoach = false;
    }

    public Person(Person person) {
        this.name = person.getName();
        this.email = person.getEmail();
        this.university = person.getUniversity();
        this.birthday = person.getBirthday();
        this.isContestManager = person.isContestManager();
        isCoach = false;
    }

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "entity_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "pk_sequence")
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToMany
    public Set<Contest> getManagedContests() {
        return managedContests;
    }

    public void setManagedContests(Set<Contest> isManagerOfContest) {
        this.managedContests = isManagerOfContest;
        if (isManagerOfContest.size() > 0) {
            isContestManager = true;
        }
    }

    public Date getBirthday() {
        return birthday;
    }

    @Column(name = "birthday")
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany
    public Set<Team> getCoachedTeams() {
        return coachedTeams;
    }

    public void setCoachedTeams(Set<Team> isCoachOfTeams) {
        this.coachedTeams = isCoachOfTeams;
        this.isCoach = true;
    }

    public boolean isContestManager() {
        return isContestManager;
    }

    public void setContestManager(boolean isContestManager) {
        this.isContestManager = isContestManager;
    }

    public boolean isCoach() {
        return isCoach;
    }

    public void setCoach(boolean isCoach) {
        this.isCoach = isCoach;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "university")
    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", university='" + university + '\'' +
                '}';
    }
}
