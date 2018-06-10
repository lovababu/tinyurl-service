package org.avol.tiny.config;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import org.avol.tiny.url.repository.URLDao;
import org.avol.tiny.url.repository.impl.URLDaoImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * spring application configuration.
 *
 * @author Durga, Padala on 10/06/18.
 */
@Configuration
@EnableConfigurationProperties
public class AppConfig {

    @Bean
    public URLDao urlDao(Bucket bucket) {
        return new URLDaoImpl(bucket);
    }

    @Bean
    public Bucket bucket(CouchConfig couchConfig) {
        Cluster cluster = CouchbaseCluster.create(couchConfig.getBootstrapHosts());
        return cluster.openBucket(couchConfig.getBucket().getName());
    }
}
