function showStudents() {

    fetch("http://localhost:8081/students")
        .then(response => response.json())
        .then(students => {

            let html = `
                <h2>Students</h2>

                <table>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Branch</th>
                        <th>Year</th>
                    </tr>
            `;

            students.forEach(student => {

                html += `
                    <tr>
                        <td>${student.studentId}</td>
                        <td>${student.name}</td>
                        <td>${student.email}</td>
                        <td>${student.phone}</td>
                        <td>${student.branch}</td>
                        <td>${student.year}</td>
                    </tr>
                `;
            });

            html += `</table>`;

            document.getElementById("content").innerHTML = html;
        })

        .catch(error => {

            document.getElementById("content").innerHTML = `
                <h2>Error</h2>
                <p>Unable to load student data.</p>
            `;

            console.log(error);
        });
}


function showCompanies() {

    fetch("http://localhost:8081/companies")
        .then(response => response.json())
        .then(companies => {

            let html = `
                <h2>Companies</h2>

                <table>
                    <tr>
                        <th>ID</th>
                        <th>Company</th>
                        <th>Job Role</th>
                        <th>Location</th>
                        <th>Package (LPA)</th>
                    </tr>
            `;

            companies.forEach(company => {

                html += `
                    <tr>
                        <td>${company.companyId}</td>
                        <td>${company.companyName}</td>
                        <td>${company.jobRole}</td>
                        <td>${company.location}</td>
                        <td>${company.packageLpa}</td>
                    </tr>
                `;
            });

            html += `</table>`;

            document.getElementById("content").innerHTML = html;
        })

        .catch(error => {

            document.getElementById("content").innerHTML = `
                <h2>Error</h2>
                <p>Unable to load company data.</p>
            `;

            console.log(error);
        });
}


function showPlacements() {

    fetch("http://localhost:8081/placements")
        .then(response => response.json())
        .then(placements => {

            let html = `
                <h2>Placements</h2>

                <table>
                    <tr>
                        <th>Placement ID</th>
                        <th>Student</th>
                        <th>Company</th>
                        <th>Job Role</th>
                        <th>Status</th>
                    </tr>
            `;

            placements.forEach(placement => {

                html += `
                    <tr>
                        <td>${placement.placementId}</td>
                        <td>${placement.studentName}</td>
                        <td>${placement.companyName}</td>
                        <td>${placement.jobRole}</td>
                        <td>${placement.status}</td>
                    </tr>
                `;
            });

            html += `</table>`;

            document.getElementById("content").innerHTML = html;
        })

        .catch(error => {

            document.getElementById("content").innerHTML = `
                <h2>Error</h2>
                <p>Unable to load placement data.</p>
            `;

            console.log(error);
        });
}


function loadDashboard() {

    fetch("http://localhost:8081/dashboard")
        .then(response => response.json())
        .then(data => {

            document.getElementById("studentCount").textContent =
                data.students;

            document.getElementById("companyCount").textContent =
                data.companies;

            document.getElementById("applicationCount").textContent =
                data.applications;

            document.getElementById("selectedCount").textContent =
                data.selected;
        })

        .catch(error => {

            console.log("Dashboard error:", error);
        });
}


loadDashboard();
function loadDashboard() {

    fetch("http://localhost:8081/dashboard")
        .then(response => response.json())
        .then(data => {

            document.getElementById("studentCount").textContent =
                data.students;

            document.getElementById("companyCount").textContent =
                data.companies;

            document.getElementById("applicationCount").textContent =
                data.applications;

            document.getElementById("selectedCount").textContent =
                data.selected;
        })

        .catch(error => {

            console.log("Dashboard error:", error);
        });
}

loadDashboard();