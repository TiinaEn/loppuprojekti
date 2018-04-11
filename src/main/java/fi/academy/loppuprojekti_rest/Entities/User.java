package fi.academy.loppuprojekti_rest.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.util.Set;

@Entity
public class User {
    @Id @GeneratedValue
    private Long Id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private String name;
    private String email;
    private String role;
    private int active;

  @ManyToMany
  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name="user_username"), inverseJoinColumns = @JoinColumn(name="role_id"))
    public Set<Role> roles;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public User(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    //    this.authorities = authorities;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }


   /* public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }*/

    public User() {
    }

    public User(String username, String password, String email, String role, int active, String description) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.active = active;

    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


  /*  public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }*/


}
