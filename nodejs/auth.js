// auth.js
const express = require('express');
const bodyParser = require('body-parser');
const jwt = require('jsonwebtoken');
const {  db } = require('../nodejs/dbconnection');

const authApp = express();
const secretKey = 'yourSecretKey';


authApp.use(bodyParser.json());

authApp.post('/login', (req, res) => {

  const { username, password } = req.body;
  // console.log(username+"-"+password);
 // console.log(req.body);
  const sql = `SELECT * FROM admin WHERE username = ? AND password = ?`;
  db.query(sql,[username,password],(err,results)=>
  
  {
    if (err) {
      console.error('Database query error:', err);
      res.status(500).json({ message: 'Internal Server Error' });
      return;
    }
   if (results.length > 0) {
      // const username = results[0];
      // const userpass = results[1];
      const { userId, pass } = results[0];
      const token = jwt.sign({ userId, pass }, secretKey, { expiresIn: '1h' });
      console.log('Token:', token);
      res.json({ token });
    } else {
      res.status(401).json({  message: 'Invalid credentials' });
    }
  });


});

const authenticateJWT = (req, res, next) => {
  const token = req.header('Authorization');
  console.log('Authorization Header:', token);
  if (!token || !token.startsWith('Bearer ')) {
    return res.status(401).json({ message: 'Unauthorized' });
  }
  
  const tokenWithoutBearer = token.split(' ')[1];
  

  jwt.verify(tokenWithoutBearer, secretKey, (err, user) => {
    if (err)
     return res.status(403).json({ message: 'Forbidden' });

    req.user = user;
    next();
  });
};

authApp.get('/index.js', authenticateJWT, (req, res) => {
  res.json({ message: 'This is a protected route', user: req.user });
});

module.exports = {authApp,authenticateJWT};
