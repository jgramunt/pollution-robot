Las instrucciones para hacerlo funcionar en un ordenador Windows son las siguientes:
Se debe tener instalado el JDK8 y Maven, y setear las variables de entorno. Dejo un enlace AQUÍ sobre cómo hacerlo. Para Maven, el archivo de instalación a descargar sería el "apache-maven-3.6.3-bin.zip" o la versión del "bin.zip" que esté disponible en el momento.
Una vez comprobado que tenemos Maven en el ordenador, debemos descargar el repositorio. En este caso, mi proyecto está en el siguiente enlace: https://github.com/jgramunt/pollution-robot
 Una vez descargado, vamos accedemos desde la consola a la carpeta en la que se encuentre el repositorio (descomprimido en caso de no haberlo clonado) y ejecutamos el comando "mvn install". Una vez finalizada la instalación, podemos ejecutar el comando "mvn spring-boot:run", que arrancará la API.
En este caso, es una API HTTP. Para inicializar un robot, tendríamos que mandar la siguiente petición (desde el mismo navegador):
http://localhost:8080/newRobot/ + una polilínea válida.
De no ser válida, el robot petaría al arrancar. Debería salir un mensaje en la consola conforme el robot se ha creado. Si aún así no funciona, es posible que el navegador no esté convirtiendo los símbolos al formato correcto. Lo mejor sería entonces probarlo desde el Swagger, al que se explica cómo acceder más abajo.
Una vez inicializado el robot, podemos arrancarlo desde la siguiente dirección:
http://localhost:8080/startRobot
Y podemos pararlo con la siguiente dirección:
http:localhost8080/stopRobot
En ambos casos veremos un mensaje en la pantalla. Por ahora tiene predeterminada la velocidad a 2 m/s, un registro cada 100 metros y printa información en la consola cada 15 minutos.
He añadido la función de que printe información cuando queramos, con la dirección:
http://localhost:8080/getReport
Aún así, si no ha recorrido 100 metros, el valor de lectura saldrá vacío porque no habrá hecho aún ningún registro.
Para ver todas las funcionalidades de una forma un poco más ordenada le he añadido una breve documentación con la herramienta Swagger, que se puede consultar aquí:
http://localhost:8080/swagger-ui.html
