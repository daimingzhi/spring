package base.domain;

import base.annonation.MySerialize;
import lombok.Builder;
import lombok.Data;

/**
 * @author dmz
 * @date Create in 20:15 2019/3/24
 */
@Data
@Builder
public class Person {
    @MySerialize
    private String name;
    private String age;
}
