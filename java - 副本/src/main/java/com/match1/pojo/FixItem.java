package com.match1.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FixItem {
   private String image;
   private String location;
   private String itemName;
   private Integer no;
   private String status;
}
