package com.match1.service;

import com.match1.pojo.FixItem;
import com.match1.pojo.Item;
import com.match1.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@org.springframework.stereotype.Service
public interface Service {
    String led(String command) throws Exception;

    List<Item> getItem();

    void deleteItem(Integer id);


    void addItem(Item item, MultipartFile photo) throws IOException;

    void updateItem(Item item, MultipartFile image) throws IOException;

    void register(String username, String password);

    User selectByUsername(String username);

    void addUser(User user);

    User selectByName(String name);

    void setUserWithImage(User user,MultipartFile image) throws IOException;



    void fixItem(MultipartFile photo, FixItem fixItem) throws IOException;

    List<Item> selectByFunction(String ability);


    String temp(String command) throws Exception;

    void fixItemSelectByName(String identify);


    FixItem getFixItem(String no);

    List<Item> getFixItemSelectByName();


    User getUserByName(String name);

    String fan(String command) throws Exception;

    String infrared(String command) throws Exception;

    String light(String command) throws Exception;

    String soil(String command) throws Exception;

    String hall(String command) throws Exception;
}
