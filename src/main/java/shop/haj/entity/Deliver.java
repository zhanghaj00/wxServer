/**
 * Create time
 */
package shop.haj.entity;

/**
 * Created by Hao on .
 * description。
 * @since 1.9.0
 */
public class Deliver {

    private String id;

    private String type;

    private String desc;

    private Boolean def;

    private Double fee;


    public Deliver() {
    }


    public Deliver(String id, String type, String desc, Boolean def, Double fee) {
        this.id = id;
        this.type = type;
        this.desc = desc;
        this.def = def;
        this.fee = fee;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getDef() {
        return def;
    }

    public void setDef(Boolean def) {
        this.def = def;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }
}
