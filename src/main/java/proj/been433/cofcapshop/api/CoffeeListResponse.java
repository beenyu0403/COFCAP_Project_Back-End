package proj.been433.cofcapshop.api;


import proj.been433.cofcapshop.item.Description;



public class CoffeeListResponse {
    private String name;
    private String imageName;
    private int price;
    private Description description;

    public CoffeeListResponse(String name, String imageName, int price, Description description) {
        this.name = name;
        this.imageName = imageName;
        this.price = price;
        this.description = description;
    }
}
