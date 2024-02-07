# Mobile App: Expense Tracker

![App Logo](app_logo.png)

## Overview

This Android Studio Kotlin mobile app serves as an expense tracker, allowing users to manage and track their expenses on a monthly basis. The app is built using the Jetpack Compose UI toolkit and follows the MVVM (Model-View-ViewModel) architecture pattern. It utilizes a Room database for data storage, and users can perform operations such as creating, updating, and deleting expenses.

## Features

- **Expense Management:**
  - Track and manage your expenses for each month.
  - Create, update, and delete individual expenses.

- **Compose UI:**
  - Utilizes Jetpack Compose for building a modern and reactive user interface.

- **MVVM Architecture:**
  - Follows the Model-View-ViewModel architecture pattern for a clear and modular codebase.

- **Room Database:**
  - Uses Room database to store expense data persistently.

- **Expense Entity:**
  - Represents an expense with the following attributes:
    - Name
    - Amount of money spent
    - Category (chosen from a dropdown menu)
    - Date

- **Camera Integration:**
  - Allows users to use their mobile camera to capture images related to expenses.

## Screenshots

![Expense List](screenshots/expense_list.png)
*View of all expenses for a selected month*

![Expense Details](screenshots/expense_details.png)
*Details of a specific expense*

## Getting Started

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/yourusername/mobile_app_second_project.git
