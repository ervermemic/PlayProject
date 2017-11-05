package models;

import com.avaje.ebean.Model;
import play.data.DynamicForm;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Enver on 11/6/2017.
 */
@Entity
@Table(name = "category")
public class Category extends Model {

    public static Model.Finder<Long, Category> findCategory = new Finder<>(Category.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "category_name", unique = true, length = 50)
    private String categoryName;


    //CONSTRUCTORS
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category() {
    }

    //GETTERS AND SETTERS
    public Long getId() {
        return id;
    }

    /**
     * Getter returns Category name
     * @return <String> name
     */
    public String getCategory() {
        return categoryName;
    }

    /**
     * Getter returns <Category> which find in database by Id.
     * @param id
     * @return <Category>
     */

    public static Category getCategoryById(Long id){
        return findCategory.byId(id);
    }

    /**
     * Getter returns <Category> which find in database by Category name.
     * @param </String> name
     * @return <Category>
     */
    public static Category getCategoryByName(String categoryName){
        return findCategory.where().eq("category", categoryName).findUnique();
    }

    /**
     * Getter returns List of all Categories
     * @return List<Category>
     */
    public static List<Category> getAllCategories(){
        return findCategory.all();
    }

    /**
     * Setter for set Category name.
     * @param </String> category
     */
    public void setCategory(String categoryName) {
        this.categoryName = categoryName;
    }


    @Override
    public String toString() {
        return "Category Id: " + id + ", Category name: " + categoryName;
    }

    /**
     * The method create new Category and returns true if it is success,
     * or returns false if it is unsuccessful.
     * @param </DynamicForm> df
     * @return boolean
     */
    public static boolean saveCategory(DynamicForm df){
        Category newCategory = new Category(df.get("category"));
        try {
            newCategory.save();
            return true;
        }catch (PersistenceException e){
            return false;
        }
    }
}
