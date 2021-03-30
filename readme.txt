2、修改application.properties
# 设置用户名
spring.mail.username=你的QQ号@qq.com
 
# 设置密码，该处的密码是QQ邮箱开启SMTP的授权码而非QQ密码
spring.mail.password=你自己的QQ授权码

下列你也可以选择更改
#设置文件的存储路径
filepathconfig.fileUrl=D:\\videoweb
filepathconfig.imagePath=\\images\\
filepathconfig.videopath=\\videos\\

#设置分页的大小
pagesize.pageSize=8
【注】如果你要上传到linux服务器上，因路径与window不同，得修改源代码。

3、运行软件：jdk1.8、mysql5.0、ffmpeg、maven3

4、启动springboot项目：mvn spring-boot:run

5、项目打包：mvn package

5、运行jar命令：java -jar videoweb.jar






# 最新的token, 过这个日期，联系邮箱liucm1997@qq.com
# token：20210531-f062ee98b0207dd3576efa93762473b7

