package com.example.gui_dbms;

import dbms.Row;
import dbms.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;


public class OperationWindow {

    @FXML
    private ComboBox<String> tables_names;

    private ArrayList<CheckBox> boxes;

    private DatabaseCaller dbCaller;

    private ArrayList<String> fieldList;
    private String selectedTable;
    private Integer checkBoxHeight = 0;
    private TableView tableView;
    public OperationWindow() {
        dbCaller = new DatabaseCaller();
        fieldList = new ArrayList<>();
        boxes = new ArrayList<>();
        tableView = new TableView();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("DBMS");

        GridPane grid = new GridPane();

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        Text sceneTitle = new Text("Database System");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label fileName = new Label("File Name:");
        grid.add(fileName, 0, 1);

        TextField fileNameTextField = new TextField();
        grid.add(fileNameTextField, 3, 1);

        Button loadButton = new Button("Load Table");
        //addButton
        HBox AddBtn = new HBox(10);
        AddBtn.setAlignment(Pos.TOP_LEFT);
        AddBtn.getChildren().add(loadButton);
        grid.add(AddBtn, 4, 1);

        Label chooseTable = new Label("Table Name:");
        grid.add(chooseTable, 0, 2);
        tables_names = new ComboBox<>();
        grid.add(tables_names, 3, 2);
        Button selectButton = new Button("Select Result");

        loadButton.setOnAction((ae) -> {
            String file_name = fileNameTextField.getText();
            try {
                if (dbCaller.getTableNames().contains(file_name))
                    throw new RuntimeException(file_name + " already exist!");
                dbCaller.loadTable(file_name);
                tables_names.getItems().add(file_name);

            } catch (RuntimeException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText(ex.toString());
                // show the dialog
                a.show();
            } finally {
                fileNameTextField.setText("");
            }
        });

        // Create action event
        tables_names.setOnAction((ae) -> {
            try {
                selectedTable = tables_names.getValue();
                String[] fields = dbCaller.getTableFields(selectedTable);
                fieldList.clear();
                boxes.clear();
                fieldList.addAll(Arrays.asList(fields));

                grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 2);
                for (int i = 0; i < fieldList.size(); i++) {
                    CheckBox cb = new CheckBox(fieldList.get(i));
                    cb.setSelected(true);
                    boxes.add(cb);
                    grid.add(cb, i%4, 3 + i/4);
                }
                checkBoxHeight = (fieldList.size() - 1) / 4;
                HBox sltBtn = new HBox(10);
                sltBtn.setAlignment(Pos.TOP_LEFT);
                sltBtn.getChildren().add(selectButton);
                grid.add(sltBtn, 4, 4+checkBoxHeight);

            } catch (Exception e) {
                System.out.println("Error in set on check boxes:" + e);
            }
        });

        selectButton.setOnAction((actionEvent) -> {
//            get selected columns from selectedTable
            try {
                ArrayList<String> cols = new ArrayList<>();
                for (CheckBox b: boxes) {
                    if (b.isSelected())
                        cols.add(b.getText());
                }
                Table result = dbCaller.selectClause(selectedTable, null, cols, null);
                List<Map<String, String>> data = getDataFromTable(result, cols);
                ObservableList<Map<String, String>> items =
                        FXCollections.<Map<String, String>>observableList(data);
                tableView.getColumns().clear();
                for (String title: cols) {
                    System.out.print(title + "111");
                    TableColumn<Map, String> oneColumn = new TableColumn<>(title);
                    oneColumn.setCellValueFactory(new MapValueFactory<>(title));
                    tableView.getColumns().add(oneColumn);
//                    columnList.add(oneColumn); //??
                }
                tableView.getItems().clear();
                tableView.getItems().addAll(items);
                tableView.setPlaceholder(
                        new Label("No rows to display"));
                grid.add(tableView, 0, 5+checkBoxHeight, 5, 1);
            } catch (Exception e) {
                System.out.println("select error: " + e);
            }
        });

        Scene scene = new Scene(grid, 1000, 1000);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ArrayList<Map<String, String>> getDataFromTable(Table table, ArrayList<String> colNameList) {
        ArrayList<Map<String, String>> result = new ArrayList<>();
        Iterator<Row> it = table.iterator();
        if (it.hasNext())   it.next();
        while (it.hasNext()) {
            Row r = it.next();
            HashMap<String, String> row = new HashMap<>();
            int i = 0;
            for (String s: colNameList) {
                row.put(s, r.get(i));
                i++;
            }
            result.add(row);
        }
        return result;
    }

}
