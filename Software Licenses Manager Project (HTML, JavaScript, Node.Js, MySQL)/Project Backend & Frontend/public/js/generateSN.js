function fetchUserInfo() {
    fetch('/api/user-info')
    .then(response => response.json())
    .then(data => {
        console.log(data); // Log the received data
        if(data.username) {
            document.querySelector('.welcome-message').innerText = `Welcome, ${data.username}!`;
        } else {
            console.log('Username not found in the response');
        }
    })
    .catch(error => console.error('Error:', error));
}
function fetchSerialNumbers() {
    fetch('/list-serial-numbers')
    .then(response => response.json())
    .then(data => {
        console.log(data); // Log the data
        const tableBody = document.getElementById('serialTable').querySelector('tbody');
        tableBody.innerHTML = ''; // Clear the table body

        // Iterate over each serial number
        data.forEach(serial => {
            const clientIdDisplay = serial.client_id ? serial.client_id : 'NULL';
            const dissociateButton = serial.client_id ? `<button class = "Dissociate-btn "onclick="dissociateSerial(${serial.id})">Dissociate Client</button>` : '';
            const row = `<tr>
                            <td>${serial.id}</td>
                            <td>${serial.serial_number}</td>
                            <td><input type="text" id="productName_${serial.id}" value="${serial.product_name}"></td>
                            <td>${new Date(serial.creation_date).toLocaleDateString()}</td>
                            <td><input type="date" id="expirationDate_${serial.id}" value="${serial.expiration_date.split('T')[0]}"></td>
                            <td>${clientIdDisplay}</td>
                    <td>
                        <button class = "Update-btn" onclick="updateSerialDetails(${serial.id})">Update</button>
                        <button class = "Toogle-btn" onclick="updateStatus(${serial.id}, '${serial.status}')">Toggle Status</button>
                        <button class = "delete-btn" onclick="deleteSerial(${serial.id})">Delete</button>
                        ${dissociateButton}
                    </td>
                 </tr>`;
    tableBody.innerHTML += row;
    });
    })
    .catch(error => console.error('Error:', error));
}


function dissociateSerial(id) {
    fetch('/dissociate-serial', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ serialNumberId: id })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Problem dissociating serial number from client');
        }
        return response.text();
    })
    .then(() => {
        alert('Serial number dissociated from client successfully');
        logActivityForFunction('Dissociated SN: ' + id);
        fetchSerialNumbers(); // Refresh the list
    })
    .catch(error => {
        console.error('Error:', error);
        alert('An error occurred while dissociating the serial number from the client.');
    });
}

function generateSerialNumber() {
    const productName = document.getElementById('productName').value;
    const expirationDate = document.getElementById('expirationDate').value;
    
    if (!productName) {
        alert('Please enter a product name.');
        return;
    }
    
    if (!expirationDate) {
        alert('Please enter an expiration date.');
        return;
    }
    
    fetch('/generate-serial-number', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ productName, expirationDate })
    })
    .then(response => response.json())
    .then(data => {
        if (data.serialNumber) {
            alert('Serial Number Generated: ' + data.serialNumber);
            fetchSerialNumbers(); // Refresh the list
            logActivityForFunction('Generated SN'); // add to recent activity   
        } else {
            alert('Failed to generate serial number. Please try again.');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('An error occurred while generating the serial number.');
    });
}

function updateSerialDetails(id) {
    const productNameInput = document.querySelector(`#productName_${id}`);
    const expirationDateInput = document.querySelector(`#expirationDate_${id}`);

    const updatedProductName = productNameInput.value;
    const updatedExpirationDate = expirationDateInput.value;

    fetch('/update-serial-details', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ id, updatedProductName, updatedExpirationDate })
    })
    .then(response => response.json())
    .then(data => {
        alert('Serial number updated successfully.');
        logActivityForFunction('Updated SN ' + id + ' details'); // add to recent activity 
        fetchSerialNumbers(); // Refresh the list
    })
    .catch(error => console.error('Error:', error));
}


function updateStatus(id, currentStatus) {
    const newStatus = currentStatus === 'active' ? 'blocked' : 'active';

    fetch('/update-serial-number', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ id, status: newStatus })
    })
    .then(response => response.text())
    .then(data => {
        alert(data);
        logActivityForFunction('Updated SN ' + id + ' Status'); // add to recent activity 
        fetchSerialNumbers(); // Refresh the list
    })
    .catch(error => console.error('Error:', error));
}


function deleteSerial(id) {
    fetch('/delete-serial-number', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ id })
    })
    .then(response => response.text())
    .then(data => {
        alert(data);
        fetchSerialNumbers(); // Refresh the list
        logActivityForFunction('Deleted SN' + id); // add to recent activity   
    })
    .catch(error => console.error('Error:', error));
}

document.addEventListener('DOMContentLoaded', fetchSerialNumbers);
document.addEventListener('DOMContentLoaded', fetchUserInfo);

//recent activity
async function logActivityForFunction(action) {
    try {
        // Fetch user information
        const userResponse = await fetch('/api/user');
        if (!userResponse.ok) {
            throw new Error('Error fetching user information');
        }
        const userData = await userResponse.json();

        // Ensure userData contains necessary user information
        if (!userData || !userData.user || !userData.user.username) {
            throw new Error('User data is not available or incomplete');
        }

        // Assign admin with the username
        const admin = userData.user.username;
        
        // Define activityData within the function scope
        const activityData = { category: "developer", admin, action};

        const response = await fetch('/api/add-activity', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(activityData),
        });

        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(`Error adding new developer activity: ${errorText}`);
        }

        // Update the list of developer activities after adding a new one
        fetchDeveloperActivitiesAndUpdateUI();
    } catch (error) {
        console.error('Error:', error.message);
        // Display error message on the UI if needed
    }
}