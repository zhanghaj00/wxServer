package shop.haj.controller;

import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import shop.haj.entity.Image;
import shop.haj.service.ImageService;
import shop.haj.utils.QiniuUtil;

import java.io.IOException;
import java.util.Map;
/** <p>Title: shop.ha.controller</p> <p/> <p> Description: ImageController </p> <p/> @author hao CreateTime：3/8/17 */
@RestController @RequestMapping(value = "/v1")
public class ImageController extends BaseController{
	private Logger logger = LogManager.getLogger(ImageController.class);
	/**
	 * 上传图片接口
	 * @param imageFile
	 * @return
	 */
	@ApiOperation(value = "上传图片接口", notes = "上传图片接口")
	@PostMapping(value = "/seller/images")
	public Map<String,Object> addImage(@RequestParam("image") MultipartFile imageFile){
		
		if(imageFile.isEmpty()){
			return rtnParam(50021,new ResponseEntity("please select a file!", HttpStatus.OK));
		}
		Image image = new Image();//imageService.addImage(imageFile);

		if (imageFile.isEmpty()) {
			image.setUrl("/images/home/shop.png");
		} else {
			try {
				image.setUrl(QiniuUtil.getInstance().upload(imageFile.getBytes()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return rtnParam(0,image);
	}
	
	
}
