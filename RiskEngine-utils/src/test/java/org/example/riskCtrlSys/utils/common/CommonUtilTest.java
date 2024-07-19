package org.example.riskCtrlSys.utils.common;

import org.example.RIskCtrlSys.modle.EventPO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonUtilTest {

    @Test
    void setFieldValue() {
    }

    @Test
    void getFieldValue() {
        EventPO eventPO = new EventPO();
        eventPO.setUser_id_int(1);


        Integer user_id_int =(Integer) CommonUtil.getFieldValue(eventPO, "user_id_int");
        System.out.println(user_id_int);


    }

    @Test
    void isExistColumn() {
    }
}