package com.example.gui_dbms;

import dbms.Row;
import dbms.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;


public class OperationWindow {

    private ComboBox<String> tables_names;

    private ArrayList<CheckBox> boxes;
    private ArrayList<TextField> insertionFields;
    private DatabaseCaller dbCaller;
    private Stage primaryStage;
    private GridPane grid;
    private ArrayList<String> fieldList;
    private ArrayList<TextField> newFieldList;
    private String selectedTable;
    private Integer checkBoxHeight = 0;
    private Integer newTableFieldHeight = 1;
    private TableView tableView;
    private Table curTable;
    public OperationWindow() {
        dbCaller = new DatabaseCaller();
        fieldList = new ArrayList<>();
        boxes = new ArrayList<>();
        tableView = new TableView();
        newFieldList = new ArrayList<>();
        insertionFields = new ArrayList<>();
        primaryStage = new Stage();
        primaryStage.setTitle("DBMS");

        grid = new GridPane();
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

        Label createNew = new Label("New Table fields:");
        grid.add(createNew, 0, 2);

        TextField field0 = new TextField();
        newFieldList.add(field0);
        grid.add(field0, 1, 2);
        Button addField = new Button("Add Field");
        TextField tableName = new TextField();
        Button createTable = new Button("Create New");
        grid.add(addField, 2, 2);
        grid.add(tableName, 3, 3);
        grid.add(createTable, 4, 3);

        Label chooseTable = new Label("Table Name:");
        grid.add(chooseTable, 0, 3+newTableFieldHeight);
        tables_names = new ComboBox<>();
        grid.add(tables_names, 3, 3+newTableFieldHeight);
        Button selectButton = new Button("Select Result");
        Button createButton = new Button("Create Table");
        Button storeButton = new Button("Store");
        Label newTableName = new Label("Table Name:");
        TextField newTableNameText = new TextField();
        Button insertButton = new Button("Insert Row");

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
                insertionFields.clear();
                fieldList.addAll(Arrays.asList(fields));

                grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 3 + newTableFieldHeight);
                for (int i = 0; i < fieldList.size(); i++) {
                    CheckBox cb = new CheckBox(fieldList.get(i));
                    cb.setSelected(true);
                    boxes.add(cb);
                    grid.add(cb, i%5, 4 + i/5 +newTableFieldHeight);

                }
                checkBoxHeight = (fieldList.size() - 1) / 5 + 1;
                HBox sltBtn = new HBox(10);
                sltBtn.setAlignment(Pos.TOP_LEFT);
                sltBtn.getChildren().add(selectButton);
                grid.add(sltBtn, 4, 4+checkBoxHeight+newTableFieldHeight);
                for (int i = 0; i < fieldList.size(); i++) {
                    TextField fd = new TextField();
                    grid.add(fd, i%5,  5+checkBoxHeight+newTableFieldHeight + i/5);
                    insertionFields.add(fd);
                }
                grid.add(insertButton, 4, 5+checkBoxHeight*2+newTableFieldHeight);

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
                curTable = dbCaller.selectClause(selectedTable, null, cols, null);
                List<Map<String, String>> data = getDataFromTable(curTable, cols);
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
                grid.add(tableView, 0, 7+checkBoxHeight*2+newTableFieldHeight, 5, 1);
                grid.add(newTableName, 0, 8+2*checkBoxHeight+newTableFieldHeight );
                grid.add(newTableNameText, 1, 8+2*checkBoxHeight+newTableFieldHeight);
                grid.add(createButton, 2, 8+2*checkBoxHeight+newTableFieldHeight);
                grid.add(storeButton, 3, 8+2*checkBoxHeight+newTableFieldHeight);

            } catch (Exception e) {
                System.out.println("select error: " + e);
            }
        });

        storeButton.setOnAction((actionEvent) -> {
            try {
                String name = newTableNameText.getText();
                curTable.writeTable(name);
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Table "+name+" stored", ButtonType.OK);
                a.show();
            } catch (Exception e) {
                System.out.println("store error: " + e);
            } finally {
                newTableNameText.setText("");
            }


        });

        createButton.setOnAction((actionEvent) -> {
            try {
                String name = newTableNameText.getText();
                dbCaller.saveTable(name, curTable);
                tables_names.getItems().add(name);
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Table "+name+" saved", ButtonType.OK);
                a.show();
            } catch (Exception e) {
                System.out.println("create from select error: " + e);
            } finally {
                newTableNameText.setText("");
            }
        });

        addField.setOnAction((ae) -> {
            TextField newField = new TextField();
            newFieldList.add(newField);
            grid.add(newField,newFieldList.size() % 5, 2 + (newFieldList.size()) / 5);
            grid.getChildren().remove(addField);
            int moves = 0;
            if (newFieldList.size() % 5 == 4){
                moves = 1;
                newTableFieldHeight += 1;
                System.out.println(newTableFieldHeight);
            }
            moveNodesVertically(moves, 2 + (newFieldList.size()+1) / 5);
            grid.add(addField, (newFieldList.size()+1) % 5, 2 + (newFieldList.size()+1) / 5);
        });


        createTable.setOnAction((ae) -> {
            try{
                ArrayList<String> sList = new ArrayList<>();
                for (TextField textField: newFieldList) {
                    String s = textField.getText();
                    if (s != null && !s.equals("")) {
                        sList.add(s);
                    }
                }
                if (!Objects.equals(tableName.getText(), "")) {
                    dbCaller.createTable(tableName.getText(), sList);
                    tables_names.getItems().add(tableName.getText());
                }
                else
                    throw new Exception("Table name cannot be empty");
            } catch (Exception ex){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText(ex.toString());
                // show the dialog
                a.show();
            } finally {
                for (Node n: newFieldList) {
                    if (n!=field0)
                        grid.getChildren().remove(n);
                }
                newFieldList.removeIf(field -> !field.equals(field0));
                grid.getChildren().remove(addField);
                grid.getChildren().remove(createTable);
                grid.getChildren().remove(tableName);
                grid.add(addField, 2, 2);
                grid.add(tableName, 3, 3);
                grid.add(createTable, 4, 3);
                moveNodesVertically(1-newTableFieldHeight, newTableFieldHeight+3);
                newTableFieldHeight = 1;
                field0.setText("");
                tableName.setText("");

            }
        });

        insertButton.setOnAction((ae) -> {
            try {
                String[] values = new String[insertionFields.size()];
                for (int i = 0; i < insertionFields.size(); i++) {
                    values[i] = insertionFields.get(i).getText();
                }
                dbCaller.insert(values, selectedTable);

            } catch (Exception e) {
                System.out.println(e);
            } finally {
                for (TextField t: insertionFields) {
                    t.setText("");
                }
            }
        });

        Scene scene = new Scene(grid, 1000, 1200);
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

    private void moveNodesVertically(int moves, int fromRow) {
        if (moves == 0)
            return;
        ArrayList<Node> nodesToMove = new ArrayList<>();
        HashMap<Node, Integer[]> oldPositions = new HashMap<>();
        for (Node n: grid.getChildren()) {
            if (GridPane.getRowIndex(n) >= fromRow) {
                nodesToMove.add(n);
                oldPositions.put(n, new Integer[]{grid.getRowIndex(n), grid.getColumnIndex(n)});
            }
        }
        grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) >= fromRow);
        for (Node n: nodesToMove) {
            grid.add(n, oldPositions.get(n)[1], oldPositions.get(n)[0]+moves);
        }
    }


}
