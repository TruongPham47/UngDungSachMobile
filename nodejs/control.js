const {KhachHang,NhanVien,SanPham,DonHang,KhoHang,PhieuNhap,PhieuXuat} = require('../nodejs/model');


/*=======================KHÁCH HÀNG=====================*/
const KhachHang_controls = {
    getall: (req, res) => {
        KhachHang.getalls((err, results) => {
            if (err) {
                console.error('Error getting khachhang:', err);
                res.status(500).json({ error: 'Internal Server Error' });
            } else {
                res.json(results);
            }
        });
    },
    insert: (req, res) => {
      const data = req.body; 
      console.log('Request Body:', data); 
      KhachHang.inserts(data, (err, result) => {
        if (err) {
          console.error('Error inserting khachhang:', err);
          res.status(500).json({ error: 'Internal Server Error' });
        } else {
          res.json({ message: 'Thêm thành công khách hàng', result });
        }
      });
    },
    deletePost: (req, res) => {
      const makh = req.params.makh;
      // Call the deletesPost function from NhanVien
      KhachHang.deletesPost(makh, (err, result) => {
        if (err) {
          // Check specific error messages and send appropriate responses
          if (err.message === 'Ràng buộc ngoại khóa với bảng donhang') {
            res.status(400).json({ error: 'Không thể xóa vì có ràng buộc ngoại khóa với bảng donhang' });
          } 
           else if (err.message === 'No employee found to delete') {
            res.status(404).json({ error: 'Không tìm thấy khách hàng để xóa' });
          } else {
            // For other errors, send a generic Internal Server Error response
            console.error('Error deleting Khách hàng:', err);
            res.status(500).json({ error: 'Internal Server Error' });
          }
        } else {
          if (result.affectedRows > 0) {
            res.json({ message: 'Xóa thành công khách hàng', result });
          } else {
            res.status(404).json({ error: 'Không tìm thấy Khách hàng để xóa' });
          }
        }
      });
    },
    updatePost: (req, res) => {
      const { makh } = req.params; 
      const updatedData = req.body; 
  
      KhachHang.updatesPost(makh, updatedData, (err, result) => {
        if (err) {
          console.error('Error updating khachhang:', err);
          res.status(500).json({ error: 'Internal Server Error' });
        } else {
          res.json({ message: 'Khách hàng cập nhật thành công', result });
        }
      });
    },
   
};

/*=======================NHÂN VIÊN=====================*/
const NhanVien_controls = {
  getallNV: (req, res) => {
      NhanVien.getalls((err, results) => {
          if (err) {
              console.error('Error getting Nhân Viên:', err);
              res.status(500).json({ error: 'Internal Server Error' });
          } else {
              res.json(results);
          }
      });
  },
  insert: (req, res) => {
    const data = req.body; 
    console.log('Request Body:', data); 
    NhanVien.inserts(data, (err, result) => {
      if (err) {
        console.error('Error inserting Nhân Viên:', err);
        res.status(500).json({ error: 'Internal Server Error' });
      } else {
        res.json({ message: 'Tạo thành công nhân viên', result });
      }
    });
  },
  
  deletePost: (req, res) => {
    const manv = req.params.manv;
  
    // Call the deletesPost function from NhanVien
    NhanVien.deletesPost(manv, (err, result) => {
      if (err) {
        // Check specific error messages and send appropriate responses
        if (err.message === 'Ràng buộc ngoại khóa với bảng phieunhap') {
          res.status(400).json({ error: 'Không thể xóa vì có ràng buộc ngoại khóa với bảng PhieuNhap' });
        } else if (err.message === 'Ràng buộc ngoại khóa với bảng phieuxuat') {
          res.status(400).json({ error: 'Không thể xóa vì có ràng buộc ngoại khóa với bảng PhieuXuat' });
        } else if (err.message === 'No employee found to delete') {
          res.status(404).json({ error: 'Không tìm thấy nhân viên để xóa' });
        } else {
          // For other errors, send a generic Internal Server Error response
          console.error('Error deleting Nhân Viên:', err);
          res.status(500).json({ error: 'Internal Server Error' });
        }
      } else {
        if (result.affectedRows > 0) {
          res.json({ message: 'Nhan vien deleted successfully', result });
        } else {
          res.status(404).json({ error: 'Không tìm thấy Nhân viên để xóa' });
        }
      }
    });
  },
  updatePost: (req, res) => {
    const { manv } = req.params; 
    const updatedData = req.body; 

    NhanVien.updatesPost(manv, updatedData, (err, result) => {
      if (err) {
        console.error('Error updating Nhân Viên:', err);
        res.status(500).json({ error: 'Internal Server Error' });
      } else {
        res.json({ message: 'Nhân Viên đã được cập nhật', result });
      }
    });
  },
 
};

/*=======================SẢN PHẨM=====================*/
const SanPham_controls = {
  getallSP: (req, res) => {
    const sokho = req.params.sokho; 
    SanPham.getalls(sokho, (err, results) => {
      if (err) {
        console.error('Error getting sanpham:', err);
        res.status(500).json({ error: 'Internal Server Error' });
      } else {
        res.json(results);
      }
    });
  },
  insert: (req, res) => {
    const data = req.body;
    console.log('Request Body:', data); 
    SanPham.inserts(data, (err, result) => {
      if (err) {
        console.error('Error inserting Sanpham:', err);
        res.status(500).json({ error: 'Internal Server Error' });
      } else {
        res.json({ message: 'Tạo thành công sản phẩm', result });
      }
    });
  },
 
  deletePost: (req, res) => {
    const masp = req.params.masp;
    SanPham.deletesPost(masp, (err, result) => {
      if (err) {
        console.error('Error deleting Sanpham:', err);
        if (err.message === 'Ràng buộc ngoại khóa với bảng DonHang') {
          res.status(400).json({ error: 'Không thể xóa vì có ràng buộc ngoại khóa với bảng DonHang' });
        } else if (err.message === 'Ràng buộc ngoại khóa với bảng PhieuNhap') {
          res.status(400).json({ error: 'Không thể xóa vì có ràng buộc ngoại khóa với bảng PhieuNhap' });
        } else if (err.message === 'Ràng buộc ngoại khóa với bảng PhieuXuat') {
          res.status(400).json({ error: 'Không thể xóa vì có ràng buộc ngoại khóa với bảng PhieuXuat' });
        } else {
          res.status(500).json({ error: 'Internal Server Error' });
        }
      } else {
        res.json({ message: 'Xóa thành công sản phẩm', result });
      }
    });
  },

  updatePost: (req, res) => {
    const masp = req.params.masp;
    const updatedData = req.body;
    SanPham.updatesPost(masp, updatedData, (err, result) => {
      if (err) {
        console.error('Error updating Sanpham:', err);
        res.status(500).json({ error: 'Internal Server Error' });
      } else {
        res.json({ message: 'Cập nhật thành công sản phẩm', result });
      }
    });
  },
};

/*=======================ĐƠN HÀNG=====================*/
const DonHang_controls = {
  getallDH: (req, res) => {
    DonHang.getalls((err, results) => {
      if (err) {
        console.error('Error getting all orders:', err);
        res.status(500).json({ error: 'Internal Server Error' });
      } else {
        res.json(results);
      }
    });
  },
  insert:  (req, res) => {
    const data = req.body;
    console.log('Request Body:', data); 
    DonHang.inserts(data, (err, result) => {
      if (err) {
        console.error('Error inserting DonHang:', err);
        res.status(500).json({ error: 'Internal Server Error' });
      } else {
        res.json({ message: 'Tạo thành công đơn hàng', result });
      }
    });
  },
 
  deletePost:  (req, res) => {
    const madh = req.params.madh;
    console.log('Mã đơn hàng cần xóa:', madh);
    DonHang.deletes(madh, (err, result) => {
      if (err) {
        console.error('Error deleting DonHang:', err);
        res.status(500).json({ error: 'Internal Server Error' });
      } else {
        res.json({ message: 'Đơn hàng đã được xóa thành công', result });
      }
    });
  },
  updatePost:  (req, res) => {
    const madh = req.params.madh;
    const updatedData = req.body;
    DonHang.updates(madh, updatedData, (err, result) => {
      if (err) {
        console.error('Error updating DonHang:', err);
        res.status(500).json({ error: 'Internal Server Error' });
      } else {
        res.json({ message: 'Cập nhật thành công đơn hàng', result });
      }
    });
  },
};

/*=======================KHO HÀNG=====================*/
const KhoHang_controls = {
  getAllWH: (req, res) => {
    KhoHang.getAll((err, results) => {
      if (err) {
        console.error('Error getting all warehouses:', err);
        res.status(500).json({ error: 'Internal Server Error' });
      } else {
        res.json(results);
      }
    });
  },
  insert: (req, res) => {
    const data = req.body;
    console.log('Request Body:', data);
    KhoHang.inserts(data, (err, result) => {
      if (err) {
        console.error('Error inserting KhoHang:', err);
        res.status(500).json({ error: 'Internal Server Error' });
      } else {
        res.json({ message: 'Tạo thành công kho hàng', result });
      }
    });
  },

  deletePost: (req, res) => {
    const sokho = req.params.sokho;
    KhoHang.deletes(sokho, (err, result) => {
      if (err) {
        console.error('Error deleting KhoHang:', err);
        if (err.message === 'Ràng buộc ngoại khóa với bảng SanPham') {
          res.status(400).json({ error: 'Không thể xóa vì có ràng buộc ngoại khóa với bảng SanPham' });
        } else {
          res.status(500).json({ error: 'Lỗi máy chủ nội bộ' });
        }
      } else {
        if (result.affectedRows > 0) {
          res.json({ message: 'Xóa thành công kho hàng', result });
        } else {
          res.status(404).json({ error: 'Không tìm thấy kho hàng để xóa' });
        }
      }
    });
  },

  updatePost: (req, res) => {
    const sokho = req.params.sokho;
    const updatedData = req.body;
    KhoHang.updates(sokho, updatedData, (err, result) => {
      if (err) {
        console.error('Error updating KhoHang:', err);
        res.status(500).json({ error: 'Internal Server Error' });
      } else {
        res.json({ message: 'Cập nhật thành công kho hàng', result });
      }
    });
  },

 
};

/*=======================PHIẾU NHẬP=====================*/
const PhieuNhap_controls = {
  getallPN: (req, res) => {
    PhieuNhap.getalls((err, results) => {
      if (err) {
        console.error('Error getting all orders:', err);
        res.status(500).json({ error: 'Internal Server Error' });
      } else {
        res.json(results);
      }
    });
  },
  insert:  (req, res) => {
    const data = req.body;
    console.log('Request Body:', data); 
    PhieuNhap.inserts(data, (err, result) => {
      if (err) {
        console.error('Error inserting PhieuNhap:', err);
        res.status(500).json({ error: 'Internal Server Error' });
      } else {
        res.json({ message: 'Tạo thành công phiếu nhập', result });
      }
    });
  },
 
  deletePost:  (req, res) => {
    const sopn = req.params.sopn;
    PhieuNhap.deletes(sopn, (err, result) => {
      if (err) {
        console.error('Error deleting PhieuNhap:', err);
        res.status(500).json({ error: 'Internal Server Error' });
      } else {
        res.json({ message: 'Xóa thành công phiếu nhập', result });
      }
    });
  },
  updatePost:  (req, res) => {
    const sopn = req.params.sopn;
    const updatedData = req.body;
    PhieuNhap.updates(sopn, updatedData, (err, result) => {
      if (err) {
        console.error('Error updating PhieuNhap:', err);
        res.status(500).json({ error: 'Internal Server Error' });
      } else {
        res.json({ message: 'Cập nhật thành công phiếu nhập', result });
      }
    });
  },
};

/*=======================PHIẾU XUẤT=====================*/
const PhieuXuat_controls = {
  getallPX: (req, res) => {
    PhieuXuat.getalls((err, results) => {
      if (err) {
        console.error('Error getting all orders:', err);
        res.status(500).json({ error: 'Internal Server Error' });
      } else {
        res.json(results);
      }
    });
  },
  insert:  (req, res) => {
    const data = req.body;
    console.log('Request Body:', data); 
    PhieuXuat.inserts(data, (err, result) => {
      if (err) {
        console.error('Error inserting PhieuXuat:', err);
        res.status(500).json({ error: 'Internal Server Error' });
      } else {
        res.json({ message: 'Tạo thành công phiếu xuất', result });
      }
    });
  },
 
  deletePost:  (req, res) => {
    const sopx = req.params.sopx;
    PhieuXuat.deletes(sopx, (err, result) => {
      if (err) {
        console.error('Error deleting PhieuXuat:', err);
        res.status(500).json({ error: 'Internal Server Error' });
      } else {
        res.json({ message: 'Xóa thành công phiếu xuất', result });
      }
    });
  },
  updatePost:  (req, res) => {
    const sopx = req.params.sopx;
    const updatedData = req.body;
    PhieuXuat.updates(sopx, updatedData, (err, result) => {
      if (err) {
        console.error('Error updating PhieuXuat:', err);
        res.status(500).json({ error: 'Internal Server Error' });
      } else {
        res.json({ message: 'Cập nhật thành công phiếu xuất', result });
      }
    });
  },
};

module.exports = {KhachHang_controls,NhanVien_controls,SanPham_controls,DonHang_controls,KhoHang_controls,PhieuNhap_controls,PhieuXuat_controls};
