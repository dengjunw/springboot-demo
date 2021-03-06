package com.dengjunw.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ResultsMapper;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;


@Configuration
public class ElasticConfig {

    @Bean
    public ElasticsearchRestTemplate elasticsearchTemplate(
            RestHighLevelClient client, ElasticsearchConverter converter,
            ResultsMapper resultsMapper) {
//        AnnotationConfigApplicationContext
        return new ElasticsearchRestTemplate(client, converter, resultsMapper);
    }
}
