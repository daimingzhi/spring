package base.annonation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author dmz
 * @date Create in 20:20 2019/3/24
 */
@JacksonAnnotationsInside
@JsonSerialize(using = MySerializtion.class)
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface MySerialize {

}
