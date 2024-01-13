function fetchSerialNumbers() {
    fetch('/list-serial-numbers')
    .then(response => response.json())
    .then(serialNumbers => {
        const tableBody = document.getElementById('serialTable').querySelector('tbody');
        tableBody.innerHTML = ''; // Clear the table body

        // Filter the serial numbers to only include those with an 'active' status
        const activeSerialNumbers = serialNumbers.filter(serial => serial.status === 'active');
        // Iterate over each active serial number and add a row to the table
        activeSerialNumbers.forEach(serial => {
            const clientIdDisplay = serial.client_id ? serial.client_id : 'Not assigned/Expired';
            const row = `<tr>
                            <td>${serial.serial_number}</td>
                            <td>${serial.product_name}</td>
                            <td>${new Date(serial.creation_date).toLocaleDateString()}</td>
                            <td>${new Date(serial.expiration_date).toLocaleDateString()}</td>
                            <td>${clientIdDisplay}</td> <!-- Display Client ID -->
                         </tr>`;
            tableBody.innerHTML += row;
        });
    })
    .catch(error => {
        console.error('Error fetching serial numbers:', error);
    });
}
function fetchAndDisplayClients() {
    fetch('/clients')
        .then(response => response.json())
        .then(clients => {
            const tableBody = document.getElementById('clientListTable').querySelector('tbody');
            tableBody.innerHTML = ''; // Clear existing rows
            clients.forEach(client => {
                const row = `<tr>
                                <td>${client.id}</td>
                                <td>${client.username}</td>
                                <td>${client.email}</td>
                                <td>${client.phoneNumber}</td>
                                <td>${client.fullName}</td>
                                <td>
                                    <!-- Add any action buttons if required -->
                                </td>
                             </tr>`;
                tableBody.innerHTML += row;
            });
        })
        .catch(error => console.error('Error:', error));
}
document.addEventListener('DOMContentLoaded', fetchSerialNumbers);
document.addEventListener('DOMContentLoaded', fetchAndDisplayClients);


document.addEventListener('DOMContentLoaded', () => {
    // Fetch user data and update welcome message
    fetch('/api/user')
    .then(response => {
        if (!response.ok) throw new Error('Not logged in');
        return response.json();
    })
    .then(data => {
        document.querySelector('.welcome-message').textContent = `Welcome, ${data.user.username}!`;
    })
    .catch(error => console.error('Error:', error));

    function fetchSerialNumbers() {
        fetch('/api/my-serial-numbers') // Updated endpoint
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch serial numbers');
            }
            return response.json();
        })
        .then(serialNumbers => {
            const tableBody = document.getElementById('serialTable').querySelector('tbody');
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

function fetchAndDisplayClients() {
    fetch('/clients')
        .then(response => response.json())
        .then(clients => {
            const tableBody = document.getElementById('clientListTable').querySelector('tbody');
            tableBody.innerHTML = ''; // Clear existing rows
            clients.forEach(client => {
                const row = `<tr>
                                <td>${client.id}</td>
                                <td>${client.username}</td>
                                <td>${client.email}</td>
                                <td>${client.subscription_type}</td>
                                <td>${new Date(client.subscription_start).toLocaleDateString()}</td>
                                <td>${new Date(client.subscription_end).toLocaleDateString()}</td>
                                <td>
                                    <!-- Add any action buttons if required -->
                                </td>
                             </tr>`;
                tableBody.innerHTML += row;
            });
        })
        .catch(error => console.error('Error:', error));
}

// Call the function on page load
document.addEventListener('DOMContentLoaded', fetchAndDisplayClients);
}
,document.addEventListener('DOMContentLoaded', function () {

    // Fetch and display recent developer activities
    fetch('/api/recent-activities/developer')
    .then(response => response.json())
    .then(data => {
        const developerActivityList = document.getElementById('developer-activity-list');

        data.forEach(activity => {
            const tableRow = document.createElement('tr');
            tableRow.innerHTML = `
            <td>${new Date(activity.date).toLocaleString(undefined, {
                year: 'numeric',
                month: '2-digit',
                day: '2-digit',
                hour: '2-digit',
                minute: '2-digit',
                hour12: false, // Use 24-hour format
            })}</td>
            <td>${activity.admin}</td>
            <td>${activity.action}</td>
        `;
            developerActivityList.appendChild(tableRow);
        });
    })
    .catch(error => console.error('Error fetching developer activities:', error));

}));

fetch('/api/user')
.then(response => {
    if (!response.ok) throw new Error('Not logged in');
    return response.json();
})
.then(data => {
    document.querySelector('.main-title p').textContent = `WELCOME ${data.user.username.toUpperCase()}!`;
    document.getElementById('user-email').textContent = `Email: ${data.user.email}`;
    document.getElementById('user-username').textContent = `Username: ${data.user.username}`;
    document.getElementById('user-fullname').textContent = `Full Name: ${data.user.fullName}`;
})
.catch(error => console.error('Error:', error));

fetch('/api/user')
        .then(response => {
            if (!response.ok) throw new Error('Not logged in');
            return response.json();
        })
        .then(data => {
            document.querySelector('.main-title p').textContent = `WELCOME ${data.user.username.toUpperCase()}!`;
        })
        .catch(error => console.error('Error:', error));

async function addNewDeveloperActivity() {
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

        // Get values from input elements
        const action = document.getElementById('developer-action').value.trim();

        // Input validation
        if (!action) {
            throw new Error('Please enter a value for the action field.');
        }

        const date = new Date().toLocaleString();

        // Define activityData within the function scope
        const activityData = { category: "developer", admin, action, date};

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

// Function to fetch developer activities and update the UI
async function fetchDeveloperActivitiesAndUpdateUI() {
    try {
        const response = await fetch('/api/recent-activities/developer');
        if (!response.ok) throw new Error('Error fetching developer activities');
        const developerActivities = await response.json();

        const developerActivityList = document.getElementById('developer-activity-list');
        developerActivityList.innerHTML = ''; // Clear existing developer activities

        developerActivities.forEach(activity => {
            const tableRow = document.createElement('tr');
            tableRow.innerHTML = `
                <td>${new Date(activity.date).toLocaleString(undefined, {
                    year: 'numeric',
                    month: '2-digit',
                    day: '2-digit',
                    hour: '2-digit',
                    minute: '2-digit',
                    hour12: false, // Use 24-hour format
                })}</td>
                <td>${activity.admin}</td>
                <td>${activity.action}</td>
            `;
            developerActivityList.appendChild(tableRow);
        });
    } catch (error) {
        console.error('Error:', error);
    }
}

// Fetch developer activities when the page loads
fetchDeveloperActivitiesAndUpdateUI();

// Periodically fetch developer activities 
setInterval(fetchDeveloperActivitiesAndUpdateUI, 5);


// ADD THIS FUNCTION TO ANY FUNCTION THAT YOU WANT TO RECORD AS AN ACTION
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