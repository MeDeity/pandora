#### 简介
    Pandora(潘多拉) SpringBoot 后端开发工具库
#### 通用说明
1.将组件库加入Springboot包扫描

pandora组件包的basePackage都是以com.mengya.*开头,一般情况下,如果你需要使用这些组件,
请在Application中加入对com.mengya.*包的扫描,否则大部分的类肯定没法加入Spring容器进行托管
```
@SpringBootApplication
@ComponentScan(basePackages = {"com.mengya.*","com.your.package"})
public class PandoraDemoApplication {
   ...
}
```

