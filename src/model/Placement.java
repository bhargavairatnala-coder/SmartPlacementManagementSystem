package model;

public class Placement {

    private int placementId;
    private int studentId;
    private int companyId;
    private String status;

    public Placement(int placementId, int studentId,
                     int companyId, String status) {

        this.placementId = placementId;
        this.studentId = studentId;
        this.companyId = companyId;
        this.status = status;
    }

    public int getPlacementId() {
        return placementId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public String getStatus() {
        return status;
    }
}