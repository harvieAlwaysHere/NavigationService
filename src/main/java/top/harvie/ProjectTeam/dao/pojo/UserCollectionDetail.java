package top.harvie.ProjectTeam.dao.pojo;

public class UserCollectionDetail {

    private  Integer id;
    private  String userid;
    private  String collectionstatus;

    private  Integer navigationid;
    private  String name;
    private  String img;
    private  String navigationstatus;

    public UserCollectionDetail() {
    }

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

    public String getCollectionstatus() {
        return collectionstatus;
    }

    public void setCollectionstatus(String collectionstatus) {
        this.collectionstatus = collectionstatus;
    }

    public Integer getNavigationid() {
        return navigationid;
    }

    public void setNavigationid(Integer navigationid) {
        this.navigationid = navigationid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNavigationstatus() {
        return navigationstatus;
    }

    public void setNavigationstatus(String navigationstatus) {
        this.navigationstatus = navigationstatus;
    }
}
