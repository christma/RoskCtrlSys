package org.example.riskCtrlSys.utils.json;

import lombok.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class JsonUtilsTest {

    @DisplayName("Json对象转Java对象")
    @Test
    public void testJsonStr2Obj() {
        String json = "{\"name\":\"John\",\"age\":18}";
        Persion persion = JsonUtils.jsonStr2Obj(json, Persion.class);
        System.out.println(persion);
    }

    @DisplayName("JAVA对象转JSON对象")
    @Test
    public void testObj2JsonStr() {
        Persion persion = new Persion();
        persion.setName("John");
        persion.setAge(18);
        String json = JsonUtils.obj2JsonStr(persion);
        System.out.println(json);
    }

    @DisplayName("JSON 对象转换MAP")
    @Test
    public void testJsonStr2Map() {
        String json = "{\"data\":[{\"name\":\"John\",\"age\":18},{\"name\":\"zora\",\"age\":23}]}";
        Map<String, List<Persion>> map = JsonUtils.jsonStr2Map(json, String.class, Persion.class);
        List<Persion> datas = map.get("data");
        datas.forEach(System.out::println);

    }

    @DisplayName("JSON 读喜庆转换List")
    @Test
    public void testJsonStr2List() {
        String json = "[{\"name\":\"John\",\"age\":18},{\"name\":\"zora\",\"age\":23}]";
        List<Persion> persions = JsonUtils.jsonStr2List(json, Persion.class);
        persions.forEach(System.out::println);
    }


    @Data
    class Persion {
        private String name;
        private int age;

        public Persion() {
        }

        public Persion(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Persion{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }


}
