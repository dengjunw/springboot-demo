package com.dengjunw.mapper;

import com.alibaba.fastjson.JSON;
import com.dengjunw.domain.CompanyIndex;
import com.google.common.collect.Lists;
import lombok.Data;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class CompanyIndexResultMapper implements SearchResultMapper, Serializable {

    @Override
    public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> aClass, Pageable pageable) {

        List<CompanyIndex> list = Lists.newArrayList();
        SearchHits hits = response.getHits();
        for (SearchHit searchHit : hits) {
            if (hits.getHits().length <= 0) {
                return null;
            }
            Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
            CompanyIndex data = JSON.parseObject(JSON.toJSONString(sourceAsMap), CompanyIndex.class);
            data.setId(Integer.parseInt(searchHit.getId()));

            HighlightField addr = searchHit.getHighlightFields().get("companyName");
            if (addr != null) {
                data.setCompanyName(addr.fragments()[0].toString());
            }
            HighlightField name = searchHit.getHighlightFields().get("frName");
            if (name != null) {
                data.setFrName(name.fragments()[0].toString());
            }
            list.add(data);
        }
        if (list.size() > 0) {
            AggregatedPage<T> result = new AggregatedPageImpl<T>((List<T>) list, pageable,
                    response.getHits().getTotalHits());
            return result;
        }
        return null;
    }

    @Override
    public <T> T mapSearchHit(SearchHit searchHit, Class<T> aClass) {
        return null;
    }
}
