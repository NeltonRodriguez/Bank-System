public class City {
    String cityName;
    String regionName;

    // Let's create a constructor (alt + ins):

    public City(String cityName, String regionName) {
        this.cityName = cityName;
        this.regionName = regionName;
    }

    // Create toString method:

    @Override
    public String toString() {
        return cityName + " - " + regionName;
    }
}
