package com.ming.ioc;

import com.ming.ioc.BlankDisc;
import com.ming.ioc.SoundConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SoundConfig.class)
public class CDPlayerTest {

    @Autowired
    private BlankDisc blankDisc;

    /*@Autowired
    private MediaPlayer mediaPlayer;*/

    /*@Test
    public void cdShouldNotBeNull() {
        System.out.println(compactDisc);
        Assert.assertNotNull(compactDisc);
    }
*/
    @Test
    public void display() {
        blankDisc.display();
    }
}
