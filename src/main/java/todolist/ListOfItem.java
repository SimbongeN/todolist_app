package todolist;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class ListOfItem{
   private ArrayList<ToDoItem> list;
   
   //default constructor
   public ListOfItem(){
      list = new ArrayList<ToDoItem>();
   }

   public ListOfItem(ArrayList<ToDoItem> itemList){
      list = itemList;
   }
   
   //method to add items in our list of to do list
   public void addItem(ToDoItem item){
      list.add(item);
   }
   
   //getter method for ArrayList<>
   public ArrayList<ToDoItem> getList(){
      return list;
   }
   
   //method to remove item
   public void RemoveItem(ToDoItem item){ 
       list.remove(item);
   }
   
   @Override
   public String toString() {
       String showList="";
       for (ToDoItem toDoItem : list) {
         showList += toDoItem.getItem()+"\n"; 
       }
       return showList;
   }

   //storing these values in a txt file 
   public static void addToBase (ArrayList<ToDoItem> list) throws IOException{
        try (PrintWriter write = new PrintWriter("ToDoItems_file.txt")) {
            for(ToDoItem task: list){
               String taskString = task.toString();
               write.println(taskString);
            }
            write.close();
        } catch (Exception e) {
         // todo: handle exception
        }

    }
      
}