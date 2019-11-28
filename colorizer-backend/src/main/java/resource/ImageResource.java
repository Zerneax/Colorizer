package resource;

import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import model.DataImage;
import process.ImageProcess;

import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Timestamp;
import java.util.List;

public class ImageResource {

    private static final String FILE_LOCATION = "file_process";

    private final ImageProcess imageProcess = new ImageProcess();

    public Router getSubRouter(final Vertx vertx) {
        final Router subRouter = Router.router(vertx);

        subRouter.route("/inverted").handler(BodyHandler.create().setUploadsDirectory(FILE_LOCATION).setDeleteUploadedFilesOnEnd(true));
        subRouter.post("/inverted").handler(this::getInvertedImage);

        subRouter.route("/black_and_white").handler(BodyHandler.create().setUploadsDirectory(FILE_LOCATION).setDeleteUploadedFilesOnEnd(true));
        subRouter.post("/black_and_white").handler(this::getBlackAndWhite);

        subRouter.route("/sepia").handler(BodyHandler.create().setUploadsDirectory(FILE_LOCATION).setDeleteUploadedFilesOnEnd(true));
        subRouter.post("/sepia").handler(this::getSepia);

        subRouter.route("/shake").handler(BodyHandler.create().setUploadsDirectory(FILE_LOCATION).setDeleteUploadedFilesOnEnd(true));
        subRouter.post("/shake").handler(this::getShake);

        return subRouter;
    }

    private void getInvertedImage(final RoutingContext routingContext){
        File fileInverted = null;

        System.out.println("Number of file received : " + routingContext.fileUploads().size());
        if(routingContext.fileUploads().size() != 1 ) {
            final JsonObject error = new JsonObject();
            error.put("error", "The number of file must be 1.");

            routingContext.response()
                    .setStatusCode(400)
                    .putHeader("content-type", "application/json")
                    .end(Json.encode(error));
        }

        FileUpload fileUpload = routingContext.fileUploads().iterator().next();

        try {
            BufferedImage bufferedImage = this.imageProcess.openImage(new File(fileUpload.uploadedFileName()));
            List<DataImage> dataImageList = this.imageProcess.getRGBOfImage(bufferedImage);
            fileInverted = this.imageProcess.createNewInvertedImage(dataImageList, bufferedImage, FILE_LOCATION + "/inverted" + this.getTimestamp() + ".png");

            routingContext.response()
                    .setStatusCode(200)
                    .sendFile(fileInverted.getAbsolutePath())
                    .end();
        } catch (Exception e) {

            final JsonObject error = new JsonObject();
            error.put("error", "Enable to process your file.");

            routingContext.response()
                    .setStatusCode(500)
                    .putHeader("content-type", "application/json")
                    .end(Json.encode(error));
        }finally {
            if(fileInverted.exists())
                fileInverted.delete();
        }

    }

    private void getBlackAndWhite(final RoutingContext routingContext) {
        File fileInverted = null;

        if(routingContext.fileUploads().size() != 1 ) {
            final JsonObject error = new JsonObject();
            error.put("error", "The number of file must be 1.");

            routingContext.response()
                    .setStatusCode(400)
                    .putHeader("content-type", "application/json")
                    .end(Json.encode(error));
        }

        FileUpload fileUpload = routingContext.fileUploads().iterator().next();

        try {
            BufferedImage bufferedImage = this.imageProcess.openImage(new File(fileUpload.uploadedFileName()));
            List<DataImage> dataImageList = this.imageProcess.getRGBOfImage(bufferedImage);
            fileInverted = this.imageProcess.createNewBlackAndWhiteImage(dataImageList, bufferedImage, FILE_LOCATION + "/black_and_white" + this.getTimestamp() + ".png");

            routingContext.response()
                    .setStatusCode(200)
                    .sendFile(fileInverted.getAbsolutePath())
                    .end();
        } catch (Exception e) {

            final JsonObject error = new JsonObject();
            error.put("error", "Enable to process your file.");

            routingContext.response()
                    .setStatusCode(500)
                    .putHeader("content-type", "application/json")
                    .end(Json.encode(error));
        }finally {
            if(fileInverted.exists())
                fileInverted.delete();
        }
    }

    private void getSepia(final RoutingContext routingContext) {
        File fileInverted = null;

        if(routingContext.fileUploads().size() != 1 ) {
            final JsonObject error = new JsonObject();
            error.put("error", "The number of file must be 1.");

            routingContext.response()
                    .setStatusCode(400)
                    .putHeader("content-type", "application/json")
                    .end(Json.encode(error));
        }

        FileUpload fileUpload = routingContext.fileUploads().iterator().next();

        try {
            BufferedImage bufferedImage = this.imageProcess.openImage(new File(fileUpload.uploadedFileName()));
            List<DataImage> dataImageList = this.imageProcess.getRGBOfImage(bufferedImage);
            fileInverted = this.imageProcess.createNewSepiaImage(dataImageList, bufferedImage, FILE_LOCATION + "/sepia" + this.getTimestamp() + ".png");

            routingContext.response()
                    .setStatusCode(200)
                    .sendFile(fileInverted.getAbsolutePath())
                    .end();
        } catch (Exception e) {

            final JsonObject error = new JsonObject();
            error.put("error", "Enable to process your file.");

            routingContext.response()
                    .setStatusCode(500)
                    .putHeader("content-type", "application/json")
                    .end(Json.encode(error));
        }finally {
            if(fileInverted.exists())
                fileInverted.delete();
        }
    }

    private void getShake(final RoutingContext routingContext) {
        File fileInverted = null;

        if(routingContext.fileUploads().size() != 1 ) {
            final JsonObject error = new JsonObject();
            error.put("error", "The number of file must be 1.");

            routingContext.response()
                    .setStatusCode(400)
                    .putHeader("content-type", "application/json")
                    .end(Json.encode(error));
        }

        FileUpload fileUpload = routingContext.fileUploads().iterator().next();

        try {
            BufferedImage bufferedImage = this.imageProcess.openImage(new File(fileUpload.uploadedFileName()));
            List<DataImage> dataImageList = this.imageProcess.getRGBOfImage(bufferedImage);
            fileInverted = this.imageProcess.createNewShakeImage(dataImageList, bufferedImage, FILE_LOCATION + "/sepia" + this.getTimestamp() + ".png");

            routingContext.response()
                    .setStatusCode(200)
                    .sendFile(fileInverted.getAbsolutePath())
                    .end();
        } catch (Exception e) {

            final JsonObject error = new JsonObject();
            error.put("error", "Enable to process your file.");
            error.put("cause", e.getMessage());

            routingContext.response()
                    .setStatusCode(500)
                    .putHeader("content-type", "application/json")
                    .end(Json.encode(error));
        }finally {
            if(fileInverted.exists())
                fileInverted.delete();
        }
    }

    protected String getTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return String.valueOf(timestamp.getTime());
    }
}
