package com.example.demo.common;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Demo example, execute the main method and enter the module table name in the console to automatically generate the corresponding project directory
public class CodeGenerator {

    /**
     * <p>
     * Read console input
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("Please enter " + tip + ":");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("Please enter the correct " + tip + "!");
    }

    public static void main(String[] args) {
        // Code generator
        AutoGenerator mpg = new AutoGenerator();

        // Global configuration
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir")+"/demo";
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("demo");
        gc.setOpen(false);
        gc.setSwagger2(true);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(false);
        // gc.setSwagger2(true); Entity property Swagger2 annotation
        gc.setServiceName("%sService");
        mpg.setGlobalConfig(gc);

        // Data source configuration
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("6625332008");
        mpg.setDataSource(dsc);

        // Package configuration
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.example.demo");
        pc.setMapper("mapper");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        // Custom configuration
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // If the template engine is freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // If the template engine is velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // Custom output configuration
        List<FileOutConfig> focList = new ArrayList<>();
        // Custom configurations will be output first
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // Custom output filename. If you set prefixes/suffixes for Entity, note that the XML name will change accordingly!!
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // Check if custom directories need to be created
                checkDir("Directories created by default methods, use custom directories");
                if (fileType == FileType.MAPPER) {
                    // Check if the mapper file already exists to avoid regeneration
                    return !new File(filePath).exists();
                }
                // Allow template file generation
                return true;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // Template configuration
        TemplateConfig templateConfig = new TemplateConfig();

        // Configure custom output templates
        // Specify custom template paths. Do not include .ftl/.vm, as the template engine will automatically recognize them
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // Strategy configuration
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass("Your own parent entity class, leave unset if not needed!");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // Common parent class
        //strategy.setSuperControllerClass("Your own parent controller class, leave unset if not needed!");
        // Common fields in the parent class
        //strategy.setSuperEntityColumns("id");
        strategy.setInclude(scanner("Table names, separated by commas").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        //strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
