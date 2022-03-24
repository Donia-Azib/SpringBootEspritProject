-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  jeu. 24 mars 2022 à 09:20
-- Version du serveur :  10.1.28-MariaDB
-- Version de PHP :  7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `bookstore`
--

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `reference` bigint(20) NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `commande_livres`
--

CREATE TABLE `commande_livres` (
  `commandes_reference` bigint(20) NOT NULL,
  `livres_reference` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demande`
--

CREATE TABLE `demande` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `titre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `feedback`
--

CREATE TABLE `feedback` (
  `id` bigint(20) NOT NULL,
  `commentaire` varchar(255) DEFAULT NULL,
  `note` int(11) NOT NULL,
  `livre_reference` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `offre_reference` bigint(20) DEFAULT NULL,
  `archiver` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `feedback`
--

INSERT INTO `feedback` (`id`, `commentaire`, `note`, `livre_reference`, `user_id`, `offre_reference`, `archiver`) VALUES
(32, 'Well this one turned out to be a winner!', 5, NULL, 4, 3, b'0'),
(33, 'Don’t miss it!', 3, NULL, 4, 3, b'0'),
(39, 'The Glass Hotel, set in Canada, is a terrific page-turner. The book kept my interest all the way through (which is something to say these days) ! The plot about a Ponzi scheme and it’s effects on many lives was quite interesting.', 5, 11, 4, NULL, b'1'),
(40, 'Good information to help the squeamish or violence-sensitive readers know to steer clear. It helps readers know what type of scenes they will experience in the book', 3, 11, 4, NULL, b'1'),
(41, 'The Glass Hotel, set in Canada, is a terrific page-turner. The book kept my interest all the way through (which is something to say these days) ! The plot about a Ponzi scheme and it’s effects on many lives was quite interesting.', 5, 11, 4, NULL, b'0'),
(42, 'The Glass Hotel, set in Canada, is a terrific page-turner. The book kept my interest all the way through (which is something to say these days) ! The plot about a Ponzi scheme and it’s effects on many lives was quite interesting.', 1, 11, 4, NULL, b'0'),
(43, 'Good information to help the squeamish or violence-sensitive readers know to steer clear. It helps readers know what type of scenes they will experience in the book', 5, 11, 4, NULL, b'0'),
(44, 'Good information to help the squeamish or violence-sensitive readers know to steer clear. It helps readers know what type of scenes they will experience in the book', 5, 11, 4, NULL, b'0'),
(45, 'Good information to help the squeamish or violence-sensitive readers know to steer clear. It helps readers know what type of scenes they will experience in the book calm', 5, 11, 4, NULL, b'0'),
(46, 'Good information to help the squeamish or violence-sensitive readers know to steer clear. It helps readers know what type of scenes they will experience in the book calm', 5, 11, 4, NULL, b'0'),
(47, 'Don’t miss it!', 3, NULL, 4, 3, b'0'),
(48, 'Don’t miss it!  dishonorable', 3, NULL, 4, 3, b'0'),
(49, 'Comprehensive book, but the indexing is wrong which makes finding the material difficult', 3, 14, 4, NULL, b'1'),
(50, 'The Glass Hotel, set in Canada, is a terrific page-turner. The book kept my interest all the way through (which is something to say these days) ! The plot about a Ponzi scheme and it’s effects on many lives was quite interesting.', 1, 14, 4, NULL, b'1'),
(51, NULL, -1, NULL, NULL, NULL, b'1'),
(52, 'Don’t miss it! it is so pleased', 3, NULL, 4, 4, b'0'),
(53, 'Don’t miss it! it is so peaceful', 3, NULL, 4, 4, b'0'),
(54, 'This was used for school and it was the book the teacher required.', 3, 17, 4, NULL, b'0'),
(55, 'warning! way way to many characters and storyliness tripping other in this jumbled mess.I hate it!', 3, 17, 4, NULL, b'0');

-- --------------------------------------------------------

--
-- Structure de la table `feed_back_stat`
--

CREATE TABLE `feed_back_stat` (
  `id` bigint(20) NOT NULL,
  `date` date DEFAULT NULL,
  `nb_negative_comments_book` int(11) NOT NULL,
  `nb_negative_comments_offer` int(11) NOT NULL,
  `nb_positive_comments_book` int(11) NOT NULL,
  `nb_positive_comments_offer` int(11) NOT NULL,
  `nb_rejected_comments_book` int(11) NOT NULL,
  `nb_rejected_comments_offer` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `feed_back_stat`
--

INSERT INTO `feed_back_stat` (`id`, `date`, `nb_negative_comments_book`, `nb_negative_comments_offer`, `nb_positive_comments_book`, `nb_positive_comments_offer`, `nb_rejected_comments_book`, `nb_rejected_comments_offer`) VALUES
(2, '2021-06-08', 0, 1, 1, 1, 1, 0),
(3, '2021-07-10', 1, 1, 3, 3, 1, 0);

-- --------------------------------------------------------

--
-- Structure de la table `livre`
--

CREATE TABLE `livre` (
  `reference` bigint(20) NOT NULL,
  `auteur` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `disponibilite` bit(1) NOT NULL,
  `prix` float NOT NULL,
  `quantite` int(11) NOT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `offre_reference` bigint(20) DEFAULT NULL,
  `wish_list_id` bigint(20) DEFAULT NULL,
  `note` double NOT NULL,
  `nb_comment` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `livre`
--

INSERT INTO `livre` (`reference`, `auteur`, `description`, `disponibilite`, `prix`, `quantite`, `titre`, `offre_reference`, `wish_list_id`, `note`, `nb_comment`) VALUES
(9, 'Gary Chapman, G. Chapman, Paul White', 'This description may be from another edition of this product. - Over 11 million copies sold - #1 New York Times Bestseller for 8 years running.', b'1', 20.16, 1, 'The Five Love Languages', NULL, NULL, 5, 0),
(10, 'Stormie Omartian', 'Omartian shares how wives candevelop a deeper relationship with their husbands by praying for them. Packedwith practical advice on praying for specific areas, including decisionmaking,fears.', b'1', 16.98, 2, 'The Power of a Praying Wife', NULL, NULL, 3, 0),
(11, 'Emily St. John Mandel', 'From the award-winning author of Station Eleven, an exhilarating novel set at the glittering intersection of two seemingly disparate events–a massive Ponzi scheme collapse and the mysterious disappearance of a woman from a ship at sea.', b'1', 20, 3, 'The Glass Hotel', 3, NULL, 4.1875, 8),
(12, 'Raven Leilani', 'Edie is stumbling her way through her twenties—sharing a subpar apartment in Bushwick, clocking in and out of her admin job, making a series of inappropriate sexual choices.', b'1', 20, 5, 'Luster', NULL, NULL, 4, 0),
(13, 'Natasha Trethewey', 'A chillingly personal and exquisitely wrought memoir of a daughter reckoning with the brutal murder of her mother at the hands of her former stepfather, and the moving, intimate story of a poet coming into her own in the wake of a tragedy', b'1', 18.2, 1, 'Memorial Drive: A Daughter\'s Memoir ', 4, NULL, 2.5, 0),
(14, 'Alan Beaulieu', 'Updated for the latest database management systems ,this introductory guide will get you up and running with SQL quickly. Whether you need to write database applications,Learning SQL, Second Edition, will help you easily master all the SQL fundamentals.', b'1', 76.56, 2, 'Learning SQL 2e', 4, NULL, 3, 1),
(15, 'Thomas Connolly , Carolyn Begg ', 'Database Systems is ideal for a one- or two-term course in database management or database design in an undergraduate or graduate level course. With its comprehensive coverage, this book can also be used as a reference for IT professionals', b'1', 50, 4, 'Database Systems: A Practical Approach to Design, Implementation, and Management', NULL, NULL, -1, 0),
(17, 'Lewis Carroll', 'Alice\'s Adventures in Wonderland is an 1865 novel written by English author Charles Lutwidge Dodgson under the pseudonym Lewis Carroll. It tells of a girl named Alice falling through a rabbit hole into a fantasy world populated by peculiar.', b'1', 60, 2, 'Alice\'s Adventures in Wonderland', NULL, NULL, 3, 2);

-- --------------------------------------------------------

--
-- Structure de la table `livre_users`
--

CREATE TABLE `livre_users` (
  `livre_reference` bigint(20) NOT NULL,
  `users_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `offre`
--

CREATE TABLE `offre` (
  `reference` bigint(20) NOT NULL,
  `date_debut` varchar(255) DEFAULT NULL,
  `date_fin` varchar(255) DEFAULT NULL,
  `pourcentage` float NOT NULL,
  `quantite` int(11) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `diponibilite` bit(1) NOT NULL,
  `prix_total` double NOT NULL,
  `prix_pourcentage` double NOT NULL,
  `nb_comment` int(11) NOT NULL,
  `note` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `offre`
--

INSERT INTO `offre` (`reference`, `date_debut`, `date_fin`, `pourcentage`, `quantite`, `user_id`, `diponibilite`, `prix_total`, `prix_pourcentage`, `nb_comment`, `note`) VALUES
(3, '2021-06-30', '2021-07-30', 25, 2, NULL, b'1', 20, 15, 4, 3.5),
(4, '2021-07-10', '2021-07-30', 25, 2, NULL, b'1', 94.7599983215332, 71.0699987411499, 2, 3);

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `reference` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(4, 'ROLE_ADMIN'),
(5, 'ROLE_MANAGEMENT'),
(6, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `wish_list_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `email`, `first_name`, `last_name`, `password`, `username`, `wish_list_id`) VALUES
(1, 'admin@admin', 'admin', 'admin', '$2a$10$cobDk1TNweTe44OdEFz/zOzBItjjxK7KMor0PtSbo1DuGZb/zEwJK', 'admin', NULL),
(2, 'donia.man@store.com', 'Donia', 'Azib', '$2a$10$8E6SZbvPl7aW..QtkTP2TeFyAPjs/oV6oE6P44cOWJwx6kSBSd8oW', 'doniaMan', NULL),
(3, 'naceur.admin@store.com', 'Naceur', 'ben Yedder', '$2a$10$N4cpcVYz9W.UUWB9gPWbJeA8/IcufPDlPmIzorbhIPvXTL0odGhKW', 'NaceurAdmin', NULL),
(4, 'forma.user@store.com', 'Forma', 'Lab', '$2a$10$m7Bor8w34Bc0pu5byx8vIOxcRhls7K.9xmpBDnvxnIEr0FkVvNuYy', 'FormaLab', NULL),
(5, 'yass.user@store.com', 'Forma', 'Lab', '$2a$10$y95EbZ8tJOSsE4KectoEAOX4Ahl1Myd.bdbfh9.qUtzaKZkz.wZb.', 'yassine', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `users_livres`
--

CREATE TABLE `users_livres` (
  `user_id` bigint(20) NOT NULL,
  `livres_reference` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(1, 6),
(2, 5),
(3, 4),
(4, 6),
(5, 5);

-- --------------------------------------------------------

--
-- Structure de la table `wish_list`
--

CREATE TABLE `wish_list` (
  `id` bigint(20) NOT NULL,
  `nombre_de_livre` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`reference`);

--
-- Index pour la table `commande_livres`
--
ALTER TABLE `commande_livres`
  ADD KEY `FK1vogr002vx54e4eqip2colxdh` (`livres_reference`),
  ADD KEY `FKg10el6rjlewviay5kw8u9umjq` (`commandes_reference`);

--
-- Index pour la table `demande`
--
ALTER TABLE `demande`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhvkrfebxojv9w2uwjn4yivls3` (`livre_reference`),
  ADD KEY `FKpwwmhguqianghvi1wohmtsm8l` (`user_id`),
  ADD KEY `FK8lj1k4a1cqhfcwdbpt1df6gtg` (`offre_reference`);

--
-- Index pour la table `feed_back_stat`
--
ALTER TABLE `feed_back_stat`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `livre`
--
ALTER TABLE `livre`
  ADD PRIMARY KEY (`reference`),
  ADD KEY `FKhavckc8ue5s5v7smxkiaw27hx` (`offre_reference`),
  ADD KEY `FKj1n107jo6hjrqfts5hpf2eybw` (`wish_list_id`);

--
-- Index pour la table `livre_users`
--
ALTER TABLE `livre_users`
  ADD KEY `FKtquqd858y7htgagpskatksuvy` (`users_id`),
  ADD KEY `FKeigwaj3p0sklvl57985qyqu4w` (`livre_reference`);

--
-- Index pour la table `offre`
--
ALTER TABLE `offre`
  ADD PRIMARY KEY (`reference`),
  ADD KEY `FKk4ovbtm5h298yo9cb74fq6159` (`user_id`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`reference`);

--
-- Index pour la table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  ADD UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  ADD KEY `FKpk2gwh1ggweerd5bpx84lcjty` (`wish_list_id`);

--
-- Index pour la table `users_livres`
--
ALTER TABLE `users_livres`
  ADD KEY `FKbabg8ftgfedmp6cecxrtak74w` (`livres_reference`),
  ADD KEY `FKo6xjvsbwgkewpfg9v1cwf1hvx` (`user_id`);

--
-- Index pour la table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`);

--
-- Index pour la table `wish_list`
--
ALTER TABLE `wish_list`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT pour la table `feed_back_stat`
--
ALTER TABLE `feed_back_stat`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `livre`
--
ALTER TABLE `livre`
  MODIFY `reference` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT pour la table `offre`
--
ALTER TABLE `offre`
  MODIFY `reference` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `wish_list`
--
ALTER TABLE `wish_list`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `commande_livres`
--
ALTER TABLE `commande_livres`
  ADD CONSTRAINT `FK1vogr002vx54e4eqip2colxdh` FOREIGN KEY (`livres_reference`) REFERENCES `livre` (`reference`),
  ADD CONSTRAINT `FKg10el6rjlewviay5kw8u9umjq` FOREIGN KEY (`commandes_reference`) REFERENCES `commande` (`reference`);

--
-- Contraintes pour la table `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `FK8lj1k4a1cqhfcwdbpt1df6gtg` FOREIGN KEY (`offre_reference`) REFERENCES `offre` (`reference`),
  ADD CONSTRAINT `FKhvkrfebxojv9w2uwjn4yivls3` FOREIGN KEY (`livre_reference`) REFERENCES `livre` (`reference`),
  ADD CONSTRAINT `FKpwwmhguqianghvi1wohmtsm8l` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `livre`
--
ALTER TABLE `livre`
  ADD CONSTRAINT `FKhavckc8ue5s5v7smxkiaw27hx` FOREIGN KEY (`offre_reference`) REFERENCES `offre` (`reference`),
  ADD CONSTRAINT `FKj1n107jo6hjrqfts5hpf2eybw` FOREIGN KEY (`wish_list_id`) REFERENCES `wish_list` (`id`);

--
-- Contraintes pour la table `livre_users`
--
ALTER TABLE `livre_users`
  ADD CONSTRAINT `FKeigwaj3p0sklvl57985qyqu4w` FOREIGN KEY (`livre_reference`) REFERENCES `livre` (`reference`),
  ADD CONSTRAINT `FKtquqd858y7htgagpskatksuvy` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `offre`
--
ALTER TABLE `offre`
  ADD CONSTRAINT `FKk4ovbtm5h298yo9cb74fq6159` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FKpk2gwh1ggweerd5bpx84lcjty` FOREIGN KEY (`wish_list_id`) REFERENCES `wish_list` (`id`);

--
-- Contraintes pour la table `users_livres`
--
ALTER TABLE `users_livres`
  ADD CONSTRAINT `FKbabg8ftgfedmp6cecxrtak74w` FOREIGN KEY (`livres_reference`) REFERENCES `livre` (`reference`),
  ADD CONSTRAINT `FKo6xjvsbwgkewpfg9v1cwf1hvx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  ADD CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
