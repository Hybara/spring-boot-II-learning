package per.learn.spring.boot.learning.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import per.learn.spring.boot.learning.Interceptor.SessionHandlerInterceptor;

/*
用来全局定制化Spring boot的MVC特性
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    /*
     添加格式化器
      */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 添加日期格式化，DateFormatter把符合pattern的字符串转化成java.util.Date
        registry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
    }

    /*
     添加拦截器
      */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionHandlerInterceptor())// 设置拦截器
                .addPathPatterns("/**");// 设置拦截的url
    }

    /*
    配置跨域访问，如果需要做更细致的跨域访问处理，可以在cotroller中使用：
        @CrossOrigin(origins = "http://192.168.1.97:8080")
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")// 设置允许被跨域访问的api为 /api
                .allowedOrigins("http://domain2.com")// 设置被允许的源
                .allowedMethods("POST", "GET");// 设置被允许的访问方式
    }

}
