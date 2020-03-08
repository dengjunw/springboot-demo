package com.dengjunw.domain;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Data
@Document(indexName = "cityindex", type = "city")
public class City implements Serializable {
    /**
     * 城市编号
     */
    private Long id;

    /**
     * 省份编号
     */
    private Long provinceid;

    /**
     * 城市名称
     */
    private String cityname;

    /**
     * 描述
     */
    private String description;

    /**
     * 城市评分
     */
    private Integer score;
}
