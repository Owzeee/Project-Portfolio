document.addEventListener('DOMContentLoaded', function () {
    // Fetch recent software management activities from the server and populate the software-management-log div
    fetch('/api/recent-activities/developer')
        .then(response => response.json())
        .then(data => displaySoftwareManagementActivities(data))
        .catch(error => console.error('Error fetching recent software management activities:', error));

    function displaySoftwareManagementActivities(activities) {
        const softwareManagementLog = document.getElementById('software-management-log');
        softwareManagementLog.innerHTML = ''; // Clear existing entries

        activities.forEach(activity => {
            const activityEntry = document.createElement('div');
            activityEntry.classList.add('software-management-entry');

            activityEntry.innerHTML = `
                <p><strong>Date:</strong> ${activity.date}</p>
                <p><strong>Action:</strong> ${activity.action}</p>
                <p><strong>Details:</strong> ${activity.details}</p>
            `;

            softwareManagementLog.appendChild(activityEntry);
        });
    }
});

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