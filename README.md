# TP1-Lasers
## _Integrantes:_
- Laganga, Mauricio - 109696  
- Ledesma, Cristian - 111426

# Lasers Game 💥☄️
- [Descripción del Proyecto](#descripción-del-proyecto)
- [Características](#características)
- [Estructura del Proyecto](#estructura-del-proyecto)

## Descripción del Proyecto

Lasers es un juego de estilo puzzle, que se desarrolla por turnos hasta que el jugador encuentra la solución del problema.
En cualquier momento el usuario puede elegir un nivel cualquiera de una lista de niveles disponibles.
El juego se desarrolla en una grilla bidimensional.
Según la configuración de cada nivel, en la grilla se disponen los siguientes elementos:
- **Emisor laser**: Puede ubicarse únicamente en el borde entre dos celdas, o en el borde de la grilla (es decir, nunca en una esquina o en el centro de una celda). El rayo se emite en una de 4 direcciones diagonales posibles.

- **Objetivo**: Puede ubicarse únicamente en el borde entre dos celdas, o en el borde de la grilla.

- **Piso**: Ocupa una celda. Permite que la celda contenga un Bloque. 
- **Bloque**: Solo puede ubicarse sobre una celda que contiene un Piso. Hay bloques de diferentes tipos:

  - *Bloque opaco fijo*: Absorbe rayos laser. No se puede mover.
  - *Bloque opaco móvil*: Absorbe rayos laser.
  - *Bloque espejo*: Refleja los rayos laser. 
  - *Bloque de vidrio*: Al ser alcanzado por un rayo, el rayo se difracta en 2. Un rayo es reflejado (al igual que el bloque espejo), y el otro continúa con la misma dirección (como si no hubiera ningún bloque).
  - *Bloque de cristal*: Al ser alcanzado por un rayo, el rayo se refracta, continuando en línea recta y saliendo por el extremo opuesto del bloque, con la misma dirección de origen.

En cada turno, el jugador puede mover un Bloque (que no sea fijo) a cualquier celda que esté libre (y que tenga un Piso).
El nivel es completado cuando todos los Objetivos son alcanzados por al menos un rayo laser.

## Características

- **Interacción con Bloques**: Mueve bloques para cambiar la dirección del láser.
- **Objetivos de Niveles**: Completa todos los objetivos para ganar el nivel.
- **Recarga Automática**: Los niveles se reinician una vez completados para poder volver a jugarlos desde cero.
- **Lectura Dinámica de Niveles**: Los niveles se cargan desde archivos de texto, lo que facilita agregar nuevos niveles.

## Estructura del Proyecto

- `modelo`: Contiene la lógica del juego, los bloques, láseres, y niveles.
- `controlador`: Define la lógica para manejar eventos del usuario y controlar el flujo del juego.
- `vista`: Define la interfaz gráfica, representaciones visuales del tablero y láseres.
- `levels`: Carpeta que contiene los archivos de texto de cada nivel, donde cada archivo define los bloques y objetivos.
- `docs`: Carpeta que contiene un UML que describe la logica del juego.