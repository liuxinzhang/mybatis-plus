package com.study.mybatisplusgenerator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 代码生成器
 * 注：对于经常改变的配置请通过控制台进行输入
 * @author elijah
 */
public class CodeGenerator {

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 代码生成器生成器配置
        config(mpg);

        // 执行代码生成
        mpg.execute();
    }

    private static void config(AutoGenerator mpg) {
        // 全局配置
        mpg.setGlobalConfig(getGlobalConfig());

        // 数据源配置
        mpg.setDataSource(getDataSourceConfig());

        // 包配置
        PackageConfig pc = getPackageConfig();
        mpg.setPackageInfo(pc);

        // 策略配置
        mpg.setStrategy(getStrategyConfig());

        // 自定义配置
        InjectionConfig ic = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();

        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return System.getProperty("user.dir") + "/mybatis-plus-generator/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        ic.setFileOutConfigList(focList);
        mpg.setCfg(ic);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
    }

    /**
     * 获取策略配置
     * https://baomidou.com/pages/061573/
     * @return
     */
    private static StrategyConfig getStrategyConfig() {
        StrategyConfig sc = new StrategyConfig();
        // 数据库表映射到实体的命名策略
        sc.setNaming(NamingStrategy.underline_to_camel);
        // 数据库表字段映射到实体的命名策略
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        // 实体类是否启用lombok进行生成
        sc.setEntityLombokModel(true);
        // Controller是否启用rest
        sc.setRestControllerStyle(true);
        // 当前参与生成的表名设置
        // sc.setInclude(scanner("表名，多个英文逗号分割").split(","));
        // 表名前缀设置，生成的代码不会包含该前缀，例如：tb_user -> User、UserMapper、UserMapper.xml
        sc.setTablePrefix("co_");

        // 实体类公共父类设置（写全路径类名），没有就不用设置
        // sc.setSuperEntityClass("com.study.domain.BaseDomain");
        //驼峰转连字符，@RequestMapping("/managerUserActionHistory")->@RequestMapping("/manager-user-action-history")
        // sc.setControllerMappingHyphenStyle(true);
        // Controller公共父类设置（写全路径类名），没有就不用设置
        // sc.setSuperControllerClass("com.study.controller.BaseController");
        // 写于父类中的公共字段
        // sc.setSuperEntityColumns("id");
        return sc;
    }

    /**
     * 获取包配置
     * https://baomidou.com/pages/061573/
     * @return
     */
    private static PackageConfig getPackageConfig() {
        PackageConfig pc = new PackageConfig();
        // 父包名
        pc.setParent("com.hrm");
        // 模块名，指的是父包下的某个模块，不是工程模块
        pc.setModuleName(scanner("模块名"));
        // 实体类包名
        pc.setEntity("domain");
        // mapper 包名
        pc.setMapper("mapper");
        return pc;
    }

    /**
     * 获取全局配置
     * https://baomidou.com/pages/061573/#%E5%85%A8%E5%B1%80%E7%AD%96%E7%95%A5-globalconfig-%E9%85%8D%E7%BD%AE
     * @return
     */
    private static GlobalConfig getGlobalConfig() {
        GlobalConfig gc = new GlobalConfig();
        // 生成文件的输出目录，默认值：D盘根目录，以下设置就是该工程的java目录
        gc.setOutputDir(System.getProperty("user.dir") + "/mybatis-plus-generator/src/main/java");
        // 是否覆盖已有文件
        gc.setFileOverride(false);
        // 生成完毕是否打开输出目录，默认值：true
        gc.setOpen(false);
        // 设置作者，默认值：null
        gc.setAuthor("elijah");
        // mapper 命名方式，默认值：null，例如：%sDao 生成 UserDao、%sMapper 生成 UserMapper
        gc.setMapperName("%sMapper");
        // Mapper xml 命名方式，默认值：null，%sDao 生成 UserDao.xml、%sMapper 生成 UserMapper.xml
        gc.setXmlName("%sMapper");
        // 指定生成的主键的 ID 类型，具体查看枚举IdType
        gc.setIdType(IdType.ASSIGN_ID);
        return gc;
    }

    /**
     * 获取数据源配置
     *
     * @return
     */
    private static DataSourceConfig getDataSourceConfig() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/hrm?useUnicode=true&useSSL=false&characterEncoding=utf8");
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");
        return dataSourceConfig;
    }

    /**
     * 读取控制台内容
     */
    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}