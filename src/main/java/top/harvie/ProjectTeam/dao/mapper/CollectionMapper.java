package top.harvie.ProjectTeam.dao.mapper;

import org.apache.ibatis.annotations.*;
import top.harvie.ProjectTeam.dao.pojo.UserCollection;
import top.harvie.ProjectTeam.dao.pojo.UserCollectionDetail;

import java.io.IOException;
import java.util.List;

public interface CollectionMapper {
    
    //增
    @Insert("INSERT INTO collection (userid,navigationid,createtime,status) VALUES (#{userid},#{navigationid},#{createtime},#{status})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void add(UserCollection userCollection)throws IOException;
    //删
    @Delete("DELETE FROM collection WHERE id=#{id}")
    void delete(Integer id)throws IOException;
    //改
    @Update("UPDATE collection SET userid=#{userid}, navigationid=#{navigationid}, status=#{status} WHERE id=#{id}")
    void update(UserCollection userCollection)throws IOException;
    //查
    @Select( "SELECT * FROM collection")
    List<UserCollection> selectAll();

    @Select("SELECT * FROM collection WHERE id=#{id}")
    UserCollection select(Integer id);

    @Select("SELECT * FROM collection WHERE userid=#{userid}")
    List<UserCollection> selectByUserid(String userid);

    @Select("SELECT collection.id,collection.userid,collection.status as collectionstatus,collection.navigationid,navigation.name,navigation.img,navigation.status as navigationstatus FROM collection LEFT OUTER JOIN navigation ON(collection.navigationid = navigation.id) WHERE collection.userid=#{userid}")
    List<UserCollectionDetail> selectDetailByUserid(String userid);


}
