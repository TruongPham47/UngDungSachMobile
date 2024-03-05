const express = require('express');
const   {KhachHang_controls,NhanVien_controls,SanPham_controls,DonHang_controls, KhoHang_controls,PhieuNhap_controls,PhieuXuat_controls} = require('../nodejs/control');

const route = express.Router();

/*=======================KHÁCH HÀNG=====================*/
route.get('/getallKH',KhachHang_controls.getall);
route.post('/insertKH', KhachHang_controls.insert);
route.post('/deleteKH/:makh', KhachHang_controls.deletePost);
route.post('/updateKH/:makh', KhachHang_controls.updatePost);

/*=======================NHÂN VIÊN=====================*/
route.get('/getallNV',NhanVien_controls.getallNV);
route.post('/insertNV', NhanVien_controls.insert);
route.post('/deleteNV/:manv', NhanVien_controls.deletePost);
route.post('/updateNV/:manv', NhanVien_controls.updatePost);

/*=======================SẢN PHẨM=====================*/
route.get('/getallSP/:sokho',SanPham_controls.getallSP);
route.post('/insertSP', SanPham_controls.insert);
route.post('/deleteSP/:masp', SanPham_controls.deletePost);
route.post('/updateSP/:masp', SanPham_controls.updatePost);

/*=======================ĐƠN HÀNG=====================*/
route.get('/getallDH',DonHang_controls.getallDH);
route.post('/insertDH', DonHang_controls.insert);
route.post('/deleteDH/:madh', DonHang_controls.deletePost);
route.post('/updateDH/:madh', DonHang_controls.updatePost);

/*=======================KHO HÀNG=====================*/
route.get('/getallWH',KhoHang_controls.getAllWH);
route.post('/insertWH', KhoHang_controls.insert);
route.post('/deleteWH/:sokho', KhoHang_controls.deletePost);
route.post('/updateWH/:sokho', KhoHang_controls.updatePost);

/*=======================PHIẾU NHẬP=====================*/
route.get('/getallPN',PhieuNhap_controls.getallPN);
route.post('/insertPN', PhieuNhap_controls.insert);
route.post('/deletePN/:sopn', PhieuNhap_controls.deletePost);
route.post('/updatePN/:sopn', PhieuNhap_controls.updatePost);

/*=======================PHIẾU XUẤT=====================*/
route.get('/getallPX',PhieuXuat_controls.getallPX);
route.post('/insertPX', PhieuXuat_controls.insert);
route.post('/deletePX/:sopx', PhieuXuat_controls.deletePost);
route.post('/updatePX/:sopx', PhieuXuat_controls.updatePost);


module.exports=route;