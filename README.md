
# ShoppingListApp

## Overview
ShoppingListApp is a simple Android application built with Jetpack Compose that allows users to create and manage a shopping list. Users can add, edit, and delete shopping items with quantities.

---

## Features
- Add shopping items with a name and quantity.
- Edit existing shopping items inline.
- Delete items from the list.
- Input validation with user-friendly error messages.
- Uses Jetpack Compose for a modern, reactive UI.

---

## Data Model

```kotlin
data class ShoppingItem(
    val id: Int,
    var name: String,
    var quantity: Int,
    var isEditing: Boolean = false
)
```

Represents a shopping item with an ID, name, quantity, and an editing state.

---

## Composables

### ShoppingListApp

The main composable managing the list state and UI.

- Manages the list of `ShoppingItem`s.
- Controls showing the dialog to add items.
- Handles adding, editing, and deleting items.

---

### ShoppingListDialog

Dialog UI to add a new item.

- Inputs for item name and quantity.
- Validation on inputs.
- Calls back on successful input to add the item.

---

### ShoppingItemEditor

UI for editing an existing shopping item.

- Editable fields for name and quantity.
- Save button triggers update.

---

### ShoppingListItem

Displays a single shopping item in the list.

- Shows name and quantity.
- Edit and delete buttons.

---

### TextField

Reusable text input field wrapper.

- Takes current value, label, and callback on value change.

---

## Helper Functions

### addingItem

Creates a new `ShoppingItem` from input values and calls a callback with it.

---

### verifyNameAndQuantity

Validates the item name and quantity fields.

- Shows Toast messages if inputs are invalid.
- Calls back with valid inputs.

---

## Usage

1. Run the app.
2. Tap **Add Item** to open the dialog.
3. Enter an item name and quantity.
4. Confirm to add the item to the list.
5. Edit or delete items using the buttons next to each entry.

---

## Requirements

- Android Studio with Compose support.
- Minimum SDK version: [specify your minSdk].
- Kotlin 1.8+ recommended.

---

## Notes

- Quantity must be a number between 0 and 1000.
- Item names cannot be blank.
- Editing state is managed locally for each item.

