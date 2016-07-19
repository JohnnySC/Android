package github.johnnysc.shopslist;

/**
 * Created by johnny on 14.07.16.
 */
public class ShopItem {
    private String address;
    private String town;
    private String phone;
    private double distance;

    public ShopItem(String address, String town, String phone, double distance) {
        this.address = address;
        this.town = town;
        this.phone = phone;
        this.distance = distance;
    }

    public String getAddress() {
        return address;
    }

    public String getTown() {
        return town;
    }

    public String getPhone() {
        return phone;
    }

    public double getDistance() {
        return distance;
    }
}
