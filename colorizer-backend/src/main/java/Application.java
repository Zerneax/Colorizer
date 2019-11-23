import io.vertx.core.Vertx;
import verticle.ColoriserVerticle;

public class Application {

    public static void main(String[] args) {
        final Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new ColoriserVerticle());
    }
}
