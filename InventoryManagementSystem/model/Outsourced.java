package InventoryManagementSystem.model;

/** This represents a model of a parts that is OutSourced it inherit from Part model
 *<br/>
 *  <strong>FUTURE ENHANCEMENT:</strong>
 * <br/>
 * Add more attributes relating to what makes an Outsourced Part <br/> Example: manufacturer's number
 */
public class Outsourced extends Part{
    private String companyName;

    /**
     * Outsourced constructor takes in all the attributes of part but include an extra attribute of companyName
     * @param id Outsourced part id
     * @param name Outsourced part name
     * @param price Outsourced part price
     * @param stock Outsourced part stock
     * @param min Outsourced part min
     * @param max Outsourced part max
     * @param companyName Outsourced part company's name
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Get the company name  from Outsourced part
     * @return string companyName part machineId
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * set the companyName of OutSourced part
     * <strong>RUNTIME ERROR:</strong>: I did not know why I could not set the company's name. At first this method was companyName = companyName. After a little googling I found out you have to use the keyword this for Java to understand that you wanted to set THIS specific attribute.
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
