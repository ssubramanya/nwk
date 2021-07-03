package com.legion.kickstart.DatabaseEntities;

import lombok.Data;

import java.util.List;

@Data
public class User implements DatabaseEntity{
    String name;
    List<Component> componentList;
    String email;
    String age;
    String dob;
}
