package shop.haj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * <p>Title: shop.ha.entity</p>
 * <p/>
 * <p>
 * Description: 购物车
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/20/17
 */
@Document(collection = "wx_cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart implements Serializable {

	@Id
	private String id;//购物车ID
	private String customerId;//用户ID
	private String shopId;//店铺ID
	private String shopName;//店铺名称
	private String supplierId;//供应商ID
	private String supplierName;//供应商名称
	private String goodsId;//商品ID
	private String goodsName;//商品名称
	private Double goodsPrice;//商品价格
	private Integer goodsNum;//商品数量
	private String goodsImage;//商品图片
	private String goodsSku;//规格说明
	private String note;//商品备注
	private String createTime;//添加到购物车的时间
	private String updateTime;//购物车更新时间

}
