package com.dengjunw.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ResultsMapper;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;


@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ElasticConfig {
    @Bean
    public ElasticsearchRestTemplate elasticsearchTemplate(
            RestHighLevelClient client, ElasticsearchConverter converter,
            ResultsMapper resultsMapper) {
        return new ElasticsearchRestTemplate(client, converter, resultsMapper);
    }
}
