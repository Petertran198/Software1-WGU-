package InventoryManagementSystem.model;
/** This represents a model of a parts that is made InHouse */
public class InHouse extends Part{
    private int machineId;

    public InHouse(int id, String name, double price, int stock, int min, int max) {
        super(id, name, price, stock, min, max);
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
