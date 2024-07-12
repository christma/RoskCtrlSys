package org.example.RIskCtrlSys.modle;

import lombok.Data;

@Data
public class ClickHousePO {

    private String name;

    public ClickHousePO(String name) {
        this.name = name;
    }
}
