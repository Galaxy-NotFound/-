package com.match1.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Component
public class GlobalStorage {
    private static GlobalStorage instance;
    private Object data;

    private GlobalStorage() {}

    public static synchronized GlobalStorage getInstance() {
        if (instance == null) {
            instance = new GlobalStorage();
        }
        return instance;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
