package shop.haj.controller;

import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import shop.haj.entity.Image;
import shop.haj.service.ImageService;

import java.io.IOException;

/**
 * <p>Title: shop.ha.controller</p>
 * <p/>
 * <p>
 * Description: ImageController
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/8/17
 */
@RestController
@RequestMapping(value = "/v1")
public class ImageController {
	
	private Logger logger = LogManager.getLogger(ImageController.class);
	
	@Autowired
	private ImageService imageService;
	
	/**
	 * 上传图片接口
	 * @param imageFile
	 * @return
	 */
	@ApiOperation(value = "上传图片接口", notes = "上传图片接口")
	@PostMapping(value = "/seller/images")
	public ResponseEntity<?> addImage(@RequestParam("image") MultipartFile imageFile){
		
		if(imageFile.isEmpty()){
			return new ResponseEntity("please select a file!", HttpStatus.OK);
		}
		
		String jsonString = "";

		/*try {

			Image image = new Image();//imageService.addImage(imageFile);
			image.setUrl("2222");
			Gson gson = new Gson();
			jsonString = gson.toJson(image);

		} catch (IOException e) {
			logger.error(e.toString(), e);
			return new ResponseEntity("addShop image file fail. " + e.toString(), HttpStatus.BAD_REQUEST);
		}*/
		Image image = new Image();//imageService.addImage(imageFile);
		image.setUrl("2222");
		Gson gson = new Gson();
		jsonString = gson.toJson(image);
		return new ResponseEntity(jsonString, new HttpHeaders(), HttpStatus.OK);
	}
	
	
}
