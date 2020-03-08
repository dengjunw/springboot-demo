package com.dengjunw.controller;

import com.dengjunw.domain.City;
import com.dengjunw.service.CityService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    @Resource
    private CityService cityService;

    @PostMapping("/add")
    public Long addCity(@RequestBody City city){
        return cityService.addCity(city);
    }

    @GetMapping("/search")
    public List<City> search(@RequestParam("keyword") String keyword,
                             @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
                             @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        return cityService.search(keyword, pageNumber, pageSize);
    }

    @GetMapping("/and/find")
    public List<City> andFind(@RequestParam("description") String description,
                              @RequestParam("score") Integer score){
        return cityService.findByDescriptionAndScore(description, score);
    }

    @GetMapping("/or/find")
    public List<City> orFind(@RequestParam("description") String description,
                             @RequestParam("score") Integer score){
        return cityService.findByDescriptionOrScore(description, score);
    }

    @GetMapping("/must/find")
    public Page<City> mustFind(@RequestParam("description") String description,
                               @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
                               @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        return cityService.findDescription(description, pageNumber, pageSize);
    }

    @GetMapping("/not/find")
    public Page<City> notFind(@RequestParam("description") String description,
                              @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
                              @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        return cityService.findDescriptionNot(description, pageNumber, pageSize);
    }

    @GetMapping("/like/find")
    public Page<City> likeFind(@RequestParam("description") String description,
                               @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
                               @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        return cityService.findDescriptionLike(description, pageNumber, pageSize);
    }
}
