Debug

Todos sus métodos son estáticos.

Para evitar chequeos del estado de debug (on/off) cada vez que se quiere imprimir algo, eso queda encapsulado en los métodos de uso general de la clase Debug, en particular la familia de métodos print() (polimórfica), que no hacen nada a no ser que esté activado el modo debug

	print(String)     // Imprime String
	println()         // Imprime un salto de línea
	println(String)   // imprime String + salto
	print(title, txt) // Imprime un par título/contenido, con un formato destacado
	print(title, List<String>) // Idem anterior pero el contenido es una lista de strings

Además, la idea es agregar sin pensarlo mucho otros métodos específicos para debugging. Como ejemplo, está el método printArgs() que imprime los argumentos con que está corriendo el programa, en un formato específico.


--------------------------------------------------------------------
Args

Todos sus métodos son estáticos.

Fuera de corregir bugs o agregar opciones, no hay que tocar más esta clase. Se cargan los argumentos al inicio (desde el main) y después lo único que corresponde hacer es llamar los getters, que se llaman con el nick del atributo, no "getX".

Lista completa de getters de Args
	String  Args.reader()         [-rdr <código_reader>]
	boolean Args.debug()          [-d]
	String  Args.seed()           [<url>] (solo como último parámetro)
	String  Args.proxy()          [-prx <proxy>]
	boolean Args.persistent()     [-persistent]
	int     Args.maxDepth()       [-depth <max_depth>]
	int     Args.maxThreads()     [-p <max_threads>]
	String  Args.sinksFile()      [-pozos <file>]
	String  Args.multilangFile()  [-multilang <file>]

Readers disponibles, por código
	sockets
	httpreq
	mockups

Notas
	MaxDepth -1 indica "sin límite", todos los demás naturales son aceptados (incluyendo 0)
	sinksFile y multilangFile son null si no están seteados
	maxThreads es 1 por defecto
	maxDepth es -1 por defecto
	debug es false por defecto
	reader es "sockets" por defecto (clase ReaderUsingSockets)


Reader

Las clases que implementan la interface Reader tienen:
	String get() - lee una página usando GET, retorna el contenido
	String put() - lee una página usando POST, retorna el contenido
	String head() - lee los headers de una página usando HEAD, retorna el contenido (los headers como un string sin parsear)
	String headers() - devuelve un Map<String, String> con los nombres de cada header como key

Por el momento hay 3 clases que implementan Reader:

	ReaderUsingHttpReq (el primero que hizo luciana, usando las librerías normales de Java)
	ReaderUsingMockUps (uno que devuelve páginas hardcodeadas, para testing y desarrollo por componentes básicamente)
	ReaderUsingSockets (el posta obligatorio de la tarea)

Para evitar problemas con concurrencia, la idea es crear un Reader nuevo para cada lectura. El ejemplo podría ser:

	Reader r = createReader();           // RedBot.createReader()
	String page = r.get(some_url);       // Después se pasaría la página al Parser, etc.
	Map<String, String> h =r.headers();  // Si corresponde

Va a ser necesario agregar métodos que automaticen la búsqueda de urls multilanguage, o que manden requests diferentes para testear otras cosas. Por ahora puse lo básico nomás, después hay que ver si hay métodos que corresponda agregar a la interface o solo a alguna implementación particular, etc.


Parser

	Parser p = new Parser(page_content);    // Parsear page
	List<String> urls = p.urls();           // Lista de urls encontradas
	List<String> emails = p.emails();       // Lista de emails encontrados
	Map<String, String> stats = p.stats();  // Otra información de la página, si corresponde

El Parser no contiene la lógica relacionada con el contenido de la página, por ejemplo si una dirección se tiene que seguir o no (sea porque se alcanzó el maxDepth, o porque ya se recorrió, o lo que fuera) o si un email es repetido. Esa lógica le corresponde al programa (en RedBot o alguna clase auxiliar si llegara a ser necesaria).
