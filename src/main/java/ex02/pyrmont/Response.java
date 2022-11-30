package ex02.pyrmont;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

/**
 * @Classname Response
 * @Description
 * @Date 2022/11/29 21:30
 * @Created by brain
 */
public class Response implements ServletResponse {
    private static final int BUFFER_SIZE = 1024;
    private OutputStream outputStream;
    private Request request;
    PrintWriter writer;

    /* This method is used to serve a static page */
    public void sendStaticResource() throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        File file = null;
        try {
            file = new File(Constants.WEB_ROOT,request.getUri());
            fis = new FileInputStream(file);
            int len = -1;

            int totalLen = 0;
            StringBuilder body = new StringBuilder();
            while ((len = fis.read(buffer)) != -1){
                totalLen += len;
                body.append(new String(buffer,0,len));
//                outputStream.write(buffer,0,len);
            }
            String message = "HTTP/1.1 200 State OK \r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length:  " + totalLen  + "\r\n" +
                    "\r\n";
            outputStream.write(message.getBytes());
            outputStream.write(body.toString().getBytes());
        }catch (FileNotFoundException e) {
            String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: 23\r\n" +
                    "\r\n" +
                    "<h1>File Not Found</h1>";
            outputStream.write(errorMessage.getBytes());
        }finally {
            if (fis != null) {
                fis.close();
            }
        }
    }

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }


    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        // autoflush is true, println() will flush,
        // but print() will not.
        writer = new PrintWriter(outputStream, true);
        return this.writer;
    }

    @Override
    public void setCharacterEncoding(String charset) {

    }

    @Override
    public void setContentLength(int len) {

    }

    @Override
    public void setContentLengthLong(long len) {

    }

    @Override
    public void setContentType(String type) {

    }

    @Override
    public void setBufferSize(int size) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale loc) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
