package com.ljsy;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class Generator {

    public static void main(String[] args) {
        String basePath = "H:\\yi-java\\generator\\gen";
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/dir?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useSSL=false&useTimezone=true&serverTimezone=UTC",
                        "root", "123456") //数据库连接
                .globalConfig(builder -> {
                    builder.author("ljsy") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(basePath); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.ljsy") // 设置父包名
                            .moduleName("yisystem") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, basePath+"\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("sys_user_role"); // 设置需要生成的表名
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
