CREATE DATABASE  IF NOT EXISTS `wifiwolf` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `wifiwolf`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: wifiwolf
-- ------------------------------------------------------
-- Server version	5.5.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_auth_page`
--

DROP TABLE IF EXISTS `t_auth_page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_auth_page` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customize_html` text,
  `template_id` bigint(20) DEFAULT NULL,
  `customize_url` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_auth_page`
--

LOCK TABLES `t_auth_page` WRITE;
/*!40000 ALTER TABLE `t_auth_page` DISABLE KEYS */;
INSERT INTO `t_auth_page` VALUES (1,'&amp;lt;%@ page contentType=&amp;quot;text/html;charset=UTF-8&amp;quot;%&amp;gt;\r\n&amp;lt;%@ include file=&amp;quot;/WEB-INF/views/include/taglib.jsp&amp;quot;%&amp;gt;\r\n&amp;lt;html&amp;gt;\r\n&amp;lt;head&amp;gt;\r\n&amp;lt;title&amp;gt;认证方式设置&amp;lt;/title&amp;gt;\r\n&amp;lt;script&amp;gt;\r\n	$(document).ready(function() {\r\n		var initModal = function(template) {\r\n			$(&amp;quot;a.thumbnail&amp;quot;).each(function() {\r\n				$(this).css(&amp;quot;background-color&amp;quot;, &amp;quot;white&amp;quot;);\r\n			});\r\n			var selectedItem = $(&amp;quot;#&amp;quot; + template).val();\r\n			$(&amp;quot;#&amp;quot; + selectedItem + &amp;quot;Img&amp;quot;).css(&amp;quot;background-color&amp;quot;, &amp;quot;blue&amp;quot;);\r\n		};\r\n		var itemClicked = function(template, item) {\r\n			$(&amp;quot;a.thumbnail&amp;quot;).each(function() {\r\n				$(this).css(&amp;quot;background-color&amp;quot;, &amp;quot;white&amp;quot;);\r\n			});\r\n			$(item).css(&amp;quot;background-color&amp;quot;, &amp;quot;blue&amp;quot;);\r\n			var str = $(item).attr(&amp;quot;id&amp;quot;);\r\n			str = str.substr(0, str.length - 3);\r\n			$(&amp;quot;#&amp;quot; + template).val(str);\r\n		};\r\n\r\n		$(&amp;quot;#authPageTemplates&amp;quot;).on(&amp;quot;shown.bs.modal&amp;quot;, function() {\r\n			initModal(\'authTemplate\')\r\n		});\r\n		$(&amp;quot;#portalPageTemplates&amp;quot;).on(&amp;quot;shown.bs.modal&amp;quot;, function() {\r\n			initModal(\'portalTemplate\')\r\n		});\r\n		$(&amp;quot;div#authPageTemplates a.thumbnail&amp;quot;).click(function() {\r\n			itemClicked(&amp;quot;authTemplate&amp;quot;, this)\r\n		});\r\n		$(&amp;quot;div#portalPageTemplates a.thumbnail&amp;quot;).click(function() {\r\n			itemClicked(&amp;quot;portalTemplate&amp;quot;, this)\r\n		});\r\n		$(&amp;quot;#inputHtmlAuthPage&amp;quot;).click(function(){\r\n			$(&amp;quot;#customizeHtml&amp;quot;).val($(&amp;quot;#authPageCustomizeHtml&amp;quot;).val());\r\n			$(&amp;quot;#customizeHtmlType&amp;quot;).val(&amp;quot;authPage&amp;quot;);\r\n			$(&amp;quot;#customizeHtmlModel&amp;quot;).modal();\r\n		});\r\n		$(&amp;quot;#intputHtmlPortalPage&amp;quot;).click(function(){\r\n			$(&amp;quot;#customizeHtml&amp;quot;).val($(&amp;quot;#portalPageCustomizeHtml&amp;quot;).val());\r\n			$(&amp;quot;#customizeHtmlType&amp;quot;).val(&amp;quot;portalPage&amp;quot;);\r\n			$(&amp;quot;#customizeHtmlModel&amp;quot;).modal();\r\n		});\r\n		$(&amp;quot;#saveCustomizeHtml&amp;quot;).click(function(){\r\n			var customizeHtmlType=$(&amp;quot;#customizeHtmlType&amp;quot;).val();\r\n			$(&amp;quot;#&amp;quot;+customizeHtmlType+&amp;quot;CustomizeHtml&amp;quot;).val($(&amp;quot;#customizeHtml&amp;quot;).val());\r\n			$(&amp;quot;#customizeHtmlModel&amp;quot;).modal(\'toggle\');\r\n		});\r\n		\r\n		$(&amp;quot;#submitForm&amp;quot;).click(function(){\r\n			if($(&amp;quot;#authPageTypeRadios2&amp;quot;).prop(&amp;quot;checked&amp;quot;)==true &amp;amp;&amp;amp; $(&amp;quot;#authPageCustomizeUrl&amp;quot;).val()==&amp;quot;&amp;quot;){\r\n				alert(&amp;quot;自定义认证页面URL不能为空！&amp;quot;);\r\n				return;\r\n			}\r\n			if($(&amp;quot;#authPageTypeRadios3&amp;quot;).prop(&amp;quot;checked&amp;quot;)==true &amp;amp;&amp;amp; $(&amp;quot;#authPageCustomizeHtml&amp;quot;).val()==&amp;quot;&amp;quot;){\r\n				alert(&amp;quot;自定义认证页面HTML内容不能为空！&amp;quot;);return;\r\n			}\r\n			\r\n			if($(&amp;quot;#portalPageTypeRadios3&amp;quot;).prop(&amp;quot;checked&amp;quot;)==true &amp;amp;&amp;amp; $(&amp;quot;#portalPageCustomizeUrl&amp;quot;).val()==&amp;quot;&amp;quot;){\r\n				alert(&amp;quot;自定义认证后页面URL不能为空！&amp;quot;);return;\r\n			}\r\n			if($(&amp;quot;#portalPageTypeRadios4&amp;quot;).prop(&amp;quot;checked&amp;quot;)==true &amp;amp;&amp;amp; $(&amp;quot;#portalPageCustomizeHtml&amp;quot;).val()==&amp;quot;&amp;quot;){\r\n				alert(&amp;quot;自定义认证后页面HTML内容不能为空！&amp;quot;);return;\r\n			}\r\n			console.log(&amp;quot;submiting form&amp;quot;);\r\n			$(&amp;quot;#form&amp;quot;).submit();\r\n		});\r\n	});\r\n&amp;lt;/script&amp;gt;\r\n&amp;lt;/head&amp;gt;\r\n\r\n&amp;lt;body&amp;gt;\r\n\r\n	&amp;lt;div class=&amp;quot;container-fluid&amp;quot;&amp;gt;\r\n		&amp;lt;ul class=&amp;quot;nav nav-tabs&amp;quot;&amp;gt;\r\n			&amp;lt;li class=&amp;quot;active&amp;quot;&amp;gt;&amp;lt;a href=&amp;quot;${ctx}/manage/authType&amp;quot;&amp;gt;认证方式设置列表&amp;lt;/a&amp;gt;&amp;lt;/li&amp;gt;\r\n		&amp;lt;/ul&amp;gt;\r\n		&amp;lt;br /&amp;gt;\r\n		&amp;lt;div class=&amp;quot;container-fluid&amp;quot;&amp;gt;\r\n			&amp;lt;div class=&amp;quot;row&amp;quot;&amp;gt;\r\n				&amp;lt;div class=&amp;quot;col-md-12&amp;quot;&amp;gt;\r\n					&amp;lt;tags:message content=&amp;quot;${message}&amp;quot; /&amp;gt;\r\n					&amp;lt;div class=&amp;quot;panel panel-default&amp;quot;&amp;gt;\r\n						&amp;lt;div class=&amp;quot;panel-heading&amp;quot;&amp;gt;\r\n							&amp;lt;h3 class=&amp;quot;panel-title&amp;quot;&amp;gt;认证方式设置&amp;lt;/h3&amp;gt;\r\n						&amp;lt;/div&amp;gt;\r\n						&amp;lt;div class=&amp;quot;panel-body&amp;quot;&amp;gt;\r\n\r\n\r\n							&amp;lt;table id=&amp;quot;contentTable&amp;quot; class=&amp;quot;table table-striped table-bordered table-condensed&amp;quot;&amp;gt;\r\n								&amp;lt;thead&amp;gt;\r\n									&amp;lt;tr&amp;gt;\r\n										&amp;lt;th style=&amp;quot;text-align: center&amp;quot;&amp;gt;序号&amp;lt;/th&amp;gt;\r\n										&amp;lt;th style=&amp;quot;text-align: center&amp;quot;&amp;gt;注册要求&amp;lt;/th&amp;gt;\r\n										&amp;lt;th style=&amp;quot;text-align: center&amp;quot;&amp;gt;登录要求&amp;lt;/th&amp;gt;\r\n										&amp;lt;th style=&amp;quot;text-align: center&amp;quot;&amp;gt;操作&amp;lt;/th&amp;gt;\r\n									&amp;lt;/tr&amp;gt;\r\n								&amp;lt;/thead&amp;gt;\r\n								&amp;lt;tbody&amp;gt;\r\n									&amp;lt;c:forEach items=&amp;quot;${authTypes}&amp;quot; var=&amp;quot;authType&amp;quot; varStatus=&amp;quot;order&amp;quot;&amp;gt;\r\n										&amp;lt;tr&amp;gt;\r\n											&amp;lt;td style=&amp;quot;text-align: center&amp;quot;&amp;gt;${order.count}&amp;amp;nbsp;&amp;lt;/td&amp;gt;\r\n											&amp;lt;td style=&amp;quot;text-align: center&amp;quot;&amp;gt;${ww:getLabel(\'register_type\',authType.registerType,\'\') }&amp;amp;nbsp;&amp;lt;/td&amp;gt;\r\n											&amp;lt;td style=&amp;quot;text-align: center&amp;quot;&amp;gt;${ww:getLabel(\'auth_type\',authType.authType,\'\') }&amp;amp;nbsp;&amp;lt;/td&amp;gt;\r\n											&amp;lt;td style=&amp;quot;text-align: center&amp;quot;&amp;gt;&amp;lt;a\r\n												href=&amp;quot;${ctx}/manage/authTypeStatus?id=${authType.id}&amp;quot; id=&amp;quot;editLink-${order.count}&amp;quot;&amp;gt;${ww:getLabel(\'auth_type_status\', authType.status,\'\') }&amp;lt;/a&amp;gt;&amp;lt;/td&amp;gt;\r\n										&amp;lt;/tr&amp;gt;\r\n									&amp;lt;/c:forEach&amp;gt;\r\n								&amp;lt;/tbody&amp;gt;\r\n							&amp;lt;/table&amp;gt;\r\n						&amp;lt;/div&amp;gt;\r\n					&amp;lt;/div&amp;gt;\r\n				&amp;lt;/div&amp;gt;\r\n			&amp;lt;/div&amp;gt;\r\n			&amp;lt;form id=&amp;quot;form&amp;quot; class=&amp;quot;form-horizontal&amp;quot; method=&amp;quot;post&amp;quot; action=&amp;quot;saveAuthSetting&amp;quot; role=&amp;quot;form&amp;quot;&amp;gt;\r\n				&amp;lt;div class=&amp;quot;panel panel-default&amp;quot;&amp;gt;\r\n					&amp;lt;div class=&amp;quot;panel-heading&amp;quot;&amp;gt;\r\n						&amp;lt;h3 class=&amp;quot;panel-title&amp;quot;&amp;gt;认证页面设置&amp;lt;/h3&amp;gt;\r\n					&amp;lt;/div&amp;gt;\r\n					&amp;lt;div class=&amp;quot;panel-body&amp;quot;&amp;gt;\r\n						&amp;lt;div class=&amp;quot;form-group&amp;quot;&amp;gt;\r\n							&amp;lt;div class=&amp;quot;col-sm-3&amp;quot;&amp;gt;\r\n								&amp;lt;div class=&amp;quot;radio&amp;quot;&amp;gt;\r\n									&amp;lt;label&amp;gt; &amp;lt;input type=&amp;quot;radio&amp;quot; name=&amp;quot;authPageType&amp;quot; id=&amp;quot;authPageTypeRadios1&amp;quot;\r\n									&amp;lt;c:if test=&amp;quot;${authPage.pageTemplate!=null}&amp;quot;&amp;gt;checked=&amp;quot;checked&amp;quot;&amp;lt;/c:if&amp;gt;\r\n										value=&amp;quot;useTemplate&amp;quot;&amp;gt; 启用&amp;lt;b&amp;gt;页面模版&amp;lt;/b&amp;gt;\r\n									&amp;lt;/label&amp;gt;\r\n								&amp;lt;/div&amp;gt;\r\n							&amp;lt;/div&amp;gt;\r\n							&amp;lt;div class=&amp;quot;col-sm-3&amp;quot;&amp;gt;\r\n								&amp;lt;select id=&amp;quot;authTemplate&amp;quot; name=&amp;quot;authTemplate&amp;quot; class=&amp;quot;form-control&amp;quot;&amp;gt;\r\n								&amp;lt;c:forEach items=&amp;quot;${authPageTemplates }&amp;quot; var=&amp;quot;authPageTemplate&amp;quot; varStatus=&amp;quot;i&amp;quot;&amp;gt;\r\n									&amp;lt;option value=&amp;quot;authTemplate${authPageTemplate.id}&amp;quot;\r\n										&amp;lt;c:if test=&amp;quot;${authPage.pageTemplate!=null &amp;amp;&amp;amp; authPageTemplate.id==authPage.pageTemplate.id}&amp;quot;&amp;gt;selected=&amp;quot;selected&amp;quot;&amp;lt;/c:if&amp;gt;\r\n									&amp;gt;${authPageTemplate.templateName}&amp;lt;/option&amp;gt;\r\n								&amp;lt;/c:forEach&amp;gt;\r\n								&amp;lt;/select&amp;gt;\r\n							&amp;lt;/div&amp;gt;\r\n							&amp;lt;div class=&amp;quot;col-sm-1&amp;quot;&amp;gt;\r\n								&amp;lt;button type=&amp;quot;button&amp;quot; class=&amp;quot;btn btn-default&amp;quot; data-toggle=&amp;quot;modal&amp;quot; id=&amp;quot;authPageTemplateBtn&amp;quot;\r\n									data-target=&amp;quot;#authPageTemplates&amp;quot;&amp;gt;效果&amp;lt;/button&amp;gt;\r\n							&amp;lt;/div&amp;gt;\r\n						&amp;lt;/div&amp;gt;\r\n						&amp;lt;div class=&amp;quot;form-group&amp;quot;&amp;gt;\r\n							&amp;lt;div class=&amp;quot;col-sm-3&amp;quot;&amp;gt;\r\n								&amp;lt;div class=&amp;quot;radio&amp;quot;&amp;gt;\r\n									&amp;lt;label&amp;gt; &amp;lt;input type=&amp;quot;radio&amp;quot; name=&amp;quot;authPageType&amp;quot; id=&amp;quot;authPageTypeRadios2&amp;quot;\r\n									&amp;lt;c:if test=&amp;quot;${authPage.customizeUrl!=null}&amp;quot;&amp;gt;checked=&amp;quot;checked&amp;quot;&amp;lt;/c:if&amp;gt;\r\n										value=&amp;quot;useCustomizeUrl&amp;quot;&amp;gt; 启用&amp;lt;b&amp;gt;自定义页面URL&amp;lt;/b&amp;gt;\r\n									&amp;lt;/label&amp;gt;\r\n								&amp;lt;/div&amp;gt;\r\n							&amp;lt;/div&amp;gt;\r\n							&amp;lt;div class=&amp;quot;col-sm-9&amp;quot;&amp;gt;\r\n								&amp;lt;input type=&amp;quot;text&amp;quot; id=&amp;quot;authPageCustomizeUrl&amp;quot; name=&amp;quot;authPageCustomizeUrl&amp;quot;\r\n									class=&amp;quot;form-control&amp;quot; placeholder=&amp;quot;URL&amp;quot; value=&amp;quot;${authPage.customizeUrl }&amp;quot;&amp;gt;\r\n							&amp;lt;/div&amp;gt;\r\n						&amp;lt;/div&amp;gt;\r\n						&amp;lt;div class=&amp;quot;form-group&amp;quot;&amp;gt;\r\n							&amp;lt;div class=&amp;quot;col-sm-3&amp;quot;&amp;gt;\r\n								&amp;lt;div class=&amp;quot;radio&amp;quot;&amp;gt;\r\n									&amp;lt;label&amp;gt; &amp;lt;input type=&amp;quot;radio&amp;quot; name=&amp;quot;authPageType&amp;quot; id=&amp;quot;authPageTypeRadios3&amp;quot;\r\n									&amp;lt;c:if test=&amp;quot;${authPage.customizeHtml!=null}&amp;quot;&amp;gt;checked=&amp;quot;checked&amp;quot;&amp;lt;/c:if&amp;gt;\r\n										value=&amp;quot;useCustomizeHtml&amp;quot;&amp;gt; 启用&amp;lt;b&amp;gt;自定义页面HTML&amp;lt;/b&amp;gt; &amp;lt;input type=&amp;quot;hidden&amp;quot;\r\n										id=&amp;quot;authPageCustomizeHtml&amp;quot; name=&amp;quot;authPageCustomizeHtml&amp;quot; value=&amp;quot;${authPage.customizeHtml }&amp;quot;&amp;gt;\r\n									&amp;lt;/label&amp;gt;\r\n								&amp;lt;/div&amp;gt;\r\n							&amp;lt;/div&amp;gt;\r\n							&amp;lt;div class=&amp;quot;col-sm-1&amp;quot;&amp;gt;\r\n								&amp;lt;button type=&amp;quot;button&amp;quot; id=&amp;quot;inputHtmlAuthPage&amp;quot; class=&amp;quot;btn btn-default &amp;quot;&amp;gt;编辑内容&amp;lt;/button&amp;gt;\r\n							&amp;lt;/div&amp;gt;\r\n						&amp;lt;/div&amp;gt;\r\n					&amp;lt;/div&amp;gt;\r\n				&amp;lt;/div&amp;gt;\r\n\r\n				&amp;lt;div class=&amp;quot;panel panel-default&amp;quot;&amp;gt;\r\n					&amp;lt;div class=&amp;quot;panel-heading&amp;quot;&amp;gt;\r\n						&amp;lt;h3 class=&amp;quot;panel-title&amp;quot;&amp;gt;认证后页面设置&amp;lt;/h3&amp;gt;\r\n					&amp;lt;/div&amp;gt;\r\n					&amp;lt;div class=&amp;quot;panel-body&amp;quot;&amp;gt;\r\n\r\n\r\n						&amp;lt;div class=&amp;quot;form-group&amp;quot;&amp;gt;\r\n							&amp;lt;div class=&amp;quot;col-sm-3&amp;quot;&amp;gt;\r\n								&amp;lt;div class=&amp;quot;radio&amp;quot;&amp;gt;\r\n									&amp;lt;label&amp;gt; &amp;lt;input type=&amp;quot;radio&amp;quot; name=&amp;quot;portalPageType&amp;quot; id=&amp;quot;portalPageTypeRadios1&amp;quot;\r\n									&amp;lt;c:if test=&amp;quot;${portalPage.useOriginUrl!=null}&amp;quot;&amp;gt;checked=&amp;quot;checked&amp;quot;&amp;lt;/c:if&amp;gt;\r\n										value=&amp;quot;useOriginUrl&amp;quot;&amp;gt; 启用&amp;lt;b&amp;gt;初始页面URL&amp;lt;/b&amp;gt;\r\n									&amp;lt;/label&amp;gt;\r\n								&amp;lt;/div&amp;gt;\r\n							&amp;lt;/div&amp;gt;\r\n						&amp;lt;/div&amp;gt;\r\n						&amp;lt;div class=&amp;quot;form-group&amp;quot;&amp;gt;\r\n							&amp;lt;div class=&amp;quot;col-sm-3&amp;quot;&amp;gt;\r\n								&amp;lt;div class=&amp;quot;radio&amp;quot;&amp;gt;\r\n									&amp;lt;label&amp;gt; &amp;lt;input type=&amp;quot;radio&amp;quot; name=&amp;quot;portalPageType&amp;quot; id=&amp;quot;portalPageTypeRadios2&amp;quot;\r\n									&amp;lt;c:if test=&amp;quot;${portalPage.pageTemplate!=null}&amp;quot;&amp;gt;checked=&amp;quot;checked&amp;quot;&amp;lt;/c:if&amp;gt;\r\n										value=&amp;quot;useTemplate&amp;quot;&amp;gt; 启用&amp;lt;b&amp;gt;页面模版&amp;lt;/b&amp;gt;\r\n									&amp;lt;/label&amp;gt;\r\n								&amp;lt;/div&amp;gt;\r\n							&amp;lt;/div&amp;gt;\r\n							&amp;lt;div class=&amp;quot;col-sm-3&amp;quot;&amp;gt;\r\n								&amp;lt;select id=&amp;quot;portalTemplate&amp;quot; name=&amp;quot;portalTemplate&amp;quot; class=&amp;quot;form-control&amp;quot;&amp;gt;\r\n									&amp;lt;c:forEach items=&amp;quot;${portalPageTemplates }&amp;quot; var=&amp;quot;portalPageTemplate&amp;quot; varStatus=&amp;quot;i&amp;quot;&amp;gt;\r\n									&amp;lt;option value=&amp;quot;portalTemplate${portalPageTemplate.id}&amp;quot;\r\n										&amp;lt;c:if test=&amp;quot;${portalPage.pageTemplate!=null &amp;amp;&amp;amp; portalPageTemplate.id==portalPage.pageTemplate.id}&amp;quot;&amp;gt;selected=&amp;quot;selected&amp;quot;&amp;lt;/c:if&amp;gt;\r\n									&amp;gt;${portalPageTemplate.templateName}&amp;lt;/option&amp;gt;\r\n								&amp;lt;/c:forEach&amp;gt;\r\n								&amp;lt;/select&amp;gt;\r\n							&amp;lt;/div&amp;gt;\r\n							&amp;lt;div class=&amp;quot;col-sm-1&amp;quot;&amp;gt;\r\n								&amp;lt;button type=&amp;quot;button&amp;quot; id=&amp;quot;portalPageTemplateBtn&amp;quot; class=&amp;quot;btn btn-default&amp;quot; data-toggle=&amp;quot;modal&amp;quot;\r\n									data-target=&amp;quot;#portalPageTemplates&amp;quot;&amp;gt;效果&amp;lt;/button&amp;gt;\r\n							&amp;lt;/div&amp;gt;\r\n						&amp;lt;/div&amp;gt;\r\n						&amp;lt;div class=&amp;quot;form-group&amp;quot;&amp;gt;\r\n							&amp;lt;div class=&amp;quot;col-sm-3&amp;quot;&amp;gt;\r\n								&amp;lt;div class=&amp;quot;radio&amp;quot;&amp;gt;\r\n									&amp;lt;label&amp;gt; &amp;lt;input type=&amp;quot;radio&amp;quot; name=&amp;quot;portalPageType&amp;quot; id=&amp;quot;portalPageTypeRadios3&amp;quot;\r\n									&amp;lt;c:if test=&amp;quot;${portalPage.customizeUrl!=null}&amp;quot;&amp;gt;checked=&amp;quot;checked&amp;quot;&amp;lt;/c:if&amp;gt;\r\n										value=&amp;quot;useCustomizeUrl&amp;quot;&amp;gt; 启用&amp;lt;b&amp;gt;自定义页面URL&amp;lt;/b&amp;gt;\r\n									&amp;lt;/label&amp;gt;\r\n								&amp;lt;/div&amp;gt;\r\n							&amp;lt;/div&amp;gt;\r\n							&amp;lt;div class=&amp;quot;col-sm-9&amp;quot;&amp;gt;\r\n								&amp;lt;input type=&amp;quot;text&amp;quot; id=&amp;quot;portalPageCustomizeUrl&amp;quot; name=&amp;quot;portalPageCustomizeUrl&amp;quot;\r\n									class=&amp;quot;form-control&amp;quot; placeholder=&amp;quot;URL&amp;quot; value=&amp;quot;${portalPage.customizeUrl }&amp;quot;&amp;gt;\r\n							&amp;lt;/div&amp;gt;\r\n						&amp;lt;/div&amp;gt;\r\n						&amp;lt;div class=&amp;quot;form-group&amp;quot;&amp;gt;\r\n							&amp;lt;div class=&amp;quot;col-sm-3&amp;quot;&amp;gt;\r\n								&amp;lt;div class=&amp;quot;radio&amp;quot;&amp;gt;\r\n									&amp;lt;label&amp;gt; &amp;lt;input type=&amp;quot;radio&amp;quot; name=&amp;quot;portalPageType&amp;quot; id=&amp;quot;portalPageTypeRadios4&amp;quot;\r\n									&amp;lt;c:if test=&amp;quot;${portalPage.customizeHtml!=null}&amp;quot;&amp;gt;checked=&amp;quot;checked&amp;quot;&amp;lt;/c:if&amp;gt;\r\n										value=&amp;quot;useCustomizeHtml&amp;quot;&amp;gt; 启用&amp;lt;b&amp;gt;自定义页面HTML&amp;lt;/b&amp;gt;&amp;lt;input type=&amp;quot;hidden&amp;quot;\r\n										id=&amp;quot;portalPageCustomizeHtml&amp;quot; name=&amp;quot;portalPageCustomizeHtml&amp;quot; value=&amp;quot;${portalPage.customizeHtml }&amp;quot;&amp;gt;\r\n									&amp;lt;/label&amp;gt;\r\n								&amp;lt;/div&amp;gt;\r\n							&amp;lt;/div&amp;gt;\r\n							&amp;lt;div class=&amp;quot;col-sm-1&amp;quot;&amp;gt;\r\n								&amp;lt;button type=&amp;quot;button&amp;quot; id=&amp;quot;intputHtmlPortalPage&amp;quot; class=&amp;quot;btn btn-default &amp;quot;&amp;gt;编辑内容&amp;lt;/button&amp;gt;\r\n							&amp;lt;/div&amp;gt;\r\n						&amp;lt;/div&amp;gt;\r\n					&amp;lt;/div&amp;gt;\r\n				&amp;lt;/div&amp;gt;\r\n				&amp;lt;input type=&amp;quot;button&amp;quot; id=&amp;quot;submitForm&amp;quot; class=&amp;quot;btn btn-primary form-control&amp;quot; value=&amp;quot;保存&amp;quot; &amp;gt;\r\n			&amp;lt;/form&amp;gt;\r\n\r\n			&amp;lt;!-- auth page selector Modal --&amp;gt;\r\n			&amp;lt;div class=&amp;quot;modal fade&amp;quot; id=&amp;quot;authPageTemplates&amp;quot; tabindex=&amp;quot;-1&amp;quot; role=&amp;quot;dialog&amp;quot;\r\n				aria-labelledby=&amp;quot;myModalLabel&amp;quot; aria-hidden=&amp;quot;true&amp;quot;&amp;gt;\r\n				&amp;lt;div class=&amp;quot;modal-dialog&amp;quot;&amp;gt;\r\n					&amp;lt;div class=&amp;quot;modal-content&amp;quot;&amp;gt;\r\n						&amp;lt;div class=&amp;quot;modal-header&amp;quot;&amp;gt;\r\n							&amp;lt;button type=&amp;quot;button&amp;quot; class=&amp;quot;close&amp;quot; data-dismiss=&amp;quot;modal&amp;quot;&amp;gt;\r\n								&amp;lt;span aria-hidden=&amp;quot;true&amp;quot;&amp;gt;&amp;amp;times;&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;sr-only&amp;quot;&amp;gt;Close&amp;lt;/span&amp;gt;\r\n							&amp;lt;/button&amp;gt;\r\n							&amp;lt;h4 class=&amp;quot;modal-title&amp;quot; id=&amp;quot;myModalLabel&amp;quot;&amp;gt;请选择认证页面模版&amp;lt;/h4&amp;gt;\r\n						&amp;lt;/div&amp;gt;\r\n						&amp;lt;div class=&amp;quot;modal-body&amp;quot;&amp;gt;\r\n							&amp;lt;div class=&amp;quot;row&amp;quot;&amp;gt;\r\n							&amp;lt;c:forEach items=&amp;quot;${authPageTemplates }&amp;quot; var=&amp;quot;authPageTemplate&amp;quot; varStatus=&amp;quot;i&amp;quot;&amp;gt;\r\n									\r\n									&amp;lt;div class=&amp;quot;col-xs-6 col-md-3&amp;quot;&amp;gt;\r\n									&amp;lt;a href=&amp;quot;#&amp;quot; class=&amp;quot;thumbnail&amp;quot; id=&amp;quot;authTemplate${authPageTemplate.id}Img&amp;quot;&amp;gt; &amp;lt;img\r\n										src=&amp;quot;${ctx}/resources/img/template_thumbnail/${authPageTemplate.templateThumbnail}&amp;quot; alt=&amp;quot;template_thumbnail&amp;quot;&amp;gt;\r\n									&amp;lt;/a&amp;gt;\r\n								&amp;lt;/div&amp;gt;\r\n								&amp;lt;/c:forEach&amp;gt;\r\n								\r\n							&amp;lt;/div&amp;gt;\r\n						&amp;lt;/div&amp;gt;\r\n						&amp;lt;div class=&amp;quot;modal-footer&amp;quot;&amp;gt;\r\n							&amp;lt;button type=&amp;quot;button&amp;quot; class=&amp;quot;btn btn-default&amp;quot; data-dismiss=&amp;quot;modal&amp;quot;&amp;gt;关闭&amp;lt;/button&amp;gt;\r\n						&amp;lt;/div&amp;gt;\r\n					&amp;lt;/div&amp;gt;\r\n				&amp;lt;/div&amp;gt;\r\n			&amp;lt;/div&amp;gt;\r\n\r\n			&amp;lt;!-- portal page selector Modal --&amp;gt;\r\n			&amp;lt;div class=&amp;quot;modal fade&amp;quot; id=&amp;quot;portalPageTemplates&amp;quot; tabindex=&amp;quot;-1&amp;quot; role=&amp;quot;dialog&amp;quot;\r\n				aria-labelledby=&amp;quot;myModalLabel&amp;quot; aria-hidden=&amp;quot;true&amp;quot;&amp;gt;\r\n				&amp;lt;div class=&amp;quot;modal-dialog&amp;quot;&amp;gt;\r\n					&amp;lt;div class=&amp;quot;modal-content&amp;quot;&amp;gt;\r\n						&amp;lt;div class=&amp;quot;modal-header&amp;quot;&amp;gt;\r\n							&amp;lt;button type=&amp;quot;button&amp;quot; class=&amp;quot;close&amp;quot; data-dismiss=&amp;quot;modal&amp;quot;&amp;gt;\r\n								&amp;lt;span aria-hidden=&amp;quot;true&amp;quot;&amp;gt;&amp;amp;times;&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;sr-only&amp;quot;&amp;gt;Close&amp;lt;/span&amp;gt;\r\n							&amp;lt;/button&amp;gt;\r\n							&amp;lt;h4 class=&amp;quot;modal-title&amp;quot; id=&amp;quot;myModalLabel&amp;quot;&amp;gt;请选择认证后页面模版&amp;lt;/h4&amp;gt;\r\n						&amp;lt;/div&amp;gt;\r\n						&amp;lt;div class=&amp;quot;modal-body&amp;quot;&amp;gt;\r\n							&amp;lt;div class=&amp;quot;row&amp;quot;&amp;gt;\r\n							&amp;lt;c:forEach items=&amp;quot;${portalPageTemplates }&amp;quot; var=&amp;quot;portalPageTemplate&amp;quot; varStatus=&amp;quot;i&amp;quot;&amp;gt;\r\n									\r\n									&amp;lt;div class=&amp;quot;col-xs-6 col-md-3&amp;quot;&amp;gt;\r\n									&amp;lt;a href=&amp;quot;#&amp;quot; class=&amp;quot;thumbnail&amp;quot; id=&amp;quot;portalTemplate${portalPageTemplate.id}Img&amp;quot;&amp;gt; &amp;lt;img\r\n										src=&amp;quot;${ctx}/resources/img/template_thumbnail/${portalPageTemplate.templateThumbnail}&amp;quot; alt=&amp;quot;template_thumbnail&amp;quot;&amp;gt;\r\n									&amp;lt;/a&amp;gt;\r\n								&amp;lt;/div&amp;gt;\r\n								&amp;lt;/c:forEach&amp;gt;\r\n								\r\n								\r\n							&amp;lt;/div&amp;gt;\r\n						&amp;lt;/div&amp;gt;\r\n						&amp;lt;div class=&amp;quot;modal-footer&amp;quot;&amp;gt;\r\n							&amp;lt;button type=&amp;quot;button&amp;quot; class=&amp;quot;btn btn-default&amp;quot; data-dismiss=&amp;quot;modal&amp;quot;&amp;gt;关闭&amp;lt;/button&amp;gt;\r\n						&amp;lt;/div&amp;gt;\r\n					&amp;lt;/div&amp;gt;\r\n				&amp;lt;/div&amp;gt;\r\n			&amp;lt;/div&amp;gt;\r\n\r\n			&amp;lt;!-- custom HTML Modal --&amp;gt;\r\n			&amp;lt;div class=&amp;quot;modal fade&amp;quot; id=&amp;quot;customizeHtmlModel&amp;quot; tabindex=&amp;quot;-1&amp;quot; role=&amp;quot;dialog&amp;quot;\r\n				aria-labelledby=&amp;quot;myModalLabel&amp;quot; aria-hidden=&amp;quot;true&amp;quot;&amp;gt;\r\n				&amp;lt;div class=&amp;quot;modal-dialog modal-lg&amp;quot;&amp;gt;\r\n					&amp;lt;div class=&amp;quot;modal-content&amp;quot;&amp;gt;\r\n						&amp;lt;div class=&amp;quot;modal-header&amp;quot;&amp;gt;\r\n							&amp;lt;button type=&amp;quot;button&amp;quot; class=&amp;quot;close&amp;quot; data-dismiss=&amp;quot;modal&amp;quot;&amp;gt;\r\n								&amp;lt;span aria-hidden=&amp;quot;true&amp;quot;&amp;gt;&amp;amp;times;&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;sr-only&amp;quot;&amp;gt;Close&amp;lt;/span&amp;gt;\r\n							&amp;lt;/button&amp;gt;\r\n							&amp;lt;h4 class=&amp;quot;modal-title&amp;quot; id=&amp;quot;myModalLabel&amp;quot;&amp;gt;自定义HTML&amp;lt;/h4&amp;gt;\r\n						&amp;lt;/div&amp;gt;\r\n						&amp;lt;div class=&amp;quot;modal-body&amp;quot;&amp;gt;\r\n							&amp;lt;textarea id=&amp;quot;customizeHtml&amp;quot; class=&amp;quot;form-control&amp;quot; rows=&amp;quot;10&amp;quot;&amp;gt;&amp;lt;/textarea&amp;gt;\r\n						&amp;lt;/div&amp;gt;\r\n						&amp;lt;div class=&amp;quot;modal-footer&amp;quot;&amp;gt;\r\n							&amp;lt;button type=&amp;quot;button&amp;quot; class=&amp;quot;btn btn-default&amp;quot; data-dismiss=&amp;quot;modal&amp;quot;&amp;gt;关闭&amp;lt;/button&amp;gt;\r\n							&amp;lt;button type=&amp;quot;button&amp;quot; id=&amp;quot;saveCustomizeHtml&amp;quot; class=&amp;quot;btn btn-primary&amp;quot;&amp;gt;保存&amp;lt;/button&amp;gt;\r\n						&amp;lt;/div&amp;gt;\r\n					&amp;lt;/div&amp;gt;\r\n				&amp;lt;/div&amp;gt;\r\n			&amp;lt;/div&amp;gt;\r\n			&amp;lt;input type=&amp;quot;hidden&amp;quot; id=&amp;quot;customizeHtmlType&amp;quot;&amp;gt;\r\n		&amp;lt;/div&amp;gt;\r\n	&amp;lt;/div&amp;gt;\r\n&amp;lt;/body&amp;gt;\r\n&amp;lt;/html&amp;gt;',NULL,NULL);
/*!40000 ALTER TABLE `t_auth_page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_auth_type`
--

DROP TABLE IF EXISTS `t_auth_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_auth_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `register_type` varchar(45) DEFAULT NULL,
  `auth_type` varchar(45) DEFAULT NULL COMMENT 'refer to dictionary',
  `status` int(11) DEFAULT NULL COMMENT '0 means currently is not used.',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_auth_type`
--

LOCK TABLES `t_auth_type` WRITE;
/*!40000 ALTER TABLE `t_auth_type` DISABLE KEYS */;
INSERT INTO `t_auth_type` VALUES (1,'NONE','NONE',1),(2,'PHONE','PHONE',2),(3,'PHONE_SMS','PHONE',2),(4,'PHONE_SMS','PHONE_SMS',2),(5,'PHONE_PASSWORD','PHONE_PASSWORD',2),(6,'PHONE_PASSWORD_SMS','PHONE_PASSWORD',2),(7,'PHONE_PASSWORD_SMS','PHONE_PASSWORD_SMS',2);
/*!40000 ALTER TABLE `t_auth_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_connection`
--

DROP TABLE IF EXISTS `t_connection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_connection` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(16) DEFAULT NULL,
  `mac` char(17) DEFAULT NULL,
  `token_id` bigint(20) NOT NULL,
  `outgoing` bigint(20) DEFAULT '0',
  `incoming` bigint(20) DEFAULT '0',
  `status` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `token_id_UNIQUE` (`token_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_connection`
--

LOCK TABLES `t_connection` WRITE;
/*!40000 ALTER TABLE `t_connection` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_connection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_dict`
--

DROP TABLE IF EXISTS `t_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(60) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1037 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_dict`
--

LOCK TABLES `t_dict` WRITE;
/*!40000 ALTER TABLE `t_dict` DISABLE KEYS */;
INSERT INTO `t_dict` VALUES (1000,'gender','1','男',1,1,NULL),(1001,'gender','0','女',2,1,NULL),(1002,'user_type','1','管理员',2,1,NULL),(1003,'user_type','2','普通用户',1,1,NULL),(1004,'wifi_status','1','可接入互联网',1,1,NULL),(1005,'wifi_status','2','不可使用网络',2,1,NULL),(1006,'is_verified','1','已验证',1,1,NULL),(1007,'is_verified','2','未验证',2,1,NULL),(1008,'auth_type','NONE','无',1,1,NULL),(1009,'auth_type','PHONE','手机号',2,1,NULL),(1010,'auth_type','PHONE_SMS','手机号 + 短信验证',3,1,NULL),(1011,'auth_type','PHONE_PASSWORD','手机号 + 密码',4,1,NULL),(1012,'auth_type','PHONE_PASSWORD_SMS','手机号 + 短信验证 + 密码',5,1,NULL),(1013,'register_type','NONE','无',1,1,NULL),(1014,'register_type','PHONE','手机号',2,1,NULL),(1015,'register_type','PHONE_SMS','手机号 + 短信验证',3,1,NULL),(1016,'register_type','PHONE_PASSWORD','手机号 + 设置密码',4,1,NULL),(1017,'register_type','PHONE_PASSWORD_SMS','手机号 + 短信验证 + 设置密码',5,1,NULL),(1018,'auth_type_status','1','禁用',NULL,NULL,NULL),(1019,'auth_type_status','2','启用',NULL,NULL,NULL),(1020,'gw_message','logged-out','您已成功登出。',1,1,NULL),(1021,'validation_code','VALID','验证成功',1,1,NULL),(1022,'validation_code','ERROR_ID_PASSWORD','用户名或密码错误',1,1,NULL),(1023,'validation_code','ERROR_VERIFY_CODE_EXPIRED','验证码已过期',1,1,NULL),(1024,'validation_code','ERROR_VERIFY_CODE_NOT_EXIST','验证码不存在',1,1,NULL),(1025,'validation_code','ERROR_VERIFY_CODE_WRONG','验证码错误',1,1,NULL),(1026,'validation_code','ERROR_SYSTEM_AUTH_TYPE','系统错误，没有设置认证方式',1,1,NULL),(1027,'validation_code','ERROR_PHONE_NUMBER_FORMAT','手机号码格式错误',1,1,NULL),(1028,'validation_code','ERROR_WIFI_DISABLED','用户不允许使用网络',1,1,NULL),(1029,'validation_code','ERROR_USER_EXIST','用户名或手机号已被注册，请使用其他用户名或手机号。',1,1,NULL),(1030,'gw_message','denied','拒绝联网',1,1,NULL),(1031,'sms-validation','ERROR_REQUEST_LESS_ONE_MIN','获取短信过于频繁',1,1,NULL),(1032,'sms-validation','ERROR_REQUEST_EXCEED_MAX','超出了当日最大短信使用量',1,1,NULL),(1033,'sms-validation','ERROR_PHONE_NUM','手机号码格式错误',1,1,NULL),(1034,'sms-validation','ERROR_SYSTEM_EXCEPTION','系统错误',1,1,NULL),(1035,'log_type','1','操作日志',1,1,NULL),(1036,'log_type','2','异常信息',2,1,NULL);
/*!40000 ALTER TABLE `t_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_log`
--

DROP TABLE IF EXISTS `t_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `log_type` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `exception` text,
  `create_by` bigint(20) DEFAULT NULL,
  `remote_addr` varchar(45) DEFAULT NULL,
  `user_agent` varchar(255) DEFAULT NULL,
  `request_uri` varchar(255) DEFAULT NULL,
  `method` varchar(45) DEFAULT NULL,
  `params` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_log`
--

LOCK TABLES `t_log` WRITE;
/*!40000 ALTER TABLE `t_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_node`
--

DROP TABLE IF EXISTS `t_node`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_node` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gateway_id` varchar(45) NOT NULL,
  `node_description` varchar(255) DEFAULT NULL,
  `owner_id` bigint(20) NOT NULL DEFAULT '1',
  `last_heartbeat_sys_uptime` int(11) DEFAULT NULL,
  `last_heartbeat_wifidog_uptime` int(11) DEFAULT NULL,
  `last_heartbeat_sys_memfree` int(11) DEFAULT NULL,
  `last_heartbeat_sys_load` float DEFAULT NULL,
  `last_heartbeat_ip` varchar(16) DEFAULT NULL,
  `last_heartbeat_timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='table for router information';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_node`
--

LOCK TABLES `t_node` WRITE;
/*!40000 ALTER TABLE `t_node` DISABLE KEYS */;
INSERT INTO `t_node` VALUES (1,'wifiwolf','asus',1,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_node` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_page_template`
--

DROP TABLE IF EXISTS `t_page_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_page_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `template_path` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `template_type` int(11) DEFAULT NULL,
  `template_name` varchar(45) NOT NULL,
  `template_thumbnail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_page_template`
--

LOCK TABLES `t_page_template` WRITE;
/*!40000 ALTER TABLE `t_page_template` DISABLE KEYS */;
INSERT INTO `t_page_template` VALUES (1,'template/template1',1,1,'登录页面1','template1.png'),(2,'template/template2',1,1,'登录页面2','template2.png'),(3,'template/template3',1,2,'门户页面1','template3.png'),(4,'template/template4',1,2,'门户页面2','template4.png');
/*!40000 ALTER TABLE `t_page_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_phone_user`
--

DROP TABLE IF EXISTS `t_phone_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_phone_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `phone_num` varchar(20) DEFAULT NULL,
  `verify_code` varchar(6) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '0: not verified\n1: already verified',
  `create_time` datetime DEFAULT NULL,
  `verify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_phone_user`
--

LOCK TABLES `t_phone_user` WRITE;
/*!40000 ALTER TABLE `t_phone_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_phone_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_portal_page`
--

DROP TABLE IF EXISTS `t_portal_page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_portal_page` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `template_id` bigint(20) DEFAULT NULL,
  `customize_html` text,
  `use_origin_url` int(11) DEFAULT '0' COMMENT '''0'' stands for not using origin URL\n''1'' stands for using origin URL',
  `customize_url` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_portal_page`
--

LOCK TABLES `t_portal_page` WRITE;
/*!40000 ALTER TABLE `t_portal_page` DISABLE KEYS */;
INSERT INTO `t_portal_page` VALUES (1,NULL,NULL,1,NULL);
/*!40000 ALTER TABLE `t_portal_page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_token`
--

DROP TABLE IF EXISTS `t_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `token` varchar(40) DEFAULT NULL,
  `auth_type_id` bigint(20) DEFAULT NULL,
  `registered_user_id` bigint(20) DEFAULT NULL,
  `phone_user_id` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `node_id` bigint(20) DEFAULT NULL,
  `origin_url` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_user_id_UNIQUE` (`phone_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_token`
--

LOCK TABLES `t_token` WRITE;
/*!40000 ALTER TABLE `t_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `sex` int(11) DEFAULT '0' COMMENT '0 means female, 1 means male',
  `age` int(11) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `wifi_status` int(11) DEFAULT '1' COMMENT '0 means wifi is not avalibale currently, 1 means user can use the network',
  `account_status` int(11) DEFAULT '1',
  `user_type` int(11) DEFAULT NULL COMMENT 'Since we only has two user types, so use 0 to indicate current user is a normal user, on the other side, 1 means admin user.',
  `create_time` datetime DEFAULT NULL,
  `is_phone_verified` int(11) DEFAULT '2',
  `is_email_verified` int(11) DEFAULT '2',
  `register_node_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='table for user information';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'admin','a17b951f12a1e1c8cbae85507daf44cd656f5425310470eaa0cb0315',1,25,'12345678901','admin@wifiwolf.com',1,1,1,'2014-11-20 16:24:38',1,1,1),(2,'user','bfaac9bc44f437aeb85ebe02ce39ce91abb1145c0b0efe8e95e84106',0,20,NULL,NULL,1,1,2,NULL,2,2,1),(3,'cbedss1418119592217','041bd14e457f0194923aef2c68528f3c6c771415b957a0a17b9cccdd',NULL,NULL,'12345678900',NULL,1,1,2,'2014-12-09 18:06:32',1,NULL,1);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-12-11 11:03:52
