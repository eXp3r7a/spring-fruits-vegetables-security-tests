INSERT INTO products (category, name) VALUES
('Vegetable', 'Tomato'),
('Vegetable', 'Cucumber'),
('Fruit', 'Banana'),
('Fruit', 'Apple'),
('Fruit', 'Grapes'),
('Vegetable', 'Carrot'),
('Vegetable', 'Patato'),
('Vegetable', 'Onion'),
('Vegetable', 'Pepper'),
('Spices', 'Paprika'),
('Spices', 'Salt'),
('Spices', 'Rosemary'),
('Spices', 'Garlic powder'),
('Spices', 'Ginger'),
('Spices', 'Cardamon');

---- phpMyAdmin SQL Dump
---- version 5.2.1
---- https://www.phpmyadmin.net/
----
---- Host: 127.0.0.1
---- Generation Time: Apr 25, 2025 at 01:22 PM
---- Server version: 10.4.32-MariaDB
---- PHP Version: 8.0.30
--
--SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
--START TRANSACTION;
--SET time_zone = "+00:00";
--
--
--/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
--/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
--/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
--/*!40101 SET NAMES utf8mb4 */;
--
----
---- Database: `db_fruits_vegetables`
----
--
---- --------------------------------------------------------
--
----
---- Table structure for table `products`
----
--
--CREATE TABLE `products` (
--  `product_id` bigint(20) NOT NULL,
--  `category` varchar(255) NOT NULL,
--  `name` varchar(255) NOT NULL
--) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
--
----
---- Dumping data for table `products`
----
--
--INSERT INTO `products` (`product_id`, `category`, `name`) VALUES
--(1, 'Vegetable', 'Tomato'),
--(2, 'Vegetable', 'Cucumber'),
--(3, 'Fruit', 'Banana'),
--(4, 'Fruit', 'Apple'),
--(5, 'Fruit', 'Grapes'),
--(6, 'Vegetable', 'Carrot'),
--(7, 'Vegetable', 'Patato'),
--(8, 'Vegetable', 'Onion'),
--(9, 'Vegetable', 'Pepper'),
--(10, 'Spices', 'Paprika'),
--(11, 'Spices', 'Salt'),
--(12, 'Spices', 'Rosemary'),
--(13, 'Spices', 'Garlic powder'),
--(14, 'Spices', 'Ginger'),
--(15, 'Spices', 'Cardamon');
--
---- --------------------------------------------------------
--
----
---- Table structure for table `recipes`
----
--
--CREATE TABLE `recipes` (
--  `recipe_id` bigint(20) NOT NULL,
--  `category` varchar(255) NOT NULL,
--  `description` varchar(255) NOT NULL,
--  `name` varchar(255) NOT NULL
--) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
--
----
---- Dumping data for table `recipes`
----
--
INSERT INTO recipes (recipe_id, category, description, name) VALUES
(1, 'Soup', 'Tomato soup with patatoes and spices', 'Tomato soup'),
(2, 'Salad', 'Salat with tomato, cucumber and white cheese ', 'Fresh salat'),
(3, 'Salad', 'Main with beef and vegetables ', 'Main with meal');
--
---- --------------------------------------------------------
--
----
---- Table structure for table `recipe_products`
----
--
--CREATE TABLE `recipe_products` (
--  `recipe_id` bigint(20) NOT NULL,
--  `product_id` bigint(20) NOT NULL
--) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
--
----
---- Dumping data for table `recipe_products`
----
--
INSERT INTO recipe_products (recipe_id, product_id) VALUES
(2, 1),
(2, 2),
(2, 8),
(2, 11),
(3, 1),
(3, 6),
(3, 7),
(3, 8),
(3, 9),
(3, 10),
(3, 11),
(3, 12),
(1, 1),
(1, 6),
(1, 7),
(1, 8),
(1, 11),
(1, 13);
--
---- --------------------------------------------------------
--
----
---- Table structure for table `users`
----
--
--CREATE TABLE `users` (
--  `user_id` bigint(20) NOT NULL,
--  `enabled` bit(1) NOT NULL,
--  `first_name` varchar(255) NOT NULL,
--  `last_name` varchar(255) NOT NULL,
--  `password` varchar(255) DEFAULT NULL,
--  `role` varchar(255) NOT NULL,
--  `username` varchar(255) NOT NULL
--) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
--
----
---- Dumping data for table `users`
----
--
INSERT INTO users (user_id, enabled, first_name, last_name, password, role, username) VALUES
(1, '1', 'Ivan', 'Ivanov', '$2a$12$GgiA6MA8N.F3lIceFeJbK.Ebi1vUNxWE8bvQcTRumeSBrQEXkktcm', 'USER', 'ivannn');
--
----
---- Indexes for dumped tables
----
--
----
---- Indexes for table `products`
----
--ALTER TABLE `products`
--  ADD PRIMARY KEY (`product_id`);
--
----
---- Indexes for table `recipes`
----
--ALTER TABLE `recipes`
--  ADD PRIMARY KEY (`recipe_id`);
--
----
---- Indexes for table `recipe_products`
----
--ALTER TABLE recipe_products
--  ADD KEY FKsjygf1hyuvredgtmgunbwg1u9 (product_id),
--  ADD KEY FKssj43103hudcp3u3x7b8twnd5 (recipe_id);
--
----
---- Indexes for table `users`
----
--ALTER TABLE `users`
--  ADD PRIMARY KEY (`user_id`);
--
----
---- AUTO_INCREMENT for dumped tables
----
--
----
---- AUTO_INCREMENT for table `products`
----
--ALTER TABLE products
--  MODIFY product_id bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
----
---- AUTO_INCREMENT for table `recipes`
----
--ALTER TABLE `recipes`
--  MODIFY `recipe_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
----
---- AUTO_INCREMENT for table `users`
----
--ALTER TABLE `users`
--  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
----
---- Constraints for dumped tables
----
--
----
---- Constraints for table `recipe_products`
----
--ALTER TABLE recipe_products
--  ADD CONSTRAINT FKsjygf1hyuvredgtmgunbwg1u9 FOREIGN KEY (product_id) REFERENCES products (product_id),
--  ADD CONSTRAINT FKssj43103hudcp3u3x7b8twnd5 FOREIGN KEY (recipe_id) REFERENCES recipes (recipe_id);
--COMMIT;
--
--/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
--/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
--/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
