package com.mazhj.felix.forum.websocket;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.mazhj.common.core.utils.SpringUtil;
import com.mazhj.felix.forum.websocket.handler.UltimateHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.Future;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author mazhj
 */
@Slf4j
@Component
public class NettyServer implements ApplicationRunner {

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    private final static String SERVICE_NAME = "netty-service";

    private final static int SERVER_PORT = 7001;

    private NamingService namingService;

    private final EventLoopGroup boss = new NioEventLoopGroup(1);

    private final EventLoopGroup worker = new NioEventLoopGroup();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        register();
        bootstrap();
    }

    private void register() throws NacosException {
        //读取配置
        String serverAddr = this.nacosDiscoveryProperties.getServerAddr();
        String namespace = this.nacosDiscoveryProperties.getNamespace();
        Properties config = new Properties();
        config.put("serverAddr",serverAddr);
        config.put("namespace",namespace);
        namingService = NacosFactory.createNamingService(config);
        //注册实例
        Instance instance = new Instance();
        instance.setIp(this.nacosDiscoveryProperties.getIp());
        instance.setPort(SERVER_PORT);
        namingService.registerInstance(SERVICE_NAME,instance);
    }

    private void bootstrap() throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boss,worker)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,128)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        //心跳处理器，心跳检测。
                        pipeline.addLast(new IdleStateHandler(30, 0, 0));
                        pipeline.addLast(new HttpServerCodec());
                        pipeline.addLast(new ChunkedWriteHandler());
                        pipeline.addLast(new HttpObjectAggregator(1024*100,false));
                        //将http请求升级到ws
                        pipeline.addLast(new WebSocketServerProtocolHandler("/"));
                        //自定义终极处理器,处理用户请求信息
                        pipeline.addLast(SpringUtil.getBean(UltimateHandler.class));
                    }
                });
        serverBootstrap.bind(SERVER_PORT).sync();
    }

    @PreDestroy
    private void destroy() throws NacosException {
        namingService.deregisterInstance(SERVICE_NAME,this.nacosDiscoveryProperties.getIp(),SERVER_PORT);
        Future<?> bossFuture = boss.shutdownGracefully();
        Future<?> workerFuture = worker.shutdownGracefully();
        bossFuture.syncUninterruptibly();
        workerFuture.syncUninterruptibly();
        log.info("关闭 netty server ");
    }
}
