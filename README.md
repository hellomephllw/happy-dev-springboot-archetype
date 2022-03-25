### 1.安装archetype

`mvn clean install`

> 先安装本archetype项目到本地或私服

### 2.生成项目

```
mvn archetype:generate \
    -DarchetypeGroupId=com.llw \
    -DarchetypeArtifactId=happy-dev-springboot-archetype \
    -DarchetypeVersion=1.0.0 \
    -DgroupId=com.self.app \
    -DartifactId=myApp \
    -Dversion=1.0 \
    -Dpackage=com.self.app
```

> 在workspace执行如上命令生成项目

### 3.命令解释
> * archetypeGroupId: archetype的groupId
> * archetypeArtifactId: archetype的artifactId
> * archetypeVersion: archetype的version
> * groupId: 新建项目的groupId
> * artifactId: 新建项目的artifactId
> * version: 新建项目的version
> * package: 新建项目的package