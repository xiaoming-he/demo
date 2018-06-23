package com.ming.ioc;

import org.springframework.stereotype.Component;

import java.util.List;

@Component("blankDisc")
public class BlankDisc implements CompactDisc {

    //@Value("${disc.title}")
    private String title;
    //@Value("${disc.artist}")
    private String artist;
    private List<String> tracks;

    public BlankDisc() {

    }

    public BlankDisc(String title, String artist, List<String> tracks) {
        this.title = title;
        this.artist = artist;
        this.tracks = tracks;
    }

    @Override
    public void display() {
        System.out.println("playing " + title + " by " + artist);
        //tracks.forEach(System.out::println);
    }
}
