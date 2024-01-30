const express = require('express');
const bcrypt = require('bcrypt');
const app = express();
const port = 3001;
const mysql = require('mysql2');
const session = require('express-session');
const crypto = require('crypto');
const nodemailer = require('nodemailer');

const saltRounds = 10;

const bodyParser = require('body-parser');

const db = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',   
    database: 'licensemaster'
});

db.connect((err) => {
    if (err) {
    console.log("Error connecting to DB");
    } else {
    console.log("Connected");
    }
    });

const secret = crypto.randomBytes(64).toString('hex');

app.use(session({
    secret: secret,
    resave: false,
    saveUninitialized: true,
    cookie: { secure: false }
}));

app.use(express.static('public'));
app.use(express.urlencoded({ extended: true }));
app.use(express.json());

app.post('/register', async (req, res) => {
  try {
      const accountType = req.body.accountType;

      if (!['client', 'softwareProvider'].includes(accountType)) {
          return res.status(400).send('Invalid account type');
      }

      db.query('SELECT * FROM software_providers WHERE email = ?', [req.body.email], async (error, results) => {
          if (error) throw error;

          if (results.length > 0) {
              return res.status(400).send('Email already registered');
          }

          const hashedPassword = await bcrypt.hash(req.body.password, saltRounds);

        const newUser = {
          fullName: req.body.fullName,
          phoneNumber: req.body.phoneNumber,
          email: req.body.email,
          username: req.body.username,
          hashedPassword: hashedPassword
    };

        let insertQuery = '';
        if (accountType === 'client') {
          insertQuery = 'INSERT INTO clients SET ?';
        } else if (accountType === 'softwareProvider') {
            insertQuery = 'INSERT INTO software_providers SET ?';
        }

          db.query(insertQuery, newUser, (error, results) => {
              if (error) throw error;

              console.log('New user registered:', newUser);
              res.send('<script>alert("Account creation was successful. Redirecting to login page..."); window.location.href = "/login.html";</script>');
          });
      });
  } catch (error) {
      console.error(error);
      res.status(500).send('Error processing your request');
  }
});

app.post('/login', async (req, res) => {
    try {
        db.query('SELECT *, "softwareProvider" as role FROM software_providers WHERE email = ?', [req.body.email], async (error, providerResults) => {
            if (error) throw error;
  
            if (providerResults.length > 0) {
                const user = providerResults[0];
                return processUserLogin(user);
            } else {
                db.query('SELECT *, "client" as role FROM clients WHERE email = ?', [req.body.email], async (error, clientResults) => {
                    if (error) throw error;
  
                    if (clientResults.length > 0) {
                        const user = clientResults[0];
                        return processUserLogin(user);
                    } else {
                        res.status(400).send('User not found');
                    }
                });
            }
        });
  
        async function processUserLogin(user) {
            const match = await bcrypt.compare(req.body.password, user.hashedPassword);
            if (match) {
                req.session.user = {
                    id: user.id,
                    email: user.email,
                    username: user.username,
                    fullName: user.fullName,
                    role: user.role
                };
  
                if (user.role === 'client') {
                    res.redirect('/licenses.html');
                } else if (user.role === 'softwareProvider') {
                    res.redirect('/NewDash.html');
                }
            } else {
                res.status(400).send('Incorrect password');
            }
        }
    } catch (error) {
        console.error(error);
        res.status(500).send('An error occurred');
    }
  });

app.post('/logout', (req, res) => {
    req.session.destroy(err => {
        if (err) {
            console.error(err);
            res.status(500).send('Error during logout');
        } else {
            res.redirect('/login.html');
        }
    });
});

app.listen(port, () => {
    console.log(`Server running at http://localhost:${port}/Login.html`);
});

const transporter = nodemailer.createTransport({
    service: 'gmail',
    auth: {
        user: 'licensemaster220@gmail.com',
        pass: 'zdgz ylle fvwq qfbl'
    }
});

app.post('/reset-password-request', async (req, res) => {
    try {
        const userEmail = req.body.email;
        let userFound = false;
        let userId;
        let userTable = '';

        const checkUser = async (table) => {
            const [results] = await db.promise().query(`SELECT id FROM ${table} WHERE email = ?`, [userEmail]);
            if (results.length > 0) {
                userFound = true;
                userId = results[0].id;
                userTable = table;
            }
        };

        await checkUser('software_providers');
        if (!userFound) await checkUser('clients');

        if (!userFound) {
            return res.send('Password reset link sent if the email is registered with us.');
        }

        const resetToken = crypto.randomBytes(20).toString('hex');
        const tokenExpiry = Date.now() + 3600000;

        await db.promise().query(`UPDATE ${userTable} SET resetToken = ?, tokenExpiry = ? WHERE id = ?`, [resetToken, tokenExpiry, userId]);

        const resetLink = `http://localhost:3001/reset-password.html?token=${resetToken}`;
        const mailOptions = {
            from: 'licensemaster220@gmail.com',
            to: userEmail,
            subject: 'Password Reset',
            text: `Click the link to reset your password: ${resetLink}`
        };

        try {
            await transporter.sendMail(mailOptions);
            res.send('Password reset link sent if the email is registered with us.');
        } catch (error) {
            console.error('Error sending email:', error);
            res.status(500).send('Error sending password reset email.');
        }

    } catch (error) {
        console.error('Error processing request:', error);
        res.status(500).send('Error processing request.');
    }
});
  app.post('/perform-password-reset', async (req, res) => {
    const { token, newPassword } = req.body;
    let userTable = '';
  
    const validateToken = async (tableName) => {
        const [results] = await db.promise().query(`SELECT id FROM ${tableName} WHERE resetToken = ? AND tokenExpiry > ?`, [token, Date.now()]);
        if (results.length > 0) {
            userTable = tableName;
        }
    };
  
    await validateToken('software_providers');
    if (!userTable) await validateToken('clients');
  
    if (!userTable) {
        return res.status(400).send('Invalid or expired token');
    }
  
    const hashedPassword = await bcrypt.hash(newPassword, saltRounds);
    await db.promise().query(`UPDATE ${userTable} SET hashedPassword = ?, resetToken = NULL, tokenExpiry = NULL WHERE resetToken = ?`, [hashedPassword, token]);
  
    res.send('<script>alert("Password was reset successfully. Redirecting you to login page now..."); window.location.href = "/login.html";</script>');
});

app.get('/api/user', (req, res) => {
    if (req.session.user) {
        res.json({ user: req.session.user });
    } else {
        res.status(401).send('Not logged in');
    }
});

function generateSerialNumber() {
    return crypto.randomBytes(10).toString('hex');
  }
  
  app.post('/api/clients', (req, res) => {
    try {
      const { name, email } = req.body;
  
      db.query('INSERT INTO clients (name, email) VALUES (?, ?)', [name, email], (error, results) => {
        if (error) {
          console.error('Error creating client:', error);
          res.status(500).json({ error: 'Internal Server Error' });
        } else {
          res.status(201).json({ message: 'Client created successfully' });
        }
      });
    } catch (error) {
      console.error('Error processing request:', error);
      res.status(500).json({ error: 'Internal Server Error' });
    }
  });
  
  app.post('/api/generate-serial', (req, res) => {
    if (!req.session.user) {
        return res.status(401).send('Unauthorized: No user logged in');
    }

    const serialNumber = crypto.randomBytes(20).toString('hex');
    const { productName, expirationDate } = req.body;
    const createdBy = req.session.user.id;

    if (!productName || !expirationDate) {
        return res.status(400).send('Product name and expiration date are required.');
    }

    const creationDate = new Date().toISOString().slice(0, 19).replace('T', ' ');
    const query = 'INSERT INTO serial_numbers (serial_number, product_name, creation_date, expiration_date, status, created_by) VALUES (?, ?, ?, ?, "active", ?)';

    db.query(query, [serialNumber, productName, creationDate, expirationDate, createdBy], (error, results) => {
        if (error) {
            console.error(error);
            return res.status(500).send('Error generating serial number: ' + error.message);
        }
        res.json({ serialNumber: serialNumber });
    });
});
  
  app.post('/api/associate-serial', (req, res) => {
    try {
      const { serialNumber, productId, clientId } = req.body;
  
      db.query('UPDATE serial_numbers SET product_id = ?, client_id = ? WHERE serial_number = ?', [productId, clientId, serialNumber], (error, results) => {
        if (error) {
          console.error('Error associating serial number:', error);
          res.status(500).json({ error: 'Internal Server Error' });
        } else {
          res.status(200).json({ message: 'Serial number associated successfully' });
        }
      });
    } catch (error) {
      console.error('Error processing request:', error);
      res.status(500).json({ error: 'Internal Server Error' });
    }
  });
  
  app.post('/api/toggle-serial', (req, res) => {
    try {
      const { serialNumber, isEnabled } = req.body;
  
      db.query('UPDATE serial_numbers SET is_enabled = ? WHERE serial_number = ?', [isEnabled, serialNumber], (error, results) => {
        if (error) {
          console.error('Error toggling serial number:', error);
          res.status(500).json({ error: 'Internal Server Error' });
        } else {
          res.status(200).json({ message: `Serial number ${isEnabled ? 'enabled' : 'disabled'} successfully` });
        }
      });
    } catch (error) {
      console.error('Error processing request:', error);
      res.status(500).json({ error: 'Internal Server Error' });
    }
  });
  
  app.post('/api/notify-client', async (req, res) => {
    try {
      const { clientId, message } = req.body;
  
      db.query('SELECT email FROM clients WHERE id = ?', [clientId], async (error, results) => {
        if (error) {
          console.error('Error fetching client email:', error);
          res.status(500).json({ error: 'Internal Server Error' });
        } else {
          const clientEmail = results[0].email;
          const mailOptions = {
            from: 'licensemaster220@gmail.com',
            to: clientEmail,
            subject: 'Notification from LicenseMaster',
            text: message,
          };
  
          await transporter.sendMail(mailOptions);
          res.status(200).json({ message: 'Client notified successfully' });
        }
      });
    } catch (error) {
      console.error('Error processing request:', error);
      res.status(500).json({ error: 'Internal Server Error' });
    }
  });
  
  app.get('/api/recent-activities/clients', (req, res) => {
    try {
        db.query('SELECT * FROM recent_activities WHERE category = "client" ORDER BY date DESC LIMIT 5', (error, results) => {
            if (error) {
                console.error('Error fetching recent client activities:', error);
                res.status(500).json({ error: 'Internal Server Error' });
            } else {
                res.json(results);
            }
        });
    } catch (error) {
        console.error('Error fetching recent client activities:', error);
        res.status(500).json({ error: 'Internal Server Error' });
    }
});

app.get('/api/recent-activities/developer', (req, res) => {
    try {
        db.query('SELECT date, admin, action FROM dev_activity_log ORDER BY date DESC LIMIT 5', (error, results) => {
            if (error) {
                console.error('Error fetching recent activities:', error);
                res.status(500).json({ error: 'Internal Server Error' });
            } else {
                res.json(results);
            }
        });
    } catch (error) {
        console.error('Error fetching recent activities:', error);
        res.status(500).json({ error: 'Internal Server Error' });
    }

});

app.post('/api/add-activity', (req, res) => {
    const { category, admin, action } = req.body;

    if (!category || !admin || !action) {
        return res.status(400).json({ status: 'error', message: 'Category, admin, and action are required fields' });
    }

    if (category !== 'developer') {
        return res.status(400).json({ status: 'error', message: 'Invalid category' });
    }

    db.query(
        'INSERT INTO dev_activity_log (category, admin, action, date) VALUES (?, ?, ?, NOW())',
        [category, admin, action],
        (error, results) => {
            if (error) {
                console.error('Error adding recent activity:', error);
                res.status(500).json({ status: 'error', message: 'Internal server error' });
            } else {
                res.status(200).json({ status: 'success' });
            }
        }
    );
});
app.post('/api/licenses', async (req, res) => {
    const { productName, licenseKey, expiryDate, purchaseDate, price } = req.body;
    const insertQuery = 'INSERT INTO licenses (productName, licenseKey, expiryDate, purchaseDate, price) VALUES (?, ?, ?, ?, ?)';
  
    db.query(insertQuery, [productName, licenseKey, expiryDate, purchaseDate, price], (error, results) => {
        if (error) {
            console.error('Error adding license:', error);
            res.status(500).send('Error adding license');
            return;
        }

        const updateActiveLicensesQuery = 'UPDATE system_info SET active_licenses = active_licenses + 1';
  
        db.query(updateActiveLicensesQuery, (error, results) => {
            if (error) {
                console.error('Error updating active licenses count:', error);
                res.status(500).send('Error updating active licenses count');
                return;
            }
  
            res.status(201).send('License added successfully');
        });
    });
  });

app.get('/api/licenses', async (req, res) => {
  const selectQuery = 'SELECT * FROM licenses';

  db.query(selectQuery, (error, results) => {
      if (error) {
          console.error('Error fetching licenses:', error);
          res.status(500).send('Error fetching licenses');
      } else {
          res.json(results);
      }
  });
});

app.get('/api/user-info', (req, res) => {
    if (req.session.user) {
        res.json({ username: req.session.user.username });
    } else {
        res.status(401).send('Not logged in');
    }
});

app.get('/api/active-licenses-count', async (req, res) => {
    try {
        const query = 'SELECT COUNT(*) AS count FROM licenses WHERE isActive = 1';
        db.query(query, (error, results) => {
            if (error) {
                throw error;
            }
            res.json({ count: results[0].count });
        });
    } catch (error) {
        console.error('Error:', error);
        res.status(500).json({ error: 'Internal Server Error' });
    }
});

app.get('/api/my-serial-numbers', (req, res) => {
    if (!req.session.user) {
        return res.status(401).send('Not logged in');
    }

    const userId = req.session.user.id;

    const query = `SELECT * FROM serial_numbers WHERE created_by = ?`;

    db.query(query, [userId], (error, results) => {
        if (error) {
            console.error('Error fetching serial numbers:', error);
            return res.status(500).send('Error fetching serial numbers');
        }
        res.json(results);
    });
});

app.delete('/api/licenses/:id', async (req, res) => {
  const licenseId = req.params.id;
  const deleteQuery = 'DELETE FROM licenses WHERE id = ?';

  db.query(deleteQuery, [licenseId], (error, results) => {
      if (error) {
          console.error('Error removing license:', error);
          res.status(500).send('Error removing license');
      } else {
          res.status(200).send('License removed successfully');
      }
  });
});

app.post('/api/licenses/:id/stop-renew', async (req, res) => {
  const licenseId = req.params.id;
  const updateQuery = 'UPDATE licenses SET isRenewing = 0 WHERE id = ?';

  db.query(updateQuery, [licenseId], (error, results) => {
      if (error) {
          console.error('Error stopping license renewal:', error);
          res.status(500).send('Error stopping license renewal');
      } else {
          res.status(200).send('License renewal stopped successfully');
      }
  });
});

app.post('/generate-serial-number', (req, res) => {
    if (!req.session.user) {
        return res.status(401).send('Unauthorized: No user logged in');
    }

    const serialNumber = crypto.randomBytes(20).toString('hex');
    const { productName, expirationDate } = req.body;
    const createdBy = req.session.user.id;

    if (!productName || !expirationDate) {
        return res.status(400).send('Product name and expiration date are required.');
    }

    const creationDate = new Date().toISOString().slice(0, 19).replace('T', ' ');
    const query = 'INSERT INTO serial_numbers (serial_number, product_name, creation_date, expiration_date, status, created_by) VALUES (?, ?, ?, ?, "active", ?)';

    db.query(query, [serialNumber, productName, creationDate, expirationDate, createdBy], (error, results) => {
        if (error) {
            console.error(error);
            return res.status(500).send('Error generating serial number: ' + error.message);
        }
        res.json({ serialNumber: serialNumber });
    });
});


app.get('/list-serial-numbers', (req, res) => {
    if (!req.session.user) {
        return res.status(401).send('Not logged in');
    }

    const userId = req.session.user.id;
    const query = `SELECT * FROM serial_numbers WHERE created_by = ?`;

    db.query(query, [userId], (error, results) => {
        if (error) {
            console.error(error);
            return res.status(500).send('Error fetching serial numbers');
        }
        res.json(results);
    });
});




app.post('/update-serial-number', (req, res) => {
    if (!req.session.user) {
        return res.status(401).send('Unauthorized');
    }

    const { id, status } = req.body;
    const userId = req.session.user.id;

    db.query('SELECT * FROM serial_numbers WHERE id = ? AND created_by = ?', [id, userId], (selectError, selectResults) => {
        if (selectError) {
            console.error(selectError);
            return res.status(500).send('Error verifying serial number ownership');
        }
        if (selectResults.length === 0) {
            return res.status(403).send('You do not have permission to modify this serial number');
        }

        const query = 'UPDATE serial_numbers SET status = ? WHERE id = ?';
        db.query(query, [status, id], (updateError, updateResults) => {
            if (updateError) {
                console.error(updateError);
                return res.status(500).send('Error updating serial number');
            }
            res.send(`Serial number status updated to ${status}`);
        });
    });
});


app.post('/delete-serial-number', (req, res) => {
    if (!req.session.user) {
        return res.status(401).send('Unauthorized');
    }

    const { id } = req.body;
    const userId = req.session.user.id;

    db.query('SELECT * FROM serial_numbers WHERE id = ? AND created_by = ?', [id, userId], (selectError, selectResults) => {
        if (selectError) {
            console.error(selectError);
            return res.status(500).send('Error verifying serial number ownership');
        }
        if (selectResults.length === 0) {
            return res.status(403).send('You do not have permission to delete this serial number');
        }

        db.query('DELETE FROM serial_numbers WHERE id = ?', [id], (deleteError, deleteResults) => {
            if (deleteError) {
                console.error(deleteError);
                return res.status(500).send('Error deleting serial number');
            }
            res.send('Serial number deleted successfully');
        });
    });
});

app.post('/update-serial-details', (req, res) => {
    if (!req.session.user) {
        return res.status(401).send('Unauthorized');
    }

    const { id, updatedProductName, updatedExpirationDate } = req.body;
    const userId = req.session.user.id;


    db.query('SELECT * FROM serial_numbers WHERE id = ? AND created_by = ?', [id, userId], (error, results) => {
        if (error) {
            console.error('Error checking serial number:', error);
            return res.status(500).send('Error checking serial number');
        }

        if (results.length === 0) {
            return res.status(403).send('You do not have permission to update this serial number');
        }

        const updateQuery = 'UPDATE serial_numbers SET product_name = ?, expiration_date = ? WHERE id = ?';
        db.query(updateQuery, [updatedProductName, updatedExpirationDate, id], (updateError, updateResults) => {
            if (updateError) {
                console.error('Error updating serial number details:', updateError);
                return res.status(500).send('Error updating serial number details');
            }
            res.json({ message: 'Serial number details updated successfully.' });
        });
    });
});

//END OF SERIAL NUMBER FUNCTIONALITY

//CLIENT MANAGEMENT FUNCTIONALITY

// Endpoint to get all clients
app.get('/clients', (req, res) => {
    db.query('SELECT id, fullName, phoneNumber, email, username FROM clients', (error, results) => {
        if (error) {
            console.error('Error fetching clients:', error);
            return res.status(500).send('Error fetching clients');
        }
        res.json(results);
    });
});

// Endpoint to update an existing client
app.put('/clients/:id', (req, res) => {
  const { email, subscription_type, subscription_end } = req.body;
  const clientId = req.params.id;

  const query = 'UPDATE clients SET email = ?, subscription_type = ?, subscription_end = ? WHERE id = ?';

  db.query(query, [email, subscription_type, subscription_end, clientId], (error, results) => {
      if (error) {
          console.error(error);
          return res.status(500).send('Error updating client details');
      }
      res.send('Client details updated successfully');
  });
});



// Endpoint to delete a client
app.delete('/clients/:id', (req, res) => {
  const clientId = req.params.id;
  
  db.query('DELETE FROM clients WHERE id = ?', [clientId], (error, results) => {
      if (error) {
          console.error(error);
          return res.status(500).send('Error deleting client');
      }
      res.send('Client deleted successfully');
  });
});

// Endpoint to associate a serial number with a client
app.post('/associate-serial', (req, res) => {
  const { serialNumberId, clientId } = req.body;

  // Check if the serial number is active
  db.query('SELECT * FROM serial_numbers WHERE id = ? AND status = "active"', [serialNumberId], (error, results) => {
      if (error) {
          return res.status(500).send('Error checking serial number status');
      }
      if (results.length === 0) {
          return res.status(404).send('Active serial number not found');
      }

      // If active, associate it with the client
      const updateQuery = 'UPDATE serial_numbers SET client_id = ? WHERE id = ?';
      db.query(updateQuery, [clientId, serialNumberId], (updateError, updateResults) => {
          if (updateError) {
              return res.status(500).send('Error associating serial number with client');
          }
          res.send('Serial number associated with client successfully');
          // Optionally, send back the updated list of clients with their associated serial numbers
      });
  });
});

// Endpoint to dissociate a serial number from a client
app.post('/dissociate-serial', (req, res) => {
  const { serialNumberId } = req.body;

  const updateQuery = 'UPDATE serial_numbers SET client_id = NULL WHERE id = ?';
  db.query(updateQuery, [serialNumberId], (updateError, updateResults) => {
      if (updateError) {
          console.error(updateError);
          return res.status(500).send('Error dissociating serial number from client');
      }
      res.send('Serial number dissociated from client successfully');
  });
});

app.get('/api/licenses/:clientId', (req, res) => {
    const clientId = req.params.clientId;

    // Replace this with proper authentication and user session retrieval
    // For demo purposes, we are using a passed clientId
    const sql = 'SELECT * FROM serial_numbers WHERE client_id = ?';
    db.query(sql, [clientId], (err, result) => {
        if (err) throw err;
        res.json(result);
    });
});
app.get('/api/userid', (req, res) => {
    if (req.session.user) {
        res.json({ userId: req.session.user.id });
    } else {
        res.status(401).send('Not logged in');
    }
});

app.post('/api/update-serial-number', (req, res) => {
    const { id, updatedExpirationDate } = req.body;

    if (!id || !updatedExpirationDate) {
        return res.status(400).send('Serial number ID and updated expiration date are required.');
    }

    const query = 'UPDATE serial_numbers SET expiration_date = ? WHERE id = ?';

    db.query(query, [updatedExpirationDate, id], (error, results) => {
        if (error) {
            console.error('Error updating serial number:', error);
            return res.status(500).send('Error updating serial number');
        }
        res.send('Serial number updated successfully');
    });
});

app.post('/api/dissociate-serial-number', (req, res) => {
    const { serialNumberId } = req.body;

    if (!serialNumberId) {
        return res.status(400).send('Serial number ID is required.');
    }

    const query = 'UPDATE serial_numbers SET client_id = NULL WHERE id = ?';

    db.query(query, [serialNumberId], (error, results) => {
        if (error) {
            console.error('Error dissociating serial number:', error);
            return res.status(500).send('Error dissociating serial number');
        }
        res.send('Serial number dissociated successfully');
    });
});