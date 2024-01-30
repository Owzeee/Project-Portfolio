document.addEventListener('DOMContentLoaded', function() {
    fetchUserIdAndLicenses();
});

function fetchUserIdAndLicenses() {
    fetch('/api/userid')
        .then(response => {
            if (!response.ok) {
                throw new Error('Not logged in');
            }
            return response.json();
        })
        .then(data => {
            const userId = data.userId;
            fetchLicenses(userId);
        })
        .catch(error => {
            console.error('Error:', error);
            // Handle the error (e.g., show login link or message)
        });
}

function fetchLicenses(clientId) {
    fetch(`/api/licenses/${clientId}`)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector('#dashboard table tbody');
            tableBody.innerHTML = ''; // Clear existing content

            data.forEach(license => {
                const row = `<tr>
                                <td>${license.product_name}</td>
                                <td>${license.serial_number}</td>
                                <td>${new Date(license.creation_date).toLocaleDateString()}</td>
                                <td><input type="date" id="expiryDate_${license.id}" value="${license.expiration_date.split('T')[0]}"></td>
                                <td>${license.status}</td>
                                <td>
                                    <button onclick="updateLicense(${license.id})">Update</button>
                                    <button onclick="dissociateSerial(${license.id})">Dissociate</button>
                                </td>
                             </tr>`;
                tableBody.innerHTML += row;
            });
        })
        .catch(error => {
            console.error('Error fetching licenses:', error);
        });
}
//GENERATE LICENSE/Serial Number

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
            const dissociateButton = serial.client_id ? `<button onclick="dissociateSerial(${serial.id})">Dissociate Client</button>` : '';
            const row = `<tr>
                            <td>${serial.id}</td>
                            <td>${serial.serial_number}</td>
                            <td><input type="text" id="productName_${serial.id}" value="${serial.product_name}"></td>
                            <td>${new Date(serial.creation_date).toLocaleDateString()}</td>
                            <td><input type="date" id="expirationDate_${serial.id}" value="${serial.expiration_date.split('T')[0]}"></td>
                            <td>${clientIdDisplay}</td>
                    <td>
                        <button onclick="updateSerialDetails(${serial.id})">Update</button>
                        <button onclick="updateStatus(${serial.id}, '${serial.status}')">Toggle Status</button>
                        <button onclick="deleteSerial(${serial.id})">Delete</button>
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
        logActivityForFunction('Dissociated SN ' + id); // add to recent activity
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
            logActivityForFunction('Generated SN'); // add to recent activity  
            fetchSerialNumbers(); // Refresh the list
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
        logActivityForFunction('Updated SN ' + ' Details');
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
        logActivityForFunction('Updated SN ' + id + " to " + currentStatus);
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
        logActivityForFunction('Deleted SN' + id); // add to recent activity  
        fetchSerialNumbers(); // Refresh the list
    })
    .catch(error => console.error('Error:', error));
}

document.addEventListener('DOMContentLoaded', fetchSerialNumbers);

function dissociateSerial(serialNumberId) {
    fetch('/api/dissociate-serial-number', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ serialNumberId: serialNumberId }) // Ensure this matches the expected format
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Problem dissociating license');
        }
        return response.text();
    })
    .then(() => {
        alert('Serial number dissociated successfully');
        logActivityForFunction('Dissociated SN ' + id);
        // Refresh or update your data accordingly
    })
    .catch(error => {
        console.error('Error:', error);
        alert('An error occurred while dissociating the license.');
    });
}

function updateLicense(id) {
    const expirationDateInput = document.querySelector(`#expiryDate_${id}`);
    const updatedExpirationDate = expirationDateInput.value;

    fetch('/api/update-serial-number', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ id, updatedExpirationDate })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Problem updating license');
        }
        return response.text();
    })
    .then(() => {
        logActivityForFunction('Updated SN ' + id);
        alert('License updated successfully');
        fetchLicenses(clientId); // Refresh the list
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

//recent activity
async function logActivityForFunction(action) {
    try {
        const userResponse = await fetch('/api/user');
        if (!userResponse.ok) {
            throw new Error('Error fetching user information');
        }
        const userData = await userResponse.json();

        if (!userData || !userData.user || !userData.user.username) {
            throw new Error('User data is not available or incomplete');
        }

        const admin = userData.user.username;
        
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

        fetchDeveloperActivitiesAndUpdateUI();
    } catch (error) {
        console.error('Error:', error.message);
    }
}

