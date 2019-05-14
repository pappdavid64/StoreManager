package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Items;
import model.SoldItems;
import util.DBConnector;
import java.util.Date;


/**
 * Controller class of the search menu.
 */
public class SearchMenuController {

    /**
     * The result list view of the view, which contains the result of the search.
     */
    @FXML
    private ListView<Items> resultListView;

    /**
     * The name text field of the view.
     */
    @FXML
    private TextField nameTextField;

    /**
     * The quantity text field of the view.
     */
    @FXML
    private TextField quantityTextField;

    /**
     * The price text field of the view.
     */
    @FXML
    private TextField priceTextField;

    /**
     * The type text field of the view.
     */
    @FXML
    private TextField typeTextField;

    /**
     * The search text field of the view.
     */
    @FXML
    private TextField searchTextField;

    /**
     * The search type choice box of the view.
     */
    @FXML
    private ChoiceBox<String> searchChoiceBox;

    /**
     * The operator choice box of the view.
     */
    @FXML
    private ChoiceBox<String> mightChoiceBox;

    /**
     * Initializes the resultLitView, the searchChoiceBox and the mightChoiceBox.
     */
    @FXML
    public void initialize()
    {
        ObservableList list = FXCollections.observableList(DBConnector.getAll());
        resultListView.setItems(list);
        searchChoiceBox.setItems(FXCollections.observableArrayList("name", "quantity", "price", "type", "id"));
        mightChoiceBox.setItems(FXCollections.observableArrayList("<", "=", ">"));

    }

    /**
     * Navigates back to the main menu.
     */
    @FXML
    private void back()
    {
        MenuLoader.showMainMenu();
    }

    /**
     * Searches in the database by the given conditions.
     */
    public void search()
    {
        resultListView.setItems(
                FXCollections.observableArrayList(
                        DBConnector.search(searchChoiceBox.getValue(), mightChoiceBox.getValue(),searchTextField.getText())
                )
        );
    }

    /**
     * Modifies the selected item by the given name and/or quantity and/or price and/or type.
     */
    public void modify()
    {
        if(resultListView.getSelectionModel().getSelectedItem() != null)
        {
            if(!nameTextField.getText().equals(""))
            {
                resultListView.getSelectionModel().getSelectedItem().setName(nameTextField.getText());
            }

            if(!quantityTextField.getText().equals(""))
            {
                resultListView.getSelectionModel().getSelectedItem().setQuantity(Integer.valueOf(quantityTextField.getText()));
            }

            if(!priceTextField.getText().equals(""))
            {
                resultListView.getSelectionModel().getSelectedItem().setPrice(Integer.valueOf(priceTextField.getText()));
            }

            if(!typeTextField.getText().equals(""))
            {
                resultListView.getSelectionModel().getSelectedItem().setType(typeTextField.getText());
            }
            DBConnector.modify(resultListView.getSelectionModel().getSelectedItem());
            resultListView.setItems(FXCollections.observableArrayList(DBConnector.getAll()));
            resultListView.refresh();
        }

    }

    /**
     * Delete the selected item.
     */
    public void delete()
    {
        if(resultListView.getSelectionModel().getSelectedItem() != null)
        {
            DBConnector.delete(resultListView.getSelectionModel().getSelectedItem());
            resultListView.setItems(FXCollections.observableArrayList(DBConnector.getAll()));
            resultListView.refresh();
        }

    }

    /**
     * Add the item to the database if the name, quantity, price, type field is filled correctly.
     */
    public void add()
    {
        if(
                !nameTextField.getText().equals("") &&
                !quantityTextField.getText().equals("") &&
                !priceTextField.getText().equals("") &&
                !typeTextField.getText().equals("")
        )
        {
            DBConnector.createItem(new Items(nameTextField.getText(), Integer.valueOf(quantityTextField.getText()),
                    Integer.valueOf(priceTextField.getText()), typeTextField.getText()) );
            resultListView.setItems(FXCollections.observableArrayList(DBConnector.getAll()));
            resultListView.refresh();
        }

    }

    /**
     * Modifies the selected item quantities by the number in the quantity text field.
     */
    public void sell()
    {
        if(resultListView.getSelectionModel().getSelectedItem() != null) {
            if (!quantityTextField.getText().equals("")) {
                Items item  = resultListView.getSelectionModel().getSelectedItem();
                int sellQuantity = Integer.valueOf(quantityTextField.getText());
                if (item.getQuantity() -  sellQuantity>= 0) {
                    item.setQuantity(item.getQuantity() - sellQuantity);

                    SoldItems soldItem = new SoldItems(item.getName(),sellQuantity , item.getPrice(),item.getType(), new Date());
                    //System.out.println(soldItem);
                    DBConnector.createItem(soldItem);
                    DBConnector.modify(item);
                    resultListView.setItems(FXCollections.observableArrayList(DBConnector.getAll()));
                    resultListView.refresh();
                }
            }
        }
    }

}
