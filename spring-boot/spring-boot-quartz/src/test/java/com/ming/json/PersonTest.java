package com.ming.json;

//import com.alibaba.fastjson.JSONObject;

/**
 * @author ming_he
 * @date 2019/6/25 9:21 AM
 */
public class PersonTest {

    public static void main(String[] args) {
        //Person person = new Person();
        //person.setF_age(18);
        //person.set_name("ming");

        //System.out.println("bean to json:" + JSONObject.toJSONString(person));

        //Person person1 = JSONObject.parseObject("{\"age\":18,\"name\":\"ming\"}", Person.class);
        //System.out.println("json to bean:" + person1);
        Integer a = 0;
        aaa(a);
        System.out.println("a="+ a);
    }

    public static void aaa(Integer b) {
         b++;
        System.out.println("b=" + b);
    }
}
