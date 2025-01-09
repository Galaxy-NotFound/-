package com.match1.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {
    private Integer id;
    private String name;
    private String image;
    private String location;
    private String msg;
    private String command;
    private String identify;
    private String status;
}
