//仰晨study 创建时间2023/4/25 20:41 星期二
package ikun.study.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来面向切面的标记注解
 */
@Retention(RetentionPolicy.RUNTIME) //运行时有效
@Target(ElementType.METHOD)         //允许写在方法上
public @interface Log {
}
