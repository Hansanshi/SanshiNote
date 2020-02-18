package ink.hansanshi.note.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author hansanshi
 * @date 2020/1/29
 */
public class ValidateUtil {

    public static void notBlankString(String... strings){
        if (StringUtils.isAnyBlank(strings)){
            throw new IllegalArgumentException();
        }
    }

    public static void notContainBlank(String... strings){
        for (String string : strings){
            if (StringUtils.contains(string, ' ')){
                throw new IllegalArgumentException();
            }
        }
    }
}
