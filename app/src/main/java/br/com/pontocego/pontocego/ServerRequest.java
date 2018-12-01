package br.com.pontocego.pontocego;

public class ServerRequest {
    private String desiredLine;
    private Double longitude;
    private Double latitude;
    
    public String getDesiredLine() {
        return desiredLine;
    }

    public void setDesiredLine(String desiredLine) {
        this.desiredLine = desiredLine;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
}
