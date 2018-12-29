package com.pingwinno;

import com.pingwinno.infrastructure.MongoDBHandler;
import com.pingwinno.presentation.DbHandler;
import com.pingwinno.presentation.OldApi;
import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.LoggerFactory;

public class Main implements Daemon {

    static org.slf4j.Logger log = LoggerFactory.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Main.class.getResourceAsStream("log4j2.json");
        org.slf4j.Logger log = LoggerFactory.getLogger(Main.class.getName());
        log.info("Connect to mongdb");
        MongoDBHandler.connect();
        log.info("Connected");
        log.info("Starting server");
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(11111);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.classnames",
                String.join(",",OldApi.class.getCanonicalName(),DbHandler.class.getCanonicalName())
                );


        try {
            jettyServer.start();
            jettyServer.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jettyServer.destroy();
        }


        log.info("Server started");
    }

    @Override
    public void init(DaemonContext daemonContext) {

    }

    @Override
    public void start() {
        log.info("starting...");
        main(null);
    }

    @Override
    public void stop() throws Exception {

    }

    @Override
    public void destroy() {
        log.info("stop");
    }
}
