package com.self.mall.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.self.mall.entity.UserInfo;
import com.self.mall.util.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class JsonUtils {

    public static ObjectMapper objectMapper = new ObjectMapper();

    static {
        //对象的所有字段全部列入
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        //取消默认转换timestamps形式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);

        //忽略空Bean转json的错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);

        //所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss
        objectMapper.setDateFormat(new SimpleDateFormat(DateTimeUtil.STANDARD_FORMAT));

        //忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

    }

    public static <T> String obj2StringPretty(T obj){
        if(obj == null){
            return null;
        }
        try {
            return obj instanceof String ? (String)obj :  objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("Parse Object to String error",e);
            return null;
        }
    }

    public static <T> String objToString(T obj){
        if(null == obj){
            return null;
        }
        try {
            return obj instanceof String ? (String)obj : objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("转换对象发生异常{}",e);
        }
        return null;
    }

    public static <T> T stringToObj(String str,Class<T> clazz){
        if(StringUtils.isEmpty(str) ||  clazz == null){
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T)str : objectMapper.readValue(str,clazz);
        } catch (IOException e) {
            log.error("字符串转对象异常{}",e );
            return null;
        }

    }

    public static <T> T stringToObj(String str, TypeReference typeReference){
        if(StringUtils.isEmpty(str) || null == typeReference){
            return null;
        }
        try {
            return typeReference.equals(String.class) ? (T)str : objectMapper.readValue(str,typeReference);
        } catch (IOException e) {
            log.error("字符串转泛型集合类型异常",e);
            return null;
        }

    }


    public static <T> T stringToObject(String str,Class<?> collection,Class<?>... clazzes){
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collection,clazzes);
        if(StringUtils.isEmpty(str) || null == clazzes){
            return null;
        }
        try {
            return objectMapper.readValue(str,javaType);
        } catch (IOException e) {
            log.error("字符串转泛型集合类型异常",e);
            return null;
        }

    }



    public static void main(String[] args) {
//        UserInfo userInfo = new UserInfo("xuyu", 28, new Date());
//        String s = objToString(userInfo);
//        System.out.println(s);
//        ArrayList<UserInfo> userInfos = new ArrayList<>();
//        for (int index = 0;index < 3; index++){
//            UserInfo userInfo = new UserInfo("xy" + index, index * 3, new Date());
//            userInfos.add(userInfo);
//        }
//        System.out.println(objToString(userInfos));
//        List<UserInfo> o = (List<UserInfo>)stringToObject("[{\"userName\":\"xy0\",\"age\":0,\"birthday\":\"2018-12-25 15:00:24\"},{\"userName\":\"xy1\",\"age\":3,\"birthday\":\"2018-12-25 15:00:24\"},{\"userName\":\"xy2\",\"age\":6,\"birthday\":\"2018-12-25 15:00:24\"}]", List.class, UserInfo.class);
//        System.out.println(o.get(0).getClass());


        List<UserInfo> o1 = (List<UserInfo>)stringToObj("[{\"userName\":\"xy0\",\"age\":0,\"birthday\":\"2018-12-25 15:00:24\"},{\"userName\":\"xy1\",\"age\":3,\"birthday\":\"2018-12-25 15:00:24\"},{\"userName\":\"xy2\",\"age\":6,\"birthday\":\"2018-12-25 15:00:24\"}]", new TypeReference<List<UserInfo>>(){});
        System.out.println(o1.get(1).getClass());
    }





}
