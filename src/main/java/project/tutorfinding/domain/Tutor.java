package project.tutorfinding.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "tutor")
@Table(name = "tutor")
//@NamedQueries({
//        @NamedQuery(
//                name = "findBySpeciality",
//                query = "select t from Tutor t where t.specialityCode = :specialityCode"
//        ),
//        @NamedQuery(
//                name = "findAll",
//                query = "select * from Tutor t"
//        ),
//        @NamedQuery(
//                name = "findAllCount",
//                query = "select count(t) from Tutor t"
//        ),
//        @NamedQuery(
//                name = "findById",
//                query = "select t from Tutor t where t.user.id = :id"
//        ),
//})
public class Tutor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "speciality_code")
    private String specialityCode;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "last_updated")
    private Timestamp lastUpdated;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSpecialityCode() {
        return specialityCode;
    }
    public void setSpecialityCode(String specialityCode) {
        this.specialityCode = specialityCode;
    }
    public Timestamp getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }
    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

}
