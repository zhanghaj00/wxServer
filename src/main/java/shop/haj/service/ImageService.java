package shop.haj.service;

import org.springframework.web.multipart.MultipartFile;
import shop.haj.entity.Image;

import java.io.IOException;

/**
 * <p>Title: shop.ha.service</p>
 * <p/>
 * <p>
 * Description: 图片服务
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/16/17
 */
public interface ImageService {
	
	/**
	 * 新增图片
	 * @param imageFile
	 * @return
	 */
	Image addImage(MultipartFile imageFile) throws IOException;
}
