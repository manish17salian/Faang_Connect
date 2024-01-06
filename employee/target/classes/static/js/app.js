var email = localStorage.getItem('email');
let existingJobs = new Set();

// Call below function on index.html load
getAppliedJobs();
// setInterval(getAppliedJobs, 60000);

function getAppliedJobs() {
    callLoader()
    fetch(`http://localhost:8080/getAppliedJobs/${email}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            if (Array.isArray(data)){
                populateTable(data);
            }else{
                // callApplyJobHandler()
            }
            setTimeout(removeLoader,1000);
            document.getElementById("user").innerHTML=email
        })
        .catch(error => {
            callError();
            console.log('Error:', error)});
}

function populateTable(data) {
    const tableBody = document.getElementById('jobs');
    // Loop through the data and create table rows
    data.forEach(item => {
        if (!existingJobs.has(item.jobID)) {
            console.log(item, 'HTML')
            const newRow = document.createElement('tr'); // Create a new table row
            // Create table cells and populate data
            newRow.innerHTML = `
            <td>#${item.jobID}</td>
            <td>
                <span class="img-thumb">
                    ${getCompanyIcon(item.companyName)}
                    <span class="ml-2">${capitalizeFirstLetter(item.companyName)}</span>
                </span>
            </td>
            <td>${item.dateApplied}</td>
            <td>${capitalizeFirstLetter(item.title)}</td>
            <td>${capitalizeFirstLetter(item.location)}</td>
            <td>$${item.salary}</td>
            <td>
                <div class="dropdown">
                    <button class="dropdown-toggle mb-0 badge badge-primary" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Skills
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
                        ${item.skills.map((i) => `<button class="dropdown-item" type="button">${i}</button>`).join('')}
                    </div>
                </div>
            </td>
            <td onclick="showPopup('${item.description}', '${item.companyName}', '${item.title}' , '${item.salary}', '${item.location}')"><label class="mb-0 badge badge-primary view-detail" title="" data-original-title="Pending" >View</label></td>
            `;
            tableBody.appendChild(newRow);
            existingJobs.add(item.jobID);
        }
    });
}

function capitalizeFirstLetter(str) {
    if (str) {
        return str.charAt(0).toUpperCase() + str.slice(1);
    } else {
        return ''
    }

}

function getCompanyIcon(company) {
    switch (company) {
        case "google":
            return "<i class='fab fa-google' style='border-radius: 50%; font-size: 30px;'></i>";
        case "facebook":
            return "<i class='fab fa-facebook' style='border-radius: 50%; font-size: 30px;'></i>";
        case "amazon":
            return "<i class='fab fa-amazon' style='border-radius: 50%; font-size: 30px;'></i>";
        case "apple":
            return "<i class='fab fa-apple' style='border-radius: 50%; font-size: 30px;'></i>";
        case "netflix":
            return "<svg xmlns='http://www.w3.org/2000/svg' x='0px' y='0px' width='30' height='30' viewBox='0 0 50 50'>" +
            "<path d='M27,6v10.75L23.48,6H23h-8.33H14v38.15l9-1.28V32.58l3.24,10.34l6.14,0.72L36,44.15V6H27z M29,8h5v30.13l-5-15.27V8z M17.39,8h4.64L23,10.96l4,12.22l2,6.12l4.06,12.4l-0.54-0.06l-4.76-0.56L27,38.66L23,25.9l-2-6.38L17.39,8z M21,41.13l-5,0.72 V10.24l5,15.96V41.13z'></path>" +
            "</svg>";
    }
}

function callLoader (){
    let loaderBody = document.getElementById("loader_body");
    loaderBody.innerHTML = `
    <div class="loader">
            <div class="spinner">
              <img src="img/loader.gif" alt="">
            </div>
          </div>
    `
}

function removeLoader (){
    let loaderBody = document.getElementById("loader_body");
    loaderBody.innerHTML =''
}

function callError(){
    let errorBody = document.getElementById("error_body");
    errorBody.innerHTML = `
    <div class="loader">
    <img src="img/error.png" style="width: 400px;">
    </div>`
}

function showPopup(description, companyName, title, salary) {
    console.log(description)
    document.getElementById("popup").innerHTML = ''
    const body = document.createElement("div");
    body.innerHTML =
    `
    <div class="modal-header" style="padding: 0px;">
                    <h3 class="modal-title">${capitalizeFirstLetter(companyName)}</h3>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="closePopup()">
                      <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                  <h1 class="modal-title" >${capitalizeFirstLetter(title)}</h1>
                  <h3 style="text-align: left;">Job Description</h3>
                  <p style="text-align: left; font-size: medium;">
                  Remote is solving global remote organizations' biggest Challenge: employing anyone anywhere compliantly, We
                    make it possible for businesses big and small to employ a global team by handling global payroll. benefits.
                    taxes. and compliance Wearn more about how it works We•re backed by A• investors and our team is world-
                    Class, literally and figuratively. as we're all scattered around the world
                    ${description}
                    </p>
                <div style="display: flex; align-items: center; padding: 10px 0px;"><h3>Salary: </h3><p style="font-size: large;"> &nbsp;Euro ${salary} per year</p></div>
                <div style="display: flex; align-items: center; padding: 5px 0px;"><h3>Location: </h3><p style="font-size: large;"> &nbsp;Remote</p></div>
                <button type="button" class="btn btn-secondary squer-btn" data-dismiss="modal" onclick="closePopup()">Close</button>
    `
    document.getElementById("popup").appendChild(body)
    // Display the overlay and popup box
    document.getElementById('overlay').style.display = 'block';
    document.getElementById('popup').style.display = 'block';
}

function closePopup() {
    // Hide the overlay and popup box
    document.getElementById('overlay').style.display = 'none';
    document.getElementById('popup').style.display = 'none';
}


function callApplyJobHandler(){
    let body = document.getElementById("zeroJobHandler");
    let handler = document.createElement("div");
    body.innerHTML = 
    `
    <img src="img/applyjob.png" style="width: 400px;">
    <button type="button" class="btn btn-secondary squer-btn" onclick="openFindJobs()">Find Jobs</button>
    `
    // body.appendChild(handler)
}

function openFindJobs(){
    window.location.href = 'home.html';
}