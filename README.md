# _Proyecto 1 - Arbol de Expresiones_

**Autores**

_Wilson Neftali Mundo Alonzo - 7690-15-6862_  
_Jefferson Ramiro Lopez Ramirez - 7690-21-1522_

**El proyecto se desarrolo en diferentes etapas.**

- Modelo
- Servicio
- Vista

**Modelo**

Clase ArbolBinario

Esta clase esta conformada por un atributo de tipos String y dos punteros.
Esta clase tambien cuenta con su contructor , setters y getters.

```java
 private String valor;
    private ArbolBinario hijoIzquierdo;
    private ArbolBinario hijoDerecho;
```

De la misma manera cuenta con 3 funciones o metodos que tienen como especificacion realizar los tres recorridos.

- Pre-Orden
- Post-Orden
- In-Orden

por ultimo esta clase cuenta con un metodo de evaluacion.

```java
 public double evaluar() {
        if (Helpers.validaciones.esOperador(valor)) {
            double izquierdo = hijoIzquierdo.evaluar();
            double derecho = hijoDerecho.evaluar();
            return Helpers.validaciones.retornarOeracion(izquierdo, derecho, valor);
        } else {

            return Double.parseDouble(valor);
        }

    }
```

Clase Pila

Esta clase cuenta con dos punteros , con un constructor y 4 metodos

_Metodo push_

El creamos un nuevo puntero del tipo NodoPila
al nuevo puntero se apunta a siguiente = top
ahora tos seria igual al nuevo nodo.

```java
 public void push(ArbolBinario arbol) {
        NodoPila nuevoNodo = new NodoPila(arbol);
        nuevoNodo.siguiente = top;
        top = nuevoNodo;
    }
```

_Metodo pop_

validamos si top es igual a null si esto es cierto la pila estaria vacia. y el puntero de tipo ArbolBinario seria igual a top.arbol. y top seria igual a top.siguiente esto expresa que que top apuntaria al siguiente. por ultimo retorna el puntero arbol.

```java
  public ArbolBinario pop() {
        if (top == null) {
            throw new RuntimeException("Pila Vacia");
        }
        ArbolBinario arbol = top.arbol;
        top = top.siguiente;
        return arbol;
    }
```

_Metodo Validacion si esta Vacia_

si al retornar top muestra que es igual a null la pila se encuentra vacia

```java

public boolean estaVacia() {
        return top == null;
    }

```

_Metodo Limpiar Pila_

Este metodo consta en limpiar la pila usando arrayLists verificando siempre que la lista esta vacia para no entrar al bucle.

```java

 public List<String> LimpiarPila() {
        List<String> valoresPostorden = new ArrayList<>();
        while (!estaVacia()) {
            ArbolBinario arbolExtraido = pop();

            arbolExtraido.recorrerPostorden(valoresPostorden);

        }
        return valoresPostorden;
    }


```

Clase PilaOperadores

Esta clase es bastante similar a la clase pila la cual consta de punteros un atributo valor y metodos de agregar , quitar , limpiar pila entre otros.

Clase Variable

Esa clase cuenta con 2 atributos que son valor y caracter, de la misma manera tambien cuenta con su contructor y sus getters y setters

```java
private Double valor;
    private char caracter;
```

**Servicio**

Clase Helpers

En esta clase contamos con distintos metodos.

_Metodo es operador_

Es esto verificamos si es igual al un operadores con la palabra equals.

```java
 public static boolean esOperador(String operador) {
            return operador.equals("*")
                    || operador.equals("/")
                    || operador.equals("+")
                    || operador.equals("-")
                    || operador.equals("^")
                    || operador.equals("√");
        }
```

_Metodo validar si la expresion es Vacia_

En este metodo validamos la variable expression usando el metodo length verificando si es menor o igual que 0 o si la epresiones es igual a "" si esto llegara a suceder le indicamos al usuario con un mensaje ("La cadena que ingreso esta vacia")

```java
 public static boolean ValidaExpressionVacia(String expression) {
            if ((expression.length() <= 0) || (expression.equals(""))) {
                throw new RuntimeException("la cadena que ingreso esta vacia");
            } else {
                return true;
            }
        }

```

Aparte de estos metodos hay otros metodos que realizan otra validaciones.

Clase NotacionPolacaServicio

En esta clase necesitamos importar las demas clases anteriores con las que se trabajo para podes implementarlas tambie se uso arrayList y List.

```java
 public List<String> getValoresPreOrden() {
        return valoresPreOrden;
    }

    public void setValoresPreOrden(List<String> valoresPreOrden) {
        this.valoresPreOrden = valoresPreOrden;
    }

    public List<String> getValoresInOrden() {
        return valoresInOrden;
    }

    public void setValoresInOrden(List<String> valoresInOrden) {
        this.valoresInOrden = valoresInOrden;
    }

    List<String> valoresInOrden = new ArrayList<>();

```

de la misma manera se implemento la notacion Polaca para podes satisfacer el tema de las expresiones. de igual manera para trabajar con la notacion polaca usamos todas las clases anteriores por eso es importante importar dichas clase.

creamos la funcion para la notacion polaca utilizando la variable expresion y un arrayListe haciendo una validacion de helpers , luego instanciamos La clase RegistroVariablesServicio , utilizando otra variable luego cremaos un arreglo usando el metodo split , seguidos usamos un for para recorrer el string , hacemos otra validacin si Helpers es numero o si Helpers es letra , esto seria una pequeña parte de este metodo.

```java
 public double notacionPolacaGeneral(String expression, ArrayList<Variable> variables) {
        if (Helpers.validaciones.ValidaExpressionVacia(expression)) {
            RegistroVariablesServicio variableServicio = new RegistroVariablesServicio();
            Variable variable;
            String[] cadena = expression.split(" ");
            for (int i = 0; i <= cadena.length - 1; i++) {
                String operador = cadena[i];

                if (Helpers.validaciones.esNumero(operador)) {
                    arbol.push(new ArbolBinario(operador));
                } else if (Helpers.validaciones.esLetra(operador)) {
                    variable = variableServicio.retornaVariable(variables, operador.charAt(0));
                    String valorVariable = String.valueOf(variable.getValor());
                    arbol.push(new ArbolBinario(valorVariable));
                } else if (Helpers.validaciones.esOperador(operador)
                        || (pilaOperadores.ValidarParentesisAbierto(operador))
                        || (pilaOperadores.ValidarParentesisCerrado(operador))) {
```

Clase RegistroVariables Servicio

En esta clase se implemento el metodo de registro de variables para darle un valor , y el metodo de retorno de variable y validacion de la expresion. Este es un pequeño ejemplo de uno de los metodos.

```java

 private Variable RegistraVariables(double numero, char caracter) {

        if (!(Character.isLetter(caracter))) {
            throw new RuntimeException("el valor ingresado como variable no es un caracter valido\n "
                    + "solo se aceptan letras del alfabeto");
        }
        Variable variable = new Variable(numero, caracter);
        return variable;
    }


```

**Vista**

Clase inicio

Aca mostramos la pantalla principal con una opcion que es la funcion de la notacion polaca.

<img src="https://onedrive.live.com/embed?resid=D219255F2ABEB74E%21296170&authkey=%21AIdPootuP_CBFOc&width=1366&height=477" width="800" height="477" />

Al dar click a la opcion del menu nos muestra esta nueva interfaz , con diferentes funciones como ingregar la expresion , validar la expresion , resolver la expresion , ingresar una nueva expresion y por ultimo muestra los direntes recorridos.

<img src="https://onedrive.live.com/embed?resid=D219255F2ABEB74E%21296171&authkey=%21AKoY3ySzpJ9EJm4&width=772&height=722" width="800" height="477" />

<img src="https://onedrive.live.com/embed?resid=D219255F2ABEB74E%21296173&authkey=%21AKCpHcgApNVv-Ys&width=998&height=726" width="800" height="477" />

<img src="https://onedrive.live.com/embed?resid=D219255F2ABEB74E%21296174&authkey=%21AFS3Tg97QpjMVEg&width=1897&height=976" width="800" height="477" />
