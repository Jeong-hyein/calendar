package calendar.view2;

import java.time.LocalDate;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class AnchorPaneNode extends AnchorPane {
	 // Date associated with this pane
    private LocalDate date;

    public AnchorPaneNode(Node... children) {
        super(children);
        // Add action handler for mouse clicked
        this.setOnMouseClicked(e -> {System.out.println("Click Date: " + date);
        FullCalendarView.date = date;
        });
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
