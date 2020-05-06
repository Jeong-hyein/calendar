package calendar.view2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import calendar.model.CalDAO;
import calendar.model.CalDO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ListController implements Initializable {

	@FXML
	private Label date;

	@FXML
	private Label schedule1, schedule2, schedule3;
	
	@FXML
	private VBox vbox;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		date.setText(FullCalendarView.date.toString());

		CalDO cal = new CalDO();
		cal.setSnidate(FullCalendarView.date.toString());
		CalDAO dao = new CalDAO();
		dao.search(cal);
		if (cal.getSchedule() != null) {
			schedule1.setText(cal.getSchedule());
		}else if(cal.getSchedule() != null ) {
			schedule2.setText(cal.getSchedule());
			
		}else if(cal.getSchedule() != null){
			schedule3.setText(cal.getSchedule());		
		}else {}
		
		
		schedule1.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Parent add;
				try {
					
					add = FXMLLoader.load(getClass().getResource("/calendar/view2/deleteSchedule.fxml"));
					Scene scene = new Scene(add);
					Stage adding = new Stage();
					adding.setScene(scene);
					adding.show();

				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		});
		schedule2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Parent add;
				try {
					
					add = FXMLLoader.load(getClass().getResource("deleteSchedule.fxml"));
					Scene scene = new Scene(add);
					Stage adding = new Stage();
					adding.setScene(scene);
					adding.show();
				
										
					
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		});
		schedule3.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Parent add;
				try {
					
					add = FXMLLoader.load(getClass().getResource("deleteSchedule.fxml"));
					Scene scene = new Scene(add);
					Stage adding = new Stage();
					adding.setScene(scene);
					adding.show();
					
										
					
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		});
			
	}

	@FXML
	private Button btninsert;

	@FXML
	void addclick(ActionEvent event) throws IOException {
		// 새창
		Parent add = FXMLLoader.load(getClass().getResource("schedule.fxml"));
		Scene scene = new Scene(add);

		Stage adding = new Stage();

		adding.setScene(scene);
		adding.showAndWait();
		
	}

		
//	@FXML //테이블뷰 마우스 클릭한 행의 정보를 텍스트필드에 표시
//	void deleteList(MouseEvent e) {
//		Parent add;
//		try {
//			add = FXMLLoader.load(getClass().getResource("deleteScuedule.fxml"));
//			Scene scene = new Scene(add);
//			Stage adding = new Stage();
//			adding.setScene(scene);
//			adding.showAndWait();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//	}
	
//		EmpDO empDO = tvEmp.getSelectionModel().getSelectedItem(); 
//		txtEmployeeId.setText(empDO.getEmployeeId());
//		txtLastName.setText(empDO.getLastName());
//		txtEmail.setText(empDO.getEmail());
//		txtHireDate.setText(empDO.getHireDate());
//		txtJobId.setText(empDO.getJobId());
//	}

}

