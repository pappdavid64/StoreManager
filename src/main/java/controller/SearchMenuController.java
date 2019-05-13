package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Items;
import model.SoldItems;
import util.DBConnector;
import java.util.Date;



public class SearchMenuController {

    @FXML
    private ListView<Items> resultListView;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField quantityTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField typeTextField;

    @FXML
    private TextField searchTextField;

    @FXML
    private ChoiceBox<String> searchChoiceBox;

    @FXML
    private ChoiceBox<String> mightChoiceBox;

    @FXML
    public void initialize()
    {
        ObservableList list = FXCollections.observableList(DBConnector.getAll());
        resultListView.setItems(list);
        searchChoiceBox.setItems(FXCollections.observableArrayList("name", "quantity", "price", "type", "id"));
        mightChoiceBox.setItems(FXCollections.observableArrayList("<", "=", ">"));

    }

    @FXML
    private void back()
    {
        MenuLoader.showMainMenu();
    }

    //@FXML
    public void search()
    {
        resultListView.setItems(
                FXCollections.observableArrayList(
                        DBConnector.search(searchChoiceBox.getValue(), mightChoiceBox.getValue(),searchTextField.getText())
                )
        );
    }

   // @FXML
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

   // @FXML
    public void delete()
    {
        if(resultListView.getSelectionModel().getSelectedItem() != null)
        {
            DBConnector.delete(resultListView.getSelectionModel().getSelectedItem());
            resultListView.setItems(FXCollections.observableArrayList(DBConnector.getAll()));
            resultListView.refresh();
        }

    }


   // @FXML
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

    //@FXML
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
