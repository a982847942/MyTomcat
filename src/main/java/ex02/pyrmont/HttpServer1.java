package ex02.pyrmont;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

/**
 * @Classname HttpServer1
 * @Description
 * @Date 2022/11/29 21:18
 * @Created by brain
 */
public class HttpServer1 {

    /** WEB_ROOT is the directory where our HTML and other files reside.
     *  For this package, WEB_ROOT is the "webroot" directory under the working
     *  directory.
     *  The working directory is the location in the file system
     *  from where the java command was invoked.
     */
    // shutdown command
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
    private boolean shutdown = false;
    public static void main(String[] args) {

        new HttpServer1().await();


    }



    public void await(){
        int port = 8080;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port,1,InetAddress.getByName("127.0.0.1"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }


        while (!shutdown){
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;
            try {
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                Request request = new Request(input);
                request.parse();

                Response response = new Response(output);
                response.setRequest(request);

                if (request.getUri() != null){
                    if (request.getUri().startsWith("/servlet/")){
                        ServletProcessor1 processor = new ServletProcessor1();
                        processor.process(request, response);
                    }else {
                        StaticResourceProcessor processor = new StaticResourceProcessor();
                        processor.process(request, response);
                    }
                    socket.close();
                    shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (socket != null){
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
