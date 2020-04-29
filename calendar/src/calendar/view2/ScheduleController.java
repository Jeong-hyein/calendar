package calendar.view2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import calendar.model.CalDAO;
import calendar.model.CalDO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ScheduleController implements Initializable{

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		snidate.setValue(FullCalendarView.date);
	}

	@FXML
	private HBox HBox;
	
    @FXML
    private Button btnSave;
    
    @FXML
    private DatePicker snidate;
    
    @FXML
    private TextField schedule;
    
    @FXML
    private TextArea memo;
    
	@FXML
    void saveclcik(ActionEvent event) throws IOException {
		
		CalDO cal = new CalDO();
    	cal.setSnidate(snidate.getValue().toString());
    	cal.setSchedule(schedule.getText());
    	cal.setMemo(memo.getText());
    	
    	CalDAO dao = new CalDAO();
    	dao.insert(cal);
    	System.out.println("등록처리됨");
		
        Stage main = (Stage) HBox.getScene().getWindow();
        main.close();

	}
//	   public void goHome() {
//		      try {
//		         BorderPane diaryListView = FXMLLoader.load(getClass().getResource("diaryList.fxml"));
//
//		          Scene scene = new Scene(diaryListView);
//
//		          Stage window = new Stage();
//		          window.setScene(scene);
//
//		          // make window visible
//		          window.show();
//		          
//		          // 메인 창 닫아주기
//		          Stage main = (Stage) rootLayout.getScene().getWindow();
//		          main.close();
//		      } catch (Exception e) {
//		         e.printStackTrace();
//		      }
//		      
//		   } // e of goHome
	
	
}
