package verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import resource.ImageResource;

public class ColoriserVerticle extends AbstractVerticle {

    private final Logger logger = LoggerFactory.getLogger(ColoriserVerticle.class);

    @Override
    public void start() throws Exception {
        this.logger.info("Starting application....");

        final Router router = Router.router(vertx);
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
