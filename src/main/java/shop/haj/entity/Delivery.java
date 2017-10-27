/**
 * Create time
 */
package shop.haj.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by Hao on .
 * description。
 * @since 1.9.0
 */
@Document(collection = "wx_delivery")
@Data
public class Delivery implements Serializable {

    @Id
    private String id;

    private String name;

    private Double limitPrice;

    private String location;

    private String type;

    private String desc;

    private Boolean def;

    private Double fee;

    private String shopId;

    private String supplierId;

    private String supplierName;

    public Delivery() {
    }

}
