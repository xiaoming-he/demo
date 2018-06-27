package com.ming.ioc;

import org.springframework.beans.factory.annotation.Autowired;

//@Component
public class CDPlayer implements MediaPlayer{

    private CompactDisc compactDisc;

    @Autowired
    public CDPlayer(CompactDisc compactDisc) {
        this.compactDisc = compactDisc;
    }

    @Override
    public void display() {
        compactDisc.display();
    }
}
