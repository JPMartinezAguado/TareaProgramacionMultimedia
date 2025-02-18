"# TareaProgramacionMultimedia"  

*********************** #uR_Bit. *************************************

Aplicación para la tarea de la asignatura de Programacion Multimedia.

version 5.0

-Creados repositorios para Base de datos Room y API de Retrofit

-Implementacion de UseCases para acceso a repositorios

-Implementada funcionalidad de elimiancion de anuncios, mediante menu desplegable.

-Correguida insercion y preseintacion de anuncios. Si al iniciar la aplicacion la islata de anuncios se vacia, carga el listado de anuncios precargados en el repositorio

-Mejoras de navegación entre pantallas, cohesión de diseño de app entre las diversas partes del app y asignación de funciones a todos los elementos visuales

version 4.0

-Añadidas dependencias de Hilt

-Encapusluada logica de DB y chistes en ViewMOdel, con uso de StateFlow 

-Acceso a una Api de chistes de Chuck Norris usando Retrofit, accesible desde el menu lateral

-Activities y ViewModels inyectados con Hilt para automatizar las dependencias

-Corregido menu de creacion de anuncios. La imagen se selecciona de un desplegable para evitar erro en caso de introducir un nombre de imagen que no correspondiese con alguna de las guardadas en drawable.

version 3.1.1
- corregidas dependencias innecesarias y conflictivas (rerlacionadas con java y googleAds)
- corregido generacion de anuncios desde la base de datos, derivadoa que la version3.0 estaba incompleta por
  problemas de sincronicidad con github

version 3.0

_añadida bSE DE DATOS SQLITE para guardar datos de usuarios

-añadida base de datos ROOm para guardar informacion de anuncios

version 2.0

Cambios realizados:
-Logs para distintos eventos, para los eventos de abrir aplicacion,
 cerrar aplicacion, low mwmory, cambio de orientacion de pantalla y
 minimizar y restaurar aplicacion. DIUchos cambiuos se encuentran en una
 nueva clase de aplicacion creada, llamada uR_BitApp, excepto el evento
 de restaurar, ya que no encontre manera de aplicar el onResume() a la
 clase, asi que lo apliqué a la activity main.

-Añadidas dependencias de Room al gradle. Para el kps, utilizo la version
 1.0.24, que es la que corresponde a mi version de Kotlin 2.0.0

-Modificado el archivo AndroidManifest, fijando la orientacion de la
 main activity a portrait, declarando permisos de Internet y almacenamiento
 y cambiando el nombre de la aplicacion
	

version 1.0

**************** DESCRIPCION DE LA APLICACIÓN ************************

La aplicación a desarrollar durante el módulo se trata de una especie
de tablón de anuncios donde organizaciones de ayuda social comunican
las acciones concretas que necesitan realizar (de muy diversos tipos, 
desde simplemente acompañar a una persona anciana a realizar la compra
hasta la reforma de un centro de acogida, o asistir a un comedor social).

De otra parte, gente dispuesta a colaborar pero que no están integradas
en dichas organizaciones pueden buscar de forma convniente y efectiva
las que se ajusten a sus habilidades (cuidado de mayores, juegoterapia,
comedores sociales, atencion inmigrantes, trabajos domesticos…)
y disponibilidad, tanto horaria como geográfica.

El nombre elegido para la app ( #uR_Bit. ) es slang de "your bit", que
hace referencia a una expression inglesa ("to do your bit") que equivale
a "poner tu granito de arena". El uso de símbolos (# _ .) es solo para
darle un toque mas contemporáneo, que parece que eso les gusta a los 
modernos :P. 
El epígrafe "a place to do your best" pretende reforzar esa idea de 
accion positiva.

Para describir las funcionalidades que incorporará la aplicacion, a partir
 de ahora, nos referiremos como ACF (App Completa y Funcional) a lo 
que supone que realizaría la aplicación si se llevase a termino respecto
a su conceptualización.

 Llamaremos FTM (Funcionalidades de la Tarea
del Modulo) a las partes que, en principio y de acuerdo con lo hablado
contigo al respecto, presupongo que tendrá incorporadas de modo funcional
al final del módulo de la asignatura.

Con esta distinction hecha, paso a enumerar las diversas funcionalidades
requeridas para la tarea:

-La app contendrá una interfaz que permitirá visualizar anuncios e interactuar
con ellos, expandiendolos para ver mas detalles, pudiendo guardarlos en una
 lista de favoritos. Parte de dicha interfaz no será operativa en la FTM,
como puede ser el login o las pantallas de creacion de anuncios

-Se conectará a Internet para obtener servicios de ubicación a través de
Google Maps. Estos datos se usarán para 
	-En el momento que un voluntario busca anuncios, poder cribar 
	aquellos que entran dentro del ambito geografico deseado.
	-Cuando una organizacion publique un anuncio, se le notificará
	a los voluntarios registrados cuyo rango geografico definido
	(junto con otros parametros como skills) coincida con la del 
	anuncio


-Manejará una base de datos: 
	-En la FTM, ésta será de caracter local, con datos "dummy" para 
	comprobar la funcionalidad de la aplicacion.
 	-En la version ACF,dicha base de datos se guardaría en servidores 
	y se rellenaria a traves de formularios de registro y publicacion de anuncios)

-Contendrá un buscador por el que se pueden restringir los anuncios mostrados 
en base a distintas variables, como pueden ser:
	-Ubicacion geografica y distancia con el que realiza la busqueda
	-Tags de skills/preferencias
	-Temporalidad
	-nombre de la ONG...

-Los usuarios podrán guardar en favoritos los anuncios que consideren interesantes
para ellos, o enviarlos a personas que consideren puedan estar interesados



****************CONCEPTUALIZACION DE LA ACF********************************************

-Respecto a la ACF, lo ideal para que fuese un proyecto viable seria la incorporación de 
capital privado. Esto se podria hacer mediante el patrocinio de empresas cuyo
target de publico coincida con el perfil de usuario que podria tener esta app,
y, a cambio de visibilidad de sus productos en la app, mediante por ejemplo pequeños 
banners entre anuncios,  a traves de un programa de puntos o freevies, recompensarian a
los voluntarios por las acciones que realizaran. Estos podrían tener la opcion de donar 
dichos rewards a obras sociales.

-Tambien  sería interesante el restringir los contenidos a acciones puramente sociales 
y de apoyo mutuo, filtrando aquellas con caracter politico, religioso o discriminatorio.

-El ámbito geográfico ideal es el local, acciones a realizar pequeñas y concretas, de duracion
corta para que aquellas personas que les gustaria ayudar, sientan que aunque su dia a dia
les restringe la capacidad de accion, puedan encontrar ese rato en la semana en que 
sentirse utiles.


