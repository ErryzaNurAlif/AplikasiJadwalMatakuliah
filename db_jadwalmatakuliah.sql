/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 10.1.34-MariaDB : Database - db_jadwalmatakuliah
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_jadwalmatakuliah` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `db_jadwalmatakuliah`;

/*Data for the table `t_dosen` */

insert  into `t_dosen`(`nip`,`nama`,`tgl_lahir`,`id_matkul`,`alamat`,`no_hp`) values 
('123456','Dwi Marlina','1982-06-14','1','Jakarta','08123917319');

/*Data for the table `t_jadwal` */

insert  into `t_jadwal`(`hari`,`jam`,`nip`,`id_matkul`,`id_ruangan`,`npm`) values 
('Senin','07.30 - 09.30','123456','1','721','201743500002');

/*Data for the table `t_mahasiswa` */

insert  into `t_mahasiswa`(`npm`,`nama`,`tgl_lahir`,`jenis_kelamin`) values 
('201743500002','Putra Muhamad Rafly','1999-04-27','Laki-Laki'),
('201743500005','Erryza Nur Alif','1999-09-09','Laki-Laki');

/*Data for the table `t_matakuliah` */

insert  into `t_matakuliah`(`id_matkul`,`matkul`,`sks`) values 
('1','Sistem Basis Data','3');

/*Data for the table `t_ruangan` */

insert  into `t_ruangan`(`id_ruangan`,`nama_ruangan`) values 
('721','R.7.2.1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
