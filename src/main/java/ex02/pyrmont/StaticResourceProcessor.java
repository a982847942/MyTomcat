package ex02.pyrmont;

import java.io.IOException;

/**
 * @Classname StaticResourceProcessor
 * @Description
 * @Date 2022/11/29 21:33
 * @Created by brain
 */
public class StaticResourceProcessor {
    public void process(Request request, Response response) {
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
