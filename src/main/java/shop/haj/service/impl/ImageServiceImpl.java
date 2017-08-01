package shop.haj.service.impl;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import shop.haj.entity.Image;
import shop.haj.mongo_repository.MongoImageRepository;
import shop.haj.service.ImageService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <p>Title: shop.ha.service.impl</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/16/17
 */
@Service
@Transactional
public class ImageServiceImpl implements ImageService {
	
	private Logger logger = LogManager.getLogger(ImageServiceImpl.class);
	
	@Autowired
	private MongoImageRepository mongoImageRepository;
	
	@Value("${image.path}")
	private String fileUploadPath;
	
	@Value("${image.baseUrl}")
	private String imageBaseUrl;
	
	/**
	 * 新增图片
	 *
	 * @param imageFile
	 * @return
	 */
	@Override
	public Image addImage(MultipartFile imageFile) throws IOException {
		
		logger.info("addImage >>> imageFile type={},  origin_name={}", imageFile.getContentType(), imageFile.getOriginalFilename());
		
		String url = saveUploadfile(imageFile);
		
		Image image = new Image();
		image.setUrl(url);

		mongoImageRepository.insert(image);
		
		logger.info("addImage >>> save image to database, image={}", image);
		
		return image;
	}
	
	/**
	 * 保存文件
	 * @param file
	 * @throws IOException
	 */
	private String saveUploadfile(MultipartFile file) throws IOException {
		
		//save image path for random
		String savedPath = "/img" + RandomUtils.nextInt(1, 4) + "/i" + RandomUtils.nextInt(1, 10) + "/" + RandomUtils.nextInt(10, 100) + "/";
		
		logger.info("saveUploadfile >>> file:{} save to path {}", file.getOriginalFilename(), savedPath);
		
		//create directory
		FileUtils.forceMkdir(new File(fileUploadPath + savedPath));
		
		String[] arr = StringUtils.split(file.getOriginalFilename(), ".");
		
		String type = arr[arr.length-1];
		
		String fileName = RandomStringUtils.randomAlphanumeric(28) + "." + type;
		
		logger.info("saveUploadfile >>> filename:{} rename to {}", file.getOriginalFilename(), fileName);
		
		byte[] bytes = file.getBytes();
		Path path = Paths.get(fileUploadPath + savedPath , fileName);
		Files.write(path, bytes);
		
		return imageBaseUrl + savedPath + fileName;
	}
	
}
