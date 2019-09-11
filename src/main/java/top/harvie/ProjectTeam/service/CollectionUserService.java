package top.harvie.ProjectTeam.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.harvie.ProjectTeam.dao.mapper.CollectionMapper;
import top.harvie.ProjectTeam.dao.pojo.UserCollection;
import top.harvie.ProjectTeam.dao.pojo.UserCollectionDetail;

import java.util.List;

@Service
public class CollectionUserService {

    private static Logger log = LogManager.getLogger(CollectionUserService.class);

    @Autowired
    CollectionMapper collectionMapper;

    //增
    public Integer add(UserCollection userCollection) throws Exception
    {
        collectionMapper.add(userCollection);
        return userCollection.getId();
    }
    //删
    public void delete(Integer id) throws Exception
    {
        collectionMapper.delete(id);
    }
    //改
    public void update(UserCollection userCollection) throws Exception
    {
        collectionMapper.update(userCollection);
    }
    //查
    public UserCollection select(Integer id)
    {
        UserCollection userCollection=collectionMapper.select(id);
        return userCollection;
    }

    public List<UserCollection> select()
    {
        List<UserCollection> navigationList=collectionMapper.selectAll();
        return navigationList;
    }

    public List<UserCollection> selectByUserId(String id)
    {
        List<UserCollection> userCollectionList=collectionMapper.selectByUserid(id);
        return userCollectionList;
    }

    public List<UserCollectionDetail> selectDetailByUserId(String id)
    {
        List<UserCollectionDetail> userCollectionList=collectionMapper.selectDetailByUserid(id);
        return userCollectionList;
    }


    
}
