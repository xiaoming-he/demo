package com.ming.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

public class ExpressiveConfig {

    @Autowired
    private Environment env;

    /*@Bean
    public CompactDisc blankDisc() {
        List<String> tracks = new ArrayList<>(3);
        tracks.add("aaaaaaa");
        tracks.add("dddd");
        tracks.add("ccc");
        return new BlankDisc(env.getProperty("disc.artist"),
                env.getProperty("disc.title"), tracks);
    }*/

}
