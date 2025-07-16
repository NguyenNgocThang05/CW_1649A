package onlineBookStore.Model;

public enum OrderStatus {
    PENDING("Pending.."),
    COMPLETE("Complete");

    private final String displayStatus;

    OrderStatus(String displayStatus) {
        this.displayStatus = displayStatus;
    }

    public String toString() {
        return displayStatus;
    }
}
