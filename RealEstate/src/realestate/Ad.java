/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package realestate;

import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toRadians;
import java.util.Date;

public class Ad {
    private int id;
    private String description;
    private int rooms;
    private double area;
    private int floors;
    private String seller;
    private boolean freeOfCharge;
    private double latitude;
    private double longitude;
    private Date createAt;

    // Constructor
    public Ad(int id, String description, int rooms, double area, int floors, 
              String seller, boolean freeOfCharge, double latitude, double longitude, Date createAt) {
        this.id = id;
        this.description = description;
        this.rooms = rooms;
        this.area = area;
        this.floors = floors;
        this.seller = seller;
        this.freeOfCharge = freeOfCharge;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createAt = createAt;
    }

    // Getter and Setter methods
    public int getId() { return id; }
    public String getDescription() { return description; }
    public int getRooms() { return rooms; }
    public double getArea() { return area; }
    public int getFloors() { return floors; }
    public String getSeller() { return seller; }
    public boolean isFreeOfCharge() { return freeOfCharge; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public Date getCreateAt() { return createAt; }

    // Distance calculation method
    public double distanceTo(double lat, double lon) {
    final int R = 6371; // Föld sugara km-ben
    double latDistance = toRadians(lat - this.latitude);
    double lonDistance = toRadians(lon - this.longitude);
    double a = sin(latDistance / 2) * sin(latDistance / 2)
            + cos(toRadians(this.latitude)) * cos(toRadians(lat))
            * sin(lonDistance / 2) * sin(lonDistance / 2);
    double c = 2 * atan2(sqrt(a), sqrt(1 - a));
    return R * c;
}
}
