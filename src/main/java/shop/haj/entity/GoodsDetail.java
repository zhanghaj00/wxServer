package shop.haj.entity;

import java.io.Serializable;

/**
 * <p>Title: shop.ha.entity</p>
 * <p/>
 * <p>
 * Description: GoodsDetail
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/20/17
 */
public class GoodsDetail implements Serializable{
	
	private int id;//序列号
	private int sequence;//序号
	private String content;//显示内容
	private int type;//类型，1：文本，2：图片地址，3：链接地址
	
	public GoodsDetail() {
	}
	
	public GoodsDetail(int id, int sequence, String content, int type) {
		this.id = id;
		this.sequence = sequence;
		this.content = content;
		this.type = type;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getSequence() {
		return sequence;
	}
	
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "GoodsDetail{" +
				"id=" + id +
				", sequence=" + sequence +
				", content='" + content + '\'' +
				", type=" + type +
				'}';
	}
}
