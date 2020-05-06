package calendar.view2;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FullCalendarView {
	private ArrayList<AnchorPaneNode> allCalendarDays = new ArrayList<>(35);
	private VBox view;
	private Text calendarTitle;
	private YearMonth currentYearMonth;
	public static LocalDate date;

	public FullCalendarView(YearMonth yearMonth) {
		currentYearMonth = yearMonth;
		// Create the calendar grid pane
		GridPane calendar = new GridPane();
		calendar.setPrefSize(600, 400);
		calendar.setGridLinesVisible(true);
		// Create rows and columns with anchor panes for the calendar
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 7; j++) {
				AnchorPaneNode ap = new AnchorPaneNode();
				ap.setPrefSize(200, 200);
				calendar.add(ap, j, i);
				allCalendarDays.add(ap);
			}
		}

		calendar.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Parent add;
				try {
					
					add = FXMLLoader.load(getClass().getResource("/calendar/view2/list.fxml"));
					Scene scene = new Scene(add);
					Stage adding = new Stage();
					adding.setScene(scene);
					adding.show();
										
					
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		});

		// Days of the week labels
		Text[] dayNames = new Text[] { new Text("일요일"), new Text("월요일"), new Text("화요일"), new Text("수요일"),
				new Text("목요일"), new Text("금요일"), new Text("토요일") };
		GridPane dayLabels = new GridPane();
		dayLabels.setPrefWidth(600);
		dayLabels.setAlignment(Pos.BASELINE_CENTER);
		Integer col = 0;
		for (Text txt : dayNames) {
			AnchorPane ap = new AnchorPane();
			ap.setPrefSize(200, 10);
			ap.setBottomAnchor(txt, 5.0);
			ap.getChildren().add(txt);
			dayLabels.add(ap, col++, 0);
		}

		// Create calendarTitle and buttons to change current month
		calendarTitle = new Text();
		Button previousMonth = new Button("<<");
		previousMonth.setOnAction(e -> previousMonth());
		Button nextMonth = new Button(">>");
		nextMonth.setOnAction(e -> nextMonth());

		Button addClick = new Button("일정추가");
//        addClick.setMargin(Right , new Insets(0, 0, 0, 10));

		addClick.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Parent add;
				try {
					add = FXMLLoader.load(getClass().getResource("schedule.fxml"));
					Scene scene = new Scene(add);

					Stage adding = new Stage();

					adding.setScene(scene);
					adding.showAndWait();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		HBox titleBar = new HBox(previousMonth, calendarTitle, nextMonth, addClick);
		titleBar.setAlignment(Pos.BASELINE_CENTER);
		// Populate calendar with the appropriate day numbers
		populateCalendar(yearMonth);
		// Create the calendar view
		view = new VBox(titleBar, dayLabels, calendar);
	}

	/**
	 * Set the days of the calendar to correspond to the appropriate date
	 * 
	 * @param yearMonth year and month of month to render
	 */
	
	public void populateCalendar(YearMonth yearMonth) {
		// Get the date we want to start with on the calendar
		LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
		// Dial back the day until it is SUNDAY (unless the month starts on a sunday)
		while (!calendarDate.getDayOfWeek().toString().equals("SUNDAY")) {
			calendarDate = calendarDate.minusDays(1);
		}
		// Populate the calendar with day numbers
		for (AnchorPaneNode ap : allCalendarDays) {
			if (ap.getChildren().size() != 0) {
				ap.getChildren().remove(0);
			}
			Text txt = new Text(String.valueOf(calendarDate.getDayOfMonth()));
			ap.setDate(calendarDate);
			ap.setTopAnchor(txt, 5.0);
			ap.setLeftAnchor(txt, 5.0);
			ap.getChildren().add(txt);
			calendarDate = calendarDate.plusDays(1);
		}
		// Change the title of the calendar
		calendarTitle.setText(yearMonth.getMonth().toString() + " " + String.valueOf(yearMonth.getYear()));
	}

	/**
	 * Move the month back by one. Repopulate the calendar with the correct dates.
	 */
	private void previousMonth() {
		currentYearMonth = currentYearMonth.minusMonths(1);
		populateCalendar(currentYearMonth);
	}

	/**
	 * Move the month forward by one. Repopulate the calendar with the correct
	 * dates.
	 */
	private void nextMonth() {
		currentYearMonth = currentYearMonth.plusMonths(1);
		populateCalendar(currentYearMonth);
	}

	public VBox getView() {
		return view;
	}

	public ArrayList<AnchorPaneNode> getAllCalendarDays() {
		return allCalendarDays;
	}

	public void setAllCalendarDays(ArrayList<AnchorPaneNode> allCalendarDays) {
		this.allCalendarDays = allCalendarDays;
	}

}
