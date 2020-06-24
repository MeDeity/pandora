package com.mengya.generator;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.mengya.generator.config.MyBatisGeneratorProperties;
import org.springframework.stereotype.Service;

@Service
public class MyBatisGenerator {

    private MyBatisGeneratorProperties myBatisGeneratorProperties;

    public MyBatisGenerator(MyBatisGeneratorProperties myBatisGeneratorProperties){
        this.myBatisGeneratorProperties = myBatisGeneratorProperties;
    }

    //代码生成项目地址

    private String projectPath;


    public void startGenerator() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setAuthor(myBatisGeneratorProperties.getAuthor());
        globalConfig.setOpen(false);//不需要打开输出目录
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(myBatisGeneratorProperties.getDatabaseUrl());
        // dsc.setSchemaName("public");
        dsc.setDriverName(myBatisGeneratorProperties.getDriverName());
        dsc.setUsername(myBatisGeneratorProperties.getDatabaseUser());
        dsc.setPassword(myBatisGeneratorProperties.getDatabasePassword());
        autoGenerator.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName(scanner("模块名"));
        pc.setParent(myBatisGeneratorProperties.getPackageName());
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