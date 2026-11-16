# 🛠️ Design Patterns Practice in Java

A hands-on practice repo: one real-world problem per Gang of Four design pattern, each with a clear challenge, a key goal, and a verifiable "Done when" criterion.

---

## 1. Creational Patterns

### **Factory Method**

* **Problem:** Build a **Notification System** that sends alerts via different channels (Email, SMS, Push Notification, WhatsApp).
* **Challenge:** Your core business logic should issue a notification without hardcoding `new EmailNotification()` or using massive `if/else` checks. The abstract creator must contain a concrete method (e.g. `notifyUser(message)`) that *uses* the product returned by the abstract factory method. Adding a new channel (e.g., Slack) should only require adding new classes without modifying existing code (Open/Closed Principle).
* **Key Goal:** Instantiate objects through a common creator interface rather than direct class instantiation.
* **Done when:** Slack is added without editing any existing file.

### **Abstract Factory**

* **Problem:** Build a **Cross-Platform UI Framework** (Light Mode vs. Dark Mode; Desktop vs. Mobile as a bonus).
* **Challenge:** Create component families (e.g., `Button`, `Checkbox`, `TextField`). When the app runs in *Dark Mode*, every component created by the factory must automatically be from the *Dark Theme* family, without mixing light and dark components.
* **Key Goal:** Provide an interface for creating families of related objects without specifying their concrete classes.
* **Done when:** The client code depends only on abstract factory/product types, and switching theme means changing a single line.

### **Builder**

* **Problem:** Build an **HTTP Request / SQL Query Constructor**.
* **Challenge:** Construct complex `HttpRequest` objects with dozens of optional parameters (headers, query params, body, auth token, timeout, retry policy). Avoid "telescoping constructors" (constructors with 10+ arguments) and build **immutable** request objects step-by-step. Validate required fields (e.g. URL) in `build()`.
* **Key Goal:** Separate the construction of a complex object from its representation.
* **Done when:** The request object has no setters, and `build()` throws if a required field is missing.

### **Singleton / Thread-Safe Singleton**

* **Problem:** Build an **In-Memory Configuration Manager** or **Connection Pool Master**.
* **Challenge:** Ensure only one instance exists across the entire application lifespan, especially under multi-threaded execution. First write a **naive** version and **prove the race condition** with several threads creating the instance at once, then fix it (holder idiom, double-checked locking with `volatile`, or enum/static-local depending on the language).
* **Key Goal:** Ensure a class has only one instance while providing a global access point.
* **Done when:** A multi-threaded test proves the naive version breaks and the fixed version always returns the same instance.

---

## 2. Structural Patterns

### **Adapter**

* **Problem:** Build a **Third-Party Payment Gateway Integration**.
* **Challenge:** Your application expects a unified interface (`processPayment(amount, currency)`). Integrate two legacy third-party APIs that you **cannot modify**: API A uses `makeTransaction(cents, currCode)` and API B uses `chargeCard(detailsMap)`.
* **Key Goal:** Wrap incompatible interfaces into a target interface your system already understands.
* **Done when:** The checkout code only knows `processPayment` and never imports API A or B directly.

### **Decorator**

* **Problem:** Build a **Customizable Beverage / Coffee Ordering System** (or File Compression/Encryption Stream).
* **Challenge:** Start with a base `Coffee` class. Customers can dynamically add ingredients (Milk, Caramel, Espresso Shot, Oat Milk, Whip Cream) in any combination. Avoid class explosion (e.g., `CoffeeWithMilkAndCaramel`). Each addition wraps the previous object and dynamically updates `getCost()` and `getDescription()`.
* **Key Goal:** Attach additional responsibilities to an object dynamically without subclassing.
* **Done when:** Any combination (including the same ingredient twice) works with zero extra classes.

### **Composite**

* **Problem:** Build a **File System Explorer** (or Organization Hierarchy / GUI Component Tree).
* **Challenge:** Treat individual files (`File`) and directories (`Folder`) uniformly through a common interface. A `Folder` can contain both `File`s and sub-`Folder`s. Calling `getSize()` on a directory recursively calculates the total size of everything inside it.
* **Key Goal:** Compose objects into tree structures to represent part-whole hierarchies.
* **Done when:** Client code calls `getSize()` without ever checking whether the node is a file or a folder.

### **Facade**

* **Problem:** Build a **Home Automation / Media Center Controller**.
* **Challenge:** Turning on "Movie Mode" requires controlling 5 subsystems: dimming lights (`LightingSystem`), turning on the projector (`Projector`), setting the input source (`HDMI`), turning on the soundbar (`SoundSystem`), and launching Netflix (`StreamingApp`). Create a simple interface `movieMode.enable()` that hides all this internal orchestration.
* **Key Goal:** Provide a unified, simplified interface to a complex subsystem.
* **Done when:** The client calls one method and never touches the subsystem classes.

---

## 3. Behavioral Patterns

### **Strategy**

* **Problem:** Build an **E-Commerce Shipping Rate Calculator**.
* **Challenge:** Calculate the shipping cost of an order based on a chosen strategy (Standard, Express, International, Local Pickup). The `Order` (context) must not contain any `if/else` on the shipping type. The strategy must be **swappable at runtime** (e.g. the customer changes the option at checkout) without modifying the context class.
* **Key Goal:** Define a family of algorithms, encapsulate each one, and make them interchangeable at runtime.
* **Bonus:** Reuse the pattern for a second family (Payment: Card, PayPal, Cash on delivery) or a Route Navigator (Driving, Walking, Biking, Public Transit).
* **Done when:** Adding a new shipping method requires only a new class.

### **Observer**

* **Problem:** Build a **Stock Market / Weather Monitoring Dashboard**.
* **Challenge:** A `StockData` subject receives real-time price updates. Multiple display elements (`MobileAlertWidget`, `RealtimeChartWidget`, `FileLogger`) must instantly reflect changes whenever prices move, without `StockData` being coupled to specific display classes. Observers must be able to **subscribe and unsubscribe at runtime**. Compare the **push** model (data sent in the notification) with the **pull** model (observer queries the subject).
* **Key Goal:** Define a 1-to-many dependency so that when one object changes state, all its dependents are notified automatically.
* **Done when:** `StockData` only knows an `Observer` interface, and an observer can be removed mid-run without errors.

### **State**

* **Problem:** Build a **Vending Machine**.
* **Challenge:** States: `NoCoin`, `HasCoin`, `Dispensing`, `SoldOut`. Actions: `insertCoin()`, `ejectCoin()`, `selectProduct()`, `dispense()`. The machine behaves differently for each action depending on its current state. Instead of massive `switch` or `if/else` blocks, delegate behavior to individual `State` objects that trigger the state transitions.
* **Key Goal:** Allow an object to alter its behavior when its internal state changes.
* **Bonus:** Implement a **Document Approval Workflow** (Draft → Under Review → Approved → Published) as a second version.
* **Done when:** The machine class contains no conditional on its current state.

### **Command**

* **Problem:** Build a **Text Editor with Undo/Redo**.
* **Challenge:** Encapsulate every *document-modifying* action (Insert Text, Delete, Bold, Paste) as a command object with `execute()` and `undo()`. Use **two stacks** (undo and redo). Executing a new command must **clear the redo stack**.
* **Key Goal:** Encapsulate a request as an object, allowing parameterization and queueing of requests with undo support.
* **Bonus:** A `MacroCommand` (a batch of commands executed and undone as one) and a command queue.
* **Done when:** Undo/redo works over a long sequence of mixed actions, and the editor never knows the concrete command types.

### **Chain of Responsibility**

* **Problem:** Build an **HTTP Request Authentication & Validation Pipeline**.
* **Challenge:** An incoming web request must pass through sequential checks: `LoggingHandler` → `RateLimitingHandler` → `AuthenticationHandler` → `AuthorizationHandler` → `SanitizationHandler`. If any handler fails, it breaks the chain and returns an error without calling the next link.
* **Key Goal:** Pass a request along a chain of handlers, letting each handler decide whether to process it or pass it to the next.
* **Done when:** Handlers can be reordered or removed without modifying any handler's code.

---

## 🗺️ Progress & Roadmap

### Phase 1: Core Decoupling
- [x] **Strategy** — E-Commerce Shipping Rate Calculator
- [x] **Factory Method** — Multi-channel Notification System
- [ ] **Observer** — Real-time Weather / Stock Dashboard

### Phase 2: Creational
- [ ] **Abstract Factory** — Multi-Theme UI Toolkit
- [ ] **Builder** — Fluent HTTP / SQL Query Constructor
- [ ] **Singleton** — Thread-safe In-Memory Config Manager

### Phase 3: Structural
- [ ] **Adapter** — Third-party Payment Gateway Integration
- [ ] **Decorator** — Dynamic Beverage Customizer
- [ ] **Composite** — File System Tree Explorer
- [ ] **Facade** — Smart Home Controller Subsystem

### Phase 4: Behavioral Workflows
- [ ] **Command** — Text Editor with Undo/Redo Stack
- [ ] **State** — Vending Machine State Engine
- [ ] **Chain of Responsibility** — Middleware Validation Pipeline
