package org.acme.utilities;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RedisService {

    private final ValueCommands<String, String> commands;

    @Inject
    public RedisService(RedisDataSource redisDataSource) {
        this.commands = redisDataSource.value(String.class);
    }

    public void setex(String key, int seconds, String value) {
        commands.setex(key, seconds, value);
    }

    public String get(String key) {
        return commands.get(key);
    }
}
