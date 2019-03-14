package fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AirQualityFxApplication extends Application {
 
    private ConfigurableApplicationContext context;
    private Parent rootNode;
 
    @Override
    public void init() throws Exception {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(AirQualityFxApplication.class);
        context = builder.run(getParameters().getRaw().toArray(new String[0]));
 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        loader.setControllerFactory(context::getBean);
        rootNode = loader.load();
    }
 
 
    @Override
    public void start(Stage primaryStage) throws Exception {
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        double width = 200;//visualBounds.getWidth();
        double height = 200;//visualBounds.getHeight();
 
        primaryStage.setScene(new Scene(rootNode, width, height));
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
 
    @Override
    public void stop() throws Exception {
        context.close();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}