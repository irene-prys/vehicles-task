package demo.task.vehicles.utils;

public class GeoCalculator {
    // 'calculate distance' algorithm is taken from https://stackoverflow.com/questions/3694380/calculating-distance-between-two-points-using-latitude-longitude
    public static double calculateDistance(double lon1, double lat1, double lon2, double lat2) {
        double theta = lon1 - lon2;
        double distance = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.cos(Math.toRadians(theta));
        distance = Math.acos(distance);
        distance = Math.toDegrees(distance);
        distance = distance * 60 * 1.1515;

        distance = distance * 1.609344;// convert to km

        return distance;

    }
}
