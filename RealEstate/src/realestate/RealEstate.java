/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package realestate;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author birob.20d
 */
public class RealEstate {
    
    public class CsvReader {
    public static List<Ad> loadFromCsv(String filename) {
        List<Ad> ads = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine();
            
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(";");
                
                if (fields.length < 11) {
                    System.err.println("Hibás sor kihagyva: " + line);
                    continue;
                }

                int id = Integer.parseInt(fields[0]);
                int rooms = Integer.parseInt(fields[1]);

                // "latlong" mező külön feldolgozása
                String[] latlong = fields[2].split(",");
                double latitude = Double.parseDouble(latlong[0]);
                double longitude = Double.parseDouble(latlong[1]);

                int floors = Integer.parseInt(fields[3]);
                double area = Double.parseDouble(fields[4]);
                String description = fields[5];
                boolean freeOfCharge = fields[6].equals("1");

                String createAtString = fields[8];
                Date createAt = dateFormat.parse(createAtString);

                String seller = fields[10];

                ads.add(new Ad(id, description, rooms, area, floors, seller, freeOfCharge, latitude, longitude, createAt));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ads;
    }
}
    
    public class NearestFreeProperty {
    public static void main(String[] args) {
        List<Ad> ads = CsvReader.loadFromCsv("realestates.csv");

        double refLat = 47.4164220114023;
        double refLon = 19.066342425796986;
        Ad nearest = null;
        double minDistance = Double.MAX_VALUE;

        for (Ad ad : ads) {
            if (ad.isFreeOfCharge()) {
                double distance = ad.distanceTo(refLat, refLon);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearest = ad;
                }
            }
        }

        if (nearest != null) {
            System.out.println("Nearest Free Property to Mesevár Óvoda:");
            System.out.println("Description: " + nearest.getDescription());
            System.out.println("Seller: " + nearest.getSeller());
            System.out.printf("Area: %.2f m²\n", nearest.getArea());
            System.out.println("Rooms: " + nearest.getRooms());
        } else {
            System.out.println("No free property found.");
        }
    }
}


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Ad> ads = CsvReader.loadFromCsv("realestates.csv");

        double totalArea = 0;
        int count = 0;
        for (Ad ad : ads) {
            if (ad.getFloors() == 1) {
                totalArea += ad.getArea();
                count++;
            }
        }

        if (count > 0) {
            double avgArea = totalArea / count;
            System.out.printf("Ground Floor Properties Average Area: %.2f m²\n", avgArea);
        } else {
            System.out.println("No ground floor properties found.");
        }
    }
    
}
