const express = require('express');
const { connectDB, db } = require('./dbconnection');
const routes = require('./index');
const {authApp,authenticateJWT} = require('./auth'); // Import the authentication code

const app = express();
const port = 3000;

// Use Express's built-in json middleware
app.use(express.json());


connectDB()
  .then(() => {
    app.use('/auth', authApp);
  
    app.use('/api', authenticateJWT, routes);
  
    app.use('/book',routes, (req, res, next) => {
      // Check if user is authenticated (token is valid)
      if (req.user) {
        // User is authenticated, grant access to static files
        next();
      } else {
        // User is not authenticated, deny access to static files
        res.status(403).json({ message: 'Forbidden: Access to static files is denied.' });
      }
    });
    

    

    app.listen(port, () => {
      console.log(`Server is running on port ${port}`);
    });
  })
  .catch((err) => {
    console.error('Failed to connect to the database:', err);
  });