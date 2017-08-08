package shop.haj.mongo_repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shop.haj.entity.wxPay.WxPayConfig;

/**
 * Created by Hao on ${date}.
 * description。
 *
 * @since 1.9.0
 */
public interface WxPayRepository extends MongoRepository<WxPayConfig,String> {

    //WxPayConfig getWxPayConfig(int shopid);
}
