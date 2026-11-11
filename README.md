# design-patterns-practice

## 1. Creational Patterns

### **Factory Method**

* **Problem:** Build a **Notification System** that sends alerts via different channels (Email, SMS, Push Notification, WhatsApp).
* **Challenge:** Your core business logic should issue a notification without hardcoding `new EmailNotification()` or using massive `if/else` checks. Adding a new channel (e.g., Slack) in the future should only require adding a new class without modifying existing code (Open/Closed Principle).
* **Key Goal:** Instantiate objects through a common creator interface rather than direct class instantiation.

### **Abstract Factory**

* **Problem:** Build a **Cross-Platform UI Framework** (for Desktop vs. Mobile, or Light Mode vs. Dark Mode).
* **Challenge:** Create component families (e.g., `Button`, `Checkbox`, `TextField`). When the app runs in *Dark Mode*, every component created by the factory must automatically be from the *Dark Theme* family without mixing light and dark components.
* **Key Goal:** Provide an interface for creating families of related objects without specifying their concrete classes.

### **Builder**

* **Problem:** Build an **HTTP Request / SQL Query Constructor**.
* **Challenge:** Construct complex `HttpRequest` objects with dozens of optional parameters (headers, query params, body, auth token, timeout, retry policy). Avoid "telescoping constructors" (constructors with 10+ arguments) and construct immutable request objects step-by-step.
* **Key Goal:** Separate the construction of a complex object from its representation.

### **Singleton / Thread-Safe Singleton**

* **Problem:** Build an **In-Memory Configuration Manager** or **Connection Pool Master**.
* **Challenge:** Ensure only one instance of the app settings/configuration exists across the entire application lifespan, especially under multi-threaded execution (prevent race conditions during initial setup).
* **Key Goal:** Ensure a class has only one instance while providing a global access point.

---

## 2. Structural Patterns

### **Adapter**

* **Problem:** Build a **Third-Party Payment Gateway Integration**.
* **Challenge:** Your application expects a unified interface (`processPayment(amount, currency)`). You need to integrate two legacy third-party APIs: API A uses `makeTransaction(cents, currCode)` and API B uses `chargeCard(detailsMap)`.
* **Key Goal:** Wrap incompatible interfaces into a target interface your system already understands.

### **Decorator**

* **Problem:** Build a **Customizable Beverage / Coffee Ordering System** (or File Compression/Encryption Stream).
* **Challenge:** Start with a base `Coffee` class. Customers can dynamically add ingredients (Milk, Caramel, Espresso Shot, Oat Milk, Whip Cream) in any combination. Avoid class explosion (e.g., `CoffeeWithMilkAndCaramel`). Each addition should wrap the previous object and dynamically update the `getCost()` and `getDescription()`.
* **Key Goal:** Attach additional responsibilities to an object dynamically without subclassing.

### **Facade**

* **Problem:** Build a **Home Automation / Media Center Controller**.
* **Challenge:** Turning on "Movie Mode" requires controlling 5 sub-systems: dimming lights (`LightingSystem`), turning on projector (`Projector`), setting input source (`HDMI`), turning on soundbar (`SoundSystem`), and launching Netflix (`StreamingApp`). Create a simple interface `movieMode.enable()` that hides all this internal orchestration.
* **Key Goal:** Provide a unified, simplified interface to a complex subsystem.

### **Composite**

* **Problem:** Build a **File System Explorer** (or Organization Hierarchy / GUI Component Tree).
* **Challenge:** Treat individual files (`File`) and directories (`Folder`) uniformly. A `Folder` can contain both `File`s and sub-`Folder`s. Calling `getSize()` on a directory should recursively calculate the total size of everything inside it.
* **Key Goal:** Compose objects into tree structures to represent part-whole hierarchies.

---

## 3. Behavioral Patterns

### **Strategy**

* **Problem:** Build an **E-Commerce Checkout & Route Navigator**.
* **Challenge:**
1. For Checkout: Calculate shipping rates based on chosen strategy (Standard, Express, International, Local Pickup).
2. For Navigation: Calculate optimal routes based on transport strategy (Driving, Walking, Biking, Public Transit).
Swap the strategy object at runtime without changing the context class.


* **Key Goal:** Define a family of algorithms, encapsulate each one, and make them interchangeable at runtime.

### **Observer**

* **Problem:** Build a **Stock Market / Weather Monitoring Dashboard**.
* **Challenge:** A `StockData` subject receives real-time price updates. Multiple display elements (`MobileAlertWidget`, `RealtimeChartWidget`, `FileLogger`) need to instantly reflect changes whenever stock prices move, without `StockData` being tightly coupled to specific display classes.
* **Key Goal:** Define a 1-to-many dependency so that when one object changes state, all its dependents are notified automatically.

### **State**

* **Problem:** Build a **Vending Machine** or **Document Approval Workflow** (Draft → Under Review → Approved → Published).
* **Challenge:** A Vending Machine behaves differently when receiving a coin depending on its current state (`NoCoin`, `HasCoin`, `SoldOut`, `Dispensing`). Instead of writing massive `switch` or `if/else` blocks inside every method, delegate behavior to individual `State` objects that trigger state transitions.
* **Key Goal:** Allow an object to alter its behavior when its internal state changes.

### **Command**

* **Problem:** Build a **Text Editor / Smart Home Remote Control with Undo/Redo**.
* **Challenge:** Implement an action executor (Copy, Paste, Bold, Delete) where each action is encapsulated into a command object with `execute()` and `undo()` methods. Maintain an execution history stack to enable infinite Undo/Redo operations.
* **Key Goal:** Encapsulate a request as an object, allowing parameterization and queueing of requests with undo support.

### **Chain of Responsibility**

* **Problem:** Build an **HTTP Request Authentication & Validation Pipeline**.
* **Challenge:** An incoming web request must pass through sequential validation checks: `LoggingHandler` → `RateLimitingHandler` → `AuthenticationHandler` → `AuthorizationHandler` → `SanitizationHandler`. If any handler fails, it breaks the chain and returns an error without calling the next link.
* **Key Goal:** Pass a request along a chain of handlers, letting each handler decide whether to process it or pass it to the next.

---
