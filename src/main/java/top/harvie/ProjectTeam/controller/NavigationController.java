package top.harvie.ProjectTeam.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import top.harvie.ProjectTeam.conf.Tools;
import top.harvie.ProjectTeam.dao.mapper.NavigationMapper;
import top.harvie.ProjectTeam.dao.pojo.Navigation;
import top.harvie.ProjectTeam.service.NavigationService;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/api")
@Api(value="Navigation",description = "页面导航栏接口")
public class NavigationController {

    @Autowired
    NavigationService navigationService;

    //增加一条导航栏条目
    @PostMapping(value = "/navigation")
    @ApiOperation(value="用于增加导航栏条目",notes="用于增加导航栏条目")
    public Map add(
            @ApiParam(required = true,name="navigation",value = "导航栏信息") @RequestBody Navigation navigation
    ){
        Map<String,Object> response=new HashMap<>();
        try{
            navigation.setreaded("0");
            navigation.setStatus("1");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            navigation.setCreattime(df.format(new Date()));
            navigationService.add(navigation);
            response.put("status","success");
            response.put("massage",null);
            response.put("data",navigation);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }

    //根据ID删除一条导航栏信息
    //增加一条导航栏条目
    @PostMapping(value = "/navigation/delete")
    @ApiOperation(value="根据ID删除指定导航栏条目",notes="根据ID删除指定导航栏条目")
    public Map delete(
            @ApiParam(required = true,name="id",value = "需要删除的导航栏的ID") @RequestParam("id") Integer id
    ){
        Map<Object,Object> response=new HashMap<>();
        try{
            navigationService.delete(id);
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

    //更新导航栏信息
    @PostMapping(value = "/navigation/update")
    @ApiOperation(value="根据ID更新指定导航栏条目",notes="根据ID更新指定导航栏条目")
    public Map update(
            @ApiParam(required = true,name="navigation",value = "需要更新的导航栏信息(ID必填)") @RequestBody Navigation navigation
    ){
        Map<Object,Object> response=new HashMap<>();
        try{
            Integer id=navigation.getId();
            Navigation updateNavigation=navigationService.select(id);
            Tools.update(updateNavigation,navigation);
            navigationService.update(updateNavigation);
            response.put("status","success");
            response.put("massage",null);
            response.put("data",updateNavigation);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }

    //查询所有导航栏信息
    @GetMapping(value = "/navigation/all")
    @ApiOperation(value="查询所有导航栏信息",notes="查询所有导航栏信息")
    public Map select(){
        Map<Object,Object> response=new HashMap<>();
        try{
            List<Navigation> navigationList=navigationService.select();
            response.put("status","success");
            response.put("massage",null);
            response.put("data",navigationList);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }

    @GetMapping(value = "/navigation")
    @ApiOperation(value="查询指定ID导航栏信息",notes="查询指定ID导航栏信息")
    public Map selectById(
            @ApiParam(required = true,name="id",value = "需要查询的导航栏的ID") @RequestParam("id") Integer id
    ){
        Map<Object,Object> response=new HashMap<>();
        try{
            Navigation navigation=navigationService.select(id);
            response.put("status","success");
            response.put("massage",null);
            response.put("data",navigation);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }

    //查询所有爬虫信息
    @GetMapping(value = "/spider/all")
    @ApiOperation(value="查询所有爬虫信息",notes="查询所有爬虫信息")
    public Map selectSpider(){
        Map<Object,Object> response=new HashMap<>();
        try{
            List<Navigation> navigationList=navigationService.selectSpider();
            response.put("status","success");
            response.put("massage",null);
            response.put("data",navigationList);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }




}
