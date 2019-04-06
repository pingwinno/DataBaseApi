package com.pingwinno;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pingwinno.infrastructure.MongoDBHandler;
import com.pingwinno.presentation.DbHandler;
import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Application;

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

        final Application application = new ResourceConfig()
                .packages("org.glassfish.jersey.examples.jackson").register(JacksonFeature.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Server jettyServer = new Server(8080);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter("jersey.config.server.provider.classnames",
                DbHandler.class.getCanonicalName());
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
    public void stop() {

    }

    @Override
    public void destroy() {
        log.info("stop");
    }
}
