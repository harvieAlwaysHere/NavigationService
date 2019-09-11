package top.harvie.ProjectTeam.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import top.harvie.ProjectTeam.conf.Tools;
import top.harvie.ProjectTeam.dao.pojo.UserCollection;
import top.harvie.ProjectTeam.dao.pojo.UserCollectionDetail;
import top.harvie.ProjectTeam.service.CollectionUserService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/api")
@Api(value="Collection",description = "用户收藏接口")
public class UserCollectionController {

    @Autowired
    CollectionUserService collectionUserService;

    //增加一条组队条目
    @PostMapping(value = "/collection")
    @ApiOperation(value="用于增加一个收藏条目",notes="用于增加一个收藏条目")
    public Map add(
            @ApiParam(required = true,name="collection",value = "收藏信息(不需要填入id,status,createtime)") @RequestBody UserCollection userCollection
    ){
        Map<String,Object> response=new HashMap<>();
        try{
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            userCollection.setCreatetime(df.format(new Date()));
            userCollection.setStatus("1");
            collectionUserService.add(userCollection);
            response.put("status","success");
            response.put("massage",null);
            response.put("data",userCollection);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }

    //根据ID删除一条收藏信息
    @PostMapping(value = "/collection/delete")
    @ApiOperation(value="根据ID删除指定收藏条目",notes="根据ID删除指定收藏条目")
    public Map delete(
            @ApiParam(required = true,name="id",value = "需要删除的收藏的ID") @RequestParam("id") Integer id
    ){
        Map<Object,Object> response=new HashMap<>();
        try{
            collectionUserService.delete(id);
            response.put("status","success");
            response.put("massage",null);
            response.put("data",null);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }

    //更新组队信息
    @PostMapping(value = "/collection/update")
    @ApiOperation(value="根据ID更新指定收藏条目",notes="根据ID更新指定收藏条目")
    public Map update(
            @ApiParam(required = true,name="collection",value = "需要更新的收藏信息(ID必填)") @RequestBody UserCollection userCollection
    ){
        Map<Object,Object> response=new HashMap<>();
        try{
            Integer id=userCollection.getId();
            UserCollection updateTeam=collectionUserService.select(id);
            Tools.update(updateTeam,userCollection);
            collectionUserService.update(updateTeam);
            response.put("status","success");
            response.put("massage",null);
            response.put("data",updateTeam);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }

    //查询所有收藏信息
    @GetMapping(value = "/collection/all")
    @ApiOperation(value="询所有收藏信息",notes="询所有收藏信息")
    public Map select(){
        Map<Object,Object> response=new HashMap<>();
        try{
            List<UserCollection> teamList=collectionUserService.select();
            response.put("status","success");
            response.put("massage",null);
            response.put("data",teamList);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }

    @GetMapping(value = "/collection")
    @ApiOperation(value="查询指定ID收藏信息",notes="查询指定ID收藏信息")
    public Map selectById(
            @ApiParam(required = true,name="id",value = "需要查询的收藏的ID") @RequestParam("id") Integer id
    ){
        Map<Object,Object> response=new HashMap<>();
        try{
            UserCollection userCollection=collectionUserService.select(id);
            response.put("status","success");
            response.put("massage",null);
            response.put("data",userCollection);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }

    @GetMapping(value = "/collection/user")
    @ApiOperation(value="查询指定userid收藏信息",notes="查询指定userid收藏信息")
    public Map selectByUserId(
            @ApiParam(required = true,name="userid",value = "查询指定userid收藏信息") @RequestParam("userid") String userid
    ){
        Map<Object,Object> response=new HashMap<>();
        try{
            List<UserCollectionDetail> userCollectionDetailList=collectionUserService.selectDetailByUserId(userid);
            response.put("status","success");
            response.put("massage",null);
            response.put("data",userCollectionDetailList);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }
    
}
