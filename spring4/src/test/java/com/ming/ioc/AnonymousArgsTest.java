package com.ming.ioc;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class AnonymousArgsTest {

    interface ObjectFactory {
        void getObject();
    }

    Map<String, ObjectFactory> map = new HashMap<>(1);

    public void a() {
        String str1 = "hello";
        String str2 = "World";
        map.put("1", new ObjectFactory() {
            @Override
            public void getObject() {
                b();
            }
        });
    }

    public void b() {
        //System.out.print(a + " " + b);
    }

    @Test
    public void test() {
        a();
        ObjectFactory objectFactory = map.get("1");
        objectFactory.getObject();
    }
}
