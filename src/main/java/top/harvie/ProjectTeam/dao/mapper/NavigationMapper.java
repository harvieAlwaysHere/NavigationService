package top.harvie.ProjectTeam.dao.mapper;

import org.apache.ibatis.annotations.*;
import top.harvie.ProjectTeam.dao.pojo.Navigation;

import java.io.IOException;
import java.util.List;

public interface NavigationMapper {

    //增
    @Insert("INSERT INTO navigation(name,time,requirement,introduction,applyway,award,addition,img,readed,status,creattime) VALUES(#{name},#{time},#{requirement},#{introduction},#{applyway},#{award},#{addition},#{img},#{readed},#{status},#{creattime})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void add(Navigation navigation)throws IOException;
    //删
    @Delete("DELETE FROM navigation WHERE id=#{id}")
    void delete(Integer id)throws IOException;
    //改
    @Update("UPDATE navigation SET name=#{name},time=#{time},requirement=#{requirement},introduction=#{introduction},applyway=#{applyway},award=#{award},addition=#{addition},img=#{img},readed=#{readed},status=#{status},creattime=#{creattime} WHERE id=#{id}")
    void update(Navigation navigation)throws IOException;
    //查
    @Select( "SELECT * FROM navigation WHERE status=1")
    List<Navigation> selectAllNavigation();

    @Select("SELECT * FROM navigation WHERE id=#{id}")
    Navigation select(Integer id);

    @Select( "SELECT addition FROM navigation")
    List<String> selectAllLink();

    @Select( "SELECT * FROM navigation WHERE status=2")
    List<Navigation> selectAllSpider();





}
