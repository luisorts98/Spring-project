package es.javaschool.train.Entity;

import javax.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "Admin", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;


@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
@JoinTable(
        name = "admin_roles",
        joinColumns = @JoinColumn(name = "admin_id",referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id")
)
    private Collection<Rol> roles;

    public Admin(int id, String email, String password, String name, String surname, Collection<Rol> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.roles = roles;
    }
    public Admin(){

    }

    public Admin(String email, String password, String name, String surname, Collection<Rol> roles) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Collection<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Rol> roles) {
        this.roles = roles;
    }
}
