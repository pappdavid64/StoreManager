package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.SoldItems;
import util.QueryBuilder;


public class StatisticsMenuController {

    @FXML
    private void back()
    {
        MenuLoader.showMainMenu();
    }

    @FXML
    public void initialize()
    {
        searchTypeChoiceBox.setItems(FXCollections.observableArrayList("name", "type"));
    }

    @FXML
    private void setSummaryLabel()
    {
        int sumPrice = 0;
        int sumQuantity = 0;
        for(SoldItems item : resultListView.getItems())
        {
            sumQuantity += item.getQuantity();
            sumPrice += item.getPrice()*item.getQuantity();
        }
        summaryLabel.setText(
                "All sold items quantity: " + sumQuantity + "\n" +
                "Income from sold item: " + sumPrice
            );
    }

    @FXML
    public void search()
    {
        QueryBuilder<SoldItems> firstQuery =
                new QueryBuilder<>(SoldItems.class)
                        .withColumn(searchTypeChoiceBox.getValue())
                        .withText(searchTextField.getText());
        QueryBuilder<SoldItems> secondQuery =
                new QueryBuilder<>(SoldItems.class)
                        .withColumn("soldDate").withOperator(">=");
        if(fromDatePicker.getValue() != null)
        {
            secondQuery.withText(fromDatePicker.getValue().toString());
        }
        QueryBuilder<SoldItems> thirdQuery =
                new QueryBuilder<>(SoldItems.class)
                        .withColumn("soldDate").withOperator("<=");
        if(toDatePicker.getValue() != null)
        {
            thirdQuery.withText(toDatePicker.getValue().toString());
        }
        resultListView.setItems(FXCollections.observableArrayList(
                new QueryBuilder<SoldItems>(SoldItems.class).andQueries(firstQuery, secondQuery, thirdQuery).build().getResultList())
        );
        resultListView.refresh();
        setSummaryLabel();

    }

    @FXML
    private TextField searchTextField;

    @FXML
    private ChoiceBox<String> searchTypeChoiceBox;

    @FXML
    private DatePicker fromDatePicker;

    @FXML
    private DatePicker toDatePicker;

    @FXML
    private Button searchButton;

    @FXML
    private Label summaryLabel;

    @FXML
    private ListView<SoldItems> resultListView;
}
