document.addEventListener('DOMContentLoaded', function () {

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

});

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

        // Define activityData within the function scope
        const activityData = { category: "developer", admin, action };

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

// Periodically fetch developer activities (every 5 seconds)
setInterval(fetchDeveloperActivitiesAndUpdateUI, 5000);


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
        const activityData = { category: "developer", admin, action };

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

// Usage example:
// Call logActivityForFunction with the name of the function where you want to log the activity
//logActivityForFunction('FunctionName');