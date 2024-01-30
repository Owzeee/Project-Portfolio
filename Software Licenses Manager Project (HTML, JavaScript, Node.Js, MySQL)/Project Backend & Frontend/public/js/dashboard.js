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

function addLicense(month, price) {
    let spendingData = JSON.parse(localStorage.getItem('monthlySpending')) || {};
    spendingData[month] = (spendingData[month] || 0) + price;
    localStorage.setItem('monthlySpending', JSON.stringify(spendingData));

    logActivityForFunction('Added License'); // add to recent activity  
    updateMonthlySpendingDisplay();

    // Fetch the updated active licenses count from the server
    fetchActiveLicensesCount();
}

document.addEventListener('DOMContentLoaded', function() {
    fetchUserInfo();
    updateDashboard();
    setupMonthlySpendingChart();
    fetchUpcomingRenewals();
    fetchActiveLicensesCount(); // Call this to initialize the active licenses count on page load
});

function updateDashboard() {
    updateMonthlySpendingDisplay();
    updateActiveLicensesDisplay();
}

function updateMonthlySpendingDisplay() {
    let spendingData = JSON.parse(localStorage.getItem('monthlySpending')) || {};
    let currentDate = new Date();
    let currentMonth = currentDate.getMonth() + 1;
    let currentYear = currentDate.getFullYear();
    let monthYearKey = `${currentMonth}-${currentYear}`;

    let monthlySpending = spendingData[monthYearKey] || 0;
    document.querySelector('.widget .monthly-spending').innerText = `$${monthlySpending} spent this month on licenses.`;
}

function updateActiveLicensesDisplay() {
    let activeLicenses = parseInt(localStorage.getItem('activeLicenses')) || 0;
    document.querySelector('.widget .active-licenses').innerText = `${activeLicenses} active licenses.`;
}

document.addEventListener('DOMContentLoaded', function() {
    updateDashboard();
    setupMonthlySpendingChart();
    fetchUpcomingRenewals();
    fetchUserInfo();
});

function setupMonthlySpendingChart() {
    let ctx = document.getElementById('myChart').getContext('2d');
    let spendingData = getMonthlySpendingDataForChart();

    new Chart(ctx, {
        type: 'line',
        data: {
            labels: spendingData.labels,
            datasets: [{
                label: 'Monthly Spending',
                data: spendingData.data,
                backgroundColor: 'rgba(54, 162, 235, 0.5)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 2
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}

function getMonthlySpendingDataForChart() {
    let spendingData = JSON.parse(localStorage.getItem('monthlySpending')) || {};
    let labels = [];
    let data = [];

    // Assuming the keys in spendingData are in the format "month-year" e.g., "4-2023"
    for (let key in spendingData) {
        if (spendingData.hasOwnProperty(key)) {
            labels.push(key);
            data.push(spendingData[key]);
        }
    }

    return { labels, data };
}

function resetData() {
    // Reset monthly spending and active licenses in localStorage
    localStorage.setItem('activeLicenses', '0');

    // Update the dashboard to reflect the changes
    updateDashboard();

    // Redraw the chart with the reset data
}

// Call this function when you need to reset the data, for example:
//resetData();

document.addEventListener('DOMContentLoaded', function() {
    drawMonthlySpendingChart();
});

function drawMonthlySpendingChart() {
    let ctx = document.getElementById('myChart').getContext('2d');
    let spendingData = JSON.parse(localStorage.getItem('monthlySpending')) || {};
    let labels = Object.keys(spendingData).sort();
    let data = labels.map(label => spendingData[label]);

    new Chart(ctx, {
        type: 'bar', // You can change the type to 'line' if you prefer
        data: {
            labels: labels,
            datasets: [{
                label: 'Monthly Spending',
                data: data,
                backgroundColor: 'rgba(54, 162, 235, 0.5)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}

function fetchUpcomingRenewals() {
    fetch('http://localhost:3001/api/licenses')
    .then(response => response.json())
    .then(licenses => {
        const upcomingRenewalsCount = countUpcomingRenewals(licenses);
        updateUpcomingRenewalsDisplay(upcomingRenewalsCount);
    })
    .catch(error => console.error('Error:', error));
}

function countUpcomingRenewals(licenses) {
    let renewalsCount = 0;
    const today = new Date();
    const fifteenDaysLater = new Date(today);
    fifteenDaysLater.setDate(fifteenDaysLater.getDate() + 15);

    licenses.forEach(license => {
        const expiryDate = new Date(license.expiryDate);
        if (expiryDate >= today && expiryDate <= fifteenDaysLater) {
            renewalsCount++;
        }
    });

    return renewalsCount;
}

function updateUpcomingRenewalsDisplay(count) {
    const renewalsElement = document.querySelector('.upcoming-renewals');
    renewalsElement.textContent = `${count} licenses are due for renewal this month.`;
}

function fetchActiveLicensesCount() {
    fetch('http://localhost:3001/api/active-licenses-count')
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        localStorage.setItem('activeLicenses', data.count.toString());
        updateActiveLicensesDisplay();
    })
    .catch(error => console.error('Error:', error));
}

function updateActiveLicensesDisplay() {
    let activeLicenses = parseInt(localStorage.getItem('activeLicenses')) || 0;
    document.querySelector('.widget .active-licenses').innerText = `${activeLicenses} active licenses.`;
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

        // Update the list of developer activities after adding a new one
        fetchDeveloperActivitiesAndUpdateUI();
    } catch (error) {
        console.error('Error:', error.message);
        // Display error message on the UI if needed
    }
}