# ClearDues
# 💸 ClearDues – Smart No-Dues Management System

![ClearDues Banner](https://img.shields.io/badge/Java-Swing-blue?style=flat-square)
![JavaMail](https://img.shields.io/badge/JavaMail-API-green?style=flat-square)
![iTextPDF](https://img.shields.io/badge/iTextPDF-Receipt%20Generation-orange?style=flat-square)

> A robust and user-friendly desktop application to track, manage, and clear student dues with real-time reminders, receipts, and record updates.

---

## 📱 Project Overview

**ClearDues** is a Java Swing-based application designed to automate and simplify the no-dues clearance process in educational institutions. It streamlines tracking of outstanding payments, sends automated email reminders, and generates digital receipts using PDF format.

---

## 📂 Features

🔐 **1. Login Module**  
→ Secure login for administrators.

🔍 **2. Choose Options**  
→ Navigate to either `Pay Dues`, `Update Student`, or `View Dues`.

📋 **3. Clear Dues Module**  
→ View all students’ data including:  
- Enrollment Number  
- Name  
- Branch  
- Dues Status  
→ Two buttons:  
- `Send Reminder` – Email students with pending dues using JavaMail API  
- `Send Receipt` – Email a PDF receipt for cleared dues using iTextPDF  

💰 **4. Pay Dues Module**  
→ Pay dues for students who have pending payments.  

✅ **5. Success Page**  
→ Confirmation page after dues are successfully paid. Auto-redirects to `Pay Dues` in 3 seconds.  

✏️ **6. Update Student Module**  
→ Modify student details like name, email, dues, etc.

---
## 🛠️ Technologies Used
- Java Swing – GUI development

- JavaMail API – Sending email reminders and receipts

- iTextPDF – Generating PDF receipts

- JTable – Displaying student records

- MySQL – Student dues data storage (optional, if DB connected)

---

## 📧 JavaMail Integration
Used for:

→ Sending due payment reminders

→ Sending receipt with PDF attachment

- 📦 Package: javax.mail.*
- 📄 Required: SMTP settings (e.g., Gmail), Authenticator for secure login.

---
## 📄 iTextPDF Integration
Used for:

→ Generating downloadable receipts after successful dues payment

- 📦 Package: com.itextpdf.text.*

📄 Used Classes:

- Document

- PdfWriter

- Paragraph

- FileOutputStream

---
## 🔁 Application Flow

```text
Login → Choose Options
          ↓
    ┌────────────┬─────────────┐
    ↓            ↓             ↓
 Pay Dues   Update Student   Clear Dues
    ↓                            ↓
    ↓     ←--- Pay/Reminder/Receipt ----→
 Success Page ←--- if dues are cleared
