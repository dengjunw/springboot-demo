package com.dengjunw.service;

import com.dengjunw.domain.City;
import com.dengjunw.repository.CityRepository;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CityService{
    @Resource
    private CityRepository cityRepository;

    /**
     * 添加文档
     * @param city 文档
     * @return
     */
    public Long addCity(City city){
       City c = cityRepository.save(city);
       return c.getId();
    }

    /**
     * 查询
     * @param keyword       关键字
     * @param pageNumber    页码
     * @param pageSize      数量
     * @return
     */
    public List<City> search(String keyword, Integer pageNumber, Integer pageSize){
        // 分页参数
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        //查询字段
        MatchQueryBuilder builder = QueryBuilders.matchQuery("description", keyword);
        //如果实体和数据的名称对应就会自动封装，pageable分页参数
        Page<City> citys = cityRepository.search(builder, pageable);
        return citys.getContent();
    }

    /**
     * AND语句查询
     * @param description
     * @param score
     * @return
     */
    public List<City> findByDescriptionAndScore(String description, Integer score){
        return cityRepository.findByDescriptionAndScore(description, score);
    }

    /**
     * OR语句查询
     * @param description
     * @param score
     * @return
     */
    public List<City> findByDescriptionOrScore(String description, Integer score){
        return cityRepository.findByDescriptionOrScore(description, score);
    }

    /**
     * 精确查询城市描述
     * @param description
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public Page<City> findDescription(String description, Integer pageNumber, Integer pageSize){
        return cityRepository.findByDescription(
                description,
                PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"))
        );
    }

    /**
     * NOT语句查询
     * @param description
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public Page<City> findDescriptionNot(String description, Integer pageNumber, Integer pageSize){
        return cityRepository.findByDescriptionNot(
                description,
                PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"))
        );
    }

    /**
     * LIKE语句查询
     * @param description
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public Page<City> findDescriptionLike(String description, Integer pageNumber, Integer pageSize){
        return cityRepository.findByDescriptionLike(
                description,
                PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"))
        );
    }
}
