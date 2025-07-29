package com.example.shoppinglistapp

import android.content.Context
import android.widget.Toast


/**
 * Adds a new shopping item and invokes a callback with the new item.
 *
 * @param sItems Current list of shopping items.
 * @param itemName Name of the item to add.
 * @param itemQuantity Quantity of the item to add.
 * @param onEdit Callback with the created [ShoppingItem].
 */
fun addingItem (
    sItems: List<ShoppingItem>,
    itemName: String,
    itemQuantity: Int,
    onEdit: (ShoppingItem) -> Unit,
) {
    val newItem = ShoppingItem(
        sItems.size + 1,
        itemName,
        itemQuantity
    )
    onEdit(newItem)
}

/**
 * Verifies item name and quantity input and invokes a callback if valid.
 * Displays a Toast message if the input is invalid.
 *
 * @param itemName Name input to validate.
 * @param itemQuantity Quantity input as string.
 * @param context Context used to show Toast messages.
 * @param quantity Parsed quantity value.
 * @param onConfirm Callback when input is valid with name and quantity.
 */
fun verifyNameAndQuantity(
    itemName: String,
    itemQuantity: String,
    context: Context,
    quantity: Int,
    onConfirm: (String, Int) -> Unit
) {
    when {
        itemName.isBlank() && itemQuantity.isBlank() -> Toast.makeText(
            context,
            "Item name and quantity cannot be blank",
            Toast.LENGTH_SHORT
        ).show()

        itemName.isBlank() -> Toast.makeText(
            context,
            "Item name cannot be blank",
            Toast.LENGTH_SHORT
        ).show()

        itemQuantity.isBlank() -> Toast.makeText(
            context,
            "Item quantity cannot be blank",
            Toast.LENGTH_SHORT
        ).show()

        else -> onConfirm(itemName, quantity)
    }
}
