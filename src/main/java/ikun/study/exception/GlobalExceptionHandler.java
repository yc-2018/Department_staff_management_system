//仰晨study 创建时间2023/4/24 20:41 星期一
package ikun.study.exception;

import ikun.study.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**全局异常处理器*/
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)  //捕获所有异常
    public Result ex(Exception exception) {
        exception.printStackTrace();
        return Result.error("ikun:对不起,操作失败,请联系管理员");
    }
}
