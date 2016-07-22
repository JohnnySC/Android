package github.johnnysc.shopslist;

import android.location.Location;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hovhannes Asatryan on 22.07.16.
 */
public class ShopItem {
    @SerializedName("address")
    private String address;

    @SerializedName("town")
    private String town;

    @SerializedName("phone")
    private String phone;

    @SerializedName("longtitude")
    private String longitude;

    @SerializedName("latitude")
    private String latitude;

    public ShopItem(String address, String town, String phone) {
        this.address = address;
        this.town = town;
        this.phone = phone;
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

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public double getDistance(){
        if(!this.longitude.equals("") && !this.latitude.equals("")) {
            double longitude = Double.parseDouble(this.longitude);
            double latitude = Double.parseDouble(this.latitude);
            double distance = 0;
            Location locationA = new Location("Moscow");
            locationA.setLatitude(55.751244);
            locationA.setLongitude(37.618423);
            Location locationB = new Location("B");
            locationB.setLatitude(latitude);
            locationB.setLongitude(longitude);
            distance = locationA.distanceTo(locationB);
            return distance;
        }
        else
            return -1;
    }
}
