# mybatis-plus快速入门

## 工程环境
以下是我所使用的工程环境，不一定需要和我保持完全一致<br />IDEA：2019.03<br />Maven：3.8.1<br />JDK：8<br />SpringBoot：2.7.3<br />H2：1.4.200
## 初始化工程
创建一个空的 Spring Boot 工程，引入相关的依赖
```text
<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>2.7.3</version>
  <relativePath/>
</parent>

<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
  </dependency>

  <dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.4.2</version>
  </dependency>
  <dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <version>1.4.200</version>
    <scope>runtime</scope>
  </dependency>
  <dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
  </dependency>
</dependencies>
```
## 数据库准备
引入工程所需要的数据库脚本（包括表结构和表数据），工程将以 H2 作为默认数据库进行演示
```sql
-- 表结构
DROP TABLE IF EXISTS user;

CREATE TABLE user
(
  id    BIGINT(20)  NOT NULL COMMENT '主键ID',
  name  VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
  age   INT(11)     NULL DEFAULT NULL COMMENT '年龄',
  email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (id)
);

-- 表数据
DELETE
FROM user;

INSERT INTO user (id, name, age, email)
VALUES (1, 'Jone', 18, 'test1@baomidou.com'),
       (2, 'Jack', 20, 'test2@baomidou.com'),
       (3, 'Tom', 28, 'test3@baomidou.com'),
       (4, 'Sandy', 21, 'test4@baomidou.com'),
       (5, 'Billie', 24, 'test5@baomidou.com');
```
## 配置

1. application.yml配置
```yml
# DataSource Config
spring:
  datasource:
    driver-class-name: org.h2.Driver
    url: jdbc:h2:mem:test
    username: root
    password: test
  sql:
    init:
      schema-locations: classpath:db/schema-h2.sql
      data-locations: classpath:db/data-h2.sql
```

2. Mapper扫描配置

在启动类上添加`@MapperScan("包扫描路径")`注解
```java
@SpringBootApplication
@MapperScan("com.study.mybatisplusquickstart.mapper")
public class MybatisPlusQuickstartApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusQuickstartApplication.class, args);
    }

}
```
## 编码

1. 引入实体类
```java
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
```

2. 引入mapper
```java
public interface UserMapper extends BaseMapper<User> {

}
```
## 测试
```java
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

}
```

