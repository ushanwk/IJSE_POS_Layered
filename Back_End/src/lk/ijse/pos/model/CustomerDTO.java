package lk.ijse.pos.model;

public class CustomerDTO {
    private String cusId;
    private String cusName;
    private String cusAddress;
    private int cusSalary;

    public CustomerDTO(String cusId, String cusName, String cusAddress, int cusSalary) {
        this.cusId = cusId;
        this.cusName = cusName;
        this.cusAddress = cusAddress;
        this.cusSalary = cusSalary;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public int getCusSalary() {
        return cusSalary;
    }

    public void setCusSalary(int cusSalary) {
        this.cusSalary = cusSalary;
    }
}
