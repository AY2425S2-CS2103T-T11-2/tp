package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Student;

/**
 * Controller for the Find Window.
 * This version only shows the result in the PersonListPanel.
 */
public class FindWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(FindWindow.class);
    private static final String FXML = "FindWindow.fxml";

    @FXML
    private VBox personListPanelPlaceholder;

    // This PersonListPanel will display the PersonCards (the result).
    private PersonListPanel personListPanel;

    /**
     * Creates a new FindWindow with the given Stage.
     *
     * @param root Stage to use as the root of the FindWindow.
     */
    public FindWindow(Stage root) {
        super(FXML, root);
        getRoot().setTitle("Find Window");
        getRoot().setMinWidth(300);
        getRoot().setMinHeight(300);
    }

    /**
     * Creates a new FindWindow.
     */
    public FindWindow() {
        this(new Stage());
    }

    /**
     * Fills up all the placeholders of this window.
     *
     * @param personList
     */
    public void fillInnerParts(ObservableList<Student> personList) {
        personListPanel = new PersonListPanel(personList);
        personListPanelPlaceholder.getChildren().clear();
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
    }

    /**
     * Closes the Find window.
     */
    @FXML
    private void handleClose() {
        getRoot().hide();
    }

    /**
     * Opens the Find window.
     */
    public void show() {
        logger.fine("Showing find window");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the Find window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the Find window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the Find window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
