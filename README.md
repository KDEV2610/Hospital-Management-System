# **Hospital Management System**

## **Overview**  
The **Hospital Management System** is a robust and user-friendly software solution designed to enhance and automate various hospital operations. It simplifies patient management, improves appointment scheduling, and optimizes resource utilization for healthcare institutions. This system ensures efficient workflows, reduces administrative burdens, and contributes to better patient care.

---

## **Features**  

### **Patient Management**  
- Add, edit, and view patient records.  
- Maintain detailed medical histories and personal information.  

### **Doctor and Staff Management**  
- Efficiently manage doctor profiles and staff schedules.  
- Assign roles and tasks to hospital personnel.  

### **Appointment Scheduling**  
- Automated booking and scheduling system to avoid conflicts.  
- Track and manage patient appointments seamlessly.  

### **Billing System**  
- Generate and track invoices for treatments and services.  
- Streamline payment processes for patients.  

### **User-Friendly Interface**  
- Intuitive design to ensure ease of use for hospital staff.  
- Secure login system for data privacy.  

---

## **Technologies Used**  
- **Backend**: Java & JDBC  
- **Database**: MySQL  
- **Version Control**: Git/GitHub  

---

## **Installation and Setup**  

1. Clone the repository:  
   ```bash
   git clone https://github.com/KDEV2610/Hospital-Management-System.git
2. Navigate to the project directory:
   ```bash
   cd Hospital-Management-System
3. Configure the database connection in the configuration file.
4. Run the application.

---

## **Usage**
- Access the system through the login page.
- Navigate through modules like Patient Management, Appointment Scheduling, and Billing.
- Manage hospital operations effectively through a secure and organized interface.

---

## **Contributing**
We welcome contributions to enhance this project! Please fork the repository, make your changes, and submit a pull request.

---

## **Contact**
For any questions or feedback, please contact:
- **LinkedIn**: https://linkedin.com/in/devanshidobriyal-5943aa2b1

---

## **Database**
Ensure that the following SQL queries are run to set up the database:

-- Example SQL script for creating the database and tables
```bash
CREATE DATABASE hospital_management;

USE hospital_management;

CREATE TABLE patients (
    patient_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(10),
    contact_number VARCHAR(15),
    medical_history TEXT
);

CREATE TABLE doctors (
    doctor_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    specialization VARCHAR(50),
    contact_number VARCHAR(15),
    schedule TEXT
);

CREATE TABLE appointments (
    appointment_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT,
    doctor_id INT,
    appointment_date DATE,
    status VARCHAR(20),
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
    FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id)
);

CREATE TABLE billing (
    bill_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT,
    total_amount DECIMAL(10, 2),
    payment_status VARCHAR(20),
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id)
);

- Make sure to replace the example SQL queries with your actual schema if different.

