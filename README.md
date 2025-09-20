# 🚚 Factory Method — Logistics 
- **Course:** Software Design Patterns
- **Name/Group:** Sergeyeva Saltanat SE-2403

---

## 1️⃣ Problem & Goal
We need a logistics app that can plan deliveries via different transport modes (**road, sea, air**).  
The app should be:
- **Loosely coupled** → client code does not depend on concrete classes.
- **Easy to extend** → adding a new transport later should be simple.

---

## 2️⃣ Why Factory Method?
Factory Method defines an interface for creating objects in a superclass and lets subclasses decide which concrete type to instantiate.

**Benefits:**
- ✅ **Decouples** client code from concrete products  
- ✅ **Centralizes** object creation logic  
- ✅ **Open for extension** – add new products with minimal changes  

---

## 3️⃣ Pattern Roles → Code Mapping
| **Pattern Role** | **Code** |
|------------------|----------|
| Product          | `Transport` (interface with `deliver()`) |
| Concrete Products| `Truck`, `Ship`, `Airplane` |
| Creator (abstract) | `Logistics` (declares `createTransport()`, implements `planDelivery()`) |
| Concrete Creator | `SimpleLogistics` (stores `TransportMode`, overrides `createTransport()`) |
| Client           | `Main` (calls `planDelivery()`; never instantiates concrete classes directly) |

---

## 4️⃣ How It Works (Design Walkthrough)
1. **Client** constructs `SimpleLogistics` with a selected `TransportMode` (e.g., `ROAD`).  
2. **Client** calls `planDelivery()`.  
3. `planDelivery()` invokes the factory method `createTransport()` to obtain a `Transport`.  
4. The **client interacts only with `Transport`**, never with `Truck`/`Ship`/`Airplane` directly.

---

## 5️⃣ How to Build & Run

### 📌 Files (no packages)
- `Transport.java`  
- `Truck.java`  
- `Ship.java`  
- `Airplane.java`  
- `TransportMode.java`  
- `Logistics.java`  
- `SimpleLogistics.java`  
- `Main.java`  

### 🖥 IntelliJ IDEA
1. **Create a plain Java project** and put all `.java` files into `src/`.
2. Open **Run Configurations**:
   - **Main class:** `Main`
   - **Program arguments:** `road` / `sea` / `air`  
     *(no arguments → runs all three modes)*

### 🖥 CLI
```bash
javac *.java
java Main road     # try: sea, air
exit

## ✅ Expected Output

[SimpleLogistics(ROAD)] Planning delivery...
Delivering goods by road using a Truck.
[SimpleLogistics(SEA)] Planning delivery...
Delivering goods by sea using a Ship.
[SimpleLogistics(AIR)] Planning delivery...
Delivering goods by air using an Airplane.

---

## 6️⃣ Extensibility

You **don’t need to add new transport types** to meet the Factory Method pattern requirement,  
but the design makes it easy if you ever want to:

1. **Create a new product class**  
   Example:  
   ```java
   public class Drone implements Transport {
       @Override
       public void deliver() {
           System.out.println("Delivering goods by drone through the air.");
       }
   }

2. **Add the new enum value**
    public enum TransportMode {
        ROAD, SEA, AIR, DRONE
    }

3. **Extend createTransport() in SimpleLogistics**
    @Override
    protected Transport createTransport() {
        switch (mode) {
            case ROAD: return new Truck();
            case SEA: return new Ship();
            case AIR: return new Airplane();
            case DRONE: return new Drone();
            default: throw new IllegalArgumentException("Unknown transport mode: " + mode);
        }
    }

✅ No changes required to Main or any other client code — **Open/Closed Principle respected.**

---

## 7️⃣ Clean Code Notes

- **Meaningful Names:**  
  Classes and methods are named after their intent: `Transport`, `Logistics`, `createTransport`, `planDelivery`.

- **Single Responsibility Principle:**  
  Each class has exactly one job:
  - `Transport` = interface for delivery behavior  
  - `Truck`, `Ship`, `Airplane` = concrete products  
  - `Logistics` = planning template with factory method  
  - `SimpleLogistics` = chooses which product to create  

- **Open/Closed Principle:**  
  The system is **open for extension** (add new products like `Drone`) but **closed for modification** (no changes to client code).

- **Program to Interfaces:**  
  `Main` only uses the `Transport` interface and never touches concrete classes.

- **Readable Methods:**  
  Small, clear, and easy to follow — `planDelivery()` and `createTransport()` are both short and focused.

---

## 8️⃣ Pros & Cons of the Compact Variant

| ✅ **Pros** | ⚠️ **Cons** |
|------------|-------------|
| Fewer classes → very easy to read | Single `switch`/`if` block may grow large as product count grows |
| Still 100% valid Factory Method | For very large systems, consider multiple creators (e.g. `RoadLogistics`, `SeaLogistics`) |
| Perfect for assignments, interviews, small projects | — |

> 💡 **Takeaway:**  
> This variant trades a small `switch` for minimal class count, making it ideal for learning or small projects.  
> For large enterprise codebases, multiple concrete creators might scale better.

---

## 9️⃣ Testing Ideas

- **Mode Tests:**  
  Verify that `TransportMode.ROAD` returns `Truck`, `SEA` returns `Ship`, and `AIR` returns `Airplane`.

- **Behavior Tests:**  
  Mock or spy a `Transport` object to ensure `planDelivery()` calls `deliver()` exactly once.

- **Extensibility Test:**  
  Add a new product (`Drone`), run without modifying `Main`, confirm it works.  
  ✅ Demonstrates **open/closed principle** in action.

---

## 🔟 UML (Mermaid)

```mermaid
classDiagram
    direction LR

    class Transport {
      +deliver() void
    }

    class Truck {
      +deliver() void
    }
    class Ship {
      +deliver() void
    }
    class Airplane {
      +deliver() void
    }

    Transport <|.. Truck
    Transport <|.. Ship
    Transport <|.. Airplane

    class Logistics {
      +createTransport() Transport
      +planDelivery() void
    }

    class TransportMode
    <<enumeration>> TransportMode
    TransportMode : ROAD
    TransportMode : SEA
    TransportMode : AIR

    class SimpleLogistics {
      -mode: TransportMode
      +SimpleLogistics(mode)
      +createTransport() Transport
    }

    Logistics <|-- SimpleLogistics
    SimpleLogistics --> TransportMode
    Logistics --> Transport : uses

---
