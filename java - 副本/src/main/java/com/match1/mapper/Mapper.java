package com.match1.mapper;

import com.match1.pojo.FixItem;
import com.match1.pojo.Item;
import com.match1.pojo.User;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface Mapper {
    String getMsgByUUID(String uuid);
    void saveMessage(String uuid,Object msg);
    List<Item> getItem();
    void addItem(Item item);
    void deleteItem(Integer id);
    void updateItem(Item item);
    User selectByUsername(String username);
    void register(String username, String password);

    void addUser(User user);

    User selectByName(String name);

    void setUserWithImage(User user);

    void fixItem(FixItem fixItem);
    

    void updateItemSelectByName(String msg,String identify);

    String getItemByIdentify(String identify);

    List<Item> selectByFunction(String ability);

    void save(Object msg);

    List<Item> getFixItemList();

    void updateItemStatusSelectByName(String identify, String status);

    User getUserByName(String name);
}
