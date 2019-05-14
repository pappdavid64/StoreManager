package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.SoldItems;
import model.Statistics;
import util.QueryBuilder;

/**
 * Controller class of the StaticsMenu.
 */
public class StatisticsMenuController {

    /**
     * Initializes the searchTypeChoiceBox.
     */
    @FXML
    public void initialize() {
        searchTypeChoiceBox.setItems(FXCollections.observableArrayList("name", "type"));
    }

    /**
     * Navigates back to the main menu.
     */
    @FXML
    private void back() {
        MenuLoader.showMainMenu();
    }

    /**
     * Sets the summary label with the statistics from the search.
     */
    private void setSummaryLabel() {
        Statistics stats = new Statistics(resultListView.getItems());
        summaryLabel.setText(
                "All sold items quantity: " + stats.getSumQuantity() + "\n" +
                "Income from sold item: " + stats.getIncome()
            );
    }

    /**
     * Search in the database by the given conditions.
     */
    public void search() {
        QueryBuilder<SoldItems> firstQuery =
                new QueryBuilder<>(SoldItems.class)
                        .withColumn(searchTypeChoiceBox.getValue())
                        .withText(searchTextField.getText());

        QueryBuilder<SoldItems> secondQuery =
                new QueryBuilder<>(SoldItems.class)
                        .withColumn("soldDate").withOperator(">=");

        if(fromDatePicker.getValue() != null) {
            secondQuery.withText(fromDatePicker.getValue().toString());
        }

        QueryBuilder<SoldItems> thirdQuery =
                new QueryBuilder<>(SoldItems.class)
                        .withColumn("soldDate").withOperator("<=");

        if(toDatePicker.getValue() != null) {
            thirdQuery.withText(toDatePicker.getValue().toString());
        }

        resultListView.setItems(FXCollections.observableArrayList(
                new QueryBuilder<SoldItems>(SoldItems.class).andQueries(firstQuery, secondQuery, thirdQuery).build().getResultList())
        );
        resultListView.refresh();
        setSummaryLabel();

    }

    /**
     * The search text field of the view.
     */
    @FXML
    private TextField searchTextField;

    /**
     * The search type choice box of the view.
     */
    @FXML
    private ChoiceBox<String> searchTypeChoiceBox;

    /**
     * The from date picker of the view.
     */
    @FXML
    private DatePicker fromDatePicker;

    /**
     * The to date picker of the view.
     */
    @FXML
    private DatePicker toDatePicker;

    /**
     * The search button of the view.
     */
    @FXML
    private Button searchButton;

    /**
     * The summary label of the view.
     */
    @FXML
    private Label summaryLabel;

    /**
     * The result list view of the view, which contains the find item by the search.
     */
    @FXML
    private ListView<SoldItems> resultListView;
}
