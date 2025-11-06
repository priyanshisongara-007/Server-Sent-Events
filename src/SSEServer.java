import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

public class SSEServer {

    public static void main(String[] args) throws Exception {
        Server server = new Server(5001);

        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");
        server.setHandler(handler);

        // Add SSE servlet at /sse endpoint
        handler.addServlet(SSEServlet.class, "/sse");

        server.start();
        System.out.println("SSE Server started at http://localhost:5001/sse");
        server.join();
    }

    public static class SSEServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            // Set content type for SSE
            resp.setContentType("text/event-stream"); //mOst important part
            resp.setCharacterEncoding("UTF-8");
            // Disable caching
            resp.setHeader("Cache-Control", "no-cache");
            resp.setHeader("Connection", "keep-alive");

            PrintWriter writer = resp.getWriter();

            // Simple event stream: send current time every second
            try {
                for (int i = 0; i < 300; i++) { // send 30 events, then close connection
                    String time = LocalTime.now().toString();

                    // SSE format:
                    // event: <event-name> (optional)
                    // data: <data> (required)
                    // Note double line break to separate events

                    writer.print("event: time\n");
                    writer.print("data: " + time + "\n\n");
                    writer.flush();

                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                writer.close();
            }
        }
    }
}

