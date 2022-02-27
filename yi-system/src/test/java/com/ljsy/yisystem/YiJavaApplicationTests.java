package com.ljsy;

import com.ljsy.yisystem.util.PinyinHelper;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class YiJavaApplicationTests {

    @Test
    public void contextLoads() {
        PinyinHelper pinyinHelper = new PinyinHelper();
        System.out.println(pinyinHelper.removeTone("（ɛ³³）ke⁵⁵kei⁵⁵"));
    }

}
