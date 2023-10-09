-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:8889
-- Tiempo de generación: 09-10-2023 a las 15:37:39
-- Versión del servidor: 5.7.39
-- Versión de PHP: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `Prueba`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `passenger`
--

CREATE TABLE `passenger` (
  `id_passenger` int(11) NOT NULL,
  `date_of_birth` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `passenger`
--

INSERT INTO `passenger` (`id_passenger`, `date_of_birth`, `name`, `surname`) VALUES
(100, '1998-05-10 00:00:00.000000', 'Antonio', 'Perez'),
(101, '1998-05-10 00:00:00.000000', 'Luis', 'Martinez'),
(102, '1998-05-10 00:00:00.000000', 'Pepe', 'Perez');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `schedule`
--

CREATE TABLE `schedule` (
  `id_schedule` int(11) NOT NULL,
  `time` datetime(6) DEFAULT NULL,
  `id_station` int(11) DEFAULT NULL,
  `id_train` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `schedule`
--

INSERT INTO `schedule` (`id_schedule`, `time`, `id_station`, `id_train`) VALUES
(203, '2023-10-18 12:34:11.000000', 10, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `station`
--

CREATE TABLE `station` (
  `id_station` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `station`
--

INSERT INTO `station` (`id_station`, `name`) VALUES
(10, 'Granada'),
(11, 'Cadiz');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ticket`
--

CREATE TABLE `ticket` (
  `id_ticket` int(11) NOT NULL,
  `id_passenger` int(11) DEFAULT NULL,
  `id_train` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `ticket`
--

INSERT INTO `ticket` (`id_ticket`, `id_passenger`, `id_train`) VALUES
(1000, 100, 1),
(1001, 101, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `train`
--

CREATE TABLE `train` (
  `id_train` int(11) NOT NULL,
  `seats` int(11) DEFAULT NULL,
  `stations` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `train`
--

INSERT INTO `train` (`id_train`, `seats`, `stations`) VALUES
(1, 60, 20),
(2, 60, 25),
(3, 60, 21);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `passenger`
--
ALTER TABLE `passenger`
  ADD PRIMARY KEY (`id_passenger`);

--
-- Indices de la tabla `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`id_schedule`),
  ADD KEY `FKfmp4u7ty7v0ni6hwgaeki6d4a` (`id_station`),
  ADD KEY `FK2sknriees2dtw0h98suaxvxtr` (`id_train`);

--
-- Indices de la tabla `station`
--
ALTER TABLE `station`
  ADD PRIMARY KEY (`id_station`);

--
-- Indices de la tabla `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`id_ticket`),
  ADD UNIQUE KEY `UK_k4g6f4t87pyw2c0g396xa36jx` (`id_passenger`),
  ADD KEY `FK1mct701sv1cyw5maqweg8lqbn` (`id_train`);

--
-- Indices de la tabla `train`
--
ALTER TABLE `train`
  ADD PRIMARY KEY (`id_train`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `passenger`
--
ALTER TABLE `passenger`
  MODIFY `id_passenger` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=103;

--
-- AUTO_INCREMENT de la tabla `schedule`
--
ALTER TABLE `schedule`
  MODIFY `id_schedule` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=204;

--
-- AUTO_INCREMENT de la tabla `station`
--
ALTER TABLE `station`
  MODIFY `id_station` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `ticket`
--
ALTER TABLE `ticket`
  MODIFY `id_ticket` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1002;

--
-- AUTO_INCREMENT de la tabla `train`
--
ALTER TABLE `train`
  MODIFY `id_train` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `schedule`
--
ALTER TABLE `schedule`
  ADD CONSTRAINT `FK2sknriees2dtw0h98suaxvxtr` FOREIGN KEY (`id_train`) REFERENCES `train` (`id_train`),
  ADD CONSTRAINT `FKfmp4u7ty7v0ni6hwgaeki6d4a` FOREIGN KEY (`id_station`) REFERENCES `station` (`id_station`);

--
-- Filtros para la tabla `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `FK1mct701sv1cyw5maqweg8lqbn` FOREIGN KEY (`id_train`) REFERENCES `train` (`id_train`),
  ADD CONSTRAINT `FKq2a5lvhfo0ko6sc1em9d2ejk0` FOREIGN KEY (`id_passenger`) REFERENCES `passenger` (`id_passenger`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
