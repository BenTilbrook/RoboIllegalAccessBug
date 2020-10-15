package com.example.roboillegalaccessbug

import android.app.Application
import androidx.datastore.Serializer
import androidx.datastore.createDataStore
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.io.InputStream
import java.io.OutputStream

@RunWith(AndroidJUnit4::class)
class ExampleUnitTest {

    private val context = ApplicationProvider.getApplicationContext<Application>()

    private val dataStore = context.createDataStore(
            fileName = "store",
            serializer = ItemSerializer()
    )

    data class Item(val value: String)

    class ItemSerializer : Serializer<Item> {
        override fun readFrom(input: InputStream): Item {
            return Item(value = "a")
        }

        override fun writeTo(t: Item, output: OutputStream) {
        }
    }

    @Test
    fun foo() {
        runBlocking { dataStore.updateData { it.copy(value = "b") } }
        Assert.assertTrue(true)
    }
}
