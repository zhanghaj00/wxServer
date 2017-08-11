/**
 * Create time
 */
package shop.haj.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.Json;

import java.util.Date;
import java.util.Random;

/**
 * Created by Hao on .
 * description。
 * @since 1.9.0
 */
public class QiniuUtil {


    private  final String ACCESS_KEY = "FNV3gT8EXDEL56C1Jk9GQrmoDX_M65n6IFdV0whY";
    private  final String SECRET_KEY = "7VSgn-TbqspJpbvlE0s4k0iHgCBXEF6Z3ccXqoEv";

    private  final String bucketName = "dsz-shop";

    final  Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    final  Zone z = Zone.autoZone();
    final  Configuration c = new Configuration(z);

    final   UploadManager uploadManager = new UploadManager(c);

    final BucketManager bucketManager = new BucketManager(auth,c);
    private   String getUpToken() {
        return auth.uploadToken(bucketName);
    }


    private static class QiuniuUtilHolder{
        private static  final QiniuUtil INSTINCE = new QiniuUtil();
    }

    private QiniuUtil(){}
    public static final QiniuUtil getInstance(){
        return QiuniuUtilHolder.INSTINCE;
    }



    public  String  upload(byte[] file){
        try {
            Response res = uploadManager.put(file, genetorString(), getUpToken());
            //TODO  返回文件名称字符串
            return  ApiConstant.QINIU_IMG_URL_PREFIX + Json.decode(res.bodyString()).get("key");
        } catch (QiniuException e) {
            e.printStackTrace();
            Response r = e.response;
            try {
                return  r.bodyString();
            } catch (QiniuException e1) {
                e1.printStackTrace();
            }
        }
        return "";

    }

    public void deleteImg(String fileName){
        //TODO 处理图片完整链接 去除/后面的文件名
        String x = fileName == null ? null : fileName.split("com/").length==2?fileName.split("com/")[1]:null;
        try {
            if (null == x ) return;
            bucketManager.delete(bucketName,x);
        } catch (QiniuException e) {
            e.printStackTrace();
            System.out.print("删除失败，失败文件名："+fileName);
        }

    }
    private static String  genetorString(){

        return  new Date().getTime() + "" + new Random().nextInt() + "" + ".jpg";

    }


}
