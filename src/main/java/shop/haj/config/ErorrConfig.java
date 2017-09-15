/**
 * Create time
 */
package shop.haj.config;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import shop.haj.controller.BaseController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * Created by Hao on .
 * description。
 * @since 1.9.0
 */
@RestController
public class ErorrConfig extends BasicErrorController {

    private Logger logger = LogManager.getLogger(ErorrConfig.class);

    public ErorrConfig(){
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }


    private static final String PATH = "/error";

    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map body = this.getErrorAttributes(request, this.isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = this.getStatus(request);
        logError(request,body);
        return new ResponseEntity(body, status);
    }

    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = this.getStatus(request);
        Map model = Collections.unmodifiableMap(this.getErrorAttributes(request, this.isIncludeStackTrace(request, MediaType.TEXT_HTML)));
        response.setStatus(status.value());
        ModelAndView modelAndView = this.resolveErrorView(request, response, status, model);
        logError(request,model);
        return modelAndView == null?new ModelAndView("error", model):modelAndView;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    private void logError(HttpServletRequest request,Map modelAndView){
        StringBuilder sb = new StringBuilder(1000);
        sb.append("-----------------------").append((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date())).append("-------------------------------------\n");
        sb.append("URI       : ").append(request.getRequestURI()).append("\n");
        sb.append("Address   : ").append(request.getRemoteAddr()).append("\n");
        sb.append("ERROR MESSAGE: ").append(modelAndView.get("message")).append("\n");
        sb.append("ERROR     : ").append(modelAndView.get("error")).append("\n");
        sb.append("ERROR STATUS: ").append(modelAndView.get("status")).append("\n");
        sb.append("ERROR PATH: ").append(modelAndView.get("path")).append("\n");
        logger.info(sb.toString());
    }
}
