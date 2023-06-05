package ikun.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan   //开启了对servlet（或者说是javaWeb)组件的支持（就是filter过滤器要用到啦)
@SpringBootApplication
public class TliasWebManagemantApplication {

    public static void main(String[] args) {
        SpringApplication.run(TliasWebManagemantApplication.class, args);
    }

}
