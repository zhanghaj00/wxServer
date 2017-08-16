/**
 * Create time
 */
package shop.haj.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by Hao on .
 * description。
 * @since 1.9.0
 */
@Document(collection = "wx_order_pay_info")
public class OrderPayInfo implements Serializable {

    private String id;
    private String orderId;

    private String prePayId;

    private String codeUrl;

    private String prePayTime;

    private String createTime;

}
