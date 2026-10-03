package model;

public class Company {

    private int companyId;
    private String companyName;
    private String jobRole;
    private String location;
    private double packageLpa;

    public Company(int companyId, String companyName,
                   String jobRole, String location, double packageLpa) {

        this.companyId = companyId;
        this.companyName = companyName;
        this.jobRole = jobRole;
        this.location = location;
        this.packageLpa = packageLpa;
    }

    public int getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getJobRole() {
        return jobRole;
    }

    public String getLocation() {
        return location;
    }

    public double getPackageLpa() {
        return packageLpa;
    }
}