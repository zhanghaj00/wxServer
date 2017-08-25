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
@Document(collection = "wx_supplier")
@Data
public class Supplier implements Serializable {

    @Id
    private String id;

    private String name;

    private String desc;

    private String shopId;

    private String createTime;
}
