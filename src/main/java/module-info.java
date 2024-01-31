module todolist {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    
    opens todolist to javafx.fxml;
    exports todolist;
}
