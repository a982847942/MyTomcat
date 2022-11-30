package ex02.pyrmont;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * @Classname ServletProcessor1
 * @Description
 * @Date 2022/11/29 21:32
 * @Created by brain
 */
public class ServletProcessor1 {
    public void process(Request request, Response response) {

        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        URLClassLoader loader = null;

        try {
            // create a URLClassLoader
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File(Constants.WEB_ROOT);
            // the forming of repository is taken from the createClassLoader method in
            // org.apache.catalina.startup.ClassLoaderFactory
            String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator))
                    .toString();
            // the code for forming the URL is taken from the addRepository method in
            // org.apache.catalina.loader.StandardClassLoader class.
//            System.out.println("------------" + repository);
            urls[0] = new URL(null, repository, streamHandler);
            loader = new URLClassLoader(urls);
//            System.out.println("-------------" + urls[0].toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        Class myClass = null;
        try {
            myClass = loader.loadClass("ex02.servlet." + servletName);
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        if (myClass != null) return;
        Servlet servlet = null;
        try {

            servlet = (Servlet) myClass.newInstance();
            servlet.service((ServletRequest) request, (ServletResponse) response);
        } catch (Exception e) {
            System.out.println(e.toString());
        } catch (Throwable e) {
            System.out.println(e.toString());
        }

    }
}
