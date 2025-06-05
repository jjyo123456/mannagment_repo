package main.java.com.example.mannagment.mannagment.auth_file;

public class Erroe_response_object {
    public String status;
    public String message;
    public String timestamp;

    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    // Getter and Setter for message
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    // Getter and Setter for timestamp
    public String getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
