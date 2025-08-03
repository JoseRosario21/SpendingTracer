# 📊 SpendingTracer

SpendingTracer is a modern Android app built with **Jetpack Compose**, designed to help users **track and manage daily expenses** intuitively. It supports both **manual expense logging** and a powerful **Digital Receipts Manager** using **OCR technology**, making it easier to keep digital records of physical receipts.

---

## ✨ Features

- ✅ Add & track expenses with amount, category, and date
- 📂 Organize expenses by category and view details
- 📷 **Digital Receipts Manager**: Scan real receipts using OCR
- 🧠 Automatic text recognition to pre-fill expense forms
- 📈 Insightful summaries (planned)
- ☁️ Offline-first architecture using Room
- 🧪 Unit tests and UI previews for key components
- 🔐 Clean Architecture & modern Android practices

---

## 📸 Screenshots

| Home Screen | Add Expense | Receipt Scanner |
|-------------|-------------|-----------------|
| *(screenshot here)* | *(screenshot here)* | *(screenshot here)* |

---

## 🧱 Tech Stack

| Layer | Tech |
|-------|------|
| UI | Jetpack Compose, Material3, Navigation |
| State | ViewModel, StateFlow |
| Data | Room, DAO, Kotlinx Serialization |
| OCR | ML Kit / Google Play Services TextRecognizer |
| DI | Hilt |
| Testing | JUnit, UI Previews |
| CI/CD | GitHub Actions (planned) |

---

## 🧠 Architecture

The project follows a **Clean MVVM structure** with separation of:
- `domain`: core business models and interfaces
- `data`: Room implementation & models
- `presentation`: composables grouped by screen
- `di`: composables grouped by screen
- `utils`: composables grouped by screen
- `App`: composables grouped by screen

---

## 📂 Digital Receipts Flow

1. User scans or uploads an image of a receipt
2. Text is extracted via OCR
3. Suggested fields are auto-filled in the add form
4. Receipt is saved with the expense (image + data)
5. All receipts are listed and viewable later

---

## 🚀 How to Run

1. Clone the repo
2. Open in Android Studio Giraffe or newer
3. Run on Android emulator or device (API 26+)
4. Grant camera/storage permissions for receipt scanning

---

## 🛠️ Future Enhancements

- 📊 Graphs & analytics per category/month
- ☁️ Cloud sync (Firebase or Google Drive)
- 🧾 Export to PDF/CSV

---

## 👨‍💻 Author

José Rosário  
[LinkedIn](https://www.linkedin.com/in/jose-rosario22/) • [GitHub](https://github.com/JoseRosario22)

---

## 📝 License

MIT – feel free to fork, learn, and build on top of it.