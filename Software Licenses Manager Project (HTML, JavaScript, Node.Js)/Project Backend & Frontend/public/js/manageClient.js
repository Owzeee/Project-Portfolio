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

function populateSubscriptionTypesDropdown(clientId) {
    fetch('/subscription-types')
        .then(response => response.json())
        .then(types => {
            const select = document.getElementById(`subscriptionType_${clientId}`);
            select.innerHTML = ''; // Clear any existing options
            types.forEach(type => {
                const option = document.createElement('option');
                option.value = type;
                option.textContent = type.charAt(0).toUpperCase() + type.slice(1); // Capitalize the first letter
                select.appendChild(option);
            });
        })
        .catch(error => console.error('Error:', error));
}

// Function to fetch and display clients
function fetchAndDisplayClients() {
    fetch('/clients')
        .then(response => response.json())
        .then(clients => {
            
        })
        .catch(error => console.error('Error:', error));
}
document.addEventListener('DOMContentLoaded', fetchAndDisplayClients);
document.addEventListener('DOMContentLoaded', fetchUserInfo);

function fetchSerialNumbers() {
    fetch('/api/my-serial-numbers') // Updated endpoint
    .then(response => {
        if (!response.ok) {
            throw new Error('Failed to fetch serial numbers');
        }
        return response.json();
    })
    .then(serialNumbers => {
        const tableBody = document.getElementById('clientTable').querySelector('tbody');
        tableBody.innerHTML = ''; // Clear the table body

        // Iterate over each serial number and add a row to the table
        serialNumbers.forEach(serial => {
            const clientIdDisplay = serial.client_id ? serial.client_id : 'Not assigned/Expired';
            const row = `<tr>
                            <td>${serial.serial_number}</td>
                            <td>${serial.product_name}</td>
                            <td>${new Date(serial.creation_date).toLocaleDateString()}</td>
                            <td>${new Date(serial.expiration_date).toLocaleDateString()}</td>
                            <td>${clientIdDisplay}</td>
                         </tr>`;
            tableBody.innerHTML += row;
        });
    })
    .catch(error => {
        console.error('Error fetching serial numbers:', error);
    });
}
document.addEventListener('DOMContentLoaded', fetchSerialNumbers);


function populateClientsDropdown() {
    fetch('/clients')
        .then(response => response.json())
        .then(clients => {
            const clientSelect = document.getElementById('clientSelect');
            clientSelect.innerHTML = '<option value="">Select a Client</option>'; // default option
            clients.forEach(client => {
                const option = document.createElement('option');
                option.value = client.id;
                option.textContent = `${client.username} (ID: ${client.id})`;
                clientSelect.appendChild(option);
            });
        })
        .catch(error => console.error('Error:', error));
}

function populateSerialNumbersDropdown() {
    fetch('/list-serial-numbers')
        .then(response => response.json())
        .then(serialNumbers => {
            const serialSelect = document.getElementById('serialSelect');
            serialSelect.innerHTML = '<option value="">Select a Serial Number</option>'; // default option
            serialNumbers.forEach(serial => {
                if (serial.status === 'active' && !serial.client_id) { // Ensure the serial is active and not associated
                    const option = document.createElement('option');
                    option.value = serial.id;
                    option.textContent = `${serial.serial_number} (ID: ${serial.id})`;
                    serialSelect.appendChild(option);
                }
            });
        })
        .catch(error => console.error('Error:', error));
}

function associateSerialToClient() {
    const clientId = document.getElementById('clientSelect').value;
    const serialNumberId = document.getElementById('serialSelect').value;

    if (!clientId || !serialNumberId) {
        alert('Please select both a client and a serial number.');
        return;
    }

    fetch('/associate-serial', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ clientId, serialNumberId })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Problem associating serial number to client');
        }
        return response.text();
    })
    .then(() => {
        alert('Serial number associated with client successfully');
        logActivityForFunction('Associated SN ' + serialNumberId + ' to client ' + clientId); // add to recent activity  
        populateClientsDropdown(); // Re-populate clients dropdown to update the data
        populateSerialNumbersDropdown(); // Re-populate serial numbers dropdown to update the data
    })
    .catch(error => {
        console.error('Error:', error);
        alert('An error occurred while associating the serial number to the client.');
    });
}

// Call these functions on page load to populate the dropdowns and the table
document.addEventListener('DOMContentLoaded', () => {
    populateClientsDropdown();
    populateSerialNumbersDropdown();
}); 

function deleteClient(clientId) {
    if (!confirm('Are you sure you want to delete this client? This action cannot be undone.')) {
        return;
    }

    fetch(`/clients/${clientId}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Problem deleting client');
        }
        return response.text();
    })
    .then(() => {
        alert('Client deleted successfully');
        logActivityForFunction('Deleted Client ' + clientId); // add to recent activity
    })
    .catch(error => {
        console.error('Error:', error);
        alert('An error occurred while deleting the client.');
    });
}


function applyChanges(clientId) {
    const subscriptionType = document.getElementById(`subscriptionType_${clientId}`).value;
    const subscriptionEnd = document.getElementById(`subscriptionEnd_${clientId}`).value;

    fetch(`/clients/${clientId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ subscriptionType, subscriptionEnd })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Problem updating client information');
        }
        return response.text();
    })
    .then(() => {
        alert('Client information updated successfully');
        fetchClients(); // Refresh the client table
    })
    .catch(error => {
        console.error('Error:', error);
        alert('An error occurred while updating the client information.');
    });
}

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

    } catch (error) {
        console.error('Error:', error.message);
        // Display error message on the UI if needed
    }
}