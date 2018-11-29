package com.wenba;

import com.wenba.rest.controller.DroolsController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */

@SpringBootTest(classes = DroolsController.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration

public class AppTest {
    /**
     * Rigorous Test :-)
     */

    @Resource
    private DroolsController droolsController;

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }



    @Test
    public void test() {
        //new TestController().test();

        //new DroolsController().fireRule();

    }


}
