## Practice 3
The goal of the project is to simulate a real-time client-server system for telemetry processing. The server receives incoming data from multiple simulated ground stations, processes telemetry data concurrently using threads, and returns reports. The practice is structured into 4 development phases, each adding concurrency and caching mechanisms.

## Technologies
- Java 18
- Eclipse IDE
- Git (with remote repository delivery)
- Provided `.jar` libraries (e.g., `p3-telemetría.jar`)
- Simulated Station Tool (Java-based)
- Visual Monitoring Panel (PanelVisualizador)

- - `ServidorTelemetría.java` – Main class to start the server and initialize components.
- `HiloRecepción.java` – Thread responsible for receiving telemetry requests and spawning request threads.
- `HiloPetición.java` – Manages a single telemetry request lifecycle.
- `Trabajo.java` – Represents a unit of telemetry analysis.
- `ColaTrabajos.java` – Thread-safe queue managing the processing workload.
- `HiloAnalizador.java` – Thread that processes telemetry data.
- `CacheTrabajosActivos.java` – Shared cache storing active and completed analysis jobs.
- `HiloLiberador.java` – Memory management thread for cleaning the cache using LRU policy.

## Testing##
Use the SimuladorEstación tool with commands like
