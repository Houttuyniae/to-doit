package io.todoit.core.git;

import io.todoit.common.expection.BizException;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author:zhangd
 * @date:2019/6/8 12:38
 */
@Slf4j
@Component
public class GitUtils {

    @Value("${git.path}")
    private String path;
    @Value("${git.github.username}")
    private String username;
    @Value("${git.github.password}")
    private String password;

    private static Git git;
    private static UsernamePasswordCredentialsProvider auth;

    /**
     * @PostConstruct 被用来修饰一个非静态的void（）方法。
     */
    @PostConstruct
    private void init(){
        try {
            File file = new File(path);
            if(!file.exists()){
                throw new FileNotFoundException();
            }
            git = Git.open(file);
            auth = new UsernamePasswordCredentialsProvider(username, password);

        } catch (IOException e) {
            log.error("init git error" ,e);
        }
    }

    public static void push(String message){
        try {
            git.add().addFilepattern("*").call();
            git.commit().setMessage(message).call();
            git.push().setCredentialsProvider(auth).call();
        } catch (GitAPIException e) {
            log.error("git remote push error" ,e);
            throw new RuntimeException(e);
        }
    }

}
