package com.example.shoppinglistapp

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class ShoppingListViews {

    /**
     * Displays a dialog for inputting a new shopping item.
     *
     * @param showDialog Whether the dialog should be shown.
     * @param onDismiss Callback when the dialog is dismissed.
     * @param itemName The current input name of the item.
     * @param itemQuantity The current input quantity as a string.
     * @param onNameChange Callback when the name input changes.
     * @param onQuantityChange Callback when the quantity input changes.
     * @param onConfirm Callback when the user confirms item creation.
     */
    @Composable
    fun ShoppingListDialog(
        showDialog: Boolean,
        onDismiss: () -> Unit,
        itemName: String,
        itemQuantity: String,
        onNameChange: (String) -> Unit,
        onQuantityChange: (String) -> Unit,
        onConfirm: (String, Int) -> Unit
    ) {
        val context = LocalContext.current

        if (showDialog) {
            AlertDialog(
                onDismissRequest = onDismiss,
                confirmButton = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(onClick = onDismiss) {
                            Text(text = "Cancel")
                        }
                        Button(onClick = {
                            try {
                                val quantity = itemQuantity.toInt()
                                verifyNameAndQuantity(itemName, itemQuantity, context, quantity, onConfirm)
                            } catch (e: NumberFormatException){
                                Toast.makeText(context, "Invalid quantity value", Toast.LENGTH_SHORT).show()
                            }
                        }) {
                            Text(text = "Add")
                        }
                    }
                },
                title = { Text("Add Shopping Item") },
                text = {
                    Column {
                        TextField(str = itemName, "item", onValueChange = onNameChange)
                        TextField(str = itemQuantity, "quantity", onValueChange = onQuantityChange)
                    }
                }
            )
        }
    }

    /**
     * Displays a UI form to edit a [ShoppingItem] inline.
     *
     * @param item The item being edited.
     * @param onEditComplete Callback when editing is completed, with new name and quantity.
     */
    @Composable
    fun ShoppingItemEditor(item: ShoppingItem, onEditComplete: (String, Int) -> Unit) {
        var editedName by rememberSaveable { mutableStateOf(item.name) }
        var editedQuantity by rememberSaveable { mutableStateOf(item.quantity.toString()) }
        var isEditing by rememberSaveable { mutableStateOf(item.isEditing) }
        val defaultValue = 1

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Column {
                TextField(str = editedName, "item") { editedName = it }
                TextField(str = editedQuantity, "quantity") { editedQuantity = it }

                Button(onClick = {
                    isEditing = false
                    onEditComplete(editedName, editedQuantity.toIntOrNull() ?: defaultValue)
                }) {
                    Text(text = "Save")
                }
            }
        }
    }

    /**
     * Displays a shopping list item in a row, with Edit and Delete buttons.
     *
     * @param item The shopping item to display.
     * @param onEditClick Callback when the Edit button is clicked.
     * @param onDeleteClick Callback when the Delete button is clicked.
     */
    @Composable
    fun ShoppingListItem(
        item: ShoppingItem,
        onEditClick: () -> Unit,
        onDeleteClick: () -> Unit
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .border(
                    border = BorderStroke(2.dp, Color.Black),
                    shape = RoundedCornerShape(20)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = item.name, modifier = Modifier.padding(8.dp))
            Text(text = "Qty: ${item.quantity}", modifier = Modifier.padding(8.dp))
            Button(onClick = onEditClick) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit the item")
            }
            Button(onClick = onDeleteClick) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete the item")
            }
        }
    }

    /**
     * A custom text input field with a label.
     *
     * @param str The current text value.
     * @param label The label text shown above the input.
     * @param onValueChange Callback when the text changes.
     */
    @Composable
    fun TextField (str: String, label: String, onValueChange: (String) -> Unit) {
        OutlinedTextField(
            value = str,
            onValueChange = onValueChange,
            singleLine = true,
            label = { Text(text = "Enter $label") },
        )
    }
}