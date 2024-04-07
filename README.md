<!--Titulo principal-->
<!--h1 Sin borde inferior-->
<div id="user-content-toc">
  <ul align="center" >
    <summary>
      <!--Titulo-->
      <h2 style="display: inline-bloc">Automatas 2</h2>
      <br>
      <!--Divizor horizontal (gradiant)-->
      <img src="https://user-images.githubusercontent.com/73097560/115834477-dbab4500-a447-11eb-908a-139a6edaec5c.gif">
    </summary>
  </ul>
</div>

<!--Titulo de Practicas-->
<div>
  <h2 style="display: inline-block">Practica 1 Analisis semantico</h2>
  <div>
    <!--Objetivo-->
    <h3>Objetivo:</h3>
    <p>
      El alumno generara la tabla de símbolos y de direcciones de un lenguaje de prueba utilizando el lenguaje de programación JAVA o herramienta
    </p>
    <!--Desarrollo-->
    <h3>Desarrollo:</h3>
    <p>
      Realice una aplicación utilizando su lenguaje de programación Java o herramienta y genere dos archivos externos – Tabla de símbolos y tabla de direcciones para un   lenguaje de prueba.
    </p>
    <p>
      Suponga que ya existe la tabla de TOKENS con la siguiente estructura:
    </p>
    <div>
    <table>
      <td>Lexema</td>
      <td>Token</td>
      <td>Posición Tabla</td>
      <td>Linea</td>
    </table>
    </div>
    <p>
      Donde:
    </p>
    <p>
      <b>Lexema</b>: el valor asociado al token en el programa fuente<br>
      <b>Token</b>: el numero de token asignado<br>
      <b>Posición Tabla</b>: posición en la tabla de símbolos, donde -1 indica que no va en tabla de símbolos y -2 si se agrega a tabla de símbolos o de direcciones<br>
      <b>Linea</b>: el numero de linea en el programa fuente<br>
    </p>
    <p>
      El programa o aplicación deberá leer la tabla de tokens al inicio y mostrar al final la tabla con los datos de “posición en tabla” modificados así como generar la tabla de símbolos y tabla de direcciones correspondiente, archivo que se podrá leer desde consola al finalizar la ejecución de la aplicación con la siguiente estructura:
    </p>
    <h3>TABLA DE SIMBOLOS</h3>
    <div>
      <table>
        <td>ID</td>
        <td>Token</td>
        <td>Valor</td>
        <td>Ambito</td>
      </table>
    </div>
    <p>
      Donde:
    </p>
    <p>
      <b>ID</b>: identificador en el programa fuente
      <b>Token</b>: NUMERO DE TOKEN de acuerdo a LEXICO
      <b>Valor</b>: asignar de acuerdo al tipo de dato , si es entero 0, si es real 0.0 si es constante string null y si es lógico true
      <b>Ambito</b>: main si pertenece al programa principal, o nombre del procedimiento o función si pertenece a algún método
    </p>
    <h3>TABLA DE DIRECCIONES</h3>
    <div>
      <table>
        <td>ID</td>
        <td>Token</td>
        <td>No. Linea</td>
        <td>VCI</td>
      </table>
    </div>
    <p>
      Donde:
    </p>
    <p>
      <b>ID</b>: identificador en el programa fuente<br>
      <b>Token</b>: NUMERO DE TOKEN de acuerdo a LEXICO<br>
      <b>No. Linea</b>: numero de linea donde se encuentra el token<br>
      <b>VCI</b>: campo que se modificara en cuando se genere VCI , por default es 0<br>
    </p>
    <p>
      Considere lo siguiente para la identificación de TOKENS - LEXICO de lenguaje de prueba
    </p>
    
  </div>
  
  <h2 style="display: inline-block">Practica 2 Vector de codigo intermedio</h2>
  <div>
    <!--Objetivo-->
    <h3>Objetivo:</h3>
    <p>
      El alumno aplica los conceptos asociados para la obtención del Vector de código intermedio en expresiones aritméticas/lógica/relacionales y estructuras de control (condicional , repetición) simulando su proceso utilizando lenguaje de programación JAVA.
    </p>
    <!--Desarrollo-->
    <h3>Desarrollo:</h3>
    <p>
      Considerando la tabla de tokens que obtuvo como salida en la practica anterior genere una aplicación que permita simular el proceso de obtención del vector de código intermedio para un conjunto de codigo que incluya expresiones aritméticas/lógicas/relacionales (cualquiera), estructuras de control (condicional, repetir, mientras), considere operadores y prioridades vistas en clase, incluya prioridad para paréntesis y utilice las pilas correspondientes (operadores, estatutos, direcciones). INCLUYA diseño lógico de la aplicación o programa en el reporte y considere manejo de errores, etapas de desarrollo de software, considere metodología de diseño para la aplicación o programa acorde al paradigma del lenguaje.
    </p>
    <p>
      El conjunto de datos de entrada no debe presentar errores (léxicos, sintácticos ni semánticos). La salida será el VCI generado de manera correcta que se desplegará en pantalla y se almacenará en un archivo de texto etiquetado VCI.
    </p>
    <!--Ejemplo-->
    <h3>Ejemplo</h3>
    <h3>Entrada:</h3>
    <h3>Tabla de tokens</h3>
    <table>
      <tr>
        <td>TOKENS</td>
        <td>TOKENS</td>
      </tr>
      <tr>
        <td>programa,-1,-1,1</td>
        <td>b&,-51,1,9</td>
      </tr>
      <tr>
        <td>uno@,-55, 0 ,1</td>
        <td>=, -26 , -1 , 9</td>
      </tr>
      <tr>
        <td>; , -75 , -1 , 1</td>
        <td>0, -61, -1, 9</td>
      </tr>
      <tr>
        <td>variables, -15, -1, 2</td>
        <td>; , -75, -1, 9</td>
      </tr>
      <tr>
        <td>entero, -11, -1, 3</td>
        <td>z$, -53, 4, 10</td>
      </tr>
      <tr>
        <td>a&, -51, 0 , 3</td>
        <td>= , -26 , -1 , 10</td>
      </tr>
      <tr>
        <td>, , -76 , -1 , 3</td>
        <td>“Hola”, -63, -1, 10</td>
      </tr>
      <tr>
        <td>b& , -51 , 1 , 3</td>
        <td>; , -75, -1, 10</td>
      </tr>
      <tr>
        <td>; , -75 , -1 , 3</td>
        <td>x% , -52, 2, 11</td>
      </tr>
      <tr>
        <td>real, -12, -1 , 4</td>
        <td>= , -26 , -1 , 11</td>
      </tr>
      <tr>
        <td>x%,-52, 2 , 4</td>
        <td>45.5, -62, -1, 11</td>
      </tr>
      <tr>
        <td>, , -76 , -1 , 4</td>
        <td>; , -75, -1, 11</td>
      </tr>
      <tr>
        <td>y%, -52, 3 , 4</td>
        <td>y%, -52, 3, 12</td>
      </tr>
      <tr>
        <td>; , -75, -1, 4</td>
        <td>= ,-26 , -1 , 12</td>
      </tr>
      <tr>
        <td>cadena, -13, -1 , 5</td>
        <td>6.6, -62, -1, 12</td>
      </tr>
      <tr>
        <td>z$, -53 , 4 , 5</td>
        <td>; , -75, -1, 12</td>
      </tr>
      <tr>
        <td>; , -75 , -1 , 5</td>
        <td>dos#, -54, 5, 13</td>
      </tr>
      <tr>
        <td>logico, -14, -1 , 6</td>
        <td>=, -26 , -1 , 13</td>
      </tr>
      <tr>
        <td>dos#, -54 , 5 , 6</td>
        <td>true, -64, -1, 13</td>
      </tr>
      <tr>
        <td>; , -75 , -1 , 6</td>
        <td>; , -75, -1, 13</td>
      </tr>
      <tr>
        <td>inicio , -2, -1,7</td>
        <td>Fin, -3,-1,14</td>
      </tr>
      <tr>
        <td>a& , -51, 0 , 8</td>
      </tr>
      <tr>
        <td>=, -26 , -1 , 8</td>
      </tr>
      <tr>
       <td>300, -61, -1, 8</td> 
      </tr>
      <tr>
        <td>; , -75, -1, 8</td>
      </tr>
    </table>
    <h3>Salida:</h3>
    <h3>Vector de código intermedio (TABLA ) considerando tabla de TOKENS</h3>
    <table>
      <tr>
        <td>a& , -51, 0 , 8</td>
      </tr>
      <tr>
        <td>300, -61, -1, 8</td>
      </tr>
      <tr>
        <td>=, -26 , -1 , 8</td>
      </tr>
      <tr>
        <td>b&, -51, 1 , 9</td>
      </tr>
      <tr>
        <td>0, -61, -1, 9</td>
      </tr>
      <tr>
        <td>=, -26 , -1 , 9</td>
      </tr>
      <tr>
        <td>z$, -53, 4, 10</td>
      </tr>
      <tr>
        <td>“Hola”, -63, -1, 10</td>
      </tr>
      <tr>
        <td>= , -26 , -1 , 10</td>
      </tr>
      <tr>
        <td>…. etc</td>
      </tr>
    </table>
  </div>
</div>
