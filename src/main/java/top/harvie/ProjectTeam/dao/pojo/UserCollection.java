package top.harvie.ProjectTeam.dao.pojo;

public class UserCollection {

    private  Integer id;
    private  String userid;
    private  String navigationid;
    private  String createtime;
    private  String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNavigationid() {
        return navigationid;
    }

    public void setNavigationid(String navigationid) {
        this.navigationid = navigationid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserCollection() {
    }
}
