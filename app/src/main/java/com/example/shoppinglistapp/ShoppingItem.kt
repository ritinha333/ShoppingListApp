package com.example.shoppinglistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Represents a shopping item with name, quantity, and editing status.
 *
 * @property id Unique identifier for the item.
 * @property name Name of the item (must not be blank).
 * @property quantity Quantity of the item (must be in 0..1000).
 * @property isEditing Whether the item is currently in edit mode.
 */
data class ShoppingItem(
    val id: Int,
    var name: String,
    var quantity: Int,
    var isEditing: Boolean = false
) {
    init {
        require(name.isNotBlank()) { "The name of the item can't be blank" }
        require(quantity in 0..1000)
    }
}

/**
 * Main Composable function that manages and displays the shopping list UI.
 * Allows adding, editing, and deleting items.
 */
@Composable
fun ShoppingListApp(sLV: ShoppingListViews) {
    var sItems by rememberSaveable { mutableStateOf(listOf<ShoppingItem>()) }
    var showDialog by rememberSaveable { mutableStateOf(false) }
    var itemName by rememberSaveable { mutableStateOf("") }
    var itemQuantity by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { showDialog = true },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Add Item")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(sItems) { item ->
                if (item.isEditing) {
                    sLV.ShoppingItemEditor(
                        item = item
                    ) { editedName, editedQuantity ->
                        sItems = sItems.map {
                            if (it.id == item.id) it.copy(
                                name = editedName,
                                quantity = editedQuantity,
                                isEditing = false
                            ) else it.copy(isEditing = false)
                        }
                    }
                } else {
                    sLV.ShoppingListItem(item = item, onEditClick = {
                        sItems = sItems.map { it.copy(isEditing = it.id == item.id) }
                    }, onDeleteClick = { sItems = sItems.filter { it.id != item.id } })
                }
            }
        }
    }

    sLV.ShoppingListDialog(
        showDialog = showDialog,
        onDismiss = { showDialog = false },
        itemName = itemName,
        itemQuantity = itemQuantity,
        onNameChange = { itemName = it },
        onQuantityChange = { itemQuantity = it },
        onConfirm = { name, quantity ->
            addingItem(sItems, name, quantity) {
                sItems = sItems + it
                showDialog = false
                itemName = ""
                itemQuantity = ""
            }
        }
    )
}

