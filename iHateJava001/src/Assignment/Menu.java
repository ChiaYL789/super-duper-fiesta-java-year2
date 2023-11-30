package Assignment;
import java.io.File;
import java.util.ArrayList;

public class Menu {
    
    // Propeties
    private ArrayList<Food> foodMenu;
    
    // Constructor
    public Menu(){
        File f = new File("HI.txt");
        System.out.println(f.exists());
    }
    
    public void addMenu(Food food){
        foodMenu.add(food);
    }
    
    public void deleteMenu(Food food){
        foodMenu.remove(food);
    }
    
    public ArrayList viewMenu(){
        return foodMenu;
    }
}
