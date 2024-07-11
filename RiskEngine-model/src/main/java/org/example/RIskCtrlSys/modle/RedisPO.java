package org.example.RIskCtrlSys.modle;

import lombok.Data;

import java.io.Serializable;

@Data
public class RedisPO implements Serializable {
    private String data;

    public RedisPO() {
    }

    public RedisPO(String data) {
        this.data = data;
    }
}
