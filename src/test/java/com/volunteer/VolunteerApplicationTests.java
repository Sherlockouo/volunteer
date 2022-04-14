package com.volunteer;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class VolunteerApplicationTests {

    @Test
    void contextLoads() {
        List<String> tables = new ArrayList<>();
        tables.add("enroll_team");
//        tables.add("role");
//        tables.add("activity");
//        tables.add("article");
//        tables.add("comment");
//        tables.add("enroll");
//        tables.add("report");
//        tables.add("team");



        FastAutoGenerator.create("jdbc:mysql://localhost:3306/volunteer?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "wdf123456")
                .globalConfig(builder -> {
                    builder.author("wb") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("/Users/wdf/Desktop"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com") // 设置父包名
                            .moduleName("volunteer")
                            .entity("pojo")
                            .controller("controller")
                            .service("service")
                            .serviceImpl("impl")
                            .mapper("mapper");

                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables)
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName(("%sServiceImpl"))
                            .entityBuilder()
                            .enableLombok()
                            .enableTableFieldAnnotation()
                            .controllerBuilder()
                            .formatFileName("%sController")
                            .enableRestStyle()
                            .mapperBuilder()
                            .mapperBuilder()
                            .enableBaseColumnList()
                            .superClass(BaseMapper.class)
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
