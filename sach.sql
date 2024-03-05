-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jan 26, 2024 at 04:46 AM
-- Server version: 8.0.31
-- PHP Version: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sach`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `username` varchar(30) COLLATE utf8mb3_unicode_ci NOT NULL,
  `password` varchar(30) COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`username`, `password`) VALUES
('teo', '123');

-- --------------------------------------------------------

--
-- Table structure for table `donhang`
--

DROP TABLE IF EXISTS `donhang`;
CREATE TABLE IF NOT EXISTS `donhang` (
  `madh` varchar(10) COLLATE utf8mb3_unicode_ci NOT NULL,
  `ngaydathang` date NOT NULL,
  `ngaygiaohang` date NOT NULL,
  `soluong` int NOT NULL,
  `tongtien` float NOT NULL,
  `makh` varchar(10) COLLATE utf8mb3_unicode_ci NOT NULL,
  `masp` varchar(10) COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`madh`),
  KEY `FK_KHACHHANG_DONHANG` (`makh`),
  KEY `FK_SANPHAM_DONHANG` (`masp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Dumping data for table `donhang`
--

INSERT INTO `donhang` (`madh`, `ngaydathang`, `ngaygiaohang`, `soluong`, `tongtien`, `makh`, `masp`) VALUES
('dh01', '2024-01-06', '2024-01-06', 500, 900000, 'kh01', 'sp01'),
('dh02', '2024-01-17', '2024-01-22', 50, 20000, 'kh01', 'sp01'),
('dh9', '2024-01-06', '2024-01-06', 11111, 444000, 'kh01', 'sp01');

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

DROP TABLE IF EXISTS `khachhang`;
CREATE TABLE IF NOT EXISTS `khachhang` (
  `makh` varchar(10) COLLATE utf8mb3_unicode_ci NOT NULL,
  `tenkh` varchar(30) COLLATE utf8mb3_unicode_ci NOT NULL,
  `diachikh` varchar(30) COLLATE utf8mb3_unicode_ci NOT NULL,
  `emailkh` varchar(30) COLLATE utf8mb3_unicode_ci NOT NULL,
  `sdtkh` int NOT NULL,
  PRIMARY KEY (`makh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Dumping data for table `khachhang`
--

INSERT INTO `khachhang` (`makh`, `tenkh`, `diachikh`, `emailkh`, `sdtkh`) VALUES
('kh01', 'Nguyễn Hoàng Nghĩaa', '248 Cao Lỗ, P4, Q8', 'nghia123@gmail.com', 123456789),
('kh02', 'Phạm Văn Bi', '2 Gò Dầu, Tân Quý. Tân Phú', 'A456@gmail.com', 367722222);

-- --------------------------------------------------------

--
-- Table structure for table `khohang`
--

DROP TABLE IF EXISTS `khohang`;
CREATE TABLE IF NOT EXISTS `khohang` (
  `sokho` varchar(10) COLLATE utf8mb3_unicode_ci NOT NULL,
  `tongsoluong` int NOT NULL,
  `soluongnhap` int NOT NULL,
  `soluongxuat` int NOT NULL,
  PRIMARY KEY (`sokho`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Dumping data for table `khohang`
--

INSERT INTO `khohang` (`sokho`, `tongsoluong`, `soluongnhap`, `soluongxuat`) VALUES
('khoA', 1000, 3000, 100),
('khoB', 1300, 1000, 200);

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
CREATE TABLE IF NOT EXISTS `nhanvien` (
  `manv` varchar(10) COLLATE utf8mb3_unicode_ci NOT NULL,
  `tennv` varchar(30) COLLATE utf8mb3_unicode_ci NOT NULL,
  `diachinv` varchar(30) COLLATE utf8mb3_unicode_ci NOT NULL,
  `emailnv` varchar(30) COLLATE utf8mb3_unicode_ci NOT NULL,
  `sdtnv` int NOT NULL,
  PRIMARY KEY (`manv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`manv`, `tennv`, `diachinv`, `emailnv`, `sdtnv`) VALUES
('66', 'ttt', 'rr', 'dede3', 33),
('nv03', 'Nguyễn Minh Trường', '250 Cao Lỗ, phường 4, quận 8', 'hieu333@gmail.com.vn', 123999991),
('nv04', 'Nguyễn Hoàng Nghĩa', '248 Cao Lỗ, P4, Q8', 'nghia123@gmail.com', 123456789);

-- --------------------------------------------------------

--
-- Table structure for table `phieunhap`
--

DROP TABLE IF EXISTS `phieunhap`;
CREATE TABLE IF NOT EXISTS `phieunhap` (
  `sopn` varchar(10) COLLATE utf8mb3_unicode_ci NOT NULL,
  `ngaypn` date NOT NULL,
  `soluongpn` int NOT NULL,
  `masp` varchar(10) COLLATE utf8mb3_unicode_ci NOT NULL,
  `manv` varchar(10) COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`sopn`),
  KEY `FK_PHIEUNHAP_SANPHAM` (`masp`),
  KEY `FK_PHIEUNHAP_NHANVIEN` (`manv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Dumping data for table `phieunhap`
--

INSERT INTO `phieunhap` (`sopn`, `ngaypn`, `soluongpn`, `masp`, `manv`) VALUES
('pn01', '2024-01-06', 400, 'sp01', 'nv04'),
('pn02', '2024-01-09', 500, 'sp01', 'nv03'),
('pn04', '2024-01-08', 500, 'sp01', 'nv03'),
('pn05', '2222-02-02', 444, 'sp01', 'nv03'),
('pn09', '2024-01-08', 500, 'sp01', 'nv03'),
('pn6', '2024-01-08', 500, 'sp01', 'nv03');

-- --------------------------------------------------------

--
-- Table structure for table `phieuxuat`
--

DROP TABLE IF EXISTS `phieuxuat`;
CREATE TABLE IF NOT EXISTS `phieuxuat` (
  `sopx` varchar(10) COLLATE utf8mb3_unicode_ci NOT NULL,
  `ngaypx` date NOT NULL,
  `soluongpx` int NOT NULL,
  `masp` varchar(10) COLLATE utf8mb3_unicode_ci NOT NULL,
  `manv` varchar(10) COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`sopx`),
  KEY `FK_PHIEUXUAT_SANPHAM` (`masp`),
  KEY `FK_PHIEUXUAT_NHANVIEN` (`manv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Dumping data for table `phieuxuat`
--

INSERT INTO `phieuxuat` (`sopx`, `ngaypx`, `soluongpx`, `masp`, `manv`) VALUES
('px01', '2024-01-07', 50, 'sp01', 'nv04'),
('px02', '2024-01-07', 20, 'sp02', 'nv04');

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

DROP TABLE IF EXISTS `sanpham`;
CREATE TABLE IF NOT EXISTS `sanpham` (
  `masp` varchar(10) COLLATE utf8mb3_unicode_ci NOT NULL,
  `tensp` varchar(30) COLLATE utf8mb3_unicode_ci NOT NULL,
  `giasp` float NOT NULL,
  `soluong` int NOT NULL,
  `loaisach` varchar(20) COLLATE utf8mb3_unicode_ci NOT NULL,
  `tentacgia` varchar(20) COLLATE utf8mb3_unicode_ci NOT NULL,
  `sokho` varchar(10) COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`masp`),
  KEY `FK_SANPHAM_KHOHANG` (`sokho`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`masp`, `tensp`, `giasp`, `soluong`, `loaisach`, `tentacgia`, `sokho`) VALUES
('sp01', 'Conan', 78000, 50, 'Truyện', 'Aoyama Gosho', 'khoA'),
('sp02', 'Toán Cao Cấp', 80000, 50, 'Sách giáo khoa', 'Vũ Tiến Việt', 'khoB'),
('sp05', 'ww', 222, 34, 'lsk', 'sd', 'khoA'),
('sp06', 'Toán A1', 80000, 100, 'Sách giáo khoa', 'Vũ Tiến Việt', 'khoA');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `donhang`
--
ALTER TABLE `donhang`
  ADD CONSTRAINT `FK_KHACHHANG_DONHANG` FOREIGN KEY (`makh`) REFERENCES `khachhang` (`makh`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_SANPHAM_DONHANG` FOREIGN KEY (`masp`) REFERENCES `sanpham` (`masp`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `FK_PHIEUNHAP_NHANVIEN` FOREIGN KEY (`manv`) REFERENCES `nhanvien` (`manv`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_PHIEUNHAP_SANPHAM` FOREIGN KEY (`masp`) REFERENCES `sanpham` (`masp`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `phieuxuat`
--
ALTER TABLE `phieuxuat`
  ADD CONSTRAINT `FK_PHIEUXUAT_NHANVIEN` FOREIGN KEY (`manv`) REFERENCES `nhanvien` (`manv`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_PHIEUXUAT_SANPHAM` FOREIGN KEY (`masp`) REFERENCES `sanpham` (`masp`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `FK_SANPHAM_KHOHANG` FOREIGN KEY (`sokho`) REFERENCES `khohang` (`sokho`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
