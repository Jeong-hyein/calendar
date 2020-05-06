package calendar.view2;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import calendar.model.CalDAO;
import calendar.model.CalDO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DelController implements Initializable{
	
	@FXML
    private DatePicker snidate;
	
    @FXML
    private TextField schedule;
    
    @FXML
    private TextArea memo;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		snidate.setValue(FullCalendarView.date);
		
		CalDO cal = new CalDO();
		cal.setSnidate(FullCalendarView.date.toString());
		CalDAO dao = new CalDAO();
		dao.search(cal);
		schedule.setText(cal.getSchedule());
		memo.setText(cal.getMemo());
	}
	
	 @FXML private Button delbtn;
	 @FXML private HBox HBox;
	 
		@FXML
	    void delclcik(ActionEvent event) throws IOException {
	        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("삭제 메시지");
	        alert.setHeaderText("일정을 삭제하려합니다.");
	        alert.setContentText("정말 삭제하시겠습니까?");

	        Optional<ButtonType>result = alert.showAndWait();
	        
			CalDO cal = new CalDO();
	    	cal.setSnidate(snidate.getValue().toString());
	    	cal.setSchedule(schedule.getText());
	    	
	    	CalDAO dao = new CalDAO();
	    	dao.delete(cal);
	    	System.out.println("삭제처리됨");
			
	    	
	        Stage main = (Stage) HBox.getScene().getWindow(); 
	        main.close();

//	        parent.document.location.reload();
	        
			Parent add = FXMLLoader.load(getClass().getResource("/calendar/view2/list.fxml"));
			Scene scene = new Scene(add);

			Stage adding = new Stage();

			adding.setScene(scene);
			adding.showAndWait();
			
			

		}
		

	
	

}
