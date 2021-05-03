package com.example.firstapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.CallSuper
import androidx.annotation.StyleRes

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

// FUNCTIONS
fun sum(a: Int, b: Int): Int {
    return a + b
}

fun sumInline(a: Int, b: Int) = a + b


// STRING TEMPLATE

fun printSum(a: Int, b: Int): Unit {
    println("sum of $a and $b is ${a + b}")
}


// LIST

fun listAdd(){
    val numbers = mutableListOf("one", "two", "three", "four")
    numbers.add("five")   // this is OK
    println(numbers)
    //numbers = mutableListOf("six", "seven")      // compilation error
}

fun listAttr(){
    val numbers = listOf("one", "two", "three", "four")
    println("Number of elements: ${numbers.size}")
    println("Third element: ${numbers.get(2)}")
    println("Fourth element: ${numbers[3]}")
    println("Index of element \"two\" ${numbers.indexOf("two")}")
}

fun listMethods(){
    val numbers = mutableListOf(1, 2, 3, 4)
    println(numbers) // 1234

    numbers.add(5)
    println(numbers) //12345

    numbers.removeAt(1)
    println(numbers) //1345

    numbers[0] = 0
    println(numbers) //0345

    numbers.shuffle()
    println(numbers)
}

// IF & WHEN
fun maxOf(a: Int, b: Int): Int {
    return if (a > b) {
        a
    } else {
        b
    }
}

fun maxOfInline(a: Int, b: Int) = if (a > b) a else b

fun whenInt(x: Int) {
    when (x) {
        1 -> println("x == 1")
        2 -> println("x == 2")
        else -> { // Note the block
            println("x is neither 1 nor 2")
        }
    }
}

fun whenCheck(obj: Any): String =
    when (obj) {
        1          -> "One"
        "Hello"    -> "Greeting"
        is Float   -> "Float"
        !is String -> "Not a string"
        else       -> "Unknown"
    }


// FOR

fun forRange(){
    for (i in 1..3) {
        println(i)
    }
}

fun forUntil(){
    for (i in 1 until 3) {
        println(i)
    }
}

fun forList(){
    val items = listOf("apple", "banana", "kiwifruit")
    for (item in items) {
        println(item)
    }
}

fun forListIndices(){
    val items = listOf("apple", "banana", "kiwifruit")
    for (index in items.indices) {
        println("item at $index is ${items[index]}")
    }
}

fun forEachList(){
    val items = listOf("apple", "banana", "kiwifruit")
    items.forEach { item ->
        println(item)
    }
}

fun forEachIndexedList(){
    val items = listOf("apple", "banana", "kiwifruit")
    items.forEachIndexed { index, item ->
        println("item at $index is $item")
    }
}


// while
fun whilePositive(){
    var x = 5
    while (x > 0) {
        x--
        println(x)
    }
}


// CLASS


class Person(var name: String, var age: Int){
    fun printInfo() = println("name: $name, age: $age")
    override fun toString() = "name: $name, age: $age"
}

fun bob(){
    val bob = Person("Bob", 31)
    println("name: ${bob.name}, age: ${bob.age}")
    bob.age = 32
    println("name: ${bob.name}, age: ${bob.age}")
    bob.printInfo()
}

class TestClass(i: Int) {
    init {
        println("Constructor $i")
    }
}

open class Animal {
    @CallSuper
    open fun makeSound() {
        println("making sound...")
    }
}

class Dog : Animal() {
    override fun makeSound() {
        println("bark!")
    }
}

fun main() {
    val dog = Dog()
    dog.makeSound()
}
