-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:8889
-- Tiempo de generación: 12-12-2023 a las 19:35:39
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
-- Estructura de tabla para la tabla `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `date_of_birth` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `admin`
--

INSERT INTO `admin` (`id`, `email`, `password`, `name`, `surname`, `date_of_birth`) VALUES
(10, 'ninePeters2@gmail.com', '$2a$10$oJMv1b7uYmzeyQMRA/Twx.BOY0L0hHP1a42vVL1ToufvSAW8rze/y', 'Nine', 'Peters', '1998-11-11'),
(23, 'alcaraz@gmail.com', '$2a$10$LMNVX5KSgZFqiV7nu1tig.0ZfG8wDZk/V7hu4ol.HI.9a1QTqaPCW', 'Carlo', 'Alcaraz', '2000-12-25'),
(46, 'admin@example.com', '$2a$10$AXAF4oYQwnnOvtKe6uLjxOG57zw7gm0oJ/Z0BZnVrbEBnV1jiEeJO', 'admin', 'adminSurname', '1998-06-19'),
(48, 'example@email.com', '$2a$10$9Qd6.mgjKjCpLjB5wAL3W.wHVydrlmnX4Q9Wf6ZnOgqzGuB4WOY72', 'Luis', 'Orts Ferrer', '2002-02-11'),
(49, 'djferrer.orts1@gmail.com', '$2a$10$KPmg3kOiqhQ7GJnaJsCV4OlR0dO787QiZ0HLLM2OOCBthckabIVxa', 'David José', 'Orts Ferrer', '2023-12-21');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `admin_roles`
--

CREATE TABLE `admin_roles` (
  `admin_id` int(11) NOT NULL,
  `roles_id` int(11) DEFAULT NULL,
  `rol_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `admin_roles`
--

INSERT INTO `admin_roles` (`admin_id`, `roles_id`, `rol_id`) VALUES
(10, NULL, 9),
(23, NULL, 22),
(46, NULL, 45),
(48, NULL, 47),
(49, NULL, 48);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `passenger`
--

CREATE TABLE `passenger` (
  `id_passenger` int(11) NOT NULL,
  `id_admin` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `passenger`
--

INSERT INTO `passenger` (`id_passenger`, `id_admin`) VALUES
(501, 23),
(505, 23),
(508, 23),
(503, 48),
(504, 48),
(506, 49),
(507, 49);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id`, `name`) VALUES
(3, 'USER'),
(4, 'USER'),
(5, 'USER'),
(6, 'USER'),
(7, 'USER'),
(8, 'USER'),
(9, 'ADMIN'),
(10, 'ADMIN'),
(11, 'USER'),
(12, 'USER'),
(13, 'USER'),
(14, 'USER'),
(15, 'USER'),
(16, 'USER'),
(17, 'USER'),
(18, 'USER'),
(19, 'USER'),
(20, 'USER'),
(21, 'USER'),
(22, 'USER'),
(23, 'ADMIN'),
(24, 'ADMIN'),
(30, 'ADMIN'),
(31, 'ADMIN'),
(32, 'ADMIN'),
(33, 'ADMIN'),
(34, 'ADMIN'),
(35, 'ADMIN'),
(36, 'ADMIN'),
(37, 'ADMIN'),
(38, 'ADMIN'),
(39, 'ADMIN'),
(40, 'ADMIN'),
(41, 'ADMIN'),
(42, 'ADMIN'),
(43, 'ADMIN'),
(44, 'USER'),
(45, 'ADMIN'),
(46, 'USER'),
(47, 'USER'),
(48, 'USER');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `schedule`
--

CREATE TABLE `schedule` (
  `id_schedule` int(11) NOT NULL,
  `time` timestamp(6) NULL DEFAULT NULL,
  `id_station` int(11) DEFAULT NULL,
  `id_train` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `schedule`
--

INSERT INTO `schedule` (`id_schedule`, `time`, `id_station`, `id_train`) VALUES
(214, '2023-12-14 22:27:00.000000', 247, 3464),
(215, '2023-11-22 03:03:00.162000', 34, 3466),
(216, '2023-12-12 19:00:00.000000', 252, 3465),
(220, '2023-12-20 14:04:00.000000', 248, 3468),
(222, '2023-12-30 16:14:00.000000', 264, 3474),
(223, '2023-12-11 21:20:00.000000', 269, 3486);

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
(34, 'Zaragoza'),
(247, 'Dos hermanas'),
(248, 'Valencia'),
(249, 'Cadiz'),
(250, 'Segovia'),
(251, 'Santiago'),
(252, 'Cordoba'),
(253, 'Badajoz'),
(254, 'Jaen'),
(255, 'Huelva'),
(256, 'Benidorm'),
(257, 'Sevilla'),
(258, 'Alicante'),
(260, 'San Sebastian'),
(261, 'Albacete'),
(262, 'Barcelona'),
(263, 'Tarragona'),
(264, 'Merida'),
(265, 'Caceres'),
(266, 'Avila'),
(267, 'Murcia'),
(268, 'Cartagena'),
(269, 'Almeria'),
(270, 'Ubeda'),
(271, 'Baeza');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ticket`
--

CREATE TABLE `ticket` (
  `id_ticket` int(11) NOT NULL,
  `id_passenger` int(11) NOT NULL,
  `id_train` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `ticket`
--

INSERT INTO `ticket` (`id_ticket`, `id_passenger`, `id_train`) VALUES
(91, 501, 3464),
(93, 503, 3464),
(94, 504, 3465),
(95, 505, 3465),
(96, 506, 3465),
(97, 507, 3474),
(98, 508, 3474);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `train`
--

CREATE TABLE `train` (
  `id_train` int(11) NOT NULL,
  `seats` int(11) DEFAULT NULL,
  `stations` int(11) DEFAULT NULL,
  `station_destination` int(11) DEFAULT NULL,
  `station_origin` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `train`
--

INSERT INTO `train` (`id_train`, `seats`, `stations`, `station_destination`, `station_origin`) VALUES
(3464, 60, 20, 249, 247),
(3465, 34, 23, 10, 252),
(3466, 36, 10, 248, 34),
(3467, 56, 23, 251, 250),
(3468, 34, 35, 34, 248),
(3469, 1, 23, 253, 254),
(3470, 2, 23, 256, 255),
(3474, 2, 1, 263, 264),
(3477, 23, 23, 250, 262),
(3485, 10, 34, 258, 263),
(3486, 15, 10, 266, 269);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKc0r9atamxvbhjjvy5j8da1kam` (`email`);

--
-- Indices de la tabla `admin_roles`
--
ALTER TABLE `admin_roles`
  ADD KEY `FK1bcjwl07gm1kcu8h3dhiyd3ol` (`admin_id`),
  ADD KEY `FK4vfnhmklair9aibr4sy0c9sho` (`roles_id`),
  ADD KEY `FKjsktfp0f41utne3idaolshbxo` (`rol_id`);

--
-- Indices de la tabla `passenger`
--
ALTER TABLE `passenger`
  ADD PRIMARY KEY (`id_passenger`),
  ADD KEY `id_admin` (`id_admin`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id`);

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
  ADD KEY `fk_id_train` (`id_train`);

--
-- Indices de la tabla `train`
--
ALTER TABLE `train`
  ADD PRIMARY KEY (`id_train`),
  ADD UNIQUE KEY `UKi7ht68tofpu7brtnm1se60w3d` (`station_origin`),
  ADD UNIQUE KEY `UKrwmldidnyro79ttfxntltdag5` (`station_destination`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT de la tabla `passenger`
--
ALTER TABLE `passenger`
  MODIFY `id_passenger` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=509;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT de la tabla `schedule`
--
ALTER TABLE `schedule`
  MODIFY `id_schedule` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=224;

--
-- AUTO_INCREMENT de la tabla `station`
--
ALTER TABLE `station`
  MODIFY `id_station` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=272;

--
-- AUTO_INCREMENT de la tabla `ticket`
--
ALTER TABLE `ticket`
  MODIFY `id_ticket` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=99;

--
-- AUTO_INCREMENT de la tabla `train`
--
ALTER TABLE `train`
  MODIFY `id_train` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3488;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `admin_roles`
--
ALTER TABLE `admin_roles`
  ADD CONSTRAINT `FK1bcjwl07gm1kcu8h3dhiyd3ol` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK4vfnhmklair9aibr4sy0c9sho` FOREIGN KEY (`roles_id`) REFERENCES `rol` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FKjsktfp0f41utne3idaolshbxo` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `passenger`
--
ALTER TABLE `passenger`
  ADD CONSTRAINT `id_admin` FOREIGN KEY (`id_admin`) REFERENCES `admin` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `schedule`
--
ALTER TABLE `schedule`
  ADD CONSTRAINT `FK2sknriees2dtw0h98suaxvxtr` FOREIGN KEY (`id_train`) REFERENCES `train` (`id_train`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FKfmp4u7ty7v0ni6hwgaeki6d4a` FOREIGN KEY (`id_station`) REFERENCES `station` (`id_station`);

--
-- Filtros para la tabla `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `FKq2a5lvhfo0ko6sc1em9d2ejk0` FOREIGN KEY (`id_passenger`) REFERENCES `passenger` (`id_passenger`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_id_train` FOREIGN KEY (`id_train`) REFERENCES `train` (`id_train`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `train`
--
ALTER TABLE `train`
  ADD CONSTRAINT `station_destination` FOREIGN KEY (`station_destination`) REFERENCES `station` (`id_station`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `station_origin` FOREIGN KEY (`station_origin`) REFERENCES `station` (`id_station`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
