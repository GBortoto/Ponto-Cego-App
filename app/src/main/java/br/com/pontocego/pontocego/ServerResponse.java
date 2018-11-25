package br.com.pontocego.pontocego;

public class ServerResponse {
    String busId;
    Long lastBusStop;

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public Long getLastBusStop() {
        return lastBusStop;
    }

    public void setLastBusStop(Long lastBusStop) {
        this.lastBusStop = lastBusStop;
    }
}
