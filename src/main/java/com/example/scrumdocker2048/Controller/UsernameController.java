/**
 * The <code>UsernameController</code> class is responsible for the logic behind the username screen.
 * It contains the logic for the UI elements and their respective events.
 */
package com.example.scrumdocker2048.Controller;

import com.example.scrumdocker2048.Model.Username;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UsernameController extends AbstractController {

    public static Username currentUsername;

    /**
     * A text field where the username is entered.
     */
    @FXML
    private TextField txtUsername;

    /**
     * A password field where the password is entered.
     */
    @FXML
    private PasswordField txtPassword;

    /**
     * A choice box that displays a list of previously entered usernames.
     */
    @FXML
    private ChoiceBox<String> dropdownUsername;

    /**
     * A button that lets the user play the game.
     */
    @FXML
    private Button btnPlay;

    /**
     * A button that lets the user go back to the start menu.
     */
    @FXML
    private Button btnBack;

    /**
     * A button that lets the user close the game.
     */
    @FXML
    private Button btnClose;

    /**
     * A string representing the current username.
     */
    private static String name;

    /**
     * Returns the current username.
     *
     * @return the current username.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the current username.
     *
     * @param name the new username.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * An observable list that contains the usernames from the database.
     */
    private ObservableList<String> list = FXCollections.observableArrayList();

    /**
     * Initializes the username screen by setting the prompt texts for the text fields and loading
     * the list of usernames from the database.
     */
    public void initialize() {
        this.txtUsername.setPromptText("username");
        this.txtPassword.setPromptText("password");
        for (Username username : Username.getList()) {
            list.add(username.getUsername());
        }
        if (list.size() > 0) {
            this.dropdownUsername.setItems(list);
            dropdownUsername.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

                public void changed(ObservableValue ov, Number value, Number new_value) {
                    txtUsername.setText(dropdownUsername.getItems().get(new_value.intValue()) + "");
                    setName(dropdownUsername.getItems().get(Integer.parseInt(new_value.intValue() + "")));
                }
            });
        }
    }

    /**
     * Go back to the startmenue.fxml if the back button was pressed
     *
     * @param event
     */
    @FXML
    void btnBackPressed(ActionEvent event) {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
        try {
            StartmenueController c = this.loadFxmlFile("startmenue.fxml", "Spiel",
                    ((Button) event.getSource()).getScene().getWindow(), StartmenueController.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Close the Stage if the Close button was pressed.
     *
     * @param event
     */
    @FXML
    void btnClosePressed(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    /**
     * Button to start the game and set the name
     *
     * @param event
     */
    @FXML
    void btnPlayPressed(ActionEvent event) {
        boolean check = true;
        name = txtUsername.getText();


        for (Username user : Username.getList()) {
            if (name.equals(user.getUsername())) {
                if (PasswordHasher.hashPassword(txtPassword.getText()).equals(user.getPassword())) {
                    Stage stage = (Stage) btnPlay.getScene().getWindow();
                    stage.close();
                    try {
                        currentUsername = user;
                        PlayfieldController c = this.loadFxmlFile("playfield.fxml", "Spiel",
                                ((Button) event.getSource()).getScene().getWindow(), PlayfieldController.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                check = false;
            }
        }
        if (check) {
            // Insert new User in Database
            Username username = new Username(txtUsername.getText(), txtPassword.getText());
            username.insertUser();

            currentUsername = username;

            Stage stage = (Stage) btnPlay.getScene().getWindow();
            stage.close();
            try {
                PlayfieldController c = this.loadFxmlFile("playfield.fxml", "Spiel",
                        ((Button) event.getSource()).getScene().getWindow(), PlayfieldController.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}