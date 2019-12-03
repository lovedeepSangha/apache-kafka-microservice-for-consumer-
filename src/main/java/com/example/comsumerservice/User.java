package com.example.comsumerservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by lovedeep in com.example.comsumerservice
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    //    @Id
    String id;
    String name;
    String company;
//    List<ObjectId> post;

    public User(String name, String company) {
        this.name = name;
        this.company = company;
    }
}
