package todolist;

import java.time.*;

public class ToDoItem{
   private String item;
   private LocalDate dueDate; //should equal = LocalDate.of(2023, 10, 28); // Specific date (year, month, day)
   private int levelOfImportant; //high = 3, intermedate = 2, low = 1;
   String StringValueImportant; //to represent level of important in string format
   
   //deflaut contractor
   public ToDoItem(){
      this.item = " "; //setting item to do to defualt none value
      this.dueDate = LocalDate.now(); //setting deflaut submission date to current date 
      this.levelOfImportant = 2; //defualt value for levelOfImportant is intermedate which is 2
      this.StringValueImportant = getLevelOfImportant();
   }    
   
   //paramterized constructor
   public ToDoItem(String item, LocalDate customDate, int levelOfImportant){
      //call all setters and getters for all attributes
      setItem(item);
      setDueDate(customDate);
      setLevelOfImportant(levelOfImportant);
   }
   
   //setter for item
   public void setItem(String item){
      
      //item is not allowed to be null if null throw exception to catch on main method
      // item must contain at leats 1 letter to be saved and can not be a space
      if(item.isEmpty() || item.equals(" ")){
         throw new IllegalArgumentException("Item can not be empty");
      }else if(item.length() >= 1){
         this.item = item;
      }
   }
   
   //getter method for item
   public String getItem(){
      return item;
   }
   
   //setter method for local data 
   public void setDueDate(LocalDate dueDate){
      
      //date can not be set to older dates then current date throw exception date can not better older then current data 
         LocalDate currentDate = LocalDate.now();
       if (dueDate.isBefore(currentDate)) {
            throw new IllegalStateException("date can not be older then current date");
       }else {
            this.dueDate = dueDate;
       } 
   }
   
   //getter method for dueDate
   public LocalDate getDueDate (){
      return this.dueDate;
   }
   
   //setter method to set level of important
   public void setLevelOfImportant (int levelOfImportant) {
      
      //level of import default value is 2 its can not be less then 1 or greater then 3
      if(levelOfImportant <= 0 || levelOfImportant > 3){
         throw new IllegalArgumentException("level Of Important can not be less then 1 or greater then 3");
      }else{
         this.levelOfImportant = levelOfImportant;
      }
      
   }
   
   //getter method to return int value oflevelOfImportant
   public int getIntLevelValue(){
      return levelOfImportant;
   }
   
   //getter method for levelOfImportant must be returned in form of string
   public String getLevelOfImportant(){
      int levelOfImportant = getIntLevelValue();
      switch(levelOfImportant){
         case 1:
            this.StringValueImportant = "low";
            break;
         case 2:
            this.StringValueImportant = "Intermedate";
            break;
         case 3:
            this.StringValueImportant = "High";
            break;
      }
      return StringValueImportant;
   }
   
   //A to string method to return all atrtribute values
   public String toString(){
     // String important = getLevelOfImportant(levelOfImportant);
      return item + "," + dueDate +"," + getLevelOfImportant();
   }
} 