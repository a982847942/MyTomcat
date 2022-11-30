package ex02.pyrmont;

import java.io.File;

/**
 * @Classname
 * @Description
 * @Date 2022/11/29 22:02
 * @Created by brain
 */
public class Constants {
    public static final String WEB_ROOT =
            System.getProperty("user.dir") + File.separator + "webroot";
    public static final String SERVLET_ROOT =
            System.getProperty("user.dir") + File.separator + "target" + File.separator
        + "classes" + File.separator + "ex02" + File.separator
            +"servlet";
//public static final String SERVLET_ROOT =
//        System.getProperty("user.dir") + File.separator + "src" + File.separator
//                + "main" + File.separator + "java" + File.separator
//                +"ex02" + File.separator + "servlet";
}
