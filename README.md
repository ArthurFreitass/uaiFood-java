[Leia esta página em português](README-pt.md)

# Uai-Foods — Food Ordering System

A console-based food ordering application built in Java, designed to simulate a real-world order flow: client registration, menu browsing, item selection, payment processing, and order persistence.

---

## Technologies

| Technology | Version |
|---|---|
| Java | 25+ |
| JDK | OpenJDK 25+ |

No external dependencies or build tools are required. The project compiles and runs with the standard Java toolchain.

---

## Purpose

Uai-Foods was developed as a learning project to practice object-oriented design principles in Java. The focus was on separating responsibilities across layers, modeling a real domain with entities and services, handling domain-specific exceptions, and keeping the application entry point clean and minimal.

---

## Features

- Client registration with name and CPF validation
- Interactive console menu display
- Single or multiple item ordering in a single session
- Order summary generation with itemized subtotals and total amount
- Payment processing with support for multiple payment methods
- Change calculation returned to the client
- Order persistence to a file at a user-specified path
- Domain exception handling with descriptive error messages

---

## Project Structure

```
src/
├── application/
│   ├── Main.java
│   └── ConsoleUI.java
└── model/
    ├── entities/
    │   ├── Client.java
    │   ├── Order.java
    │   ├── OrderItem.java
    │   ├── Product.java
    │   └── enums/
    │       └── OrderStatus.java
    ├── exceptions/
    │   └── DomainException.java
    ├── service/
    │   ├── Menu.java
    │   ├── OrderRepository.java
    │   ├── OrderService.java
    │   └── PaymentService.java
    └── util/
        ├── CheckOrderNumber.java
        ├── DateTimeUtil.java
        ├── PaymentUtil.java
        └── SimpleValidation.java
```

---

## Layer Responsibilities

### `application`
The entry point of the application. `Main` initializes the runtime locale and delegates all execution to `ConsoleUI`, which handles the full user interaction flow — registration, ordering, payment, and file saving.

### `model.entities`
Contains the core domain objects of the application.

- `Client` — represents the customer, holding name and CPF with validation on both fields
- `Product` — represents a menu item with name and price
- `OrderItem` — associates a `Product` with a quantity and computes the line subtotal
- `Order` — aggregates `OrderItem` instances, tracks status, computes the total, generates the order summary, and processes payment
- `enums/OrderStatus` — defines the lifecycle states of an order: `PENDING` and `FINISHED`

### `model.exceptions`
- `DomainException` — a custom unchecked exception used to signal rule violations within the domain layer, such as invalid CPF length, null fields, or invalid order numbers

### `model.service`
Contains the application services that coordinate domain logic.

- `Menu` — holds the product catalog and exposes it for display and selection
- `OrderService` — responsible for instantiating `Order` objects and delegating payment processing
- `OrderRepository` — handles order persistence, writing the order summary to a file
- `PaymentService` — interface defining the contract for payment processing implementations

### `model.util`
Utility classes with stateless helper methods.

- `CheckOrderNumber` — validates whether a given menu item number is within the accepted range
- `DateTimeUtil` — provides a shared date/time formatter used in order summaries
- `PaymentUtil` — builds the payment method selection message and returns the appropriate `PaymentService` implementation based on user input
- `SimpleValidation` — validates user input for yes/no prompts and payment method choices

---

## How to Run

```bash
# Compile
javac -d out -sourcepath src src/application/Main.java

# Run
java -cp out application.Main
```

---

## Order Flow

```
Start
  └── Client registration (name + CPF)
        └── Menu display
              └── Item selection (repeatable)
                    └── Order summary
                          └── Payment method selection
                                └── Amount input
                                      └── Order saved to file
```
