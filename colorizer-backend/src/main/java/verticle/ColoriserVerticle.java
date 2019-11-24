package verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.CorsHandler;
import resource.ImageResource;

import java.util.HashSet;
import java.util.Set;

public class ColoriserVerticle extends AbstractVerticle {

    private final Logger logger = LoggerFactory.getLogger(ColoriserVerticle.class);

    @Override
    public void start() throws Exception {
        this.logger.info("Starting application....");

        final Router router = Router.router(vertx);

        Set<String> allowedHeaders = new HashSet<>();
        allowedHeaders.add("x-requested-with");
        allowedHeaders.add("Access-Control-Allow-Origin");
        allowedHeaders.add("origin");
        allowedHeaders.add("Content-Type");
        allowedHeaders.add("accept");
        allowedHeaders.add("X-PINGARUNER");

        Set<HttpMethod> allowedMethods = new HashSet<>();
        allowedMethods.add(HttpMethod.GET);
        allowedMethods.add(HttpMethod.POST);
        allowedMethods.add(HttpMethod.OPTIONS);
        /*
         * these methods aren't necessary for this sample,
         * but you may need them for your projects
         */
        allowedMethods.add(HttpMethod.DELETE);
        allowedMethods.add(HttpMethod.PATCH);
        allowedMethods.add(HttpMethod.PUT);

        router.route().handler(CorsHandler.create("*").allowedHeaders(allowedHeaders).allowedMethods(allowedMethods));

        final ImageResource imageResource = new ImageResource();
        final Router imageResourceSubRouter = imageResource.getSubRouter(vertx);
        router.mountSubRouter("/api/v1/images", imageResourceSubRouter);

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(8080);

        this.logger.info("Application started");
    }

    @Override
    public void stop() throws Exception {
        this.logger.info("Application stopped");
        super.stop();
    }
}
