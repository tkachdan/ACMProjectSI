package persistance.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tkachdan on 03-Dec-14.
 */
@Entity
public class Team {
    private int id;
    private String name;
    private int rank;
    private State state;
    private Contest attendsContest;
    private Team isCloneOf;


    private Set<Person> teamMembers = new HashSet<Person>(3);


    public Team() {
    }

    public Team(String name) {
        this.name = name;
    }

    public Team(String name, int rank, State state, Contest attendsContest, Team isCloneOf, Set<Person> teamMembers) {
        this.name = name;
        this.rank = rank;
        this.state = state;
        this.attendsContest = attendsContest;
        this.isCloneOf = isCloneOf;
        this.teamMembers = teamMembers;
    }

    public Team(Team team) {
        this.name = team.getName();
        this.rank = team.getRank();
        this.state = team.getState();
        this.attendsContest = team.getAttendsContest();
        this.isCloneOf = team.getIsCloneOf();
        this.teamMembers = team.getTeamMembers();
    }

    public Team(String name, int rank) {
        this.name = name;
        this.rank = rank;
    }

    public Team(String name, int rank, State state) {
        this.name = name;
        this.rank = rank;
        this.state = state;
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

    @OneToOne
    public Contest getAttendsContest() {
        return attendsContest;
    }

    public void setAttendsContest(Contest attendsContest) {
        this.attendsContest = attendsContest;
    }

    @OneToOne
    public Team getIsCloneOf() {
        return isCloneOf;
    }

    public void setIsCloneOf(Team isCloneOf) {
        this.isCloneOf = isCloneOf;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @OneToMany
    public Set<Person> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(Set<Person> teamMembers) {
        this.teamMembers = teamMembers;
    }

    @Column(name = "rank")
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (id != team.id) return false;
        if (rank != team.rank) return false;
        if (name != null ? !name.equals(team.name) : team.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + rank;
        return result;
    }
}
