#### 如何使用
1. 在Application中增加扫描组件package
```java
@ComponentScan(basePackages = {"com.mengya.log"})
@MapperScan(basePackages = {"com.mengya.log.mybatis"})
public class VideoServerApplication {
    
}
```
2. 执行sys_log.sql,初始化数据库

3. 在方法级别中添加
```java
public class Test{
    @Log("备注信息")
    public void methodName(){
        
    }
}
```