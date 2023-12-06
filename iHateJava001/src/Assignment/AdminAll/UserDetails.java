/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment.AdminAll;

public class UserDetails {

    private String name;
    private double credit;
    private String role;
    private String password;
    private String id;
    
    public UserDetails() {

    }

    public UserDetails(String id, String name, String password, String role, double credit) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.credit = credit;
    }

    public UserDetails(String name, String password, String role, double credit) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.credit = credit;
    }

    public UserDetails(String name, double credit) {
        this.name = name;
        this.credit = credit;
    }

    // Getters and setters for user details
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

}

