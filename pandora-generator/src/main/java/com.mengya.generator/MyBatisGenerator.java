package com.mengya.generator;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;


@PropertySource("classpath:security.properties")
public class MyBatisGenerator {

    //代码生成项目地址
    @Value("${mybatis.projectPath}")
    private String projectPath;

    @Autowired
    private AutoGenerator autoGenerator;
    @Autowired
    private GlobalConfig globalConfig;

    public void startGenerator() {
        if (true) {
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator();
            System.out.println(myBatisGenerator.projectPath);
        } else {
//          gc.setOutputDir(projectPath + "/masterpat/src/main/java");
//          gc.setOutputDir(projectPath + "/src/main/java");//现在是单模块
            globalConfig.setAuthor("lysoft");
            globalConfig.setOpen(false);//不需要打开输出目录
            autoGenerator.setGlobalConfig(globalConfig);

            // 数据源配置
            DataSourceConfig dsc = new DataSourceConfig();
            dsc.setUrl("jdbc:mysql://rm-wz9galx1ae6s9r1786o.mysql.rds.aliyuncs.com:3306/masterpat?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&useSSL=false&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=convertToNull");
            // dsc.setSchemaName("public");
            dsc.setDriverName("com.mysql.jdbc.Driver");
            dsc.setUsername("masterpat");
            dsc.setPassword("masterpat@2020");
            autoGenerator.setDataSource(dsc);

            // 包配置
            PackageConfig pc = new PackageConfig();
            //pc.setModuleName(scanner("模块名"));
            pc.setParent("com.liyisoft.masterpat");
            autoGenerator.setPackageInfo(pc);

//        // 自定义配置
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                // to do nothing
//            }
//        };
//        List<FileOutConfig> focList = new ArrayList<>();
//        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输入文件名称
//                return projectPath + "/masterpat/src/main/resources/mapper/" + pc.getModuleName()
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
//        cfg.setFileOutConfigList(focList);
//        autoGenerator.setCfg(cfg);
//        TemplateConfig templateConfig = new TemplateConfig();
//        templateConfig.setXml(null);
//        templateConfig.setController("/templates/mapper.xml.ftl");
//        autoGenerator.setTemplate(templateConfig);

            // 策略配置
            StrategyConfig strategy = new StrategyConfig();
            strategy.setNaming(NamingStrategy.underline_to_camel);
            strategy.setColumnNaming(NamingStrategy.underline_to_camel);
            strategy.setSuperEntityClass("com.liyisoft.masterpat.common.BaseEntity");
            strategy.setEntityLombokModel(true);
            strategy.setSuperControllerClass("com.liyisoft.masterpat.common.BaseController");
            strategy.setInclude(new String[]{"pat_address", "pat_advance_order", "pat_coin", "pat_message", "pat_message_user", "pat_negative", "pat_order", "pat_photographs_order",
                    "pat_sigin", "pat_take_photo_order", "pat_trans_coin", "pat_user_follow", "pat_user_share"});
            strategy.setSuperEntityColumns("id");
            strategy.setControllerMappingHyphenStyle(true);
            strategy.setTablePrefix(pc.getModuleName() + "_");
            autoGenerator.setStrategy(strategy);
            // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
            autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
            autoGenerator.execute();
        }
    }

}