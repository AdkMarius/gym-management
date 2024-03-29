package sn.esmt.gymManagement.payLoad;

public class ApiResponse {
	private boolean success;
    private String message;

    public ApiResponse(boolean success, String message) {
        this.setSuccess(success);
        this.setMessage(message);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
