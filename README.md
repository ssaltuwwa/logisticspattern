# 🚚 Factory Method — Logistics (Compact, No Packages)

A clear, **human-readable Factory Method implementation** for a logistics scenario.  
This compact variant uses **one concrete creator (`SimpleLogistics`)** that chooses which product (`Transport`) to instantiate based on a small enum (`TransportMode`).  
It keeps class count low while staying 100% correct for Factory Method.

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

## 5️⃣ UML (Mermaid)

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
