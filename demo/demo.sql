-- MySQL dump 10.13  Distrib 8.0.30, for macos12.5 (arm64)
--
-- Host: localhost    Database: ilovejava
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Post`
--

DROP TABLE IF EXISTS `Post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Post` (
  `id` varchar(36) NOT NULL,
  `content` longtext,
  `creationDate` datetime DEFAULT NULL,
  `removed` int NOT NULL,
  `updateDate` datetime DEFAULT NULL,
  `author_id` varchar(36) NOT NULL,
  `parent_id` varchar(36) DEFAULT NULL,
  `thread_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn7gx6xx1edujxkuk0dcan2df0` (`author_id`),
  KEY `FKpyxocq1i2wp79mwq2demeqq0r` (`parent_id`),
  KEY `FKcd79q5bpjhjx78b5b6ejoc3br` (`thread_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Post`
--

LOCK TABLES `Post` WRITE;
/*!40000 ALTER TABLE `Post` DISABLE KEYS */;
INSERT INTO `Post` VALUES ('45cd7b10-ae06-47e4-82a6-e774c7aa8580','\n\nI&#39;m having some trouble understanding how to use Java for web development. Can anyone help me out?','2022-12-23 20:07:38',0,'2022-12-23 20:07:38','e375dd97-b0e5-4560-993d-81630b80db31',NULL,'dd11e9b8-38a6-4257-a0e9-613faef1e716'),('a1f8635b-4833-4057-975a-1e18b7b319c1','\n\nHey guys, I&#39;m trying to build a simple GUI for a personal project using Swing but I&#39;m having some trouble with the layout. Any tips on how to get started?','2022-12-23 20:08:27',0,'2022-12-23 20:08:27','8dc24f84-57bf-410f-b9d0-1714421a141f',NULL,'87d3a0b3-f388-4500-838c-b6b0ab6cee54'),('ceeb5907-d5b3-4de2-a6e9-ba55192e13e6','\n\nHi everyone, I&#39;m having some issues with TextFields in my JavaFX application. When I try to type in the TextField, it only allows me to type one character and then it clears the field. Any ideas on what could be causing this?','2022-12-23 20:09:15',0,'2022-12-23 20:09:16','6ff362ae-a64a-4be2-be65-9f8c046688c5',NULL,'1825d89b-e68b-448b-819e-0fc6f30b20e2'),('301e9876-d98f-4656-af09-db76b886b6db','\n\nHi everyone, I&#39;m trying to use the Java Reflection API to dynamically invoke a method, but I&#39;m having some issues. I keep getting a **NoSuchMethodException**, even though I&#39;m pretty sure the method exists. Here&#39;s my code:\r\n\r\n```java\r\nClass clazz = MyClass.class;\r\nMethod method = clazz.getMethod(&quot;myMethod&quot;, String.class, int.class);\r\nObject result = method.invoke(null, &quot;hello&quot;, 5);```','2022-12-23 20:09:44',0,'2022-12-23 20:10:19','4e6bad9d-7410-4e6a-b201-8d14796f1e13',NULL,'22389a66-91fd-4616-a386-38e369eec25c'),('c4200f1f-4093-460e-9547-aca6421ba8d9','\n\nhi,\r\n\r\nhave you tried setting the `setTextFormatter` property on the TextField? That could be causing the issue you&#39;re experiencing.','2022-12-23 20:10:46',0,'2022-12-23 20:10:46','035cc763-983b-4fb0-807e-b6f81f22afd5','ceeb5907-d5b3-4de2-a6e9-ba55192e13e6','1825d89b-e68b-448b-819e-0fc6f30b20e2'),('d5a664cf-d130-40f5-b9e3-b763aea55e71','\n\nHi , thanks for the suggestion! I did try setting the `setTextFormatter` property, but that didn&#39;t seem to fix the issue. Here&#39;s the code I&#39;m using for the TextField:\r\n\r\n```java\r\nTextField textField = new TextField();\r\ntextField.setTextFormatter(new TextFormatter&lt;&gt;(change -&gt; {\r\n    if (change.isContentChange()) {\r\n        if (change.getControlNewText().length() &gt; 10) {\r\n            change.setText(change.getControlNewText().substring(0, 10));\r\n        }\r\n    }\r\n    return change;\r\n}));\r\n```','2022-12-23 20:12:08',0,'2022-12-23 20:12:08','6ff362ae-a64a-4be2-be65-9f8c046688c5','c4200f1f-4093-460e-9547-aca6421ba8d9','1825d89b-e68b-448b-819e-0fc6f30b20e2'),('9546ef3e-c049-4305-b67e-e6f9b62a6c86','\n\nHave you checked that the method is public? That could be the issue.','2022-12-23 20:13:15',0,'2022-12-23 20:13:15','035cc763-983b-4fb0-807e-b6f81f22afd5','301e9876-d98f-4656-af09-db76b886b6db','22389a66-91fd-4616-a386-38e369eec25c'),('7c5cd9de-8e82-47aa-b506-928f00ba628a','\n\nYes, the method is public','2022-12-23 20:13:39',0,'2022-12-23 20:13:39','4e6bad9d-7410-4e6a-b201-8d14796f1e13','9546ef3e-c049-4305-b67e-e6f9b62a6c86','22389a66-91fd-4616-a386-38e369eec25c'),('77725ef4-d0c6-4629-a200-a4c7b1cd4403','\n\nIt might be helpful to print out the class and method objects to verify that they are being created correctly. You can do this using the toString() method.','2022-12-23 20:14:13',0,'2022-12-23 20:14:13','6ff362ae-a64a-4be2-be65-9f8c046688c5','301e9876-d98f-4656-af09-db76b886b6db','22389a66-91fd-4616-a386-38e369eec25c'),('300ae64e-af87-4281-a8e1-8168deaacb16','\n\nThanks, I&#39;ll give that a try.\r\n\r\n I also just realized that I might be using the wrong class name. I&#39;ll double check that as well.','2022-12-23 20:14:56',0,'2022-12-23 20:14:56','4e6bad9d-7410-4e6a-b201-8d14796f1e13','77725ef4-d0c6-4629-a200-a4c7b1cd4403','22389a66-91fd-4616-a386-38e369eec25c'),('dffa5e3a-75db-4264-b136-16494aa2fafc','\n\nOK, I was indeed using the wrong class name. I fixed that and now it&#39;s working as expected. Thanks for the help!','2022-12-23 20:15:28',0,'2022-12-23 20:15:28','4e6bad9d-7410-4e6a-b201-8d14796f1e13','300ae64e-af87-4281-a8e1-8168deaacb16','22389a66-91fd-4616-a386-38e369eec25c'),('3a2a9470-2ab8-4636-9e8e-8cfd1e7a7f35','\n\nGlad you got it working','2022-12-23 20:15:49',0,'2022-12-23 20:15:49','6ff362ae-a64a-4be2-be65-9f8c046688c5','dffa5e3a-75db-4264-b136-16494aa2fafc','22389a66-91fd-4616-a386-38e369eec25c'),('df5968e4-0bb9-498c-941e-9a42bd30e952','\n\nHave you tried using a layout manager? It can make it a lot easier to manage the layout of your GUI components.','2022-12-23 20:17:52',0,'2022-12-23 20:17:52','6ff362ae-a64a-4be2-be65-9f8c046688c5','a1f8635b-4833-4057-975a-1e18b7b319c1','87d3a0b3-f388-4500-838c-b6b0ab6cee54'),('9ebaf5ed-97da-4151-a2dc-3a551464441a','\n\nYeah, I&#39;ve tried using a few different layout managers but I&#39;m still having trouble getting the layout to look how I want it. Do you have any specific recommendations?','2022-12-23 20:18:08',0,'2022-12-23 20:18:08','8dc24f84-57bf-410f-b9d0-1714421a141f','df5968e4-0bb9-498c-941e-9a42bd30e952','87d3a0b3-f388-4500-838c-b6b0ab6cee54'),('a1442681-4b66-4779-9e02-9b4ff07c576e','\n\nOne layout manager that I&#39;ve found really useful is the `GridBagLayout`.\r\n\r\n It&#39;s a little more complex to use than some of the other layout managers, but it gives you a lot of control over the layout of your components. There are also some good tutorials online that can help you get started.','2022-12-23 20:18:38',0,'2022-12-23 20:18:38','035cc763-983b-4fb0-807e-b6f81f22afd5','9ebaf5ed-97da-4151-a2dc-3a551464441a','87d3a0b3-f388-4500-838c-b6b0ab6cee54'),('7affca00-9199-432e-9885-6441b73d712b','\n\nThanks, I&#39;ll check it out. I appreciate the help!','2022-12-23 20:18:55',0,'2022-12-23 20:18:55','8dc24f84-57bf-410f-b9d0-1714421a141f','a1442681-4b66-4779-9e02-9b4ff07c576e','87d3a0b3-f388-4500-838c-b6b0ab6cee54'),('b13e4799-9e46-4d5d-9978-2e43e68db10b','\n\nI second this !','2022-12-23 20:19:21',0,'2022-12-23 20:19:21','6ff362ae-a64a-4be2-be65-9f8c046688c5','a1442681-4b66-4779-9e02-9b4ff07c576e','87d3a0b3-f388-4500-838c-b6b0ab6cee54'),('b60351af-7c7e-4e12-b734-042771e3c170','\n\nSure, have you tried looking at the Java Servlet API? It&#39;s a really useful tool for handling HTTP requests and responses in Java.','2022-12-23 20:20:37',0,'2022-12-23 20:20:37','8dc24f84-57bf-410f-b9d0-1714421a141f','45cd7b10-ae06-47e4-82a6-e774c7aa8580','dd11e9b8-38a6-4257-a0e9-613faef1e716'),('1096660b-463f-44cf-bfcd-0bd35e2b3a46','\n\nYeah, I&#39;ve looked at that but I&#39;m still having trouble. Do you have any resources or tips on how to get started with Java web development?','2022-12-23 20:20:54',0,'2022-12-23 20:20:54','e375dd97-b0e5-4560-993d-81630b80db31','b60351af-7c7e-4e12-b734-042771e3c170','dd11e9b8-38a6-4257-a0e9-613faef1e716'),('72d0f465-6c59-4270-ab4a-b1b89029d9a3','\n\nYou might find this tutorial helpful: https://www.tutorialspoint.com/servlets/index.htm. It covers the basics of using the Servlet API and also has some exercises to practice.','2022-12-23 20:21:15',0,'2022-12-23 20:21:15','035cc763-983b-4fb0-807e-b6f81f22afd5','1096660b-463f-44cf-bfcd-0bd35e2b3a46','dd11e9b8-38a6-4257-a0e9-613faef1e716'),('0fc5a557-190f-493f-af0a-e8cf21cbfaec','\n\nThanks, I&#39;ll check it out. I appreciate the help!','2022-12-23 20:21:33',0,'2022-12-23 20:21:33','e375dd97-b0e5-4560-993d-81630b80db31','72d0f465-6c59-4270-ab4a-b1b89029d9a3','dd11e9b8-38a6-4257-a0e9-613faef1e716'),('788ac959-d544-4c1d-988d-5bbde8727f79','\n\nHere&#39;s a cheatsheet with stuff, careful, it&#39;s in french !\r\n\r\n# Design Patterns\r\n\r\n# Singleton\r\n\r\n**Probl&egrave;me** : Unicit&eacute; d&rsquo;une instance de classe, avec un point global d&rsquo;acc&egrave;s\r\n\r\n**Utilisations** : \r\n\r\n- Fabrique\r\n- Eviter l&rsquo;incoh&eacute;rence de donn&eacute;es / &eacute;crasement\r\n- Unicit&eacute; (duh)\r\n\r\n**Avantages :**\r\n\r\n- Acc&egrave;s contr&ocirc;l&eacute;\r\n- R&eacute;duction des namespaces (pas de variables globales)\r\n- Etendable par h&eacute;ritage\r\n- Possibilit&eacute; de faire `n` instances\r\n\r\n**Inconv&eacute;nients** : \r\n\r\n- Utilisation non naturelle, pas &agrave; travers `Classe::new`\r\n\r\n**Solution** : Remplacer le constructeur (privatis&eacute;) par une autre m&eacute;thode\r\n\r\n**Impl&eacute;mentation** : Contructeur priv&eacute;, m&eacute;thode statique pour `.instance()`\r\n\r\n```java\r\npublic class Singleton {\r\n\r\n	private static Singleton instance = null; // ... = new Singleton() if thread safe\r\n\r\n	public static Singleton instance() {\r\n		if (instance == null) instance = new Singleton(); // useless if thread safe\r\n		return instance;\r\n	}\r\n\r\n	private Singleton() {...}\r\n}\r\n```\r\n\r\n# Fabrique\r\n\r\n**Probl&egrave;me** : Avoir une interface pour la cr&eacute;ation d&rsquo;objets, en laissant les sous-classes choisir la classe concr&egrave;te &agrave; instancier\r\n\r\n**Utilisations** : \r\n\r\n- Ne pas devoir anticiper la classe des objets que la fabrique doit cr&eacute;er\r\n- Laisser les sous-classes sp&eacute;cifier les objets cr&eacute;es\r\n\r\n**Avantages** : \r\n\r\n- Code client flexible et r&eacute;utilisable\r\n- Client uniquement d&eacute;pendant de l&rsquo;interface `Produit` n&rsquo;importe la classe `ConcreteProduct`\r\n\r\n**Inconv&eacute;nients** : \r\n\r\n- Besoin d&rsquo;instancier la fabrique &agrave; chaque utilisation de celle-ci\r\n\r\n**Impl&eacute;mentation** : La classe `Cr&eacute;ateur` peut &ecirc;tre **abstraite** (doit &ecirc;tre sp&eacute;cialis&eacute;e, n&rsquo;a pas d&rsquo;impl&eacute;mentation par d&eacute;faut) ou **concr&egrave;te** (peut &ecirc;tre sp&eacute;cialis&eacute;e, fournit une impl&eacute;mentation par d&eacute;faut)\r\n\r\n```java\r\npublic class Product {...}\r\npublic class ConcreteProduct extends Product {...}\r\npublic class OtherConcreteProduct extends Product {...}\r\n\r\npublic abstract class Creator {\r\n	public abstract Product create();\r\n}\r\n\r\npublic class ConcreteProductCreator extends Creator {\r\n	public Product create() {\r\n		reurn new ConcreteProduct();\r\n	}\r\n}\r\n\r\npublic class OtherConcreteProductCreator extends Creator {\r\n	public Product create() {\r\n		reurn new OtherConcreteProduct();\r\n	}\r\n}\r\n```\r\n\r\n```java\r\npublic class Product {...}\r\npublic class ConcreteProduct extends Product {...}\r\npublic class OtherConcreteProduct extends Product {...}\r\n\r\npublic class Creator {\r\n	public Product create() {\r\n		return new ConcreteProduct(); // by default, use Concrete Product\r\n	}\r\n}\r\n\r\npublic class OtherConcreteProductCreator extends Creator {\r\n	public Product create() {\r\n		reurn new OtherConcreteProduct();\r\n	}\r\n}\r\n```\r\n\r\n# Fabrique Abstraite\r\n\r\n**Probl&egrave;me** : Abstraire la cr&eacute;ation de familles concr&egrave;tes afin de faciliter l&rsquo;utilisation de d&rsquo;objets apparent&eacute;s et/ou interd&eacute;pendants.\r\n\r\n**Utilisations** :\r\n\r\n- Configurer un syst&egrave;me avec une des multiples familles concr&egrave;tes de produits\r\n- Imposer l&rsquo;utilisation d&rsquo;une m&ecirc;me famille pour les produits\r\n\r\n**Avantages** : \r\n\r\n- Isoler les classes concr&egrave;tes en utilisant des interfaces abstraites\r\n- Facilite le changement de famille de produits\r\n- Favorise la coh&eacute;rence entre produits\r\n\r\n**Inconv&eacute;nients** **:**\r\n\r\n- Difficile d&rsquo;ajouter de nouveaux types de produits car il faut &eacute;tendre toutes les classes des produits concrets\r\n\r\n**Impl&eacute;mentation** : Cr&eacute;er une fabrique abstraite puis pour chaque type **concret** de produit, cr&eacute;er une fabrique abstraite impl&eacute;mentant celle-ci. \r\n\r\n```java\r\npublic class ProductType1 {...}\r\npublic class ProductType2 {...}\r\n\r\npublic class ConcreteProductType1 extends ProductType1 {...}\r\npublic class ConcreteProductType1 extends ProductType1 {...}\r\n\r\npublic abstract class Factory {\r\n	public abstract ProductType1 createProductType1();\r\n	public abstract ProductType2 createProductType2();\r\n}\r\n\r\npublic class ConcreteFactory extends Factory {\r\n	public abstract ProductType1 createProductType1(\r\n		return new ConcreteProductType1();\r\n	);\r\n	public abstract ProductType2 createProductType2(\r\n		return new ConcreteProductType2();\r\n	);\r\n```\r\n\r\n```java\r\npublic class ProductType1 {...}\r\npublic class ProductType2 {...}\r\n\r\npublic class ConcreteProductType1 extends ProductType1 {...}\r\npublic class ConcreteProductType1 extends ProductType1 {...}\r\n\r\n// concrete factories go here\r\n\r\npublic abstract class Factory {\r\n	protected ProductType1Factory productType1Factory;\r\n	protected ProductType2Factory productType2Factory;\r\n\r\n	public ProductType1 createProductType1() {\r\n		return productType1Factory.createProductType1()\r\n	}\r\n\r\n	public ProductType2 createProductType2() {\r\n		return productType2Factory.createProductType2()\r\n	}\r\n}\r\n\r\npublic class ConcreteFactory extends Factory {\r\n	public ConcreteFactory() {\r\n		productType1Factory = new ConcreteProductType1Factory();\r\n		productType2Factory = new ConcreteProductType2Factory();\r\n	}\r\n}\r\n```\r\n\r\n```java\r\npublic class ProductType1 {...}\r\npublic class ProductType2 {...}\r\n\r\n// concrete factories go here\r\n\r\npublic class Factory {\r\n	protected ProductType1Factory productType1Factory;\r\n	protected ProductType2Factory productType2Factory;\r\n\r\n	public Factory(ProductType1Factory t1f, ProductType2Factory t2f) {\r\n		productType1Factory = t1f;\r\n		productType2Factory = t2f;\r\n	}\r\n\r\n	// getters and setters for the factory types\r\n\r\n	public ProductType1 createProductType1() {\r\n		return productType1Factory.createProductType1()\r\n	}\r\n\r\n	public ProductType2 createProductType2() {\r\n		return productType2Factory.createProductType2()\r\n	}\r\n}\r\n```\r\n\r\n# Fa&ccedil;ade\r\n\r\n**Probl&egrave;me** : Simplifier l&rsquo;utilisation d&rsquo;un sous-syt&egrave;me &agrave; travers des fonctionnalit&eacute;s haut niveau faites &agrave; partir de fonctionnalti&eacute;s bas niveau\r\n\r\n**Utilisations** :\r\n\r\n- Simplification d&rsquo;un sous-syst&egrave;me, masquer sa compl&eacute;xit&eacute;\r\n- D&eacute;coupler un sous-syt&egrave;me de ses clients\r\n\r\n**Avantages** : \r\n\r\n- Cacher l&rsquo;impl&eacute;mentation d&rsquo;une fonctionnalit&eacute;\r\n- Permet la modification du sous-syst&egrave;me sans affecter le client\r\n- N&rsquo;emp&ecirc;che pas l&rsquo;acc&egrave;s au sous-syst&egrave;me mais propse une utilisation simplifi&eacute;e\r\n\r\n**Inconv&eacute;nients** **:**\r\n\r\n- Perte de fonctionnalit&eacute;s possible\r\n- N&rsquo;emp&ecirc;che pas l&rsquo;acc&egrave;s au sous-syst&egrave;me mais propse une utilisation simplifi&eacute;e :^)\r\n\r\n**Impl&eacute;mentation** : Regrouper les instructions bas niveau pour former une instruction haut niveau\r\n\r\n```java\r\npublic class Facade {\r\n	public Facade() {...} // optional, if needs initialization\r\n	public void action1() { // \r\n		smallAction1();\r\n		smallAction2();\r\n		smallAction3();\r\n	}\r\n}\r\n```\r\n\r\n# Adaptateur\r\n\r\n**Probl&egrave;me** : R&eacute;soudre l&rsquo;incompatibilit&eacute; d&rsquo;interfaces\r\n\r\n**Utilisations** :\r\n\r\n- Rendre des interfaces compatibles en introduisant un niveau d&rsquo;indirection (wrapper)\r\n- Faire un lien entre un syst&egrave;me et un programme\r\n\r\n**Avantages** : \r\n\r\n- Adapter une interface alors qu&rsquo;on n&rsquo;a pas acc&egrave;s &agrave; son code source\r\n- Rendre plusieurs interfaces compatibles\r\n\r\n**Inconv&eacute;nients** **:**\r\n\r\n- La classe adapt&eacute;e n&rsquo;est plus utilisable directement, sauf si transparence bidirectionnelle\r\n\r\n**Impl&eacute;mentation** : Cr&eacute;ation d&rsquo;un **wrapper** autour de la classe incompatible. Possibilit&eacute; de cr&eacute;er un adaptateur **bidirectionnel** (possibilit&eacute; de convertir l&rsquo;objet &agrave; la fois en classe `adapt&eacute;e` et classe `target`).\r\n\r\n```java\r\npublic abstract class Target {\r\n	public abstract void action1(...);\r\n}\r\n\r\npublic class Adaptee {\r\n	public void halfaction(...){...}\r\n	public void halfaction2(...){...}\r\n}\r\n\r\npublic class AdapteeAdapter extends Target {\r\n	private Adaptee instance;\r\n\r\n	public AdapteeAdapter(Adaptee a) {\r\n		instance = a;\r\n	}	\r\n\r\n	@Override\r\n	public void action1(...) {\r\n		a.halfaction();\r\n		a.halfaction2();\r\n	}\r\n```','2022-12-23 20:25:09',0,'2022-12-23 20:26:01','035cc763-983b-4fb0-807e-b6f81f22afd5',NULL,'d9f14880-5f31-49a6-acd9-6f8a1405fcb3'),('0d656253-afc3-4293-9437-21f1bea951e2','\n\n\r\n# Procuration\r\n\r\n**Probl&egrave;me** : Contr&ocirc;ler l&rsquo;acc&egrave;s &agrave; un objet sans le modifier directement &agrave; travers un objet interm&eacute;diaire\r\n\r\n**Utilisations** :\r\n\r\n- g&eacute;rer le cycle de vie de l&rsquo;objet\r\n- optimiser l&rsquo;acc&egrave;s &agrave; l&rsquo;objet\r\n- restreindre l&rsquo;acc&egrave;s &agrave; l&rsquo;objet\r\n- gestion de cache..\r\n\r\n**Avantages** : \r\n\r\n- un niveau d&rsquo;indirection pour l&rsquo;acc&egrave;s &agrave; l&rsquo;objet\r\n- permet d&rsquo;optimiser l&rsquo;acc&egrave;s &agrave; l&rsquo;objet\r\n- permet de v&eacute;rifier des permissions\r\n\r\n**Inconv&eacute;nients** **:**\r\n\r\n- R&eacute;plique exacte de son sujet r&eacute;el\r\n\r\n**Impl&eacute;mentation** : La classe `Proxy` peut ne pas avoir besoin de conna&icirc;tre le type du sujet, elle peut &eacute;galement faire r&eacute;f&eacute;rence au sujet pour le traiter plus tard.\r\n\r\n```java\r\npublic class RandomObjectProxy extends RandomObjectImplementation {\r\n	private RandomObject subject;\r\n	private String constructorArg;\r\n\r\n	public RandomObjectProxy(String constructorArg) {\r\n		this.constructorArg = constructorArg;\r\n	}\r\n\r\n	public load() {\r\n		if (subject == null) subject = new RandomObjectImplementation();\r\n		return subject;\r\n	}\r\n}\r\n```\r\n\r\n# D&eacute;corateur\r\n\r\n**Probl&egrave;me** : Attacher un nombre de classes / propri&eacute;t&eacute;s &agrave; un objet \r\n\r\n**Utilisations** :\r\n\r\n- attacher dynamiquement des responsabilit&eacute;s suppl&eacute;mentaires &agrave; un objet\r\n- fournir une alternative &agrave; l&rsquo;h&eacute;ritage pour &eacute;tendre des fonctionnalit&eacute;s\r\n- ajouter ou retirer des responsabilit&eacute;s dynamiquement\r\n\r\n**Avantages** : \r\n\r\n- plus de fl&eacute;xibilit&eacute; que l&rsquo;h&eacute;ritage\r\n- pas de surcharge des classes\r\n\r\n**Inconv&eacute;nients** **:**\r\n\r\n- d&eacute;corateur et composants non identiques (le d&eacute;corateur est une enveloppe transparente)\r\n- produit des objets qu&rsquo;on peut confondre entre eux (difficile &agrave; debug)\r\n- interface d&eacute;corateur doit correspondre &agrave; celle du composant\r\n- impossible de supprimer des d&eacute;corations enfouies dans les couches\r\n\r\n**Impl&eacute;mentation** : Garder la classe `Composant` l&eacute;g&egrave;re, d&eacute;corateur et composant doivent descendre de la m&ecirc;me classe (ici, interface)\r\n\r\n```java\r\npublic interface Component {\r\n	void method1();\r\n	void method2();\r\n}\r\n\r\npublic class ConcreteComponent implements Component {\r\n	public void method1() {...}\r\n	public void method2() {...}\r\n}\r\n\r\npublic abstract class Decorator implements Component {\r\n\r\n	protected Component decorated;\r\n\r\n	public Decorator(Component original) {\r\n		decorated = original;\r\n	}\r\n}\r\n\r\npublic class ConcreteDecorator extends Decorator {\r\n\r\n	public ConcreteDecorator(Component original) {\r\n		super(original);\r\n	}\r\n\r\n	@Override\r\n	public void method1() {\r\n		decorated.method1();\r\n	}\r\n\r\n	@Override\r\n	public void method2() {\r\n		decorated.method2();\r\n	}\r\n}\r\n```\r\n\r\n# Composite\r\n\r\n**Probl&egrave;me** : Cr&eacute;er une structure comportant de la hi&eacute;rarchie\r\n\r\n**Utilisations** :\r\n\r\n- Cr&eacute;er des structures arborescentes\r\n- Permettre de traiter individuellement les membres de ces groupes\r\n\r\n**Avantages** : \r\n\r\n- Facile d&rsquo;ajouter et supprimer des composants\r\n- Simplification de l&rsquo;acc&egrave;s aux sous-couches\r\n- Permet de composer des classes de fa&ccedil;on r&eacute;cursive\r\n\r\n**Inconv&eacute;nients** **:**\r\n\r\n- Difficile de restreindre le type des enfants\r\n\r\n**Impl&eacute;mentation** : D&eacute;finir une classe abstraite `Composant` , des `Composites` et des `Feuilles` qui en h&eacute;ritent. Les **Composites** pouvant contenir des **Composants**\r\n\r\n```java\r\npublic interface Component {\r\n	void method();\r\n}\r\n\r\npublic class Leaf implements Component {\r\n	public void method() { ... }\r\n}\r\n\r\npublic class Composite implements Component {\r\n	private List&lt;Component&gt; children = new ArrayList();\r\n	public void add(Component component) {\r\n		children.add(component);\r\n	}\r\n	public void remove(Component component) {\r\n		children.remove(component)\r\n	}\r\n	public Component getChild(int i) {\r\n		children.get(i);\r\n	}\r\n	public void method() { ... } // example would be children.forEach(c -&gt; c.method());\r\n}\r\n```\r\n\r\n# Commande\r\n\r\n**Probl&egrave;me** : Invoquer une liste d&rsquo;op&eacute;rations dans un certain ordre, depuis diff&eacute;rents endroits\r\n\r\n**Utilisations** :\r\n\r\n- Encapsuler les actions comme des objets, pour les stocker dans une file d&rsquo;attente / historique\r\n- Mettre l&rsquo;invocation d&rsquo;une action sous la forme d&rsquo;un objet &agrave; part enti&egrave;re\r\n\r\n**Avantages** : \r\n\r\n- Supprime les d&eacute;pendances entre l&rsquo;invocateur et le r&eacute;cepteur\r\n- Les commandes sont des objets de premi&egrave;re classe\r\n- Permet de grouper des commandes avec le pattern Composite\r\n- Ajout facile de nouvelles commandes\r\n\r\n**Inconv&eacute;nients** **:**\r\n\r\n- Les commandes doivent &ecirc;tre lin&eacute;aires dans le temps\r\n\r\n**Impl&eacute;mentation** : Les actions deviennent des classes comportant une m&eacute;thode d&rsquo;&eacute;x&eacute;cution et &eacute;ventuellement une m&eacute;thode undo. Le client demande &agrave; un `Receiver` d&rsquo;&eacute;x&eacute;cuter une commande. Un `Invoker` g&eacute;re l&rsquo;&eacute;x&eacute;cution des `ConcreteCommand` (qui elles impl&eacute;mentent `Command`). \r\n\r\n```java\r\npublic interface Command {\r\n	void execute();\r\n	void undo();\r\n}\r\n\r\npublic class ConcreteCommand implements Command {\r\n	public ConcreteCommand(...) {...} // optionnal, if the command has parameters\r\n	public void execute() {...}\r\n	public void undo() {...}\r\n}\r\n\r\npublic class Invoker {\r\n	private Stack&lt;Command&gt; history;\r\n	public Invoker() { history = new Stack(); }\r\n	public execute(Command cmd) {\r\n		history.push(cmd);\r\n		cmd.execute();\r\n	}\r\n	public undo() {\r\n		history.peek().undo();\r\n		history.pop();\r\n	}\r\n}\r\n```\r\n\r\n# Observateur\r\n\r\n**Probl&egrave;me** : MVC, avoir un couplage entre diffuseur et abonn&eacute;. Synchroniser des classes coop&eacute;rantes tout en les laissant ind&eacute;pendantes.\r\n\r\n**Utilisations** :\r\n\r\n- D&eacute;finir une interd&eacute;pendance de type **OneToMany** entre des objets\r\n- Informer tous les objets d&eacute;pendants d&rsquo;un diffuseur lorsque l&rsquo;&eacute;tat de celui-ci change\r\n\r\n**Avantages** : \r\n\r\n- Couplage faible entre sujet et observateurs, n&rsquo;importe la classe et le moment\r\n- Support de la diffusion d&rsquo;&eacute;v&eacute;nements\r\n\r\n**Inconv&eacute;nients** **:**\r\n\r\n- Peut causer des cascades de mises &agrave; jour car les observateurs ne connaissent pas la pr&eacute;sence des autres observateurs\r\n\r\n**Impl&eacute;mentation** : `Observable` garde une liste de ses `Observateurs`, il notifie tous ses sujets lors d&rsquo;un changement en appelant leur m&eacute;thode (et en passant un message en param&egrave;tre si besoin)\r\n\r\n```java\r\npublic interface Observer {\r\n	void udpate(...); // possibility to specify a message or other stuff\r\n}\r\n\r\npublic abstract class Observable {\r\n	private List&lt;Observer&gt; observers;\r\n	public void add(Observer o) { observers.add(o) }\r\n	public void remove(Observer o) { observers.remove(o) }\r\n	public void notify(...) { // possibility to specify a message or other stuff\r\n		observers.forEach(o -&gt; o.notify(...));\r\n	}\r\n}\r\n\r\npublic class ConcreteObserver implements Observer {\r\n	public void update(...) {...}\r\n}\r\n\r\npublic class ConcreteObservable extends Observable {}\r\n```\r\n\r\n# It&eacute;rateur\r\n\r\n**Probl&egrave;me** : Parcourir l&rsquo;ensemble d&rsquo;une Liste / Collection dans l&rsquo;ordre, sans se soucier de l&rsquo;organisation de celle-ci\r\n\r\n**Utilisations** :\r\n\r\n- Parcourir s&eacute;qentiellement des agr&eacute;gats sans conna&icirc;tre leur structure interne\r\n- Parcourir des &eacute;l&eacute;ments sans conna&icirc;tre leur type\r\n\r\n**Avantages** : \r\n\r\n- Simplifier l&rsquo;interface de l&rsquo;agr&eacute;gat\r\n- G&eacute;rer plusieurs parcours diff&eacute;rents d&rsquo;un agr&eacute;gat\r\n- Modifier le m&eacute;canisme de parcours d&rsquo;un agr&eacute;gat\r\n\r\n**Inconv&eacute;nients** **:**\r\n\r\n- L&rsquo;agr&eacute;gat doit ne pas &ecirc;tre modifi&eacute; pendant qu&rsquo;il est parcouru\r\n\r\n**Impl&eacute;mentation** : D&eacute;l&eacute;guer le parcours d&rsquo;un agr&eacute;gat dans un objet annexe, &eacute;ventuellement ajouter des op&eacute;rations telles que `previous()`\r\n\r\n```java\r\npublic class Type;\r\n\r\npublic interface TypeIterable {\r\n	TypeIterator createIterator();\r\n}\r\n\r\npublic interface TypeIterator {\r\n	boolean hasNext();\r\n	Type next();\r\n	Type current();\r\n}\r\n\r\npublic class ConcreteTypeIterator implements TypeIterator {\r\n	TypeIterable collection; // agregate\r\n	Type current; // current step\r\n\r\n	... // variables keeping track of what has already been visited\r\n\r\n	public ConcreteTypeIterator(TypeIterable data) {\r\n		collection = data;\r\n	}\r\n	\r\n	public boolean hasNext() {...}\r\n	public Type next() {...}\r\n	public Type current() {...}\r\n}\r\n\r\npublic abstract class ConcreteTypeIterable implements TypeIterable  {\r\n\r\n	...\r\n\r\n	public TypeIterator createIterator() {\r\n		return new ConcreteTypeIterator(this);\r\n	}\r\n}\r\n```','2022-12-23 20:27:00',0,'2022-12-23 20:27:00','035cc763-983b-4fb0-807e-b6f81f22afd5','788ac959-d544-4c1d-988d-5bbde8727f79','d9f14880-5f31-49a6-acd9-6f8a1405fcb3'),('3bd43284-7acd-4c96-ac09-ad8d67ce24af','\n\n# Visiteur\r\n\r\n**Probl&egrave;me** : Aiguiller dynamiquement pour s&eacute;parer les actions des objets sur lequels elles op&eacute;rent\r\n\r\n**Utilisations** :\r\n\r\n- effecteur des op&eacute;rations sans se soucier de la compatibilit&eacute; avec des types d&rsquo;&eacute;l&eacute;ment trait&eacute;s\r\n\r\n**Avantages** : \r\n\r\n- facile d&rsquo;ajouter de nouvelles op&eacute;rations, un visiteur = une op&eacute;ration\r\n- regroupe les actions communes dans `Visiteur` tout en permettant d&rsquo;autres\r\n- peut accumuler un &eacute;tat\r\n\r\n**Inconv&eacute;nients** **:**\r\n\r\n- l&rsquo;ajout de nouveaux types concrets est difficile, devant modifier tous les visiteurs\r\n- les op&eacute;rations ont peut &ecirc;tre besoin d&rsquo;&ecirc;tre riches, cassant le principe d&rsquo;encapsulation\r\n\r\n**Impl&eacute;mentation** : Double Dispatch, les classes `Visitables` acceptent des visiteurs et &eacute;x&eacute;cutent les bonnes m&eacute;thodes de ceux-ci en fonction de leur type.\r\n\r\n```java\r\npublic interface Visitor {\r\n	void visitType1(Type1 o);\r\n	void visitType2(Type2 o);\r\n}\r\n\r\npublic class ConcreteVisitor implements Visitor {\r\n	public void visitType1(Type1 o) {...}\r\n	public void visitType2(Type2 o) {...}\r\n}\r\n\r\npublic class OtherConcreteVisitor implements Visitor {\r\n	public void visitType1(Type1 o) {...}\r\n	public void visitType2(Type2 o) {...}\r\n}\r\n\r\npublic interface Visitable {\r\n	void accept(Visitor v);\r\n}\r\n\r\npublic class Type1 implements Visitable {\r\n	public void accept(Visitor v) { v.visitType1(this); }\r\n	...\r\n}\r\n\r\npublic class Type2 implements Visitable {\r\n	public void accept(Visitor v) { v.visitType2(this); }\r\n	...\r\n}\r\n```\r\n\r\n# Etat\r\n\r\n**Probl&egrave;me** : Repr&eacute;senter diff&eacute;rents &eacute;tats d&rsquo;un syst&egrave;me\r\n\r\n**Utilisations** :\r\n\r\n- modifier le comportement d&rsquo;un objet lorsque son &eacute;tat interne change\r\n- pouvoir choisir des op&eacute;rations conditionnellement &agrave; l&rsquo;&eacute;tat\r\n\r\n**Avantages** : \r\n\r\n- s&eacute;pare les comportements relatifs &agrave; chaque &eacute;tat\r\n- facilite l&rsquo;ajout et la suppression d&rsquo;&eacute;tats\r\n- rend les transitions plus explicites\r\n\r\n**Inconv&eacute;nients** **:**\r\n\r\n- cr&eacute;e de nouveaux objets\r\n\r\n**Impl&eacute;mentation** : Cr&eacute;er une classe pour chaque &eacute;tat diff&eacute;rent, avec les actions modifi&eacute;es en cons&eacute;quence\r\n\r\n```java\r\npublic interface ObjectState {\r\n	void action(Object o);\r\n}\r\n\r\npublic class SomeObject {\r\n	private ObjectState state;\r\n	...\r\n	public void setState(ObjectState s) { state = s; }\r\n	public void action() { s.action(); }\r\n}\r\n\r\npublic class ObjectState1 implements ObjectState {\r\n	public void action(Object o) {...} // calls o.setState(new ObjectState2())\r\n}\r\n\r\npublic class ObjectState2 implements ObjectState {\r\n	public void action(Object o) {...}\r\n}\r\n```\r\n\r\n# Strat&eacute;gie\r\n\r\n**Probl&egrave;me** : repr&eacute;senter et utiliser diff&eacute;rentes variantes d&rsquo;un m&ecirc;me algorithme\r\n\r\n**Utilisations** :\r\n\r\n- encapsuler des algorithmes de la m&ecirc;me famille, les rendre interchangeables\r\n- choisir une variante &agrave; utiliser en fonction du contexte\r\n- permettre aux algorithmes d&rsquo;&eacute;voluer sans impacter les clients (versionning)\r\n\r\n**Avantages** : \r\n\r\n- alternative &agrave; l&rsquo;h&eacute;ritage pour obtenir de nouvelles variantes\r\n- s&eacute;paration de l&rsquo;impl&eacute;mentation et du contexte de l&rsquo;algorithme\r\n- &eacute;limine les conditionnelles pour le choix d&rsquo;une variante d&rsquo;algorithme\r\n- fournit diff&eacute;rentes impl&eacute;mentations du m&ecirc;me comportement\r\n\r\n**Inconv&eacute;nients** **:**\r\n\r\n- augmentation du nombre d&rsquo;objets\r\n- n&eacute;cessite de conna&icirc;tre les possibilit&eacute;s concernant les variations\r\n- n&eacute;cessite l&rsquo;utilisation de la m&ecirc;me interface pour toutes les variations\r\n\r\n**Impl&eacute;mentation** : Pour l&rsquo;objet cible, ajouter un attribut `Strat&eacute;gie` ainsi que un setter, l&rsquo;action &agrave; &eacute;x&eacute;cuter fera appel &agrave; la m&eacute;thode de celle-ci\r\n\r\n```java\r\npublic interface Strategy {\r\n	void doSomething();\r\n}\r\n\r\npublic SomeObject {\r\n	...\r\n	private Strategy strategy;\r\n	public void setStrategy(Strategy s) { strategy = s; }\r\n	void doSomething() { s.doSomething(); }\r\n	...\r\n}\r\n\r\npublic class ConcreteStrategy1 implements Strategy {\r\n	void doSomething();\r\n}\r\n\r\npublic class ConcreteStrategy2 implements Strategy {\r\n	void doSomething();\r\n}\r\n```','2022-12-23 20:27:14',0,'2022-12-23 20:27:14','035cc763-983b-4fb0-807e-b6f81f22afd5','0d656253-afc3-4293-9437-21f1bea951e2','d9f14880-5f31-49a6-acd9-6f8a1405fcb3');
/*!40000 ALTER TABLE `Post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Post_Post`
--

DROP TABLE IF EXISTS `Post_Post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Post_Post` (
  `Post_id` varchar(36) NOT NULL,
  `children_id` varchar(36) NOT NULL,
  UNIQUE KEY `UK_c2tes0wcfqr4s3hj5j1c7lktx` (`children_id`),
  KEY `FKo2mdax05d1td61j4urlso0rro` (`Post_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Post_Post`
--

LOCK TABLES `Post_Post` WRITE;
/*!40000 ALTER TABLE `Post_Post` DISABLE KEYS */;
INSERT INTO `Post_Post` VALUES ('ceeb5907-d5b3-4de2-a6e9-ba55192e13e6','c4200f1f-4093-460e-9547-aca6421ba8d9'),('c4200f1f-4093-460e-9547-aca6421ba8d9','d5a664cf-d130-40f5-b9e3-b763aea55e71'),('301e9876-d98f-4656-af09-db76b886b6db','9546ef3e-c049-4305-b67e-e6f9b62a6c86'),('9546ef3e-c049-4305-b67e-e6f9b62a6c86','7c5cd9de-8e82-47aa-b506-928f00ba628a'),('301e9876-d98f-4656-af09-db76b886b6db','77725ef4-d0c6-4629-a200-a4c7b1cd4403'),('77725ef4-d0c6-4629-a200-a4c7b1cd4403','300ae64e-af87-4281-a8e1-8168deaacb16'),('300ae64e-af87-4281-a8e1-8168deaacb16','dffa5e3a-75db-4264-b136-16494aa2fafc'),('dffa5e3a-75db-4264-b136-16494aa2fafc','3a2a9470-2ab8-4636-9e8e-8cfd1e7a7f35'),('a1f8635b-4833-4057-975a-1e18b7b319c1','df5968e4-0bb9-498c-941e-9a42bd30e952'),('df5968e4-0bb9-498c-941e-9a42bd30e952','9ebaf5ed-97da-4151-a2dc-3a551464441a'),('9ebaf5ed-97da-4151-a2dc-3a551464441a','a1442681-4b66-4779-9e02-9b4ff07c576e'),('a1442681-4b66-4779-9e02-9b4ff07c576e','7affca00-9199-432e-9885-6441b73d712b'),('a1442681-4b66-4779-9e02-9b4ff07c576e','b13e4799-9e46-4d5d-9978-2e43e68db10b'),('45cd7b10-ae06-47e4-82a6-e774c7aa8580','b60351af-7c7e-4e12-b734-042771e3c170'),('b60351af-7c7e-4e12-b734-042771e3c170','1096660b-463f-44cf-bfcd-0bd35e2b3a46'),('1096660b-463f-44cf-bfcd-0bd35e2b3a46','72d0f465-6c59-4270-ab4a-b1b89029d9a3'),('72d0f465-6c59-4270-ab4a-b1b89029d9a3','0fc5a557-190f-493f-af0a-e8cf21cbfaec'),('788ac959-d544-4c1d-988d-5bbde8727f79','0d656253-afc3-4293-9437-21f1bea951e2'),('0d656253-afc3-4293-9437-21f1bea951e2','3bd43284-7acd-4c96-ac09-ad8d67ce24af');
/*!40000 ALTER TABLE `Post_Post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Post_User`
--

DROP TABLE IF EXISTS `Post_User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Post_User` (
  `Post_id` varchar(36) NOT NULL,
  `votes_id` varchar(36) NOT NULL,
  PRIMARY KEY (`Post_id`,`votes_id`),
  KEY `FK3dbl95cckbgmo2hfamljq0ni8` (`votes_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Post_User`
--

LOCK TABLES `Post_User` WRITE;
/*!40000 ALTER TABLE `Post_User` DISABLE KEYS */;
INSERT INTO `Post_User` VALUES ('0d656253-afc3-4293-9437-21f1bea951e2','035cc763-983b-4fb0-807e-b6f81f22afd5'),('0d656253-afc3-4293-9437-21f1bea951e2','4e6bad9d-7410-4e6a-b201-8d14796f1e13'),('0d656253-afc3-4293-9437-21f1bea951e2','6ff362ae-a64a-4be2-be65-9f8c046688c5'),('0d656253-afc3-4293-9437-21f1bea951e2','8dc24f84-57bf-410f-b9d0-1714421a141f'),('0d656253-afc3-4293-9437-21f1bea951e2','e375dd97-b0e5-4560-993d-81630b80db31'),('301e9876-d98f-4656-af09-db76b886b6db','035cc763-983b-4fb0-807e-b6f81f22afd5'),('301e9876-d98f-4656-af09-db76b886b6db','4e6bad9d-7410-4e6a-b201-8d14796f1e13'),('301e9876-d98f-4656-af09-db76b886b6db','6ff362ae-a64a-4be2-be65-9f8c046688c5'),('3a2a9470-2ab8-4636-9e8e-8cfd1e7a7f35','4e6bad9d-7410-4e6a-b201-8d14796f1e13'),('3bd43284-7acd-4c96-ac09-ad8d67ce24af','035cc763-983b-4fb0-807e-b6f81f22afd5'),('3bd43284-7acd-4c96-ac09-ad8d67ce24af','4e6bad9d-7410-4e6a-b201-8d14796f1e13'),('3bd43284-7acd-4c96-ac09-ad8d67ce24af','6ff362ae-a64a-4be2-be65-9f8c046688c5'),('3bd43284-7acd-4c96-ac09-ad8d67ce24af','8dc24f84-57bf-410f-b9d0-1714421a141f'),('3bd43284-7acd-4c96-ac09-ad8d67ce24af','e375dd97-b0e5-4560-993d-81630b80db31'),('72d0f465-6c59-4270-ab4a-b1b89029d9a3','8dc24f84-57bf-410f-b9d0-1714421a141f'),('72d0f465-6c59-4270-ab4a-b1b89029d9a3','e375dd97-b0e5-4560-993d-81630b80db31'),('77725ef4-d0c6-4629-a200-a4c7b1cd4403','4e6bad9d-7410-4e6a-b201-8d14796f1e13'),('788ac959-d544-4c1d-988d-5bbde8727f79','035cc763-983b-4fb0-807e-b6f81f22afd5'),('788ac959-d544-4c1d-988d-5bbde8727f79','4e6bad9d-7410-4e6a-b201-8d14796f1e13'),('788ac959-d544-4c1d-988d-5bbde8727f79','6ff362ae-a64a-4be2-be65-9f8c046688c5'),('788ac959-d544-4c1d-988d-5bbde8727f79','8dc24f84-57bf-410f-b9d0-1714421a141f'),('788ac959-d544-4c1d-988d-5bbde8727f79','e375dd97-b0e5-4560-993d-81630b80db31'),('a1442681-4b66-4779-9e02-9b4ff07c576e','035cc763-983b-4fb0-807e-b6f81f22afd5'),('a1442681-4b66-4779-9e02-9b4ff07c576e','4e6bad9d-7410-4e6a-b201-8d14796f1e13'),('a1442681-4b66-4779-9e02-9b4ff07c576e','6ff362ae-a64a-4be2-be65-9f8c046688c5'),('a1442681-4b66-4779-9e02-9b4ff07c576e','8dc24f84-57bf-410f-b9d0-1714421a141f'),('b13e4799-9e46-4d5d-9978-2e43e68db10b','4e6bad9d-7410-4e6a-b201-8d14796f1e13'),('b60351af-7c7e-4e12-b734-042771e3c170','e375dd97-b0e5-4560-993d-81630b80db31'),('c4200f1f-4093-460e-9547-aca6421ba8d9','035cc763-983b-4fb0-807e-b6f81f22afd5'),('c4200f1f-4093-460e-9547-aca6421ba8d9','6ff362ae-a64a-4be2-be65-9f8c046688c5'),('ceeb5907-d5b3-4de2-a6e9-ba55192e13e6','035cc763-983b-4fb0-807e-b6f81f22afd5'),('d5a664cf-d130-40f5-b9e3-b763aea55e71','035cc763-983b-4fb0-807e-b6f81f22afd5'),('dffa5e3a-75db-4264-b136-16494aa2fafc','035cc763-983b-4fb0-807e-b6f81f22afd5'),('dffa5e3a-75db-4264-b136-16494aa2fafc','4e6bad9d-7410-4e6a-b201-8d14796f1e13'),('dffa5e3a-75db-4264-b136-16494aa2fafc','6ff362ae-a64a-4be2-be65-9f8c046688c5');
/*!40000 ALTER TABLE `Post_User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tag`
--

DROP TABLE IF EXISTS `Tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Tag` (
  `id` varchar(16) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tag`
--

LOCK TABLES `Tag` WRITE;
/*!40000 ALTER TABLE `Tag` DISABLE KEYS */;
INSERT INTO `Tag` VALUES ('java','Java is a popular programming language','Java'),('swing','Swing is a Java GUI widget toolkit','Swing'),('jdbc','JDBC is a Java API for accessing databases','JDBC'),('javafx','JavaFX is a Java GUI widget toolkit','JavaFX');
/*!40000 ALTER TABLE `Tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Thread`
--

DROP TABLE IF EXISTS `Thread`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Thread` (
  `id` varchar(36) NOT NULL,
  `locked` int NOT NULL,
  `removed` int NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `entry_id` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK19csn91jhyrqv1dhxhbegk0wr` (`entry_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Thread`
--

LOCK TABLES `Thread` WRITE;
/*!40000 ALTER TABLE `Thread` DISABLE KEYS */;
INSERT INTO `Thread` VALUES ('dd11e9b8-38a6-4257-a0e9-613faef1e716',0,0,'Java Web Tips','45cd7b10-ae06-47e4-82a6-e774c7aa8580'),('87d3a0b3-f388-4500-838c-b6b0ab6cee54',0,0,'Swing layouts','a1f8635b-4833-4057-975a-1e18b7b319c1'),('1825d89b-e68b-448b-819e-0fc6f30b20e2',0,0,'Issues with javafx textfields','ceeb5907-d5b3-4de2-a6e9-ba55192e13e6'),('22389a66-91fd-4616-a386-38e369eec25c',0,0,'Java Reflections','301e9876-d98f-4656-af09-db76b886b6db'),('d9f14880-5f31-49a6-acd9-6f8a1405fcb3',0,0,'Design Patterns','788ac959-d544-4c1d-988d-5bbde8727f79');
/*!40000 ALTER TABLE `Thread` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Thread_Post`
--

DROP TABLE IF EXISTS `Thread_Post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Thread_Post` (
  `Thread_id` varchar(36) NOT NULL,
  `posts_id` varchar(36) NOT NULL,
  PRIMARY KEY (`Thread_id`,`posts_id`),
  UNIQUE KEY `UK_7ag17tel6rmagofyehuwv9w9e` (`posts_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Thread_Post`
--

LOCK TABLES `Thread_Post` WRITE;
/*!40000 ALTER TABLE `Thread_Post` DISABLE KEYS */;
INSERT INTO `Thread_Post` VALUES ('1825d89b-e68b-448b-819e-0fc6f30b20e2','c4200f1f-4093-460e-9547-aca6421ba8d9'),('1825d89b-e68b-448b-819e-0fc6f30b20e2','ceeb5907-d5b3-4de2-a6e9-ba55192e13e6'),('1825d89b-e68b-448b-819e-0fc6f30b20e2','d5a664cf-d130-40f5-b9e3-b763aea55e71'),('22389a66-91fd-4616-a386-38e369eec25c','300ae64e-af87-4281-a8e1-8168deaacb16'),('22389a66-91fd-4616-a386-38e369eec25c','301e9876-d98f-4656-af09-db76b886b6db'),('22389a66-91fd-4616-a386-38e369eec25c','3a2a9470-2ab8-4636-9e8e-8cfd1e7a7f35'),('22389a66-91fd-4616-a386-38e369eec25c','77725ef4-d0c6-4629-a200-a4c7b1cd4403'),('22389a66-91fd-4616-a386-38e369eec25c','7c5cd9de-8e82-47aa-b506-928f00ba628a'),('22389a66-91fd-4616-a386-38e369eec25c','9546ef3e-c049-4305-b67e-e6f9b62a6c86'),('22389a66-91fd-4616-a386-38e369eec25c','dffa5e3a-75db-4264-b136-16494aa2fafc'),('87d3a0b3-f388-4500-838c-b6b0ab6cee54','7affca00-9199-432e-9885-6441b73d712b'),('87d3a0b3-f388-4500-838c-b6b0ab6cee54','9ebaf5ed-97da-4151-a2dc-3a551464441a'),('87d3a0b3-f388-4500-838c-b6b0ab6cee54','a1442681-4b66-4779-9e02-9b4ff07c576e'),('87d3a0b3-f388-4500-838c-b6b0ab6cee54','a1f8635b-4833-4057-975a-1e18b7b319c1'),('87d3a0b3-f388-4500-838c-b6b0ab6cee54','b13e4799-9e46-4d5d-9978-2e43e68db10b'),('87d3a0b3-f388-4500-838c-b6b0ab6cee54','df5968e4-0bb9-498c-941e-9a42bd30e952'),('d9f14880-5f31-49a6-acd9-6f8a1405fcb3','0d656253-afc3-4293-9437-21f1bea951e2'),('d9f14880-5f31-49a6-acd9-6f8a1405fcb3','3bd43284-7acd-4c96-ac09-ad8d67ce24af'),('d9f14880-5f31-49a6-acd9-6f8a1405fcb3','788ac959-d544-4c1d-988d-5bbde8727f79'),('dd11e9b8-38a6-4257-a0e9-613faef1e716','0fc5a557-190f-493f-af0a-e8cf21cbfaec'),('dd11e9b8-38a6-4257-a0e9-613faef1e716','1096660b-463f-44cf-bfcd-0bd35e2b3a46'),('dd11e9b8-38a6-4257-a0e9-613faef1e716','45cd7b10-ae06-47e4-82a6-e774c7aa8580'),('dd11e9b8-38a6-4257-a0e9-613faef1e716','72d0f465-6c59-4270-ab4a-b1b89029d9a3'),('dd11e9b8-38a6-4257-a0e9-613faef1e716','b60351af-7c7e-4e12-b734-042771e3c170');
/*!40000 ALTER TABLE `Thread_Post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Thread_Tag`
--

DROP TABLE IF EXISTS `Thread_Tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Thread_Tag` (
  `Thread_id` varchar(36) NOT NULL,
  `tags_id` varchar(16) NOT NULL,
  KEY `FKrtgnmspl4xu6yi09uicepct8g` (`tags_id`),
  KEY `FKb55751b2ec5car0eaj1tr3mv4` (`Thread_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Thread_Tag`
--

LOCK TABLES `Thread_Tag` WRITE;
/*!40000 ALTER TABLE `Thread_Tag` DISABLE KEYS */;
INSERT INTO `Thread_Tag` VALUES ('dd11e9b8-38a6-4257-a0e9-613faef1e716','java'),('87d3a0b3-f388-4500-838c-b6b0ab6cee54','java'),('87d3a0b3-f388-4500-838c-b6b0ab6cee54','swing'),('1825d89b-e68b-448b-819e-0fc6f30b20e2','javafx'),('22389a66-91fd-4616-a386-38e369eec25c','java'),('d9f14880-5f31-49a6-acd9-6f8a1405fcb3','java');
/*!40000 ALTER TABLE `Thread_Tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User` (
  `id` varchar(36) NOT NULL,
  `admin` int NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `points` int DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES ('e375dd97-b0e5-4560-993d-81630b80db31',0,'pierre.dupont@gmail.com','$2a$10$i9M5UzuZuCDN53QvgZ/1/OQ6h.b3G5gFwKydbM81IbBZ/drG4Tbwe',0,'pierredupont'),('8dc24f84-57bf-410f-b9d0-1714421a141f',0,'marie.martin@gmail.com','$2a$10$i9M5UzuZuCDN53QvgZ/1/OQ6h.b3G5gFwKydbM81IbBZ/drG4Tbwe',1,'mariemartin'),('5b57f881-8b61-4936-9ca4-c314eb44c4cc',0,'jacques.leblanc@gmail.com','$2a$10$i9M5UzuZuCDN53QvgZ/1/OQ6h.b3G5gFwKydbM81IbBZ/drG4Tbwe',0,'jacquesleblanc'),('c873b43a-292f-4a22-9ccf-48ab2ccc4e01',0,'chloe.durand@gmail.com','$2a$10$i9M5UzuZuCDN53QvgZ/1/OQ6h.b3G5gFwKydbM81IbBZ/drG4Tbwe',0,'chloedurand'),('6ff362ae-a64a-4be2-be65-9f8c046688c5',0,'antoine.moreau@gmail.com','$2a$10$i9M5UzuZuCDN53QvgZ/1/OQ6h.b3G5gFwKydbM81IbBZ/drG4Tbwe',4,'antoinemoreau'),('dba3f409-4bf6-4336-82ee-fb899b4e59de',0,'sophie.roy@gmail.com','$2a$10$i9M5UzuZuCDN53QvgZ/1/OQ6h.b3G5gFwKydbM81IbBZ/drG4Tbwe',0,'sophieroy'),('4e6bad9d-7410-4e6a-b201-8d14796f1e13',0,'etienne.duval@gmail.com','$2a$10$i9M5UzuZuCDN53QvgZ/1/OQ6h.b3G5gFwKydbM81IbBZ/drG4Tbwe',4,'etienneduval'),('035cc763-983b-4fb0-807e-b6f81f22afd5',0,'eva.lefevre@gmail.com','$2a$10$i9M5UzuZuCDN53QvgZ/1/OQ6h.b3G5gFwKydbM81IbBZ/drG4Tbwe',9,'evalefevre'),('c1b12dcf-0293-4aa8-9912-f18a567165b6',0,'nathan.dubois@gmail.com','$2a$10$i9M5UzuZuCDN53QvgZ/1/OQ6h.b3G5gFwKydbM81IbBZ/drG4Tbwe',0,'nathandubois'),('e55bfe53-cc4d-4c30-b330-4df2b95e9b05',0,'mod@palmes.dev','$2a$10$BW9ByXr6oxatVdEzMO.KWeWS7q8gWLacpMvTfgRZNSsyQRYdavcYi',0,'mr_mod');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-23 20:32:56
