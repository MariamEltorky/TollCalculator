-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 11, 2023 at 07:14 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tollcalculator`
--

-- --------------------------------------------------------

--
-- Table structure for table `freevehicles`
--

CREATE TABLE `freevehicles` (
  `VehicleType` text NOT NULL,
  `ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `freevehicles`
--

INSERT INTO `freevehicles` (`VehicleType`, `ID`) VALUES
('Motorbike', 1),
('Tractor', 2),
('Emergency', 3),
('Diplomat', 4),
('Foreign', 5),
('Military', 6);

-- --------------------------------------------------------

--
-- Table structure for table `holidays`
--

CREATE TABLE `holidays` (
  `Id` int(11) NOT NULL,
  `DayMonth` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `holidays`
--

INSERT INTO `holidays` (`Id`, `DayMonth`) VALUES
(4000, '2013-01-01'),
(4001, '2013-03-28'),
(4002, '2013-03-29'),
(4003, '2013-04-01'),
(4004, '2013-04-30'),
(4005, '2013-05-01'),
(4006, '2013-05-08'),
(4007, '2013-05-09'),
(4008, '2013-06-05'),
(4009, '2013-06-06'),
(4010, '2013-06-21'),
(4011, '2013-11-01'),
(4012, '2013-12-24'),
(4013, '2013-12-25'),
(4014, '2013-12-26'),
(4015, '2013-12-31');

-- --------------------------------------------------------

--
-- Table structure for table `tollfee`
--

CREATE TABLE `tollfee` (
  `Id` int(11) NOT NULL,
  `StartTime` time(4) NOT NULL,
  `EndTime` time(4) NOT NULL,
  `Fee` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tollfee`
--

INSERT INTO `tollfee` (`Id`, `StartTime`, `EndTime`, `Fee`) VALUES
(5000, '06:00:00.0000', '06:29:59.0000', 8),
(5001, '06:30:00.0000', '06:59:59.0000', 13),
(5002, '07:00:00.0000', '07:59:59.0000', 18),
(5003, '08:00:00.0000', '08:29:59.0000', 13),
(5004, '08:30:00.0000', '14:59:59.0000', 8),
(5005, '15:00:00.0000', '15:29:59.0000', 13),
(5006, '15:30:00.0000', '16:59:59.0000', 18),
(5007, '17:00:00.0000', '17:59:59.0000', 13),
(5008, '18:00:00.0000', '18:29:59.0000', 8);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `freevehicles`
--
ALTER TABLE `freevehicles`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `holidays`
--
ALTER TABLE `holidays`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `tollfee`
--
ALTER TABLE `tollfee`
  ADD PRIMARY KEY (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
