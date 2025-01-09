package com.match1.controller;
import com.match1.pojo.*;
import com.match1.utils.AliOSSUtils;
import com.match1.service.Service;
import com.match1.utils.JwtUtil;
import com.match1.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ServerController {
    @Autowired
    private Service service;
    @Autowired
    private AliOSSUtils aliOSSUtils;

    @GetMapping("api/wxLogin/{code}")
    public Result wxLogin(@PathVariable String code){
        Map<String,Object> map = new HashMap<>();
        map.put("code",code);
        return Result.success(JwtUtil.genToken(map));
    }
    @PostMapping("api/fix")
    public Result fixItem(@ModelAttribute FixItem fixItem,@RequestParam("photo") MultipartFile photo) throws IOException {
        FixItem result = service.getFixItem(fixItem.getNo().toString());
        if(result == null){
            service.fixItem(photo,fixItem);
            return Result.success("已收到反馈");
        }
        else {
           if(result.getNo().equals(fixItem.getNo())){
               return Result.error("当前房间号已存在");
           }else {
               service.fixItem(photo, fixItem);
               return Result.success("已收到反馈");
           }
        }
    }
    @PostMapping("api/fixItem")
    public Result fixItem(String identify){
        System.out.println(identify);
        service.fixItemSelectByName(identify);
        return Result.success();
    }
    @GetMapping("api/getFixItemList")
    public Result fixItem(){
        List<Item> items = service.getFixItemSelectByName();
        return Result.success(items);
    }

    @GetMapping("api/getFixItem")
    public Result getFixItem(String no){
        FixItem fixItem = service.getFixItem(no);
        return Result.success(fixItem);

    }
    @PostMapping("api/led")
    public Result led(String command) throws Exception {
        String result = service.led(command);
        return Result.success(result);
    }
    @PostMapping("api/temp")
    public Result temp(String command) throws Exception {
        String result = service.temp(command);
        return Result.success(result);
    }
    @PostMapping("api/fan")
    public Result fan(String command) throws Exception {
        String result = service.fan(command);
        return Result.success(result);
    }
    @PostMapping("api/infrared")
    public Result infrared(String command) throws Exception {
        String result = service.infrared(command);
        return Result.success(result);
    }
    @PostMapping("api/light")
    public Result light(String command) throws Exception {
        String result = service.light(command);
        return Result.success(result);
    }
    @PostMapping("api/soil")
    public Result soil(String command) throws Exception {
        String result = service.soil(command);
        return Result.success(result);
    }
    @PostMapping("api/hall")
    public Result hall(String command) throws Exception {
        String result = service.hall(command);
        return Result.success(result);
    }

    @PostMapping("api/upload")
    public Result upload(@RequestHeader MultipartFile image) throws IOException {
        String url = aliOSSUtils.upload(image);
        System.out.println(url);
        return Result.success(url);
    }
    @GetMapping("api/getItem")
    public Result getItem(){
        List<Item> items = service.getItem();
        return Result.success(items);
    }
    @PostMapping("api/addItem")
    public Result addItem(@ModelAttribute Item item, @RequestParam("photo") MultipartFile photo) throws IOException {
        service.addItem(item,photo);
        return Result.success();
    }
    @DeleteMapping("api/deleteItem")
    public Result deleteItem(Integer id){
        service.deleteItem(id);
        return Result.success();
    }
   @PostMapping("api/updateItem")
    public Result updateItem(@RequestBody Item item,MultipartFile photo) throws IOException {
        service.updateItem(item,photo);
        return Result.success();
   }
   @PostMapping("api/register")
    public Result register(@RequestParam String username,@RequestParam String password){
       User selectResult = service.selectByName(username);
       if (selectResult == null) {
           service.register(username, password);
           return Result.success("注册成功");
       } else {
           return Result.error("该用户名已经存在,注册失败");
       }

   }
   @PostMapping("api/selectByUsername")
    public Result selectByUsername(@RequestParam String username){
            User user = service.selectByUsername(username);
            if (user != null) {
                UserInfo result = new UserInfo(user.getId(),user.getUsername(),user.getName(),user.getGender());
                return Result.success(result);
            }
            else {
                return Result.error("未找到用户");
            }
   }
   @PostMapping("api/login")
    public Result login(String username,String password){
       System.out.println(username + password);
        User userResult = service.selectByUsername(username);
       if(userResult == null){
           return Result.error("该用户名不存在");
       }
       if(Md5Util.getMD5String(password).equals(userResult.getPassword())){
           Map<String,Object> map = new HashMap<>();
           map.put("id",userResult.getId());
           map.put("username",username);
           return Result.success(JwtUtil.genToken(map));
       }else {
           return Result.error("密码错误");
       }
   }
   @PostMapping("api/addUser")
   public Result addUser(@RequestBody User user){
           service.addUser(user);
           return Result.success();

   }
   @GetMapping("api/getUserByName")
   public Result getUserByName(String name){
        User user = service.getUserByName(name);
        return Result.success(user);
   }
   @PostMapping("api/selectByName")
    public Result selectByName(String name){
        User user = service.selectByName(name);
        return Result.success(user);
   }

   @PostMapping("api/setUserWithImage")
    public Result setUserWithImage(@RequestBody User user,MultipartFile image) throws IOException {
        service.setUserWithImage(user,image);
        return Result.success();
   }
   @GetMapping("api/hello")
    public Result sayHello(String data){
        if(data != null){
            System.out.println(data);
            return Result.success("ok");
        }

        else {
            return Result.error("错误");
        }
   }
   @GetMapping("api/selectByFunction")
    public Result selectByFunction(String ability){
        System.out.println(ability);
        List<Item> items = service.selectByFunction(ability);
        return Result.success(items);
    }

}
