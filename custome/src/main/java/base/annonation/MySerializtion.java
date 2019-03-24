package base.annonation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;
import java.util.Objects;

/**
 * @author dmz
 * @date Create in 20:22 2019/3/24
 */
public class MySerializtion extends JsonSerializer implements ContextualSerializer {
    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        System.out.println("我定义的序列化器来了");

    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty property)
            throws JsonMappingException {
        if (property != null) {
            if (Objects.equals(property.getType().getRawClass(), String.class)) { // 非 String 类直接跳过
                MySerialize sensitiveInfo = property.getAnnotation(MySerialize.class);
                if (sensitiveInfo == null) {
                    sensitiveInfo = property.getContextAnnotation(MySerialize.class);
                }
                if (sensitiveInfo != null) { // 如果能得到注解，就将注解的 value 传入 SensitiveInfoSerialize
                    return new MySerializtion();
                }
            }

            return serializerProvider.findValueSerializer(property.getType(), property);
        }
        return serializerProvider.findNullValueSerializer(property);
    }
}
