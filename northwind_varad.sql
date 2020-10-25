-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 25, 2020 at 02:14 PM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.0.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `northwind_varad`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customerid` int(11) NOT NULL,
  `customername` varchar(20) NOT NULL,
  `customeraddress` varchar(50) NOT NULL,
  `customeremail` varchar(20) NOT NULL,
  `customercontactnumber` int(10) NOT NULL,
  `customerloginpassword` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customerid`, `customername`, `customeraddress`, `customeremail`, `customercontactnumber`, `customerloginpassword`) VALUES
(1, 'Abishek Deshpande', 'Aurangabad', 'abhi@gmail.com', 2147483647, 'Abhi@123'),
(2, 'Vishwajeet Deulkar', 'Aurangabad', 'vish@gmail.com', 2147483647, 'Vish@123'),
(3, 'Vivek Deulkar', 'Aurangabad', 'vivi@gmail.com', 2147483647, 'Vivi@123'),
(4, 'Varad Deulkar', 'Aurangabad', 'varad@gmail.com', 2147483647, 'Vsp@123');

-- --------------------------------------------------------

--
-- Table structure for table `plan`
--

CREATE TABLE `plan` (
  `planid` int(11) NOT NULL,
  `planname` varchar(20) NOT NULL,
  `plantype` varchar(20) NOT NULL,
  `plantariff` varchar(20) NOT NULL,
  `planvalidity` varchar(20) NOT NULL,
  `planrental` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `plan`
--

INSERT INTO `plan` (`planid`, `planname`, `plantype`, `plantariff`, `planvalidity`, `planrental`) VALUES
(1, 'ABC Plan1', 'BroadBand', '12500', '3 Months', '6464'),
(2, 'ABC Plan2', 'BroadBand', '14500', '6 Months', '3444'),
(3, 'ABC Plan3', 'BroadBand', '17500', '4 Months', '3644'),
(4, 'ABC Plan4', 'BroadBand', '27500', '3 Months', '5744');

-- --------------------------------------------------------

--
-- Table structure for table `subscription`
--

CREATE TABLE `subscription` (
  `subscriptionid` int(20) NOT NULL,
  `sub_customerid` int(11) NOT NULL,
  `sub_planid` int(11) NOT NULL,
  `sub_startdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subscription`
--

INSERT INTO `subscription` (`subscriptionid`, `sub_customerid`, `sub_planid`, `sub_startdate`) VALUES
(1, 1, 3, '2020-10-25 10:30:10'),
(2, 2, 2, '2020-10-25 10:30:36'),
(3, 3, 4, '2020-10-25 10:31:06'),
(4, 4, 2, '2020-10-25 10:31:12');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customerid`);

--
-- Indexes for table `plan`
--
ALTER TABLE `plan`
  ADD PRIMARY KEY (`planid`);

--
-- Indexes for table `subscription`
--
ALTER TABLE `subscription`
  ADD PRIMARY KEY (`subscriptionid`),
  ADD KEY `sub_customerid` (`sub_customerid`),
  ADD KEY `sub_planid` (`sub_planid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `customerid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `plan`
--
ALTER TABLE `plan`
  MODIFY `planid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `subscription`
--
ALTER TABLE `subscription`
  MODIFY `subscriptionid` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `subscription`
--
ALTER TABLE `subscription`
  ADD CONSTRAINT `subscription_ibfk_1` FOREIGN KEY (`sub_customerid`) REFERENCES `customer` (`customerid`),
  ADD CONSTRAINT `subscription_ibfk_2` FOREIGN KEY (`sub_planid`) REFERENCES `plan` (`planid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
