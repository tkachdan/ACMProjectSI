package persistance.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
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
    private Set<Contest> isManagerOfContest;
    private Set<Team> isCoachOfTeams;

    public Person() {
    }

    public Person(String name, String email, String university, Date birthday) {
        this.name = name;
        this.email = email;
        this.university = university;
        this.birthday = birthday;
    }

    public Person(Person person) {
        this.name = person.getName();
        this.email = person.getEmail();
        this.university = person.getUniversity();
        this.birthday = person.getBirthday();
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

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Contest> getIsManagerOfContest() {
        return isManagerOfContest;
    }

    public void setIsManagerOfContest(Contest contest) {
        this.isManagerOfContest.add(contest);
    }

    public void setIsManagerOfContest(Set<Contest> isManagerOfContest) {
        this.isManagerOfContest = isManagerOfContest;
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

    @OneToMany(fetch = FetchType.EAGER)
    public Set<Team> getIsCoachOfTeams() {
        return isCoachOfTeams;
    }

    public void setIsCoachOfTeams(Team team) {
        if (this.isCoachOfTeams != null)
            this.isCoachOfTeams.add(team);
        else {
            Set<Team> teams = new HashSet<Team>();
            teams.add(team);
            this.setIsCoachOfTeams(teams);
        }

//        if(this.isCoachOfTeams == null){
//            this.isCoachOfTeams = new HashSet<>();
//        }
//        this.isCoachOfTeams.add(team); ////.getIsCoachOfTeams().add(team);
    }

    public void setIsCoachOfTeams(Set<Team> isCoachOfTeams) {
        this.isCoachOfTeams = isCoachOfTeams;
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
