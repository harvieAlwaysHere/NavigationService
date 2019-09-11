package top.harvie.ProjectTeam.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import top.harvie.ProjectTeam.dao.mapper.NavigationMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import top.harvie.ProjectTeam.dao.pojo.Navigation;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SpiderService {

    private static Logger log = LogManager.getLogger(SpiderService.class);

    @Autowired
    NavigationMapper navigationMapper;

    @Scheduled(cron = "0/59 * * * * ?")  //每59秒执行一次
    private void downloadHtml()
    {
        String url="http://www.52jingsai.com/bisai/shangyechuangye/chuangye/index.php?jsstatus=2&jssort=0";
        try{
            Document doc= Jsoup.connect(url).get();
            Elements linkList=doc.select("dt.xs2_tit").select("a");
            for(Element link : linkList){
                String linkUrl="http://www.52jingsai.com/"+link.attr("href");
                List<String> existLinks= navigationMapper.selectAllLink();
//                log.info(existLinks.toString());
//                log.info(existLinks.contains(linkUrl));
                if(!existLinks.contains(linkUrl)){
                    downloadHtmlDetail(linkUrl);
                }
            }


        }catch (IOException e){
            log.info("发生异常：",e.toString());
        }
    }

    private void downloadHtmlDetail(String linkUrl)
    {
        try{
            Navigation item=new Navigation();
            Document doc= Jsoup.connect(linkUrl).get();
            String title=doc.select("#ct > div.mn > div.vw > div.arc_tit > h1").get(0).text();
            String content=doc.select("#article_content ").select("p,div:not([class])").toString().replaceAll("我爱竞赛网","小时代").replaceAll("data","http://www.52jingsai.com/data").replaceAll("portal/.php","http://www.52jingsai.com/portal.php").replaceAll("<img","<img style='max-width:100%;height:auto;'");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createtime=df.format(new Date());
            String img="http://www.52jingsai.com/"+doc.select("#article_content ").select("a:has(img)").first().select("img").attr("src");
            item.setIntroduction(content);
            item.setCreattime(createtime);
            item.setImg(img);
            item.setAddition(linkUrl);
            item.setStatus("2");
            item.setName(title);
            navigationMapper.add(item);
        }catch (IOException e){
            log.info("发生异常：",e.toString());
        }
    }

}
