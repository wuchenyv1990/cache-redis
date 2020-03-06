#cache-redis

spring cache + redis<br>

- json序列化反序列化泛型配置
- 多个cacheManager配置
- spring cache注解配置
- redisson连接，指定了spring.redis.redisson.config 需要在文件里配置

### 简要说明
- user相关有增删查三个接口，通过配置spring cache来实现缓存
- userDao用redis模拟，使用一个hash保存，key为db:users
- 依赖中使用了redisson starter，指定config为redisson.yaml，因启动有warn提示、和可读性配置了默认的序列化FstCodec为json
- redisson提供了一些分布式锁等组件，这里只用了一个计数器，另外一个实现是直接使用redisTemplate来生成userId，都是按照ct:userId为key