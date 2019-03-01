-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: tpjava
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `categorias` (
  `idCategoria` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (4,'Drama','Género literario caracterizado por la representación de acciones y situaciones humanas conflictivas'),(5,'Comedia','Obra dramática, en especial la que muestra lo ridículo, con elementos que divierten y hacen reír, y con un desenlace feliz.'),(6,'Terror','Género literario que se define por la sensación que causa: miedo. '),(7,'Thriller','Es un género estrechamente emparentado con el de misterio, pero con más acción, donde predomina el suspenso, y el temor de ser víctima de un asesinato por parte del protagonista.'),(8,'Fantasia','La literatura fantástica, puede también presentarnos un objeto o personaje tomado de la realidad, realizando acciones que en un entorno real serían descabelladas o imposibles.'),(9,'Ciencia ficcion','G�nero de narraciones imaginarias que no pueden darse en el mundo que conocemos,');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comentarios`
--

DROP TABLE IF EXISTS `comentarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `comentarios` (
  `fechaHora` datetime NOT NULL,
  `usuarioID` int(11) NOT NULL,
  `ISBN` varchar(18) NOT NULL,
  `comentario` varchar(300) NOT NULL,
  PRIMARY KEY (`fechaHora`,`usuarioID`,`ISBN`),
  KEY `comentarioLibro_idx` (`ISBN`),
  KEY `coment` (`ISBN`),
  KEY `comentarioUsuario_idx` (`usuarioID`),
  CONSTRAINT `comentarioLibro` FOREIGN KEY (`ISBN`) REFERENCES `libros` (`isbn`),
  CONSTRAINT `comentarioUsuario` FOREIGN KEY (`usuarioID`) REFERENCES `usuarios` (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentarios`
--

LOCK TABLES `comentarios` WRITE;
/*!40000 ALTER TABLE `comentarios` DISABLE KEYS */;
INSERT INTO `comentarios` VALUES ('2019-03-01 04:06:36',2,'978-987-23036-5-5','El mejor libro de la saga!!! '),('2019-03-01 04:08:22',3,'978-987-725-333-7','El mejor libro de terror de toda la historia, dos semanas sin poder dormir!!!');
/*!40000 ALTER TABLE `comentarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detallesventas`
--

DROP TABLE IF EXISTS `detallesventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `detallesventas` (
  `fechaVenta` datetime NOT NULL,
  `idVenta` int(11) NOT NULL,
  `ISBN` varchar(18) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  PRIMARY KEY (`idVenta`,`fechaVenta`,`ISBN`),
  KEY `libroDetalle_idx` (`ISBN`),
  CONSTRAINT `idVentaDetalle` FOREIGN KEY (`idVenta`) REFERENCES `ventas` (`idventa`),
  CONSTRAINT `libroDetalle` FOREIGN KEY (`ISBN`) REFERENCES `libros` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallesventas`
--

LOCK TABLES `detallesventas` WRITE;
/*!40000 ALTER TABLE `detallesventas` DISABLE KEYS */;
INSERT INTO `detallesventas` VALUES ('2019-03-01 04:08:30',75,'978-987-725-333-7',1,421),('2019-03-01 04:11:59',76,'978-950-547-183-6',1,540),('2019-03-01 04:12:00',76,'978-987-23036-5-5',1,252),('2019-03-01 04:15:32',77,'978-950-547-181-2',2,472),('2019-03-01 04:16:32',78,'978-950-547-206-2',1,356),('2019-03-01 04:17:16',79,'978-987-738-349-2',1,345),('2019-03-01 04:18:35',80,'978-950-46-4657-0',1,123),('2019-03-01 04:19:46',81,'978-987-763-180-7',1,398),('2019-03-01 04:21:20',82,'978-987-725-333-7',2,842),('2019-03-01 04:21:22',82,'978-950-547-206-2',1,356);
/*!40000 ALTER TABLE `detallesventas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entregas`
--

DROP TABLE IF EXISTS `entregas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `entregas` (
  `idEntrega` int(11) NOT NULL AUTO_INCREMENT,
  `estado` varchar(45) NOT NULL,
  `fechaEntrega` datetime DEFAULT NULL,
  `direccion` varchar(100) NOT NULL,
  `idVenta` int(11) NOT NULL,
  PRIMARY KEY (`idEntrega`),
  KEY `idVenta_idx` (`idVenta`),
  CONSTRAINT `ventaEntrega` FOREIGN KEY (`idVenta`) REFERENCES `ventas` (`idventa`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entregas`
--

LOCK TABLES `entregas` WRITE;
/*!40000 ALTER TABLE `entregas` DISABLE KEYS */;
INSERT INTO `entregas` VALUES (6,'Recibido',NULL,'Tucuman 237',75),(7,'Recibido',NULL,'Entre rios 1123',76);
/*!40000 ALTER TABLE `entregas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libros`
--

DROP TABLE IF EXISTS `libros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `libros` (
  `ISBN` varchar(18) NOT NULL,
  `titulo` varchar(45) DEFAULT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `autor` varchar(45) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `edicion` varchar(45) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `idCategoria` int(11) NOT NULL,
  `imagen` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ISBN`),
  KEY `idSubCategoria_idx` (`idCategoria`),
  CONSTRAINT `idCategoria` FOREIGN KEY (`idCategoria`) REFERENCES `categorias` (`idcategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libros`
--

LOCK TABLES `libros` WRITE;
/*!40000 ALTER TABLE `libros` DISABLE KEYS */;
INSERT INTO `libros` VALUES ('978-950-46-4657-0','La llave del �guila','Diego ha quedado hu�rfano y vive en Mendoza. Decide buscar a su familia materna con las �nicas pistas que tiee: un apellido y una peque�a llave de oro con forma de �guila. No imagina las aventuras que deber� correr, ayudado por sus amigos, hasta descubrir el secreto que encierra la historia de sus padres.','Elisa Rold�n','1996-02-01','Alfaguara',123,4,'La llave del aguila.jpg'),('978-950-547-181-2','La Comunidad del Anillo','Es el primero de los tres vol�menes que forman la novela El Se�or de los Anillos, secuela de El hobbit, del escritor brit�nico J. R. R. Tolkien. ','J. R. R. Tolkien. ','1954-06-29','George Allen & Unwin',236,8,'LaComunidadDelAnillo.jpg'),('978-950-547-183-6','El retorno del Rey','Es el tercer volumen de la novela de fantas?a heroica El Se?or de los Anillos, del escritor brit?nico J. R. R. Tolkien.','J. R. R. Tolkien. ','0025-02-05','George Allen & Unwin',540,8,'El retorno del rey.jpg'),('978-950-547-206-2','Las dos torres','La historia transcurre dentro del universo ficticio de la Tierra Media, y en ella se contin�a la narraci�n de las aventuras de los protagonistas de La Comunidad del Anillo: la muerte de Boromir, el secuestro de Merry y Pippin por los orcos de Saruman y su posterior huida, las batallas de Aragorn.','J. R. R. Tolkien','1954-11-11','George Allen & Unwin',356,8,'LasDosTorres.jpg'),('978-987-23036-5-5','Harry Portter y la piedra filosofal','Harry Potter crece en la casa de sus t?os, los Dursley, quienes le ocultan su verdadera historia familiar; al cumplir Harry once a?os de edad, empiezan a llegarle cartas de remitente desconocido, que van aumentando en n?mero a medida que sus t?os no dejan que las abra. Las mismas traen la noticia de que el ni?o ha sido admitido en el Colegio Hogwarts de Magia y Hechicer?a, ya que, al igual que sus padres, tiene poderes m?gicos.','J. K. Rowling','0016-05-12','Bloomsbury Publishing',252,8,'HarryPotter1_.jpg'),('978-987-628-342-7','Harry Portter y la camara secreta','Es el segundo libro de la serie literaria Harry Potter, escrito por la autora brit�nica J. K. Rowling en 1998','J. K. Rowling','1998-06-02','Bloomsbury Publishing',354,8,'harryPotter2.jpg'),('978-987-725-127-2','La Torre Oscura I: El pistolero','Narra la historia del pistolero Roland de Gilead, y su b�squeda para atrapar al hombre de negro, el primero de sus muchos pasos para llegar a su destino final, la Torre Oscura.','Stephen King','1982-05-30','Donald M. Grant, Publisher',121,7,'torre_oscura_Stephen_King.jpg'),('978-987-725-333-7','El resplandor','Al escritor Jack Torrance le es ofrecido un empleo como cuidador del hotel Overlook durante el invierno junto a su familia.','Stephen King','0029-06-01','Doubleday ',421,6,'ElResplandor.jpg'),('978-987-738-349-2','La pregunta de sus ojos','La pregunta de sus ojos es una novela de 2005 escrita por el autor argentino Eduardo Sacheri, en la que se basa la pel�cula de 2009 El secreto de sus ojos.',' Eduardo Sacheri,','2005-01-01','Alfaguara',345,4,'elsecretodesushojos.jpg'),('978-987-763-180-7','El cazador de sue�os','Situada en la ciudad ficticia de Derry, Maine, El cazador de sue�os es la historia de cuatro amigos cuyas vidas son alteradas al salvar a Douglas Duddits Cavell, un ni�o con s�ndrome de Down, de un ataque provocado por otros chicos de por all�.','Stephen King','2001-03-20','Scribner',398,6,'El cazador de suenios.jpg');
/*!40000 ALTER TABLE `libros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usuarios` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) NOT NULL,
  `contraseña` varchar(10) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `tipoUsuario` int(11) DEFAULT NULL,
  `fechaDeAlta` datetime DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `Usuario_UNIQUE` (`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (2,'nacho96','nacho96','Nacho','Gonzalez','tomas4fiorenza@gmail.com',1,'2018-09-18 20:11:43'),(3,'aguAgu1992','1234','Agustin','Aguero','tomas4fiorenza@gmail.com',1,'2018-10-19 17:10:04'),(5,'aguFiorenza','123456789','Agustin','Fiorenza','tomas4fiorenza@gmail.com',0,'2019-02-24 19:47:50'),(6,'pabloF','millo1973','Pablo','Fois','tomas4fiorenza@gmail.com',1,'2019-03-01 16:09:09');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventas`
--

DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ventas` (
  `idVenta` int(11) NOT NULL AUTO_INCREMENT,
  `idUsuario` int(11) DEFAULT NULL,
  `importe` double DEFAULT NULL,
  `fechaVenta` datetime DEFAULT NULL,
  PRIMARY KEY (`idVenta`),
  KEY `usuarioVentas_idx` (`idUsuario`),
  CONSTRAINT `usuarioVentas` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
INSERT INTO `ventas` VALUES (74,2,504,'2019-03-01 03:49:06'),(75,3,421,'2019-03-01 04:08:36'),(76,6,792,'2019-03-01 04:12:07'),(77,6,472,'2019-03-01 04:15:34'),(78,3,356,'2019-03-01 04:16:38'),(79,3,345,'2019-03-01 04:17:25'),(80,2,123,'2019-03-01 04:18:41'),(81,2,398,'2019-03-01 04:19:54'),(82,2,1198,'2019-03-01 04:21:23');
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-01 16:24:28
