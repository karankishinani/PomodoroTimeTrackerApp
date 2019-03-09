package com.ptt;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
class User {

    private User(){}

    private
    @Id @GeneratedValue(strategy= GenerationType.AUTO) long id;
    private String firstName;
    private String lastName;
    private String email;

    User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}

@Data
@Entity
class Project {

    private Project(){}

    private
    @Id @GeneratedValue(strategy= GenerationType.AUTO) long id;
    private String projectName;

    Project(String projectName) {
        this.projectName = projectName;
    }
}
