package todolist;

import java.net.URL;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.StringTokenizer;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
 
public class AppUIController implements Initializable {
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    //variables to use to store values to create ToDoItems 
    static LocalDate userDate;
    static String Item;
    static int importantLevel;
    
    //Arrays to store task by groups
    static ArrayList<ToDoItem> list = new ArrayList<ToDoItem>();
    static ArrayList<String> keepItems = new ArrayList<>();
    static ArrayList<String> tommorrowTask = new ArrayList<>();
    static ArrayList<String> importantItem = new ArrayList<>();
    static ArrayList<String> TodaysItems = new ArrayList<>();


    //Adding task scene part
    
    @FXML
    private Label endMassage;

    @FXML
    private ToggleGroup levelOfImportant;

    @FXML
    private DatePicker userDueDate;

    @FXML
    private RadioButton userLevelHigh;

    @FXML
    private RadioButton userLevelLow;

    @FXML
    private RadioButton userLevelMiddle;

    @FXML
    private TextField userTask;

    //setting todaysdate label to current date
    DateFormat Date = DateFormat.getDateInstance();
    Calendar cals = Calendar.getInstance();
    String currentdate = Date.format(cals.getTime());
    //AppUI scene part
    @FXML
    private Label TodaysDate =  new Label(currentdate);

    @FXML
    private ListView<String> toDisplayItems = new ListView<>();

    @FXML
    void addnewTask(ActionEvent event) throws IOException {
    
        //switch to add task scene to allow user to add task
        Parent root = FXMLLoader.load(getClass().getResource("AddingTask.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
void deleteItem(ActionEvent event) {
    ObservableList<String> selectedItems = toDisplayItems.getSelectionModel().getSelectedItems();
    
    Iterator<ToDoItem> iterator = list.iterator();
    while (iterator.hasNext()) {
        ToDoItem tasks = iterator.next();
        String currentTask = tasks.getItem();
        for (String task : selectedItems) {

            if(keepItems.contains(task))
                keepItems.remove(task);

            if(TodaysItems.contains(task))
                TodaysItems.remove(task);

            if(importantItem.contains(task))
                importantItem.remove(task);

            if(tommorrowTask.contains(task))
                tommorrowTask.remove(task);

            if(currentTask.equals(task))
                iterator.remove();
        }
    }

    if (!selectedItems.isEmpty()) {
        toDisplayItems.getItems().removeAll(selectedItems);
    }
    
    try {
        ListOfItem.addToBase(list);
    } catch (IOException e) {
        // todo Auto-generated catch block
        e.printStackTrace();
    }
}


    @FXML
    void displayAllTasks(ActionEvent event) {
        
        //display all task
        toDisplayItems.getItems().clear();
        toDisplayItems.getItems().addAll(keepItems);
        
    }

    @FXML
    void importantList(ActionEvent event) {

        //display important list
        toDisplayItems.getItems().clear();
        toDisplayItems.getItems().addAll(importantItem);

    }

    @FXML
    void todaysList(ActionEvent event) {
    
        //GET TODAYS LIST AND DISPLAY IT IN LISTVIEW
        toDisplayItems.getItems().clear();
        toDisplayItems.getItems().addAll(TodaysItems);    
    }
 
    @FXML
    void tommorrowList(ActionEvent event) {
        
        //display tommorrow items
        toDisplayItems.getItems().clear();
        toDisplayItems.getItems().addAll(tommorrowTask);
        
    }

    //listview method to display list
    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        TodaysDate.setText(currentdate);
        try {
            ListOfItem.addToBase(list);
        } catch (IOException e) {
            //todo Auto-generated catch block
            System.out.println("i ams here");
            e.printStackTrace();
        }
        toDisplayItems.getItems().addAll(keepItems);             
    }

    //Adding task scene part
    @FXML
    void addItem(ActionEvent event) throws IOException {
        // Item = userTask.getText();
        // addtoKeepItems(Item);
        // seperateDates();
        try{
            Item = userTask.getText();
            seperateDates();
            if(levelOfImportant.getSelectedToggle() == null)
                throw new IllegalArgumentException("Add level of important");
             addtoKeepItems(Item);

            root = FXMLLoader.load(getClass().getResource("AppUI.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            endMassage.setText("Invalid! Recheck All fields");
            keepItems.remove(Item);
        }
        
        
    

    }

    //back to Appui scene
    @FXML
    void backButtion(ActionEvent event) throws IOException{

        root = FXMLLoader.load(getClass().getResource("AppUI.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void getDueDate(ActionEvent event) {
        //get date and store it in local variable
        try{
            userDate = userDueDate.getValue();
        }catch(Exception e){
            endMassage.setText("Invalid Input Try Again");
        }
    }

    @FXML
    void getLevelOfImportant(ActionEvent event) throws Exception {
        
       if(levelOfImportant.getSelectedToggle()==userLevelHigh){
            importantLevel = 3;
        }else if(levelOfImportant.getSelectedToggle()==userLevelLow){
            importantLevel = 1;
        }else if(levelOfImportant.getSelectedToggle()== userLevelMiddle){
           importantLevel = 2;
        }else{
            throw new Exception("invalid level of important");
        }

    }

    @FXML
    void getTask(ActionEvent event) {
        //get task
        Item = userTask.getText();
        
    }
  
    public static void additemstoArrayList(String Item, LocalDate userDate, int importantLevel){
        ToDoItem createItem = new ToDoItem(Item,userDate,importantLevel);
        list.add(createItem);

        
        System.out.println(list);
    }

    public void addtoKeepItems(String item) {
        //adding task to temp arraylist
         keepItems.add(item);
        
         //adding to high importants
        if(AppUIController.importantLevel == 3){
            importantItem.add(Item);
            
        }


         additemstoArrayList(item, AppUIController.userDate, AppUIController.importantLevel);   
    }

    public void seperateDates() {

        //seperate items by date  
        if(AppUIController.userDate.isEqual(LocalDate.now())){
            TodaysItems.add(Item);
            
           
        }else if((LocalDate.now().plusDays(1)).isEqual(userDate)){
            tommorrowTask.add(Item);
            
            
        }else{
            //do nothing 
        }
        
    }

    //load task from files file (simple file database)
    public static void Loadtask() throws IOException{
        
        Scanner input = new Scanner(System.in);
        File ToDoItems_file = new File("ToDoItems_file.txt");
        String line = " ";
        StringTokenizer token;
        input.close();

        LocalDate currentDate = LocalDate.now();
        LocalDate nexDate = LocalDate.now().plusDays(1);
        String nextDateString = String.valueOf(nexDate);
        String currdateString = String.valueOf(currentDate);

        input = new Scanner(ToDoItems_file);
        while(input.hasNextLine()){
            line = input.nextLine();
            if(line.equals("  ")){
                //do nothing
            }else{
                token = new StringTokenizer(line, ",");
                int integer_level;
                String task = token.nextToken();
                String taskDate = token.nextToken();
                String level = token.nextToken();
                LocalDate tasksDate = LocalDate.parse(taskDate);

                if(level.equals("High")){
                    importantItem.add(task);
                    integer_level = 3;
                }else if(level.equals("low")){
                    integer_level = 1;
                }else{
                    integer_level = 2;
                }

                //load tasks to ArrayList
                ToDoItem creatItem = new ToDoItem(task,tasksDate,integer_level); 
                list.add(creatItem);

                if(taskDate.equals(currdateString)){
                    keepItems.add(task);
                    TodaysItems.add(task);
                }else if(taskDate.equals(nextDateString)) {
                    keepItems.add(task);
                    tommorrowTask.add(task);
                                    
                }else{
        
                    LocalDate date = LocalDate.parse(taskDate);
                    if(date.isAfter(currentDate)){
                        keepItems.add(task);
                    }else{
                        //add things to outdate 
                        System.out.println("im in outdated file");
                    } 
                }
            }
        }
        input.close();
    }
}