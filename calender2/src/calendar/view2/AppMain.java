package calendar.view2;

import java.time.YearMonth;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppMain extends Application{
	
	@Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/calendar/view2/fullCalendar.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Full Calendar FXML Example");
        primaryStage.setScene(new Scene(root));
        // Get the controller and add the calendar view to it
        MainController controller = loader.getController();
        controller.calendarPane.getChildren().add(new FullCalendarView(YearMonth.now()).getView());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
