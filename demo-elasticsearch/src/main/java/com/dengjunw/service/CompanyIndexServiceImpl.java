package com.dengjunw.service;

import com.dengjunw.domain.CompanyIndex;
import com.dengjunw.mapper.CompanyIndexResultMapper;
import com.dengjunw.util.PageHelper;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CompanyIndexServiceImpl {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public PageHelper<CompanyIndex> searchCompany(Integer userId, String keyword,
                                                  Integer topHundred, Integer highLevel,
                                                  Integer focus, Integer pageNum, Integer size) {
        BoolQueryBuilder boolQueryBuilderSearch = QueryBuilders.boolQuery();
        if (topHundred != null && topHundred == 1) {
            boolQueryBuilderSearch.must(QueryBuilders.termQuery("topHundred", 1));
        }
        if (highLevel != null && highLevel == 1) {
            boolQueryBuilderSearch.must(QueryBuilders.termQuery("highLevel", 1));
        }
        if (focus != null && focus == 1) {
            boolQueryBuilderSearch.must(QueryBuilders.matchPhraseQuery("focusUser", userId));
        }

        BoolQueryBuilder boolQueryBuilderKeyword = QueryBuilders.boolQuery();
        if (!StringUtils.isEmpty(keyword)) {
            boolQueryBuilderKeyword.should(QueryBuilders.matchQuery("companyName", keyword));
            boolQueryBuilderKeyword.should(QueryBuilders.matchQuery("frName", keyword));
        }
        boolQueryBuilderSearch.must(boolQueryBuilderKeyword);

//        DisMaxQueryBuilder disMaxQueryBuilder = QueryBuilders.disMaxQuery();
//        if (StringUtils.isNotBlank(keyword)) {
//            disMaxQueryBuilder.add(QueryBuilders.termQuery("companyName", keyword));
//            disMaxQueryBuilder.add(QueryBuilders.termQuery("frName", keyword));
//        }
//        if (topHundred != null && topHundred == 1) {
//            disMaxQueryBuilder.add(QueryBuilders.termQuery("topHundred", 1));
//        }
//        if (highLevel != null && highLevel == 1) {
//            disMaxQueryBuilder.add(QueryBuilders.termQuery("highLevel", 1));
//        }
//        if (focus != null && focus == 1) {
//            disMaxQueryBuilder.add(QueryBuilders.matchPhraseQuery("focusUser", userId));
//        }

        List<String> highlightFields = Lists.newArrayList("companyName", "frName");
        HighlightBuilder.Field[] fieldsArr = new HighlightBuilder.Field[highlightFields.size()];
        List<HighlightBuilder.Field> list = highlightFields.stream()
                .map(fieldName -> new HighlightBuilder.Field(fieldName)
                        .preTags("<span style=\"color:red\">").postTags("</span>"))
                .collect(Collectors.toList());
        HighlightBuilder.Field[] fields = list.toArray(fieldsArr);

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilderSearch)
//                .withQuery(boolQueryBuilderKeyword)
                .withHighlightFields(fields)
                .withPageable(PageRequest.of(pageNum - 1, size))
                .build();

        Page<CompanyIndex> page = elasticsearchRestTemplate.queryForPage(searchQuery, CompanyIndex.class, new CompanyIndexResultMapper());
        if (ObjectUtils.isEmpty(page)) {
            PageHelper<CompanyIndex> companyIndexPageHelper = new PageHelper<>(0, pageNum, size);
            return companyIndexPageHelper;
        }
        Long totalElements = page.getTotalElements();
        List<CompanyIndex> content = page.getContent();
        for (CompanyIndex companyIndex : content) {
            if (!StringUtils.isEmpty(companyIndex.getFocusUser())) {
                List<String> strings = Splitter.on(",").splitToList(companyIndex.getFocusUser());
                if (strings.contains(userId + "")) {
                    companyIndex.setFocus(1);
                }
            }
        }
        PageHelper<CompanyIndex> companyIndexPageHelper = new PageHelper<>(totalElements.intValue(), pageNum, size);
        companyIndexPageHelper.setData(page.getContent());
        return companyIndexPageHelper;
    }
}
