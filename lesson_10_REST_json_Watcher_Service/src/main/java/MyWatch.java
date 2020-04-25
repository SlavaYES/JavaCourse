import java.util.List;

class MyWatch {

    private String status;
    private List<String> files;

    public MyWatch() {
    }

    public MyWatch(String status, List<String> files) {
        this.status = status;
        this.files = files;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

}