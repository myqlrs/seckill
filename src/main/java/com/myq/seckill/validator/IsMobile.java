package com.myq.seckill.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author 孟赟强
 * @date 2021/4/1-20:05
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {IsMobileValidator.class})//引进校验器
public @interface IsMobile {

    boolean required() default true;//默认不能为空

    String message() default "手机号码格式错误";//校验不通过输出信息

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
