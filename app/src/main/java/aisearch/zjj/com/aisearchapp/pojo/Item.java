package aisearch.zjj.com.aisearchapp.pojo;

/**
 * Created by Administrator on 2020/2/6.
 */

public class Item {
    private String id;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Item(String id, String title) {
        this.id = id;
        this.title = title;
    }
}
