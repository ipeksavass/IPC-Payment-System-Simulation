package com.ipeksavas.restfulapplication.data.source

import com.ipeksavas.restfulapplication.domain.model.Product

object ProductCatalog {
    
    val products: List<Product> = listOf(
        // --- 1. Beverages ---
        Product(id = "1", name = "Turkish Coffee", departmentName = "Beverages", price = 65.0),
        Product(id = "2", name = "Espresso", departmentName = "Beverages", price = 55.0),
        Product(id = "3", name = "Iced Latte", departmentName = "Beverages", price = 85.0),
        Product(id = "4", name = "Black Tea", departmentName = "Beverages", price = 25.0),
        Product(id = "5", name = "Green Tea", departmentName = "Beverages", price = 35.0),
        Product(id = "6", name = "Fresh Orange Juice", departmentName = "Beverages", price = 70.0),
        Product(id = "7", name = "Mineral Water", departmentName = "Beverages", price = 15.0),
        Product(id = "8", name = "Ayran", departmentName = "Beverages", price = 20.0),
        Product(id = "9", name = "Lemonade", departmentName = "Beverages", price = 50.0),
        Product(id = "10", name = "Hot Chocolate", departmentName = "Beverages", price = 75.0),
        
        // --- 2. Snacks---
        Product(id = "11", name = "Potato Chips", departmentName = "Snacks", price = 45.0),
        Product(id = "12", name = "Dark Chocolate Bar", departmentName = "Snacks", price = 38.0),
        Product(id = "13", name = "Hazelnut Wafer", departmentName = "Snacks", price = 22.0),
        Product(id = "14", name = "Salted Peanuts", departmentName = "Snacks", price = 40.0),
        Product(id = "15", name = "Cheese Crackers", departmentName = "Snacks", price = 28.0),
        Product(id = "16", name = "Chocolate Chip Cookies", departmentName = "Snacks", price = 35.0),
        Product(id = "17", name = "Gummy Bears", departmentName = "Snacks", price = 30.0),
        Product(id = "18", name = "Roasted Almonds", departmentName = "Snacks", price = 65.0),
        Product(id = "19", name = "Pretzel Sticks", departmentName = "Snacks", price = 20.0),
        Product(id = "20", name = "Fruit Granola Bar", departmentName = "Snacks", price = 32.0),
        
        // --- 3. Grains & Pasta---
        Product(id = "21", name = "Baldo Rice 1kg", departmentName = "Grains & Pasta", price = 85.0),
        Product(id = "22", name = "Osmancik Rice 1kg", departmentName = "Grains & Pasta", price = 75.0),
        Product(id = "23", name = "Spaghetti Pasta 500g", departmentName = "Grains & Pasta", price = 28.0),
        Product(id = "24", name = "Penne Pasta 500g", departmentName = "Grains & Pasta", price = 30.0),
        Product(id = "25", name = "Fusilli Pasta 500g", departmentName = "Grains & Pasta", price = 30.0),
        Product(id = "26", name = "Fine Bulgur 1kg", departmentName = "Grains & Pasta", price = 42.0),
        Product(id = "27", name = "Coarse Pilaf Bulgur 1kg", departmentName = "Grains & Pasta", price = 45.0),
        Product(id = "28", name = "Red Lentils 1kg", departmentName = "Grains & Pasta", price = 55.0),
        Product(id = "29", name = "Chickpeas 1kg", departmentName = "Grains & Pasta", price = 68.0),
        Product(id = "30", name = "White Beans 1kg", departmentName = "Grains & Pasta", price = 72.0),
        
        // --- 4. Cleaning---
        Product(id = "31", name = "Dishwashing Liquid 750ml", departmentName = "Cleaning", price = 55.0),
        Product(id = "32", name = "Laundry Detergent 3kg", departmentName = "Cleaning", price = 210.0),
        Product(id = "33", name = "Fabric Softener 1.5L", departmentName = "Cleaning", price = 95.0),
        Product(id = "34", name = "Surface Cleaner 1L", departmentName = "Cleaning", price = 48.0),
        Product(id = "35", name = "Bleach 1L", departmentName = "Cleaning", price = 38.0),
        Product(id = "36", name = "Glass Cleaner Spray", departmentName = "Cleaning", price = 45.0),
        Product(id = "37", name = "Dishwasher Tablets 30pcs", departmentName = "Cleaning", price = 185.0),
        Product(id = "38", name = "Kitchen Sponge 3-Pack", departmentName = "Cleaning", price = 25.0),
        Product(id = "39", name = "Microfiber Cleaning Cloth", departmentName = "Cleaning", price = 35.0),
        Product(id = "40", name = "Liquid Hand Soap 500ml", departmentName = "Cleaning", price = 42.0),
        
        // --- 5. Sauces ---
        Product(id = "41", name = "Tomato Ketchup 650g", departmentName = "Sauces", price = 52.0),
        Product(id = "42", name = "Mayonnaise 550g", departmentName = "Sauces", price = 65.0),
        Product(id = "43", name = "Yellow Mustard 250g", departmentName = "Sauces", price = 48.0),
        Product(id = "44", name = "Barbecue Sauce 350g", departmentName = "Sauces", price = 58.0),
        Product(id = "45", name = "Soy Sauce 250ml", departmentName = "Sauces", price = 62.0),
        Product(id = "46", name = "Pomegranate Molasses 350ml", departmentName = "Sauces", price = 75.0),
        Product(id = "47", name = "Hot Chili Sauce 250ml", departmentName = "Sauces", price = 45.0),
        Product(id = "48", name = "Ranch Dressing 300g", departmentName = "Sauces", price = 55.0),
        Product(id = "49", name = "Pesto Sauce 190g", departmentName = "Sauces", price = 88.0),
        Product(id = "50", name = "Garlic Aioli Sauce 300g", departmentName = "Sauces", price = 54.0)
    )
}