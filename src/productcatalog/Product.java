package productcatalog;

/**
 *
 * @author NamelessKing
 */
public class Product {

    private int id;
    private String name;
    private float price;
    private String addData;
    private byte[] image;
    private String description;

    public Product(int id, String name, float price, String addData, byte[] image, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.addData = addData;
        this.image = image;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getAddData() {
        return addData;
    }

    public void setAddData(String addData) {
        this.addData = addData;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    

}
