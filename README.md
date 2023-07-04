### protoc编译Java文件命令
```cmd
protoc --java_out=${OUTPUT_DIR} path/to/your/proto/file
```

### dmzj api
#### 技术栈：
1. 语言: kotlin 1.8.22
2. 框架: Spring boot 3.1.1
3. 服务器: undertow
4. 网络请求: Retrofit 2.9 + OkHttp3 4.11.0
5. Json解析: Gson 2.10.1

#### 2023/07/04更新
+ 更新Spring boot大版本
+ 使用undertow替换tomcat
+ 使用Retrofit2 + OkHttp3替换webflux进行网络请求
+ 使用Gson序列化返回对象

### 完成内容
1. 新闻API
2. 漫画API
3. 小说API

### TODO
1. 用户API