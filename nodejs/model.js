const { connectDB, db } = require('../nodejs/dbconnection');



/*=======================KHÁCH HÀNG=====================*/
const KhachHang = {
  getalls: (callback) => {
    db.query('SELECT * FROM khachhang', callback);
  },
  
  inserts: (data, callback) => {
    
    const query = 'INSERT INTO khachhang (makh, tenkh, diachikh, sdtkh, emailkh) VALUES (?, ?, ?, ?, ?)';
    const values = [data.makh, data.tenkh, data.diachikh, data.sdtkh, data.emailkh];

    db.query(query, values, (err, result) => {
      if (err) {
        console.error('Error inserting khachhang:', err);
        callback(err, null);
      } else {
        callback(null, result);
      }
    });
  },

  deletesPost: (makh, callback) => { 
        checkDonHangConstraintKH(makh, (errDonHang, resultDonHang) => {
          if (errDonHang) {
            console.error('Error checking DonHang constraints:', errDonHang);
            callback(errDonHang, null);
          } else if (resultDonHang.length > 0) {
        
            callback(new Error('Ràng buộc ngoại khóa với bảng donhang'), null);
          } else {
      
            const deleteQuery = 'DELETE FROM khachhang WHERE makh = ?';
            db.query(deleteQuery, [makh], (error, result) => {
              if (error) {
                console.error('Error deleting Nhân Viên:', error);
                callback(error, null);
              } else {
                callback(null, result);
              }
            });
          }
        });     
},

  updatesPost: (makh, updatedData, callback) => {
    const query = 'UPDATE khachhang SET ? WHERE makh = ?';
    db.query(query, [updatedData, makh], (err, result) => {
      if (err) {
        console.error('Error updating khachhang:', err);
        callback(err, null);
      } else {
        callback(null, result);
      }
    });
  }
};

// Kiểm tra ràng buộc với bảng Đơn Hàng
const checkDonHangConstraintKH = (makh, callback) => {
  const query = 'SELECT * FROM donhang WHERE makh = ?';
  db.query(query, [makh], (err, result) => {
    if (err) {
      console.error('Lỗi khi kiểm tra Phiếu Xuất:', err);
      callback(err, null);
    } else {
      callback(null, result);
    }
  });
};

/*=======================NHÂN VIÊN=====================*/
const NhanVien = {
  getalls: (callback) => {
    db.query('SELECT * FROM nhanvien', callback);
  },
  
  inserts: (data, callback) => {
    
    const query = 'INSERT INTO nhanvien (manv, tennv, diachinv, sdtnv, emailnv) VALUES (?, ?, ?, ?, ?)';
    const values = [data.manv, data.tennv, data.diachinv, data.sdtnv, data.emailnv];

    db.query(query, values, (err, result) => {
      if (err) {
        console.error('Error inserting Nhanvien:', err);
        callback(err, null);
      } else {
        callback(null, result);
      }
    });
  },

  deletesPost: (manv, callback) => {
    checkPhieuNhapConstraintNV(manv, (errPhieuNhap, resultPhieuNhap) => {
      if (errPhieuNhap) {
        console.error('Error checking PhieuNhap constraints:', errPhieuNhap);
        callback(errPhieuNhap, null);
      } else if (resultPhieuNhap.length > 0) {
       
        callback(new Error('Ràng buộc ngoại khóa với bảng phieunhap'), null);
      } else {
     
        checkPhieuXuatConstraintNV(manv, (errPhieuXuat, resultPhieuXuat) => {
          if (errPhieuXuat) {
            console.error('Error checking PhieuXuat constraints:', errPhieuXuat);
            callback(errPhieuXuat, null);
          } else if (resultPhieuXuat.length > 0) {
        
            callback(new Error('Ràng buộc ngoại khóa với bảng phieuxuat'), null);
          } else {
      
            const deleteQuery = 'DELETE FROM nhanvien WHERE manv = ?';
            db.query(deleteQuery, [manv], (error, result) => {
              if (error) {
                console.error('Error deleting Nhân Viên:', error);
                callback(error, null);
              } else {
                callback(null, result);
              }
            });
          }
        });
      }
    });
},

  updatesPost: (manv, updatedData, callback) => {
    const query = 'UPDATE nhanvien SET ? WHERE manv = ?';
    db.query(query, [updatedData, manv], (err, result) => {
      if (err) {
        console.error('Error updating khachhang:', err);
        callback(err, null);
      } else {
        callback(null, result);
      }
    });
  }
};

// Kiểm tra ràng buộc với bảng Phiếu Nhập
const checkPhieuNhapConstraintNV = (manv, callback) => {
  const query = 'SELECT * FROM phieunhap WHERE manv = ?';
  db.query(query, [manv], (err, result) => {
    if (err) {
      console.error('Lỗi khi kiểm tra Phiếu Nhập:', err);
      callback(err, null);
    } else {
      callback(null, result);
    }
  });
};

// Kiểm tra ràng buộc với bảng Phiếu Xuất
const checkPhieuXuatConstraintNV = (manv, callback) => {
  const query = 'SELECT * FROM phieuxuat WHERE manv = ?';
  db.query(query, [manv], (err, result) => {
    if (err) {
      console.error('Lỗi khi kiểm tra Phiếu Xuất:', err);
      callback(err, null);
    } else {
      callback(null, result);
    }
  });
};

/*=======================SẢN PHẨM=====================*/
const SanPham = { 
  getalls: (sokho, callback) => {
    const query = `
      SELECT sanpham.*, khohang.sokho
      FROM sanpham
      INNER JOIN khohang ON sanpham.sokho = khohang.sokho
      WHERE khohang.sokho = ?;
    `;
    db.query(query, [sokho], callback);
  },
  
  inserts: (data, callback) => {
    const query = 'INSERT INTO sanpham (masp, tensp, giasp, soluong, loaisach, tentacgia, sokho) VALUES (?, ?, ?, ?, ?, ?, ?)';
    const values = [data.masp, data.tensp, data.giasp, data.soluong, data.loaisach, data.tentacgia, data.sokho];

    db.query(query, values, (err, result) => {
      if (err) {
        console.error('Error inserting Sanpham:', err);
        callback(err, null);
      } else {
        callback(null, result);
      }
    });
  },

  deletesPost: (masp, callback) => {
    checkDonHangConstraint(masp, (err, donhangResult) => {
      if (err) {
        callback(err, null);
      } else {
        if (donhangResult.length > 0) {
          callback(new Error('Ràng buộc ngoại khóa với bảng DonHang'), null);
        } else {
          checkPhieuNhapConstraint(masp, (err, phieunhapResult) => {
            if (err) {
              callback(err, null);
            } else {
              if (phieunhapResult.length > 0) {
                callback(new Error('Ràng buộc ngoại khóa với bảng PhieuNhap'), null);
              } else {
                checkPhieuXuatConstraint(masp, (err, phieuxuatResult) => {
                  if (err) {
                    callback(err, null);
                  } else {
                    if (phieuxuatResult.length > 0) {
                      callback(new Error('Ràng buộc ngoại khóa với bảng PhieuXuat'), null);
                    } else {
                      const deleteQuery = 'DELETE FROM sanpham WHERE masp = ?';
                      db.query(deleteQuery, [masp], (err, result) => {
                        if (err) {
                          console.error('Error deleting Sanpham:', err);
                          callback(err, null);
                        } else {
                          callback(null, result);
                        }
                      });
                    }
                  }
                });
              }
            }
          });
        }
      }
    });
  },
  
  updatesPost: (masp, updatedData, callback) => {
    const query = 'UPDATE sanpham SET ? WHERE masp = ?';
    db.query(query, [updatedData, masp], (err, result) => {
      if (err) {
        console.error('Error updating Sanpham:', err);
        callback(err, null);
      } else {
        callback(null, result);
      }
    });
  },
};

// Kiểm tra ràng buộc với bảng DonHang
const checkDonHangConstraint = (masp, callback) => {
  const query = 'SELECT * FROM donhang WHERE masp = ?';
  db.query(query, [masp], (err, result) => {
    if (err) {
      console.error('Error checking DonHang:', err);
      callback(err, null);
    } else {
      callback(null, result);
    }
  });
};

// Kiểm tra ràng buộc với bảng PhieuNhap
const checkPhieuNhapConstraint = (masp, callback) => {
  const query = 'SELECT * FROM phieunhap WHERE masp = ?';
  db.query(query, [masp], (err, result) => {
    if (err) {
      console.error('Error checking PhieuNhap:', err);
      callback(err, null);
    } else {
      callback(null, result);
    }
  });
};

// Kiểm tra ràng buộc với bảng PhieuXuat
const checkPhieuXuatConstraint = (masp, callback) => {
  const query = 'SELECT * FROM phieuxuat WHERE masp = ?';
  db.query(query, [masp], (err, result) => {
    if (err) {
      console.error('Error checking PhieuXuat:', err);
      callback(err, null);
    } else {
      callback(null, result);
    }
  });
};


/*=======================ĐƠN HÀNG=====================*/
const DonHang = {
  
  getalls: (callback) => {
    db.query('SELECT * FROM donhang', callback);
  },

  
  inserts: (data, callback) => {
    const query = 'INSERT INTO donhang (madh, ngaydathang, ngaygiaohang, soluong, tongtien, makh, masp) VALUES (?, ?, ?, ?, ?, ?, ?)';
    const values = [data.madh, data.ngaydathang, data.ngaygiaohang, data.soluong, data.tongtien, data.makh, data.masp];

    db.query(query, values, (err, result) => {
      if (err) {
        console.error('Error inserting DonHang:', err);
        callback(err, null);
      } else {
        callback(null, result);
      }
    });
  },

  deletes: (madh, callback) => {
    const query = 'DELETE FROM donhang WHERE madh = ?';
    db.query(query, [madh], (err, result) => {
      if (err) {
        console.error('Error deleting DonHang:', err);
        callback(err, null);
      } else {
        callback(null, result);
      }
    });
  },

  updates: (madh, updatedData, callback) => {
    const query = 'UPDATE donhang SET ? WHERE madh = ?';
    db.query(query, [updatedData, madh], (err, result) => {
      if (err) {
        console.error('Error updating DonHang:', err);
        callback(err, null);
      } else {
        callback(null, result);
      }
    });
  },
};

/*=======================KHO HÀNG=====================*/
const KhoHang = {
  getAll: (callback) => {
    db.query('SELECT * FROM khohang', callback);
  },
  inserts: (data, callback) => {
    const query = 'INSERT INTO khohang (sokho, tongsoluong, soluongnhap, soluongxuat) VALUES (?, ?, ?, ?)';
    const values = [data.sokho, data.tongsoluong, data.soluongnhap, data.soluongxuat];

    db.query(query, values, (err, result) => {
      if (err) {
        console.error('Error inserting KhoHang:', err);
        callback(err, null);
      } else {
        callback(null, result);
      }
    });
  },
  deletes: (sokho, callback) => {
    // Kiểm tra ràng buộc ngoại khóa với bảng 'sanpham' trước khi xóa
    const checkSanPhamQuery = 'SELECT * FROM sanpham WHERE sokho = ?';
    db.query(checkSanPhamQuery, [sokho], (err, result) => {
      if (err) {
        console.error('Error checking SanPham:', err);
        callback(err, null);
      } else {
        if (result.length > 0) {
          // Nếu có ràng buộc ngoại khóa, không xóa 'khohang'
          const error = new Error('Ràng buộc ngoại khóa với bảng SanPham');
          console.error(error.message);
          callback(error, null);
        } else {
          // Nếu không có ràng buộc ngoại khóa, thực hiện xóa dữ liệu từ 'khohang'
          const deleteQuery = 'DELETE FROM khohang WHERE sokho = ?';
          db.query(deleteQuery, [sokho], (err, result) => {
            if (err) {
              console.error('Error deleting KhoHang:', err);
              callback(err, null);
            } else {
              callback(null, result);
            }
          });
        }
      }
    });
  },

  updates: (sokho, updatedData, callback) => {
    const query = 'UPDATE khohang SET ? WHERE sokho = ?';
    db.query(query, [updatedData, sokho], (err, result) => {
      if (err) {
        console.error('Error updating KhoHang:', err);
        callback(err, null);
      } else {
        callback(null, result);
      }
    });
  },
};

/*=======================PHIẾU NHẬP=====================*/
const PhieuNhap = {
  
  getalls: (callback) => {
    db.query('SELECT * FROM phieunhap', callback);
  },

  
  inserts: (data, callback) => {
    const query = 'INSERT INTO phieunhap (sopn, ngaypn, soluongpn, masp, manv) VALUES (?, ?, ?, ?, ?)';
    const values = [data.sopn, data.ngaypn, data.soluongpn, data.masp, data.manv];

    db.query(query, values, (err, result) => {
      if (err) {
        console.error('Error inserting PhieuNhap:', err);
        callback(err, null);
      } else {
        callback(null, result);
      }
    });
  },

  deletes: (sopn, callback) => {
    const query = 'DELETE FROM phieunhap WHERE sopn = ?';
    db.query(query, [sopn], (err, result) => {
      if (err) {
        console.error('Error deleting PhieuNhap:', err);
        callback(err, null);
      } else {
        callback(null, result);
      }
    });
  },

  updates: (sopn, updatedData, callback) => {
    const query = 'UPDATE phieunhap SET ? WHERE sopn = ?';
    db.query(query, [updatedData, sopn], (err, result) => {
      if (err) {
        console.error('Error updating PhieuXuat:', err);
        callback(err, null);
      } else {
        callback(null, result);
      }
    });
  },
};

/*=======================PHIẾU XUẤT=====================*/
const PhieuXuat = {
  
  getalls: (callback) => {
    db.query('SELECT * FROM phieuxuat', callback);
  },

  
  inserts: (data, callback) => {
    const query = 'INSERT INTO phieuxuat (sopx, ngaypx, soluongpx, masp, manv) VALUES (?, ?, ?, ?, ?)';
    const values = [data.sopx, data.ngaypx, data.soluongpx, data.masp, data.manv];

    db.query(query, values, (err, result) => {
      if (err) {
        console.error('Error inserting PhieuXuat:', err);
        callback(err, null);
      } else {
        callback(null, result);
      }
    });
  },

  deletes: (sopx, callback) => {
    const query = 'DELETE FROM phieuxuat WHERE sopx = ?';
    db.query(query, [sopx], (err, result) => {
      if (err) {
        console.error('Error deleting PhieuXuat:', err);
        callback(err, null);
      } else {
        callback(null, result);
      }
    });
  },

  updates: (sopx, updatedData, callback) => {
    const query = 'UPDATE phieuxuat SET ? WHERE sopx = ?';
    db.query(query, [updatedData, sopx], (err, result) => {
      if (err) {
        console.error('Error updating PhieuXuat:', err);
        callback(err, null);
      } else {
        callback(null, result);
      }
    });
  },
};



module.exports = {KhachHang,NhanVien,SanPham,DonHang,KhoHang,PhieuNhap,PhieuXuat};