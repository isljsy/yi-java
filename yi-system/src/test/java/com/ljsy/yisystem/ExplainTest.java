package com.ljsy.yisystem;

import com.ljsy.yisystem.service.impl.DirExplainServiceImpl;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExplainTest {



    @Test
    public void getExplainValue(){
        System.out.println(new DirExplainServiceImpl().getExplainValue(1));
    }

}
