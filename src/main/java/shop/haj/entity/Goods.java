package shop.haj.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: shop.ha.entity</p>
 * <p/>
 * <p>
 * Description: Goods
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/4/17
 */
@Document(collection = "wx_goods")
@Data
public class Goods implements Serializable{


	@Id
	private String id;
	
	private String uuid;
	
	private String name;
	
	private String shopId;

	private String supplierId;
	
	private Integer status;
	
	private Double originalPrice;
	
	private Double sellPrice;
	
	private Integer stock;
	
	private Integer salesVolume;
	
	private String innerCid;

	private String innerCidName;
	
	private String globalCid;
	
	private Integer isRecommend;
	
	private Integer isDeleted ; //1是正常  0 是下架
	
	private String createTime;
	
	private String updateTime;
	
	private List<Image> images;
	
	private List<GoodsDetail> goodsDetails;//商品详情
	
	private GoodsSkuInfo goodsSkuInfo;
	
	public Goods() {
	}
}
