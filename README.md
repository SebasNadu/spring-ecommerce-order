# spring-ecommerce-order

## Features

### Step 1.1

- [x] Replace JdbcTemplate for spring data jpa.
- [x] Use JPA in the entities and services.
- [x] Add logging to JPA

### Step 1.2

- [x] Add Pagination for Products
- [x] Add wishlist items
- [x] Add Pagination for wishlist items

### Step 1.3

- [x] Create an Option entity
- [x] The entity should contain a name up to 50 characters
- [x] The Option quantity must be at least 1 and less than 100_000_000
- [x] Duplicated option names are not allowed within the same product to prevent confusion during purchase.

- [x] Add Admin functionality to create new option for product
- [x] Create an endpoint in the admin controller for adding new options to products

### Step 2.1

### Step 2.1 — Stripe Integration & Order Management

- [x] Add DotEnv dependency to manage environment variables securely (.env files).
- [x] Implement EnvironmentPostProcessor to load `.env` variables into Spring Environment at startup.
- [x] Add `.env` file at the project root (excluded from version control).
- [x] Define `StripeProperties` as configuration properties for Stripe API keys and settings.
- [x] Create `StripeClientConfiguration` bean to build a reusable HTTP client for Stripe API calls.
- [x] Define `StripeClient` interface leveraging `HttpExchange` for clean and testable Stripe operations.
- [x] Implement `StripeService` to encapsulate Stripe API interactions; include unit tests to verify behavior.
- [x] Refactor `CartItem` entity to replace `productId` with `optionId` for precise product variant handling.
- [x] Validate that `CartItem.quantity` does not exceed available stock quantity in the associated `Option`.
- [x] Add unitPrice to the `Option` entity and dto to reflect the price of each option.

- [ ] Create `Order` entity with the following fields:
    - `id`
    - `orderDate` (timestamp of order creation)
    - `status` (enum: PENDING, PAID, SHIPPED, CANCELED, etc.)
    - `checkoutSessionId` (Stripe checkout session identifier)
    - `paymentAmount` (total paid amount)
    - `member` (Many-to-One relation to the ordering Member)
- [ ] Design `OrderItem` entity to represent individual purchased options with:
    - `option` (Many-to-One relation to Option)
    - `order` (Many-to-One relation to Order)
    - `quantity`
    - `unitPrice`
- [ ] Establish relationships:
    - `Order` to `Member` as Many-to-One.
    - `Order` to `OrderItem` as One-to-Many.
    - `OrderItem` to `Option` as Many-to-One.
    - `OrderItem` to `Order` as Many-to-One.
