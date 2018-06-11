package org.avol.tinyservice.common.config;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import org.avol.tinyservice.url.repository.URLDao;
import org.avol.tinyservice.url.repository.impl.URLDaoImpl;
import org.avol.tinyservice.url.service.URLService;
import org.avol.tinyservice.url.service.impl.URLServiceImpl;
import org.springframework.beans.factory.annotation.Value;
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
    public URLService urlService(URLDao urlDao, CouchConfig couchConfig) {
        return new URLServiceImpl(urlDao, couchConfig.getDefaultExpiryInDays());
    }

    @Bean
    public Bucket bucket(CouchConfig couchConfig) {
        Cluster cluster = CouchbaseCluster.create(couchConfig.getBootstrapHosts());
        return cluster.openBucket(couchConfig.getBucket().getName());
    }
}
