package shop.haj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * <p>Title: shop.ha.entity</p>
 * <p/>
 * <p>
 * Description: 店铺历史访问记录
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/4/17
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "wx_visitshop")
public class VisitShop implements Serializable {

	private String id;

	private String customerId;
	
	private String shopId;
	
	private String visitTime;
}
