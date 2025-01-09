package com.match1.service;

import com.match1.mapper.Mapper;
import com.match1.pojo.*;
import com.match1.server.CommandHandler;
import com.match1.utils.AliOSSUtils;
import com.match1.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

@Service
public class ServiceImpl implements com.match1.service.Service {


    // 关闭线程池

    @Autowired
    private AliOSSUtils aliOSSUtils;
    @Autowired
    private Mapper mapper;
    @Autowired
    private CommandHandler handler;

    @Override
    public String led(String command) throws Exception {
        handler.channelWrite(CommandHandler.getChannelId(),command);
        HashMap<String, Object> test = handler.getTest();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        try {
            // 调度任务并获取Future
            ScheduledFuture<String> scheduledFuture = scheduler.schedule(() -> {
                String data = (String) test.get("msg");
                mapper.updateItemSelectByName(data,"data");
                return mapper.getItemByIdentify("led");
            }, 1, TimeUnit.SECONDS);

            // 获取任务结果
            String result = scheduledFuture.get(); // 这将会阻塞直到任务完成

            // 返回结果
            return result;
        } finally {
            // 确保调度器关闭
            scheduler.shutdown();
        }

    }

    @Override
    public List<Item> getItem() {
        return mapper.getItem();
    }


    @Override
    public void deleteItem(Integer id) {
        mapper.deleteItem(id);
    }

    @Override
    public void addItem(Item item, MultipartFile photo) throws IOException {
        String url = aliOSSUtils.upload(photo);
        item.setImage(url);
        mapper.addItem(item);
    }

    @Override
    public void updateItem(Item item, MultipartFile image) throws IOException {
        String url = aliOSSUtils.upload(image);
        item.setImage(url);
        mapper.updateItem(item);
    }

    @Override
    public void register(String username, String password) {
        String md5String = Md5Util.getMD5String(password);
        mapper.register(username,md5String);
    }

    @Override
    public User selectByUsername(String username) {
        User result = mapper.selectByUsername(username);
        return result;
    }

    @Override
    public void addUser(User user) {
        mapper.addUser(user);
    }

    @Override
    public User selectByName(String name) {
       User user =  mapper.selectByName(name);
        return user;
    }

    @Override
    public void setUserWithImage(User user,MultipartFile image) throws IOException {
        String upload = aliOSSUtils.upload(image);
        user.setImage(upload);
        mapper.setUserWithImage(user);
    }



    @Override
    public void fixItem(MultipartFile photo, FixItem fixItem) throws IOException {
        String result = aliOSSUtils.upload(photo);
        fixItem.setImage(result);
        mapper.fixItem(fixItem);
    }

    @Override
    public List<Item> selectByFunction(String ability) {
        return mapper.selectByFunction(ability);
    }

    @Override
    public String temp(String command) throws Exception {
        handler.channelWrite(CommandHandler.getChannelId(),command);
        HashMap<String, Object> test = handler.getTest();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        try {
            // 调度任务并获取Future
            ScheduledFuture<String> scheduledFuture = scheduler.schedule(() -> {
                String data = (String) test.get("msg");
                mapper.updateItemSelectByName(data,"temp");
                return mapper.getItemByIdentify("temp");
            }, 1, TimeUnit.SECONDS);

            // 获取任务结果
            String result = scheduledFuture.get(); // 这将会阻塞直到任务完成

            // 返回结果
            return result;
        } finally {
            // 确保调度器关闭
            scheduler.shutdown();
        }

    }

    @Override
    public String fan(String command) throws Exception {
        handler.channelWrite(CommandHandler.getChannelId(),command);
        HashMap<String, Object> test = handler.getTest();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        try {
            // 调度任务并获取Future
            ScheduledFuture<String> scheduledFuture = scheduler.schedule(() -> {
                String data = (String) test.get("msg");
                mapper.updateItemSelectByName(data,"fan");
                return mapper.getItemByIdentify("fan");
            }, 1, TimeUnit.SECONDS);

            // 获取任务结果
            String result = scheduledFuture.get(); // 这将会阻塞直到任务完成

            // 返回结果
            return result;
        } finally {
            // 确保调度器关闭
            scheduler.shutdown();
        }

    }
    @Override
    public String infrared(String command) throws Exception {
        handler.channelWrite(CommandHandler.getChannelId(),command);
        HashMap<String, Object> test = handler.getTest();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        try {
            // 调度任务并获取Future
            ScheduledFuture<String> scheduledFuture = scheduler.schedule(() -> {
                String data = (String) test.get("msg");
                mapper.updateItemSelectByName(data,"infrared");
                return mapper.getItemByIdentify("infrared");
            }, 1, TimeUnit.SECONDS);

            // 获取任务结果
            String result = scheduledFuture.get(); // 这将会阻塞直到任务完成

            // 返回结果
            return result;
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // 确保调度器关闭
            scheduler.shutdown();
        }

    }
    @Override
    public String light(String command) throws Exception {
        handler.channelWrite(CommandHandler.getChannelId(),command);
        HashMap<String, Object> test = handler.getTest();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        try {
            // 调度任务并获取Future
            ScheduledFuture<String> scheduledFuture = scheduler.schedule(() -> {
                String data = (String) test.get("msg");
                mapper.updateItemSelectByName(data,"light");
                return mapper.getItemByIdentify("light");
            }, 1, TimeUnit.SECONDS);

            // 获取任务结果
            String result = scheduledFuture.get(); // 这将会阻塞直到任务完成

            // 返回结果
            return result;
        } finally {
            // 确保调度器关闭
            scheduler.shutdown();
        }

    }
    @Override
    public String soil(String command) throws Exception {
        handler.channelWrite(CommandHandler.getChannelId(),command);
        HashMap<String, Object> test = handler.getTest();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        try {
            // 调度任务并获取Future
            ScheduledFuture<String> scheduledFuture = scheduler.schedule(() -> {
                String data = (String) test.get("msg");
                mapper.updateItemSelectByName(data,"soil");
                return mapper.getItemByIdentify("soil");
            }, 1, TimeUnit.SECONDS);

            // 获取任务结果
            String result = scheduledFuture.get(); // 这将会阻塞直到任务完成

            // 返回结果
            return result;
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // 确保调度器关闭
            scheduler.shutdown();
        }

    }
    @Override
    public String hall(String command) throws Exception {
        handler.channelWrite(CommandHandler.getChannelId(),command);
        HashMap<String, Object> test = handler.getTest();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        try {
            // 调度任务并获取Future
            ScheduledFuture<String> scheduledFuture = scheduler.schedule(() -> {
                String data = (String) test.get("msg");
                mapper.updateItemSelectByName(data,"hail");
                return mapper.getItemByIdentify("hail");
            }, 1, TimeUnit.SECONDS);

            // 获取任务结果
            String result = scheduledFuture.get(); // 这将会阻塞直到任务完成

            // 返回结果
            return result;
        } finally {
            // 确保调度器关闭
            scheduler.shutdown();
        }

    }

    @Override
    public void fixItemSelectByName(String identify) {
        String status = "报修中";
        mapper.updateItemStatusSelectByName(identify,status);
    }

    @Override
    public FixItem getFixItem(String no) {
        return null;
    }

    @Override
    public List<Item> getFixItemSelectByName() {
        List<Item> result = mapper.getFixItemList();
        return result;
    }

    @Override
    public User getUserByName(String name) {
    User result =  mapper.getUserByName(name);
    return result;
    }


}



