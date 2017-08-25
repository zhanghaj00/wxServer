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
@Document(collection = "wx_comment")
@Data
public class Comment implements Serializable {

    @Id
    private String id;

    private String comment;

    private String sku;

    private Integer star;

    private String goodsId;

    private String orderId;

    private String shopId;

    private String customerId;

    private Customer customer;

    private String createTime;

    private String starMark;


}
