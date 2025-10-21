# 🐾 Dog Food Mobile Application

A modern and user-friendly **mobile application** built with a **Firebase backend**, designed to make purchasing dog food easier and more engaging.  
Users can **register, log in, browse dog food items, add them to their cart**, and stay updated with the **latest dog-related news**.  
Admins can manage items and update dog information in real time, ensuring a **seamless and dynamic shopping experience**.

---

## 🚀 Features

### 👤 User Features
- Register and log in using Firebase Authentication  
- Browse a variety of dog food products  
- Add or remove items from the shopping cart  
- View latest **dog-related news** and tips (real-time updates from Firebase)  
- Place and track orders  
- User-friendly interface optimized for mobile experience  

### 🛠️ Admin Features
- Admin login through Firebase  
- Add, edit, or remove dog food items  
- Update dog news and information  
- Manage product inventory and prices  
- View customer orders and activity  

---

## 💻 Tech Stack

| Component | Technology |
|------------|-------------|
| Frontend | **Flutter / React Native / Android Studio (Java/Kotlin)** |
| Backend | **Firebase** |
| Database | **Firebase Firestore (NoSQL)** |
| Authentication | **Firebase Authentication (Email/Password, Google, etc.)** |
| Storage | **Firebase Storage** for product images |
| Notifications | **Firebase Cloud Messaging (FCM)** for news and updates |

---

## 🧩 App Modules

1. **Authentication Module**
   - Handles user registration and login using Firebase Authentication.

2. **Product Module**
   - Displays available dog food products.
   - Allows users to add products to the cart.

3. **Cart Module**
   - Manages the user’s selected items with total price calculation.
   - Updates in real time with Firestore listeners.

4. **News Module**
   - Displays latest dog care tips and product updates.
   - Admins can post new articles or news items.

5. **Admin Module**
   - Admins can manage product listings and user data directly from the app.

---

## ⚙️ Installation & Setup

### Prerequisites
- Install [Android Studio](https://developer.android.com/studio) or [VS Code](https://code.visualstudio.com/)  
- Install Flutter SDK (if using Flutter)  
- A Firebase account and project created at [Firebase Console](https://console.firebase.google.com)

  📁 Project Structure
DogFoodApp/
│
├── 📂 lib/                         # Main app source code
│   ├── main.dart                   # App entry point
│   ├── screens/
│   │   ├── login_screen.dart
│   │   ├── register_screen.dart
│   │   ├── home_screen.dart
│   │   ├── product_detail_screen.dart
│   │   ├── cart_screen.dart
│   │   └── admin_panel_screen.dart
│   ├── models/
│   │   ├── product_model.dart
│   │   └── user_model.dart
│   ├── services/
│   │   ├── auth_service.dart
│   │   ├── product_service.dart
│   │   └── news_service.dart
│   └── widgets/
│       ├── product_card.dart
│       ├── cart_item.dart
│       └── news_card.dart
│
├── 📂 assets/
│   ├── images/
│   └── icons/
│
├── 📂 android/                     # Android-specific files (contains google-services.json)
├── 📂 ios/                         # iOS-specific files (contains GoogleService-Info.plist)
├── 📄 pubspec.yaml                 # Dependencies and assets (for Flutter)
├── 📄 README.md                    # Documentation
└── 📄 .gitignore                   # Git ignore file


🧠 Future Enhancements

Add online payment integration (Stripe, PayPal, etc.)

Enable order tracking and delivery updates

Add push notifications for new dog food arrivals

Introduce wishlist and product reviews

Add multi-language support

👩‍💻 Author

Developed by: Pramodya Warnakula295@gmail.com

GitHub: https://github.com/pramodya112
