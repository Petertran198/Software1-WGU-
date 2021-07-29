package InventoryManagementSystem.model;

/** This represents a model of a parts that is made InHouse it inherit from Part model
 * <br/>
 * <strong>FUTURE ENHANCEMENT:</strong>
 * <br/> Add more data for InHouse parts like which employee or department created the part
 */
public class InHouse extends Part{
    private int machineId;

    /**
     * InHouse constructor takes in all the attributes of part but include an extra attribute of machineId <br/>
     * <strong>RUNTIME ERROR:</strong>: I did not know how to connected part with InHouse part. If I wanted to make an InHouse part I had to first populate all the attributes of Part first. After a little googling I realized I had to use 'super()' first to recieve the data from part.s
     * @param id InHouse part id
     * @param name InHouse part name
     * @param price InHouse part price
     * @param stock InHouse part stock
     * @param min InHouse part min
     * @param max InHouse part max
     * @param machineId InHouse part machineId
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.setMachineId(machineId);
    }

    /**
     * Get the machineId from InHouse part
     * @return int Inhouse part machineId
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * set the machineId of InHouse part
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
