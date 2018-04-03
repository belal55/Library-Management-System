-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 21, 2017 at 05:43 PM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hello`
--

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `bookid` int(20) NOT NULL,
  `bookname` varchar(100) NOT NULL,
  `author` varchar(50) NOT NULL,
  `copy` int(20) NOT NULL,
  `shelfno` varchar(20) NOT NULL,
  `courseId` int(20) NOT NULL,
  `dept` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`bookid`, `bookname`, `author`, `copy`, `shelfno`, `courseId`, `dept`) VALUES
(1, 'Java Programming Language', 'Ken Arnold, James Gosling, David Holmes', 4, 'A1', 1001, 'cse'),
(2, 'Schaum''s Outline Series (Data Structure)', 'Seymour Lipschutz', 8, 'A2', 1002, 'cse'),
(3, ' Algorithms Made Easy', 'Narasimha Karumanchi', 2, 'A3', 1003, 'cse'),
(4, 'Basic Circuit Analysis', 'K.V.V. Murthy and M.S.Kamath', 5, 'E1', 2001, 'eee'),
(5, 'Introductory Circuit Analysis', 'Boylestad', 10, 'E2', 2001, 'eee'),
(6, 'Electronic Device', 'Boylestad abrahm linkon', 10, 'E3', 2003, 'eee'),
(7, 'Learn C', 'Jhon Smith', 10, 'c1', 1004, 'cse'),
(8, 'Learn Assembly', 'Jhon Smith', 5, 'A1', 1008, 'cse'),
(10, 'Learn C Programming', 'Jhon clerk', 10, 'c1', 1004, 'cse'),
(13, 'Algorithms using c', 'seymour', 12, 'c1', 1003, 'cse'),
(14, 'Algorithms using cpp', 'jhon smith', 11, 'c1', 1003, 'cse'),
(15, 'Data Structue using cpp', 'Helin karlk', 12, 'c1', 1003, 'cse'),
(16, 'Database System concepts', 'Henry', 5, 'd1', 1007, 'cse'),
(17, 'Alternate Current analysis', 'Jecson Robert', 10, 'E1', 2002, 'eee'),
(18, 'Database for Beginners', 'Anthone clark', 11, 'd1', 1007, 'cse');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`bookid`),
  ADD KEY `courseId` (`courseId`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
