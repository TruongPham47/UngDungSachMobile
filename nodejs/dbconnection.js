const mysql = require('mysql');

const db = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: '',
  database: 'sach',
});

function connectDB() {
  return new Promise((resolve, reject) => {
    db.connect((err) => {
      if (err) {
        console.error('Error connecting to database:', err);
        reject(err);
      } else {
        console.log('Connected to database');
        resolve();
      }
    });
  });
}

module.exports = {
  connectDB,
  db
};
