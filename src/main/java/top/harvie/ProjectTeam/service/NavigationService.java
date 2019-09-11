package top.harvie.ProjectTeam.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.harvie.ProjectTeam.dao.mapper.NavigationMapper;
import top.harvie.ProjectTeam.dao.pojo.Navigation;

import java.util.List;

@Service
@CacheConfig(cacheNames = "navigation")
public class NavigationService {

    private static Logger log = LogManager.getLogger(NavigationService.class);

    @Autowired
    NavigationMapper navigationMapper;

    //增
    @CacheEvict(key="'NavigationCache'")
    public Integer add(Navigation navigation) throws Exception
    {
        navigationMapper.add(navigation);
        return navigation.getId();
    }
    //删
    @CacheEvict(key="'NavigationCache'")
    public void delete(Integer id) throws Exception
    {
        navigationMapper.delete(id);
    }
    //改
    @CacheEvict(key="'NavigationCache'")
    public void update(Navigation navigation) throws Exception
    {
        navigationMapper.update(navigation);
    }
    //查
    public Navigation select(Integer id)
    {
        Navigation navigation=navigationMapper.select(id);
        return navigation;
    }

    @Cacheable(key="'NavigationCache'")
    public List<Navigation> select()
    {
        List<Navigation> navigationList=navigationMapper.selectAllNavigation();
        return navigationList;
    }

    public List<Navigation> selectSpider()
    {
        List<Navigation> navigationList=navigationMapper.selectAllSpider();
        return navigationList;
    }
}
