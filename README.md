<p>Las instrucciones para hacerlo funcionar en un ordenador Windows son las siguientes:</p>

<p>· Se debe tener instalado el <strong>JDK8 </strong>y <strong>Maven</strong>, y setear las variables de entorno. Dejo un enlace <a href="https://howtodoinjava.com/maven/how-to-install-maven-on-windows/" target="_blank">AQUÍ</a> sobre cómo hacerlo. Para Maven, el archivo de instalación a descargar sería el "apache-maven-3.6.3-bin.zip" o la versión del "<strong>bin.zip</strong>" que esté disponible en el momento.</p>

<p>· Una vez comprobado que tenemos Maven en el ordenador, debemos descargar el <strong>repositorio</strong>. En este caso, mi proyecto está en el siguiente enlace: <a href="https://github.com/jgramunt/pollution-robot" target="_blank">https://github.com/jgramunt/pollution-robot</a></p>

<p>· Una vez descargado, vamos accedemos desde la consola a la carpeta en la que se encuentre el repositorio (descomprimido en caso de no haberlo clonado) y ejecutamos el comando "<strong>mvn install</strong>". Una vez finalizada la instalación, podemos ejecutar el comando "<strong>mvn spring-boot:run</strong>", que arrancará la API.</p>

<p>En este caso, es una API HTTP. Para inicializar un robot, tendríamos que mandar la siguiente petición (desde el mismo navegador):</p>

<p><a href="http://localhost:8080/newRobot/" target="_blank">http://localhost:8080/newRobot/</a> + una polilínea válida.</p>

<p>De no ser válida, el robot petaría al arrancar. Debería salir un mensaje en la consola conforme el robot se ha creado. Si aún así no funciona, es posible que el navegador no esté convirtiendo los símbolos al formato correcto. Lo mejor sería entonces probarlo desde el Swagger, al que se explica cómo acceder más abajo.</p>

<p>Una vez inicializado el robot, podemos arrancarlo desde la siguiente dirección:</p>

<p><a href="http://localhost:8080/startRobot" target="_blank">http://localhost:8080/startRobot</a></p>

<p>Y podemos pararlo con la siguiente dirección:</p>

<p>http:localhost8080/stopRobot</p>

<p>En ambos casos veremos un mensaje en la pantalla. Por ahora tiene predeterminada la velocidad a 2 m/s, un registro cada 100 metros y printa información en la consola cada 15 minutos.</p>

<p>He añadido la función de que printe información cuando queramos, con la dirección:</p>

<p><a href="http://localhost:8080/getReport" target="_blank">http://localhost:8080/getReport</a></p>

<p>Aún así, si no ha recorrido 100 metros, el valor de lectura saldrá vacío porque no habrá hecho aún ningún registro.</p>

<p>Para ver todas las funcionalidades de una forma un poco más ordenada le he añadido una breve documentación con la herramienta <strong>Swagger</strong>, que se puede consultar aquí:</p>

<p><a href="http://localhost:8080/swagger-ui.html" target="_blank">http://localhost:8080/swagger-ui.html</a></p>

<br>
