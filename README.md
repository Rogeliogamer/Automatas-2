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
<div id="user-content-toc">
  <ul align="rigth" >
    <summary>
      <!--Titulo-->
      <h2 style="display: inline-block">Practica 1 Analisis semantico</h2>
      <!--Divizor horizontal (gradiant)-->
      <img src="https://user-images.githubusercontent.com/73097560/115834477-dbab4500-a447-11eb-908a-139a6edaec5c.gif">
    </summary>
  </ul>
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
      <th>Lexema</th>
      <th>Token</th>
      <th>Posición Tabla</th>
      <th>Linea</th>
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
      El programa o aplicación deberá leer la tabla de tokens al inicio y mostrar al final la tabla con los datos de <b>“posición en tabla” modificados</b> así como generar la tabla de símbolos y tabla de direcciones correspondiente, archivo que se podrá leer desde consola al finalizar la ejecución de la aplicación con la siguiente estructura:
    </p>
    <h3>TABLA DE SIMBOLOS</h3>
    <div>
      <table>
        <th>ID</th>
        <th>Token</th>
        <th>Valor</th>
        <th>Ambito</th>
      </table>
    </div>
    <p>
      Donde:
    </p>
    <p>
      <b>ID</b>: identificador en el programa fuente<br>
      <b>Token</b>: NUMERO DE TOKEN de acuerdo a LEXICO<br>
      <b>Valor</b>: <b>asignar de acuerdo al tipo de dato</b>, si es entero 0, si es real 0.0 si es constante string null y si es lógico true<br>
      <b>Ambito</b>: main si pertenece al programa principal, o nombre del procedimiento o función si pertenece a algún método<br>
    </p>
    <h3>TABLA DE DIRECCIONES</h3>
    <div>
      <table>
        <th>ID</th>
        <th>Token</th>
        <th>No. Linea</th>
        <th>VCI</th>
      </table>
    </div>
    <p>
      Donde:
    </p>
    <p>
      <b>ID</b>: identificador en el programa fuente<br>
      <b>Token</b>: NUMERO DE TOKEN de acuerdo a LEXICO<br>
      <b>No. Linea</b>: numero de linea donde se encuentra el token<br>
      <b>VCI</b>: campo que se modificara en cuando se genere VCI , <b>por default es 0</b>
    </p>
    <p>
      Considere lo siguiente para la identificación de TOKENS - <b>LEXICO de lenguaje de prueba</b>
    </p>
    <div>
      <table>
        <tr>
          <th colspan="2">Palabras Reservadas</th>
          <th colspan="2">Operadores Aritméticos/ Relacionales/ Lógicos</th>
          <th colspan="2">Identificadores</th>
          <th colspan="2">Constantes</th>
          <th colspan="2">Caracteres</th>
        </tr>
        <tr>
          <td>programa</td>
          <td>-1</td>
          <td>*</td>
          <td>-21</td>
          <td>Enteros</td>
          <td>-51</td>
          <td>Enteros</td>
          <td>-61</td>
          <td>(</td>
          <td>-73</td>
        </tr>
        <tr>
          <td>inicio</td>
          <td>-2</td>
          <td>/</td>
          <td>-22</td>
          <td>Reales</td>
          <td>-52</td>
          <td>Reales</td>
          <td>-62</td>
          <td>)</td>
          <td>-74</td>
        </tr>
        <tr>
          <td>fin</td>
          <td>-3</td>
          <td>%</td>
          <td>-23</td>
          <td>String</td> 
          <td>-53</td>
          <td>String</td>
          <td>-63</td>
          <td>;</td>
          <td>-75</td>
        </tr>
        <tr>
          <td>leer</td>
          <td>-4</td>
          <td>+</td>
          <td>-24</td>
          <td>Logico</td>
          <td>-54</td>
          <td>Logico (true)</td>
          <td>-64</td>
          <td>,</td>
          <td>-76</td>
        </tr>
        <tr>
          <td>escribir</td>
          <td>-5</td>
          <td>-</td>
          <td>-25</td>
          <td>ID-Gral.</td>
          <td>-55</td>
          <td>Logico (false)</td>
          <td>-65</td>
          <!--Celdas vacias-->
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td>si</td>
          <td>-6</td>
          <td>=</td>
          <td>-26</td>
          <!--Celdas vacias-->
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td>sino</td>
          <td>-7</td>
          <td><</td>
          <td>-31</td>
          <!--Celdas vacias-->
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td>mientras</td>
          <td>-8</td>
          <td><=</td>
          <td>-32</td>
          <!--Celdas vacias-->
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td>repetir</td>
          <td>-9</td>
          <td>></td>
          <td>-33</td>
          <!--Celdas vacias-->
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td>hasta</td>
          <td>-10</td>
          <td>>=</td>
          <td>-34</td>
          <!--Celdas vacias-->
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td>entero</td>
          <td>-11</td>
          <td>==</td>
          <td>-35</td>
          <!--Celdas vacias-->
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td>real</td>
          <td>-12</td>
          <td>!=</td>
          <td>-36</td>
          <!--Celdas vacias-->
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td>cadena</td>
          <td>-13</td>
          <td>&&</td>
          <td>-41</td>
          <!--Celdas vacias-->
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td>logico</td>
          <td>-14</td>
          <td>||</td>
          <td>-42</td>
          <!--Celdas vacias-->
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td>variables</td>
          <td>-15</td>
          <td>!</td>
          <td>-43</td>
          <!--Celdas vacias-->
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td>entonces</td>
          <td>-16</td>
          <!--Celdas vacias-->
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td>hacer</td>
          <td>-17</td>
          <!--Celdas vacias-->
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
      </table>
    </div>
    <h3>EJEMPLO TABLA DE TOKENS antes de la ejecución</h3>
    <div>
      <table>
        <tr>
          <th></th>
          <th>TOKENS</th>
          <th></th>
          <th>TOKENS</th>
        </tr>
        <tr>
          <td>1</td>
          <td>programa, -1, -1, 1</td>
          <td>26</td>
          <td>b&, -51, -2, 9</td>
        </tr>
        <tr>
          <td>2</td>
          <td>uno@, -55, -2, 1</td>
          <td>27</td>
          <td>=, -26, -1, 9</td>
        </tr>
        <tr>
          <td>3</td>
          <td>;, -75, -1, 1</td>
          <td>28</td>
          <td>0, -61, -1, 9</td>
        </tr>
        <tr>
          <td>4</td>
          <td>variables, -15, -1, 2</td>
          <td>29</td>
          <td>; , -75, -1, 9</td>
        </tr>
        <tr>
          <td>5</td>
          <td>entero, -11, -1, 3</td>
          <td>30</td>
          <td>z$, -53, -2, 10</td>
        </tr>
        <tr>
          <td>6</td>
          <td>a&, -51, -2, 3</td>
          <td>31</td>
          <td>=, -26, -1, 10</td>
        </tr>
        <tr>
          <td>7</td>
          <td>,, -76, -1, 3</td>
          <td>32</td>
          <td>“Hola”, -63, -1, 10</td>
        </tr>
        <tr>
          <td>8</td>
          <td>b&, -51, -2, 3</td>
          <td>33</td>
          <td>;, -75, -1, 10</td>
        </tr>
        <tr>
          <td>9</td>
          <td>;, -75, -1, 3</td>
          <td>34</td>
          <td>x%, -52, -2, 11</td>
        </tr>
        <tr>
          <td>10</td>
          <td>real, -12, -1, 4</td>
          <td>35</td>
          <td>=, -26, -1, 11</td>
        </tr>
        <tr>
          <td>11</td>
          <td>x%, -52, -2, 4</td>
          <td>36</td>
          <td>45.5, -62, -1, 11</td>
        </tr>
        <tr>
          <td>12</td>
          <td>,, -76, -1, 4</td>
          <td>37</td>
          <td>;, -75, -1, 11</td>
        </tr>
        <tr>
          <td>13</td>
          <td>y%, -52, -2, 4</td>
          <td>38</td>
          <td>y%, -52, -2, 12</td>
        </tr>
        <tr>
          <td>14</td>
          <td>;, -75, -1, 4</td>
          <td>39</td>
          <td>=, -26, -1, 12</td>
        </tr>
        <tr>
          <td>15</td>
          <td>cadena, -13, -1, 5</td>
          <td>40</td>
          <td>6.6, -62, -1, 12</td>
        </tr>
        <tr>
          <td>16</td>
          <td>z$, -53, -2, 5</td>
          <td>41</td>
          <td>;, -75, -1, 12</td>
        </tr>
        <tr>
          <td>17</td>
          <td>;, -75, -1, 5</td>
          <td>42</td>
          <td>dos#, -54, -2, 13</td>
        </tr>
        <tr>
          <td>18</td>
          <td>logico, -14, -1, 6</td>
          <td>43</td>
          <td>=, -26, -1, 13</td>
        </tr>
        <tr>
          <td>19</td>
          <td>dos#, -54, -2, 6</td>
          <td>44</td>
          <td>true, -64, -1, 13</td>
        </tr>
        <tr>
          <td>20</td>
          <td>;, -75, -1, 6</td>
          <td>45</td>
          <td>;, -75, -1, 13</td>
        </tr>
        <tr>
          <td>21</td>
          <td>inicio, -2, -1, 7</td>
          <td>46</td>
          <td>Fin, -3, -1, 14</td>
        </tr>
        <tr>
          <td>22</td>
          <td>a&, -51, -2, 8</td>
          <td>47</td>
          <!--Celdas vacias-->
          <td></td>
        </tr>
        <tr>
          <td>23</td>
          <td>=, -26, -1, 8</td>
          <td>48</td>
          <!--Celdas vacias-->
          <td></td>
        </tr>
        <tr>
          <td>24</td>
          <td>300, -61, -1, 8</td>
          <td>49</td>
          <!--Celdas vacias-->
          <td></td>
        </tr>
        <tr>
          <td>25</td>
          <td>;, -75, -1, 8</td>
          <td>50</td>
          <!--Celdas vacias-->
          <td></td>
        </tr>
      </table>
    </div>
    <h3>EJEMPLO Tabla de Símbolos</h3>
    <div>
      <table>
        <tr>
          <th>ID</th>
          <th>TOKEN</th>
          <th>VALOR</th>
          <th>AMBITO</th>
        </tr>
        <tr>
          <td>a&</td>
          <td>-51</td>
          <td>0</td>
          <td>Main</td>
        </tr>
        <tr>
          <td>b&</td>
          <td>-51</td>
          <td>0</td>
          <td>Main</td>
        </tr>
        <tr>
          <td>x%</td>
          <td>-52</td>
          <td>0.0</td>
          <td>Main</td>
        </tr>
        <tr>
          <td>y%</td>
          <td>-52</td>
          <td>0.0</td>
          <td>Main</td>
        </tr>
        <tr>
          <td>z$</td>
          <td>-53</td>
          <td>Null</td>
          <td>Main</td>
        </tr>
        <tr>
          <td>dos#</td>
          <td>-54</td>
          <td>True</td>
          <td>Main</td>
        </tr>
      </table> 
    </div>
    <h3>EJEMPLO Tabla de direcciones</h3>
    <div>
      <table>
        <tr>
          <th>ID</th>
          <th>TOKEN</th>
          <th>No. Linea</th>
          <th>VCI</th>
        </tr>
        <tr>
          <td>uno@</td>
          <td>-55</td>
          <td>1</td>
          <td>0</td>
        </tr>
      </table>
    </div>
    <h3>EJEMPLO TABLA DE TOKENS después de la ejecución</h3>
    <div>
      <table>
        <tr>
          <th></th>
          <th>TOKENS</th>
          <th></th>
          <th>TOKENS</th>
        </tr>
        <tr>
          <td>1</td>
          <td>programa, -1, -1, 1</td>
          <td>26</td>
          <td>b&, -51, -1, 9</td>
        </tr>
        <tr>
          <td>2</td>
          <td>uno@, -55, 0, 1</td>
          <td>27</td>
          <td>=, -26, -1, 9</td>
        </tr>
        <tr>
          <td>3</td>
          <td>;, -75, -1, 1</td>
          <td>28</td>
          <td>0, -61, -1, 9</td>
        </tr>
        <tr>
          <td>4</td>
          <td>variables, -15, -1, 2</td>
          <td>29</td>
          <td>;, -75, -1, 9</td>
        </tr>
        <tr>
          <td>5</td>
          <td>entero, -11, -1, 3</td>
          <td>30</td>
          <td>z$, -53, 4, 10</td>
        </tr>
        <tr>
          <td>6</td>
          <td>a&, -51, 0, 3</td>
          <td>31</td>
          <td>=, -26, -1, 10</td>
        </tr>
        <tr>
          <td>7</td>
          <td>,, -76, -1, 3</td>
          <td>32</td>
          <td>“Hola”, -63, -1, 10</td>
        </tr>
        <tr>
          <td>8</td>
          <td>b&, -51, 1, 3</td>
          <td>33</td>
          <td>;, -75, -1, 10</td>
        </tr>
        <tr>
          <td>9</td>
          <td>;, -75, -1, 3</td>
          <td>34</td>
          <td>x%, -52, 2, 11</td>
        </tr>
        <tr>
          <td>10</td>
          <td>real, -12, -1, 4</td>
          <td>35</td>
          <td>=, -26, -1, 11</td>
        </tr>
        <tr>
          <td>11</td>
          <td>x%, -52, 2, 4</td>
          <td>36</td>
          <td>45.5, -62, -1, 11</td>
        </tr>
        <tr>
          <td>12</td>
          <td>,, -76, -1, 4</td>
          <td>37</td>
          <td>;, -75, -1, 11</td>
        </tr>
        <tr>
          <td>13</td>
          <td>y%, -52, 3, 4</td>
          <td>38</td>
          <td>y%, -52, 3, 12</td>
        </tr>
        <tr>
          <td>14</td>
          <td>;, -75, -1, 4</td>
          <td>39</td>
          <td>=, -26, -1, 12</td>
        </tr>
        <tr>
          <td>15</td>
          <td>cadena, -13, -1, 5</td>
          <td>40</td>
          <td>6.6, -62, -1, 12</td>
        </tr>
        <tr>
          <td>16</td>
          <td>z$, -53, 4, 5</td>
          <td>41</td>
          <td>;, -75, -1, 12</td>
        </tr>
        <tr>
          <td>17</td>
          <td>;, -75, -1, 5</td>
          <td>42</td>
          <td>dos#, -54, 5, 13</td>
        </tr>
        <tr>
          <td>18</td>
          <td>logico, -14, -1, 6</td>
          <td>43</td>
          <td>=, -26, -1, 13</td>
        </tr>
        <tr>
          <td>19</td>
          <td>dos#, -54, 5, 6</td>
          <td>44</td>
          <td>true, -64, -1, 13</td>
        </tr>
        <tr>
          <td>20</td>
          <td>;, -75, -1, 6</td>
          <td>45</td>
          <td>;, -75, -1, 13</td>
        </tr>
        <tr>
          <td>21</td>
          <td>inicio, -2, -1, 7</td>
          <td>46</td>
          <td>Fin, -3, -1, 14</td>
        </tr>
        <tr>
          <td>22</td>
          <td>a&, -51, 0, 8</td>
          <td>47</td>
          <!--Celdas vacias-->
          <td></td>
        </tr>
        <tr>
          <td>23</td>
          <td>=, -26, -1, 8</td>
          <td>48</td>
          <!--Celdas vacias-->
          <td></td>
        </tr>
        <tr>
          <td>24</td>
          <td>300, -61, -1, 8</td>
          <td>49</td>
          <!--Celdas vacias-->
          <td></td>
        </tr>
        <tr>
          <td>25</td>
          <td>;, -75, -1, 8</td>
          <td>50</td>
          <!--Celdas vacias-->
          <td></td>
        </tr>
      </table>
    </div>
  </div>

  <ul align="rigth">
    <summary>
      <!--Titulo-->
      <h2 style="display: inline-block">Practica 2 Vector de codigo intermedio</h2>
      <!--Divizor horizontal (gradiant)-->
      <img src="https://user-images.githubusercontent.com/73097560/115834477-dbab4500-a447-11eb-908a-139a6edaec5c.gif">
    </summary>
  </ul>
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
