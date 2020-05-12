##安装FastDFS环境
    https://mp.weixin.qq.com/s?__biz=MzI1NDY0MTkzNQ==&mid=2247488005&idx=1&sn=001d4902d240613654b8c6ab3dcfa89d&scene=21#wechat_redirect
##安装NGINX
    https://app.yinxiang.com/fx/91ccf97d-38bb-46b2-a7f3-231a36bbde9a
##配置文件参考
    https://github.com/search?q=fastdfs   
##Maven依赖参考
    https://mvnrepository.com/artifact/net.oschina.zcx7878/fastdfs-client-java/1.27.0.0
##可以上传下载,但是浏览器访问404
    解决办法:在nginx.conf配置文件中注释掉第一行,修改为 user root;
##使用FastDFS时需要准备三件事
    -启动tracker
        /usr/bin/fdfs_trackerd /etc/fdfs/tracker.conf start    
    -启动storage
        /usr/bin/fdfs_storaged /etc/fdfs/storage.conf start
    -启动Nginx
        cd /usr/local/nginx/sbin
        ./nginx
