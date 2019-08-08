package com.ming.json;

//import com.alibaba.fastjson.annotation.JSONField;
//import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ming_he
 * @date 2019/6/25 9:20 AM
 */
public class Person {

    //@JsonProperty("name")
    private String _name;

    //@JsonProperty("age")
    private int f_age;

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int getF_age() {
        return f_age;
    }

    public void setF_age(int f_age) {
        this.f_age = f_age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "_name='" + _name + '\'' +
                ", f_age=" + f_age +
                '}';
    }
}
